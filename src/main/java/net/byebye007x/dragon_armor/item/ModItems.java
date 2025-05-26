package net.byebye007x.dragon_armor.item;

import net.byebye007x.dragon_armor.DragonArmor;
import net.byebye007x.dragon_armor.item.custom.BedrockCrackerItem;
import net.byebye007x.dragon_armor.item.custom.BedrockDrillItem;
import net.byebye007x.dragon_armor.item.custom.ModArmorItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.List;

public class ModItems {

    public static final Item DRAGON_UPGRADE_TEMPLATE = registerItem("dragon_upgrade_template", new SmithingTemplateItem(
            Text.translatable("upgrade.dragon_armor.dragon_upgrade.applies_to").formatted(Formatting.BLUE),
            Text.translatable("upgrade.dragon_armor.dragon_upgrade.ingredients").formatted(Formatting.BLUE),
            Text.translatable("upgrade.dragon_armor.dragon_upgrade.title").formatted(Formatting.GRAY),
            Text.translatable("upgrade.dragon_armor.dragon_upgrade.base_slot_description"),
            Text.translatable("upgrade.dragon_armor.dragon_upgrade.additions_slot_description"),
            List.of(Identifier.of("minecraft", "item/empty_armor_slot_helmet"),
                    Identifier.of("minecraft", "item/empty_armor_slot_chestplate"),
                    Identifier.of("minecraft", "item/empty_armor_slot_legging"),
                    Identifier.of("minecraft", "item/empty_armor_slot_boots")), // ghost texture for base slot
            List.of(Identifier.of("minecraft", "item/empty_slot_diamond"))   // ghost texture for additions slot (can be anything)
    ));
    public static final Item WARDEN_HEART = registerItem("warden_heart", new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item WITHER_SKELETON_SKULL_PART = registerItem("wither_skeleton_skull_part", new Item(new Item.Settings()));
    public static final Item BEDROCK_SHARD = registerItem("bedrock_shard", new Item(new Item.Settings().fireproof()));

    public static final Item BEDROCK_CRACKER = registerItem("bedrock_cracker", new BedrockCrackerItem(new Item.Settings().maxDamage(1)));
    public static final Item BEDROCK_DRILL = registerItem("bedrock_drill", new BedrockDrillItem(ToolMaterials.NETHERITE, new Item.Settings().fireproof()));

    public static final Item DRAGON_HELMET = registerItem("dragon_helmet",
            new ModArmorItem(ModArmorMaterials.DRAGON_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
                    .fireproof().rarity(Rarity.EPIC).maxCount(1)));
    public static final Item DRAGON_CHESTPLATE = registerItem("dragon_chestplate",
            new ArmorItem(ModArmorMaterials.DRAGON_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                    .fireproof().rarity(Rarity.EPIC).maxCount(1)));
    public static final Item DRAGON_LEGGING = registerItem("dragon_legging",
            new ArmorItem(ModArmorMaterials.DRAGON_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()
                    .fireproof().rarity(Rarity.EPIC).maxCount(1)));
    public static final Item DRAGON_BOOTS = registerItem("dragon_boots",
            new ArmorItem(ModArmorMaterials.DRAGON_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()
                    .fireproof().rarity(Rarity.EPIC).maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(DragonArmor.MOD_ID, name), item);
    }

    public static void registerModItems() {
        DragonArmor.LOGGER.info("Registering Items for " + DragonArmor.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(WARDEN_HEART);
            fabricItemGroupEntries.add(DRAGON_UPGRADE_TEMPLATE);
            fabricItemGroupEntries.add(BEDROCK_SHARD);
            fabricItemGroupEntries.add(WITHER_SKELETON_SKULL_PART);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(DRAGON_HELMET);
            fabricItemGroupEntries.add(DRAGON_CHESTPLATE);
            fabricItemGroupEntries.add(DRAGON_LEGGING);
            fabricItemGroupEntries.add(DRAGON_BOOTS);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(BEDROCK_CRACKER);
            fabricItemGroupEntries.add(BEDROCK_DRILL);
        });
    }
}
