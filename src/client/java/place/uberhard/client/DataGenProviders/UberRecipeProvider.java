package place.uberhard.client.DataGenProviders;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import place.uberhard.data.UberToolParts;
import place.uberhard.recipes.UberToolRecipeBuilder;
import place.uberhard.reg.UberItems;

public class UberRecipeProvider extends FabricRecipeProvider {



    public UberRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, BootstrapContext<Recipe<?>> recipes, BootstrapContext<Advancement> advancements) {
        return new RecipeProvider(recipes, advancements) {
            @Override
            public void buildRecipes() {
                HolderLookup.RegistryLookup<Item> itemLookup = registryLookup.lookupOrThrow(Registries.ITEM);

                Collection<ResourceKey<Item>> TOOL_HEADS = UberToolParts.TOOL_HEAD_QUALITIES.keySet();
                for (ResourceKey<Item> toolHeadId : TOOL_HEADS) {
                    Item toolHeadItem = BuiltInRegistries.ITEM.getValue(toolHeadId);
                    if (toolHeadItem == null) return;

                    shaped(RecipeCategory.TOOLS, UberItems.COPPER_ROD)
                            .pattern("n")
                            .pattern("n")
                            .pattern("n")
                            .define('n', Items.COPPER_NUGGET)
                            .group("multi_bench")
                            .unlockedBy(getHasName(Items.COPPER_NUGGET), has(Items.COPPER_NUGGET))
                            .save(output);
                    shaped(RecipeCategory.TOOLS, UberItems.GOLD_ROD)
                            .pattern("n")
                            .pattern("n")
                            .pattern("n")
                            .define('n', Items.GOLD_NUGGET)
                            .group("multi_bench")
                            .unlockedBy(getHasName(Items.GOLD_NUGGET), has(Items.GOLD_NUGGET))
                            .save(output);
                    shaped(RecipeCategory.TOOLS, UberItems.IRON_ROD)
                            .pattern("n")
                            .pattern("n")
                            .pattern("n")
                            .define('n', Items.IRON_NUGGET)
                            .group("multi_bench")
                            .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                            .save(output);


                    UberToolRecipeBuilder.toolqual(itemLookup, RecipeCategory.TOOLS, UberToolParts.getTool(toolHeadItem))
                            .requires(UberItemTagProvider.ATTACHERS)
                            .requires(UberItemTagProvider.HANDLES)
                            .requires(toolHeadItem)
                            .unlockedBy(getHasName(toolHeadItem), has(toolHeadItem))
                            .save(output);

                    //shapeless(RecipeCategory.TOOLS, UberToolParts.getTool(toolHeadItem))
                }



            }
        };
    }

    @Override
    public String getName() {
        return "UberRecipeProvider";
    }

}
