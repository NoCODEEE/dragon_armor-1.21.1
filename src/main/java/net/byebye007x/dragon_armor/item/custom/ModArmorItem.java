package net.byebye007x.dragon_armor.item.custom;

import com.google.common.collect.ImmutableMap;
import net.byebye007x.dragon_armor.DragonArmor;
import net.byebye007x.dragon_armor.item.ModArmorMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

public class ModArmorItem extends ArmorItem {
    private static final Map<RegistryEntry<ArmorMaterial>, List<StatusEffectInstance>> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<RegistryEntry<ArmorMaterial>, List<StatusEffectInstance>>())
                    .put(ModArmorMaterials.DRAGON_ARMOR_MATERIAL,
                            List.of(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 400, 0, false, false),
                                    new StatusEffectInstance(StatusEffects.WATER_BREATHING, 40, 0, false, false),
                                    new StatusEffectInstance(StatusEffects.RESISTANCE, 40, 1, false, false)
                            ))
                    .build();

    private static final Identifier DRAGON_SPEED = Identifier.of(DragonArmor.MOD_ID, "dragon_armor_speed_boost");
    private static final Identifier DRAGON_HEALTH = Identifier.of(DragonArmor.MOD_ID, "dragon_armor_heath_boost");
    private static final Identifier DRAGON_SUBMERGE_MINING = Identifier.of(DragonArmor.MOD_ID, "dragon_armor_submerge_mining_boost");
    private static final Identifier DRAGON_BLOCK_REACH = Identifier.of(DragonArmor.MOD_ID, "dragon_armor_block_reach_boost");
    private static final Identifier DRAGON_ENTITY_REACH = Identifier.of(DragonArmor.MOD_ID, "dragon_armor_entity_reach_boost");

    public ModArmorItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient() && entity instanceof PlayerEntity player) {
            if (isWearingFullDragonArmor(player)) {
                evaluateArmorEffects(player);
                applyAttributeModifiers(player);
                removeDarknessEffect(player);
            } else {
                removeAttributeModifiers(player);
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private void evaluateArmorEffects(PlayerEntity player) {
        for (Map.Entry<RegistryEntry<ArmorMaterial>, List<StatusEffectInstance>> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            RegistryEntry<ArmorMaterial> mapArmorMaterial = entry.getKey();
            List<StatusEffectInstance> mapStatusEffects = entry.getValue();

            if (hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffects);
            }
        }
    }

    private void addStatusEffectForMaterial(PlayerEntity player, RegistryEntry<ArmorMaterial> mapArmorMaterial, List<StatusEffectInstance> mapStatusEffect) {
        boolean hasPlayerEffect = mapStatusEffect.stream().allMatch(effect -> player.hasStatusEffect(effect.getEffectType()));

        if (!hasPlayerEffect) {
            for (StatusEffectInstance instance : mapStatusEffect) {
                player.addStatusEffect(new StatusEffectInstance(instance.getEffectType(),
                        instance.getDuration(), instance.getAmplifier(), instance.isAmbient(), instance.shouldShowParticles()));
            }
        }
    }

    private void removeDarknessEffect(PlayerEntity player) {
        boolean hasDarkness = player.hasStatusEffect(StatusEffects.DARKNESS);

        if (hasDarkness) {
            player.removeStatusEffect(StatusEffects.DARKNESS);
        }
    }

    //Room for refactoring
    private void applyAttributeModifiers(PlayerEntity player) {
        var speedAttr = player.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        var healthAttr = player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        var aquaAffAttr = player.getAttributeInstance(EntityAttributes.PLAYER_SUBMERGED_MINING_SPEED);
        var blcRchAttr = player.getAttributeInstance(EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE);
        var enRchAttr = player.getAttributeInstance(EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE);

        if (speedAttr != null && speedAttr.getModifier(DRAGON_SPEED) == null) {
            speedAttr.addPersistentModifier(new EntityAttributeModifier(
                    DRAGON_SPEED, 0.20, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
            ));
        }

        if (aquaAffAttr != null && aquaAffAttr.getModifier(DRAGON_SUBMERGE_MINING) == null) {
            aquaAffAttr.addPersistentModifier(new EntityAttributeModifier(
                    DRAGON_SUBMERGE_MINING, 0.20, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
            ));
        }

        if (blcRchAttr != null && blcRchAttr.getModifier(DRAGON_BLOCK_REACH) == null) {
            blcRchAttr.addPersistentModifier(new EntityAttributeModifier(
                    DRAGON_BLOCK_REACH, 2, EntityAttributeModifier.Operation.ADD_VALUE
            ));
        }

        if (enRchAttr != null && enRchAttr.getModifier(DRAGON_ENTITY_REACH) == null) {
            enRchAttr.addPersistentModifier(new EntityAttributeModifier(
                    DRAGON_ENTITY_REACH, 2, EntityAttributeModifier.Operation.ADD_VALUE
            ));
        }

        if (healthAttr != null && healthAttr.getModifier(DRAGON_HEALTH) == null) {
            healthAttr.addPersistentModifier(new EntityAttributeModifier(
                    DRAGON_HEALTH,4.0, EntityAttributeModifier.Operation.ADD_VALUE));
            if (player.getHealth() > player.getMaxHealth()) {
                player.setHealth(player.getMaxHealth());
            }
        }
    }

    //Room for refactoring
    private void removeAttributeModifiers(PlayerEntity player) {
        var speedAttr = player.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        var healthAttr = player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        var aquaAffAttr = player.getAttributeInstance(EntityAttributes.PLAYER_SUBMERGED_MINING_SPEED);
        var blcRchAttr = player.getAttributeInstance(EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE);
        var enRchAttr = player.getAttributeInstance(EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE);

        if (speedAttr != null && speedAttr.getModifier(DRAGON_SPEED) != null) {
            speedAttr.removeModifier(DRAGON_SPEED);
        }

        if (aquaAffAttr != null && aquaAffAttr.getModifier(DRAGON_SUBMERGE_MINING) != null) {
            aquaAffAttr.removeModifier(DRAGON_SUBMERGE_MINING);
        }

        if (blcRchAttr != null && blcRchAttr.getModifier(DRAGON_BLOCK_REACH) != null) {
            blcRchAttr.removeModifier(DRAGON_BLOCK_REACH);
        }

        if (enRchAttr != null && enRchAttr.getModifier(DRAGON_ENTITY_REACH) != null) {
            enRchAttr.removeModifier(DRAGON_ENTITY_REACH);
        }

        if (healthAttr != null && healthAttr.getModifier(DRAGON_HEALTH) != null) {
            healthAttr.removeModifier(DRAGON_HEALTH);
            if (player.getHealth() > player.getMaxHealth()) {
                player.setHealth(player.getMaxHealth());
            }
        }
    }

    private boolean hasFullSuitOfArmorOn(PlayerEntity player) {
        ItemStack boots = player.getInventory().getArmorStack(0);
        ItemStack leggings = player.getInventory().getArmorStack(1);
        ItemStack breastplate = player.getInventory().getArmorStack(2);
        ItemStack helmet = player.getInventory().getArmorStack(3);

        return !helmet.isEmpty() && !breastplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }

    private boolean hasCorrectArmorOn(RegistryEntry<ArmorMaterial> material, PlayerEntity player) {
        for (ItemStack stack : player.getInventory().armor) {
            if (!(stack.getItem() instanceof ArmorItem item) || item.getMaterial() != material) {
                return false;
            }
        }
        return true;
    }

    private boolean isWearingFullDragonArmor(PlayerEntity player) {
        return hasFullSuitOfArmorOn(player) && hasCorrectArmorOn(getMaterial(), player);
    }

}
