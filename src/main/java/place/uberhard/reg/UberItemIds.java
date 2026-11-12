package place.uberhard.reg;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import place.uberhard.UberHardCraft;
import java.util.List;

public class UberItemIds {

    public static final ResourceKey<CreativeModeTab> UBER_CREATIVE_TAB_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(), UberHardCraft.id("creative_tab")
    );

    public static ResourceKey<Item> create(String name) {
        return ResourceKey.create(Registries.ITEM, UberHardCraft.id(name));
    }

    public static ResourceKey<Item> COPPER_ROD = create("copper_rod");
    public static ResourceKey<Item> COPPER_HANDLE = create("copper_handle");
    public static ResourceKey<Item> IRON_ROD = create("iron_rod");
    public static ResourceKey<Item> IRON_HANDLE = create("iron_handle");
    public static ResourceKey<Item> GOLD_ROD = create("gold_rod");
    public static ResourceKey<Item> GOLD_HANDLE = create("gold_handle");

    public static ResourceKey<Item> FLINT_PICKHEAD = create("flint_pickaxe_head");

    public static ResourceKey<Item> BARK_FIBER = create("bark_fiber");

    public static ResourceKey<Item> FLINT_PICKAXE = create("flint_pickaxe");

    public static void init() {}

}
