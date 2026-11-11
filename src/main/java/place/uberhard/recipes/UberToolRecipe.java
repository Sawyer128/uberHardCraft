package place.uberhard.recipes;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.ShapelessCraftingRecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;
import place.uberhard.data.UberToolParts;
import place.uberhard.reg.UberComponents;

import java.util.List;

public class UberToolRecipe extends NormalCraftingRecipe {

    public static final MapCodec<UberToolRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(
            i -> i.group(
                    Recipe.CommonInfo.MAP_CODEC.forGetter(o -> o.commonInfo),
                    CraftingRecipe.CraftingBookInfo.MAP_CODEC.forGetter(o -> o.bookInfo),
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter(o -> o.result),
                    Ingredient.CODEC.listOf(1, 9).fieldOf("ingredients").forGetter(o -> o.ingredients)
            )
            .apply(i, UberToolRecipe::new)

    );

    public static final StreamCodec<RegistryFriendlyByteBuf, UberToolRecipe> STREAM_CODEC = StreamCodec.composite(
      CommonInfo.STREAM_CODEC,
      o -> o.commonInfo,
      CraftingRecipe.CraftingBookInfo.STREAM_CODEC,
      o -> o.bookInfo,
      ItemStackTemplate.STREAM_CODEC,
      o -> o.result,
      Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()),
      o -> o.ingredients,
      UberToolRecipe::new
    );

    public static final RecipeSerializer<UberToolRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    private final ItemStackTemplate result;
    private final List<Ingredient> ingredients;

    public UberToolRecipe(CommonInfo commonInfo, CraftingBookInfo bookInfo, ItemStackTemplate result, List<Ingredient> ingredients) {
        super(commonInfo, bookInfo);
        this.result = result;
        this.ingredients = ingredients;
    }

    public boolean matches(final CraftingInput input, final Level level) {
        if (input.ingredientCount() != this.ingredients.size()) {
            return false;
        } else {
            return input.size() == 1 && this.ingredients.size() == 1 ? this.ingredients.getFirst().test(input.getItem(0)) : input.stackedContents().canCraft(this, null);
        }
    }

    @Override
    public ItemStack assemble(final CraftingInput input) {

        int totalQuality = 0;

        for (ItemStack stack : input.items()) {
            if (!stack.isEmpty()) totalQuality += UberToolParts.getQuality(BuiltInRegistries.ITEM.getResourceKey(stack.getItem()).get());
        }

        ItemStack finalResult = this.result.create();
        finalResult.set(UberComponents.UBER_TOOL_QUALITY, totalQuality);

        return finalResult;
    }

    @Override
    public List<RecipeDisplay> display() {
        return List.of(
                new ShapelessCraftingRecipeDisplay(
                        this.ingredients.stream().map(Ingredient::display).toList(),
                        new SlotDisplay.ItemStackSlotDisplay(this.result),
                        new SlotDisplay.ItemSlotDisplay(Items.CRAFTING_TABLE)
                )
        );
    }

    @Override
    protected PlacementInfo createPlacementInfo() {
        return PlacementInfo.create(this.ingredients);
    }

    @Override
    public RecipeSerializer<UberToolRecipe> getSerializer() {
        return SERIALIZER;
    }

}
