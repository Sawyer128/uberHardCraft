package place.uberhard.data;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import place.uberhard.reg.UberItems;

import java.util.Map;

public class ToolComponentQualities {

    // All the tool heads, handles, and attachers should be included
    // List of handles and attachers can be found in the tag generator in the client side

    private static final Map<Item, Integer> QUALITIES = Map.of(
            //Handles
            Items.STICK, 1,
            //Attachers
            Items.STRING, 10,
            Items.SLIME_BALL, 5,
            UberItems.BARK_FIBER, 1,
            //Tool heads
            UberItems.FLINT_PICKHEAD, 5
    );

    public static int getQuality(Item item) {
        return QUALITIES.getOrDefault(item, 0);
    }



}
