package net.byebye007x.dragon_armor.datagen;

import net.byebye007x.dragon_armor.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.WARDEN_HEART, Models.GENERATED);
        itemModelGenerator.register(ModItems.DRAGON_UPGRADE_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.WITHER_SKELETON_SKULL_PART, Models.GENERATED);
        itemModelGenerator.register(ModItems.BEDROCK_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.BEDROCK_CRACKER, Models.GENERATED);
        itemModelGenerator.register(ModItems.BEDROCK_DRILL, Models.GENERATED);

        itemModelGenerator.registerArmor((ArmorItem) ModItems.DRAGON_HELMET);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.DRAGON_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.DRAGON_LEGGING);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.DRAGON_BOOTS);
    }
}
