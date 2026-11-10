package place.uberhard.reg;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class UberItems {

    public static Item register(ResourceKey<Item> itemKey, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {

        Item item = itemFactory.apply(settings.setId(itemKey));

        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static final Item FLINT_PICKHEAD = register(UberItemIds.FLINT_PICKHEAD, Item::new, new Item.Properties());

    public static final Item BARK_FIBER = register(UberItemIds.BARK_FIBER, Item::new, new Item.Properties());


    public static void init() {

    }

}
