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
    // Each HashMap is used in the tagProvider and recipeProvider to automatically create tags and recipes

    public static final Map<ResourceKey<Item>, Integer> ATTACHER_QUALITIES = Map.of(
            //Attachers
            BlockItemIds.TRIPWIRE.item(), 10,
            ItemIds.SLIME_BALL, 5,
            UberItemIds.BARK_FIBER, 1
    );

    public static final Map<ResourceKey<Item>, Integer> HANDLE_QUALITIES = Map.of(
            //Handles
            ItemIds.STICK, 1
    );

    public static final Map<ResourceKey<Item>, Integer> TOOL_HEAD_QUALITIES = Map.of(
            //Tool heads
            UberItemIds.FLINT_PICKHEAD, 5
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
