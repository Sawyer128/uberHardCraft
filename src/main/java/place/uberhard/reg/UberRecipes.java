package place.uberhard.reg;

import net.fabricmc.fabric.api.recipe.v1.sync.RecipeSynchronization;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import place.uberhard.UberHardCraft;
import place.uberhard.recipes.UberToolRecipe;

public class UberRecipes {

    /*public static final RecipeSerializer<UberToolRecipe> UBER_TOOL_RECIPE_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            UberHardCraft.id("crafting_tool_quality"),
            UberToolRecipe.SERIALIZER
    );*/

    public static void init() {

        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, UberHardCraft.id("crafting_tool_quality"), UberToolRecipe.SERIALIZER);

    }
}
