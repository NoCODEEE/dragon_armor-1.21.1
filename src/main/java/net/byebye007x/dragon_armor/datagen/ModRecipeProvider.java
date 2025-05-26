package net.byebye007x.dragon_armor.datagen;

import net.byebye007x.dragon_armor.DragonArmor;
import net.byebye007x.dragon_armor.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DRAGON_UPGRADE_TEMPLATE)
                .pattern("XWX")
                .pattern("SNS")
                .pattern("XCX")
                .input('W', ModItems.WARDEN_HEART)
                .input('N', Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
                .input('C', Items.CONDUIT)
                .input('X', Items.NETHERITE_INGOT)
                .input('S', Items.NETHER_STAR)
                .criterion(hasItem(ModItems.WARDEN_HEART), conditionsFromItem(ModItems.WARDEN_HEART))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.BEDROCK_DRILL)
                .pattern(" B ")
                .pattern("DND")
                .pattern("IRI")
                .input('B', ModItems.BEDROCK_SHARD)
                .input('N', Items.NETHERITE_INGOT)
                .input('D', Items.DIAMOND)
                .input('I', Items.IRON_BLOCK)
                .input('R', Items.REDSTONE_BLOCK)
                .criterion(hasItem(ModItems.BEDROCK_SHARD), conditionsFromItem(ModItems.BEDROCK_SHARD))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.BEDROCK_CRACKER)
                .pattern("N")
                .pattern("B")
                .input('B', Items.BREEZE_ROD)
                .input('N', Items.NETHERITE_INGOT)
                .criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT))
                .offerTo(recipeExporter);

        offerCompactingRecipe(recipeExporter, RecipeCategory.MISC, Items.WITHER_SKELETON_SKULL, ModItems.WITHER_SKELETON_SKULL_PART);

        SmithingTransformRecipeJsonBuilder.create(Ingredient.ofItems(ModItems.DRAGON_UPGRADE_TEMPLATE),
                        Ingredient.ofItems(Items.NETHERITE_HELMET),
                        Ingredient.ofItems(Items.DRAGON_EGG),
                        RecipeCategory.COMBAT,
                        ModItems.DRAGON_HELMET)
                .criterion(hasItem(ModItems.DRAGON_UPGRADE_TEMPLATE), conditionsFromItem(ModItems.DRAGON_UPGRADE_TEMPLATE))
                .offerTo(recipeExporter, Identifier.of(DragonArmor.MOD_ID, "netherite_to_dragon_helmet"));
        SmithingTransformRecipeJsonBuilder.create(Ingredient.ofItems(ModItems.DRAGON_UPGRADE_TEMPLATE),
                        Ingredient.ofItems(Items.NETHERITE_CHESTPLATE),
                        Ingredient.ofItems(Items.DRAGON_EGG),
                        RecipeCategory.COMBAT,
                        ModItems.DRAGON_CHESTPLATE)
                .criterion(hasItem(ModItems.DRAGON_UPGRADE_TEMPLATE), conditionsFromItem(ModItems.DRAGON_UPGRADE_TEMPLATE))
                .offerTo(recipeExporter, Identifier.of(DragonArmor.MOD_ID, "netherite_to_dragon_chestplate"));
        SmithingTransformRecipeJsonBuilder.create(Ingredient.ofItems(ModItems.DRAGON_UPGRADE_TEMPLATE),
                        Ingredient.ofItems(Items.NETHERITE_LEGGINGS),
                        Ingredient.ofItems(Items.DRAGON_EGG),
                        RecipeCategory.COMBAT,
                        ModItems.DRAGON_LEGGING)
                .criterion(hasItem(ModItems.DRAGON_UPGRADE_TEMPLATE), conditionsFromItem(ModItems.DRAGON_UPGRADE_TEMPLATE))
                .offerTo(recipeExporter, Identifier.of(DragonArmor.MOD_ID, "netherite_to_dragon_legging"));
        SmithingTransformRecipeJsonBuilder.create(Ingredient.ofItems(ModItems.DRAGON_UPGRADE_TEMPLATE),
                        Ingredient.ofItems(Items.NETHERITE_BOOTS),
                        Ingredient.ofItems(Items.DRAGON_EGG),
                        RecipeCategory.COMBAT,
                        ModItems.DRAGON_BOOTS)
                .criterion(hasItem(ModItems.DRAGON_UPGRADE_TEMPLATE), conditionsFromItem(ModItems.DRAGON_UPGRADE_TEMPLATE))
                .offerTo(recipeExporter, Identifier.of(DragonArmor.MOD_ID, "netherite_to_dragon_boots"));
    }
}
