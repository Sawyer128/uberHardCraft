package place.uberhard.data;

import net.minecraft.references.BlockItemIds;
import net.minecraft.references.ItemIds;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import place.uberhard.reg.UberItemIds;
import place.uberhard.reg.UberItems;

import java.util.HashMap;
import java.util.Map;

public class UberToolParts {

    // All the tool heads, handles, and attachers should be included

    //Special ones aren't included in the tag

    public static final Map<ResourceKey<Item>, Integer> SPEC_HANDLE_QUALITIES = Map.of(
            //Special Handles
            ItemIds.BLAZE_ROD, 10
    );

    public static final Map<ResourceKey<Item>, Integer> SPEC_ATTACHER_QUALITIES = Map.of(
            //Special Attachers
            ItemIds.MAGMA_CREAM, 10
    );

    // Each HashMap is used in the tagProvider and recipeProvider to automatically create tags and recipes

    public static final Map<ResourceKey<Item>, Integer> ATTACHER_QUALITIES = Map.of(
            //Attachers
            BlockItemIds.TRIPWIRE.item(), 3,
            ItemIds.SLIME_BALL, 5,
            UberItemIds.BARK_FIBER, 1,
            ItemIds.HONEY_BOTTLE, 5
    );

    public static final Map<ResourceKey<Item>, Integer> HANDLE_QUALITIES = Map.ofEntries(
            //Handles
            Map.entry(ItemIds.STICK, 1),
            Map.entry(ItemIds.BONE, 4),
            Map.entry(ItemIds.BREEZE_ROD, 10),
            Map.entry(BlockItemIds.CARROT_CROP.item(), 2),
            Map.entry(BlockItemIds.BAMBOO.item(), 2),
            Map.entry(UberItemIds.COPPER_ROD, 5),
            Map.entry(UberItemIds.COPPER_HANDLE, 10),
            Map.entry(UberItemIds.IRON_ROD, 15),
            Map.entry(UberItemIds.IRON_HANDLE, 20),
            Map.entry(UberItemIds.GOLD_ROD, 16),
            Map.entry(UberItemIds.GOLD_HANDLE, 21)
    );

    public static final Map<ResourceKey<Item>, Integer> TOOL_HEAD_QUALITIES = Map.of(
            //Tool heads
            UberItemIds.FLINT_PICKHEAD, 3
    );

    private static final Map<Item, Item> HEAD_TO_TOOL = Map.of(
            //What each tool head crafts into (for recipe)
            UberItems.FLINT_PICKHEAD, UberItems.FLINT_PICKAXE
    );


    public static int getQuality(ResourceKey<Item> itemId) {

        Map<ResourceKey<Item>, Integer> QUALITIES = new HashMap<>();
        QUALITIES.putAll(ATTACHER_QUALITIES);
        QUALITIES.putAll(HANDLE_QUALITIES);
        QUALITIES.putAll(TOOL_HEAD_QUALITIES);

        return QUALITIES.getOrDefault(itemId, 0);
    }

    public static Item getTool(Item item) { return HEAD_TO_TOOL.getOrDefault(item, Items.AIR); }



}
