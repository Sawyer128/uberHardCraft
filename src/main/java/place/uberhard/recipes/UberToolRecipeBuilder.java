package place.uberhard.recipes;

import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UberToolRecipeBuilder implements RecipeBuilder{

    private final HolderGetter<Item> items;
    private final RecipeCategory category;
    private final ItemStackTemplate result;
    private final List<Ingredient> ingredients = new ArrayList<>();
    private final RecipeUnlockAdvancementBuilder advancementBuilder = new RecipeUnlockAdvancementBuilder();
    private @Nullable String group;

    private UberToolRecipeBuilder(final HolderGetter<Item> items, final RecipeCategory category, final ItemStackTemplate result) {
        this.items = items;
        this.category = category;
        this.result = result;
    }

    public static UberToolRecipeBuilder toolqual(final HolderGetter<Item> items, final RecipeCategory category, final ItemStackTemplate result) {
        return new UberToolRecipeBuilder(items, category, result);
    }

    public static UberToolRecipeBuilder toolqual(final HolderGetter<Item> items, final RecipeCategory category, final ItemLike item) {
        return toolqual(items, category, item, 1);
    }

    public static UberToolRecipeBuilder toolqual(final HolderGetter<Item> items, final RecipeCategory category, final ItemLike item, final int count) {
        return new UberToolRecipeBuilder(items, category, new ItemStackTemplate(item.asItem(), count));
    }

    public UberToolRecipeBuilder requires(final TagKey<Item> tag) {
        return this.requires(Ingredient.of(this.items.getOrThrow(tag)));
    }

    public UberToolRecipeBuilder requires(final ItemLike item) {
        return this.requires(item, 1);
    }

    public UberToolRecipeBuilder requires(final ItemLike item, final int count) {
        for (int i = 0; i < count; i++) {
            this.requires(Ingredient.of(item));
        }

        return this;
    }

    public UberToolRecipeBuilder requires(final Ingredient ingredient) {
        return this.requires(ingredient, 1);
    }

    public UberToolRecipeBuilder requires(final Ingredient ingredient, final int count) {
        for (int i = 0; i < count; i++) {
            this.ingredients.add(ingredient);
        }

        return this;
    }

    public UberToolRecipeBuilder unlockedBy(final String name, final Criterion<?> criterion) {
        this.advancementBuilder.unlockedBy(name, criterion);
        return this;
    }

    public UberToolRecipeBuilder group(final @Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public ResourceKey<Recipe<?>> defaultId() {
        return RecipeBuilder.getDefaultRecipeId(this.result);
    }

    @Override
    public void save(final RecipeOutput output, final ResourceKey<Recipe<?>> id) {
        UberToolRecipe recipe = new UberToolRecipe(
                RecipeBuilder.createCraftingCommonInfo(true), RecipeBuilder.createCraftingBookInfo(this.category, this.group), this.result, this.ingredients
        );
        output.accept(id, recipe, this.advancementBuilder.build(output, id, this.category));
    }

}
