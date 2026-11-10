package place.uberhard.reg;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import place.uberhard.UberHardCraft;

public class UberItemIds {

    public static ResourceKey<Item> create(String name) {
        return ResourceKey.create(Registries.ITEM, UberHardCraft.id(name));
    }

    public static ResourceKey<Item> FLINT_PICKHEAD = create("flint_pickaxe_head");

    public static ResourceKey<Item> BARK_FIBER = create("bark_fiber");


    public static void init() {

    }

}
