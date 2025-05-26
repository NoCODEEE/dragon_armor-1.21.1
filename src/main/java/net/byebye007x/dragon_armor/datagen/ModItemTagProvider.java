package net.byebye007x.dragon_armor.datagen;

import net.byebye007x.dragon_armor.item.ModItems;
import net.byebye007x.dragon_armor.item.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Items.DRAGON_ARMOR_MOD_ITEMS)
                .add(ModItems.WARDEN_HEART)
                .add(ModItems.DRAGON_UPGRADE_TEMPLATE)
                .add(ModItems.DRAGON_HELMET)
                .add(ModItems.DRAGON_CHESTPLATE)
                .add(ModItems.DRAGON_LEGGING)
                .add(ModItems.DRAGON_BOOTS)
                .add(ModItems.BEDROCK_SHARD)
                .add(ModItems.BEDROCK_CRACKER)
                .add(ModItems.BEDROCK_DRILL)
        ;

        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.DRAGON_HELMET)
                .add(ModItems.DRAGON_CHESTPLATE)
                .add(ModItems.DRAGON_LEGGING)
                .add(ModItems.DRAGON_BOOTS)
        ;
    }
}
