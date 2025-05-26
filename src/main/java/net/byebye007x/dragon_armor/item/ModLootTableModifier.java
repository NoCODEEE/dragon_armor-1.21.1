package net.byebye007x.dragon_armor.item;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class ModLootTableModifier {
    private static final Identifier WARDEN_DROP = Identifier.of("minecraft", "entities/warden");
    private static final Identifier WITHER_SKELETON_DROP = Identifier.of("minecraft", "entities/wither_skeleton");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {

            if (WARDEN_DROP.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1f))
                        .conditionally(RandomChanceLootCondition.builder(1f))
                        .with(ItemEntry.builder(ModItems.WARDEN_HEART))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (WITHER_SKELETON_DROP.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1f))
                        .conditionally(RandomChanceLootCondition.builder(0.25f))
                        .with(ItemEntry.builder(ModItems.WITHER_SKELETON_SKULL_PART))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 3f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (LootTables.BURIED_TREASURE_CHEST.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1f))
                        .conditionally(RandomChanceLootCondition.builder(1f))
                        .with(ItemEntry.builder(Items.NAUTILUS_SHELL))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 5f)).build());

                tableBuilder.pool(poolBuilder.build());
            }
        });
    }

}
