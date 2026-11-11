package place.uberhard.reg;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import place.uberhard.items.FlintTool;
import java.util.function.Function;

public class UberItems {

    public static Item register(ResourceKey<Item> itemKey, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {

        Item item = itemFactory.apply(settings.setId(itemKey));

        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static final Item FLINT_PICKHEAD = register(UberItemIds.FLINT_PICKHEAD, Item::new, new Item.Properties());

    public static final Item BARK_FIBER = register(UberItemIds.BARK_FIBER, Item::new, new Item.Properties());

    public static final Item FLINT_PICKAXE = register(
            UberItemIds.FLINT_PICKAXE,
            Item::new,
            new Item.Properties().pickaxe(FlintTool.FLINT_TOOL_MATERIAL, 1f, 1f));


    public static void init() { }

}
