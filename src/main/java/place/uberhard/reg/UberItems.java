package place.uberhard.reg;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import place.uberhard.UberHardCraft;
import place.uberhard.component.ComponentWithTooltip;
import place.uberhard.items.FlintTool;
import place.uberhard.items.UberToolItem;

import java.util.function.Function;

public class UberItems {

    public static Item register(ResourceKey<Item> itemKey, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {

        Item item = itemFactory.apply(settings.setId(itemKey));

        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    // Items to add: cloth, metal rods, hoglin tusk, wither bone, sturdy cane
    // crazy idea, whenever you cook raw ore in a furnace it turns into unrefined lump

    public static final Item COPPER_ROD = register(UberItemIds.COPPER_ROD, Item::new, new Item.Properties());
    public static final Item COPPER_HANDLE = register(UberItemIds.COPPER_HANDLE, Item::new, new Item.Properties());
    public static final Item GOLD_ROD = register(UberItemIds.GOLD_ROD, Item::new, new Item.Properties());
    public static final Item GOLD_HANDLE = register(UberItemIds.GOLD_HANDLE, Item::new, new Item.Properties());
    public static final Item IRON_ROD = register(UberItemIds.IRON_ROD, Item::new, new Item.Properties());
    public static final Item IRON_HANDLE = register(UberItemIds.IRON_HANDLE, Item::new, new Item.Properties());

    public static final Item FLINT_PICKHEAD = register(UberItemIds.FLINT_PICKHEAD, Item::new, new Item.Properties());

    public static final Item BARK_FIBER = register(UberItemIds.BARK_FIBER, Item::new, new Item.Properties());

    public static final Item FLINT_PICKAXE = register(
            UberItemIds.FLINT_PICKAXE,
            UberToolItem::new,
            new Item.Properties().pickaxe(FlintTool.FLINT_TOOL_MATERIAL, 1f, 1f));

    public static final CreativeModeTab UBER_CREATIVE_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(FLINT_PICKAXE))
            .title(Component.translatable("creativeTab.uberhardcraft"))
            .displayItems((parameters, output) -> {
                output.accept(COPPER_ROD);
                output.accept(COPPER_HANDLE);
                output.accept(GOLD_ROD);
                output.accept(GOLD_HANDLE);
                output.accept(IRON_ROD);
                output.accept(IRON_HANDLE);

                output.accept(BARK_FIBER);

                output.accept(FLINT_PICKHEAD);

                output.accept(FLINT_PICKAXE);

                for (int i = 0; i < 3; i++) {
                    ItemStack stack = new ItemStack(FLINT_PICKAXE);
                    stack.set(UberComponents.UBER_TOOL_QUALITY, (i*5)+1);
                    stack.set(UberComponents.COMPONENT_WITH_TOOLTIP, new ComponentWithTooltip((i*5)+1));
                    output.accept(stack);
                }
            })
            .build();


    public static void init() {

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, UberItemIds.UBER_CREATIVE_TAB_KEY, UBER_CREATIVE_TAB);

    }

}
