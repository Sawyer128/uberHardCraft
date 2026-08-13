package cool.place.common;

import cool.place.UberHardCraft;
import cool.place.item.*;
import cool.place.type.FlintTool;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;

import java.util.function.Function;

public class UberItems {

    public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        // Create the item key.
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(UberHardCraft.MOD_ID, name));

        // Create the item instance.
        T item = itemFactory.apply(settings.setId(itemKey));

        // Register the item.
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static final Item ROCK = register("rock_chunk", Item::new, new Item.Properties());
    public static final Item FLINT_AXEHEAD = register("flint_axehead", axehead_item::new, new Item.Properties());
    public static final Item FLINT_SHOVELHEAD = register("flint_shovelhead", shovelhead_item::new, new Item.Properties());
    public static final Item FLINT_PICKAXEHEAD = register("flint_pickaxehead", pickhead_item::new, new Item.Properties());
    public static final Item BARK = register("bark_fiber", Item::new, new Item.Properties());
    public static final Item HARD_CLAY_BALL = register("hard_clay_ball", Item::new, new Item.Properties());
    public static final Item WOODEN_SIFT = register("wooden_sift", SiftItem::new, new Item.Properties()
            .durability(10));
    public static final Item FIRESTARTER = register("firestarter", Item::new, new Item.Properties()
            .durability(10));
    public static final Item WOODEN_TONGS = register("wooden_tongs", TongsItem::new, new Item.Properties()
            .durability(10));
    public static final Item METAL_TONGS = register("metal_tongs", TongsItem::new, new Item.Properties()
            .durability(100));
    public static final Item FLINT_AXE = register(
            "flint_axe",
            settings -> new AxeItem(FlintTool.FLINT_TOOL_MATERIAL, 2.0F, -3.0F, settings),
            new Item.Properties()
    );
    public static final Item FLINT_SHOVEL = register(
            "flint_shovel",
            settings -> new ShovelItem(FlintTool.FLINT_TOOL_MATERIAL, 1.0F, -3.0F, settings),
            new Item.Properties()
    );
    public static final Item FLINT_PICKAXE = register(
            "flint_pickaxe",
            Item::new,
            new Item.Properties().pickaxe(FlintTool.FLINT_TOOL_MATERIAL, 1f, 1f)
    );

    public static final Item COPPER_AXEHEAD = register("copper_axehead", axehead_item::new, new Item.Properties());
    public static final Item COPPER_SHOVELHEAD = register("copper_shovelhead", shovelhead_item::new, new Item.Properties());
    public static final Item COPPER_PICKHEAD = register("copper_pickhead", pickhead_item::new, new Item.Properties());




    public static void initialize() {
        // Get the event for modifying entries in the ingredients group.
        // And register an event handler that adds items to the group oh your choice (INGREDIENTS).
        /*CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS)
                .register((creativeTab) -> creativeTab.accept(UberItems.ROCK));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS)
                .register((creativeTab) -> creativeTab.accept(UberItems.FLINT_AXEHEAD));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS)
                .register((creativeTab) -> creativeTab.accept(UberItems.FLINT_PICKAXEHEAD));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS)
                .register((creativeTab) -> creativeTab.accept(UberItems.FLINT_SHOVELHEAD));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS)
                .register((creativeTab) -> creativeTab.accept(UberItems.BARK));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register((creativeTab) -> creativeTab.accept(UberItems.FLINT_AXE));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register((creativeTab) -> creativeTab.accept(UberItems.FLINT_SHOVEL));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register((creativeTab) -> creativeTab.accept(UberItems.FLINT_PICKAXE));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register((creativeTab) -> {
            creativeTab.accept(UberBlocks.FLINT_PLATE.asItem());
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register((creativeTab) -> {
            creativeTab.accept(UberBlocks.FLINT_PLATE_LARGE.asItem());
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register((creativeTab) -> {
            creativeTab.accept(UberBlocks.FLINT_PLATE_SMALL.asItem());
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register((creativeTab) -> {
            creativeTab.accept(UberBlocks.BLANK_MOLD.asItem());
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register((creativeTab) -> {
            creativeTab.accept(UberBlocks.MOLD_AXEHEAD.asItem());
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register((creativeTab) -> {
            creativeTab.accept(UberBlocks.MOLD_SHOVELHEAD.asItem());
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register((creativeTab) -> {
            creativeTab.accept(UberBlocks.MOLD_PICKHEAD.asItem());
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register((creativeTab) -> {
            creativeTab.accept(UberBlocks.FIRED_CRUCIBLE.asItem());
        });*/

    }

}
