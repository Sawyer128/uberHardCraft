package cool.place;

import java.util.function.Function;
import javax.tools.Tool;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;

public class EpicItems {

    public static <T extends Item> T register(
        String name,
        Function<Item.Properties, T> itemFactory,
        Item.Properties settings
    ) {
        // Create the item key.
        ResourceKey<Item> itemKey = ResourceKey.create(
            Registries.ITEM,
            Identifier.fromNamespaceAndPath(UberHardCraft.MOD_ID, name)
        );

        // Create the item instance.
        T item = itemFactory.apply(settings.setId(itemKey));

        // Register the item.
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static final Item ROCK = register(
        "rock_chunk",
        Item::new,
        new Item.Properties()
    );
    public static final Item FLINT_AXEHEAD = register(
        "flint_axehead",
        axehead_item::new,
        new Item.Properties()
    );
    public static final Item FLINT_SHOVELHEAD = register(
        "flint_shovelhead",
        shovelhead_item::new,
        new Item.Properties()
    );
    public static final Item FLINT_PICKAXEHEAD = register(
        "flint_pickaxehead",
        pickhead_item::new,
        new Item.Properties()
    );
    public static final Item BARK = register(
        "bark_fiber",
        Item::new,
        new Item.Properties()
    );
    public static final Item WOODEN_SIFT = register(
        "wooden_sift",
        sift_item::new,
        new Item.Properties().durability(10)
    );
    public static final Item FLINT_AXE = register(
        "flint_axe",
        settings ->
            new AxeItem(flint_tool.FLINT_TOOL_MATERIAL, 2.0F, -3.0F, settings),
        new Item.Properties()
    );
    public static final Item FLINT_SHOVEL = register(
        "flint_shovel",
        settings ->
            new ShovelItem(
                flint_tool.FLINT_TOOL_MATERIAL,
                1.0F,
                -3.0F,
                settings
            ),
        new Item.Properties()
    );
    public static final Item FLINT_PICKAXE = register(
        "flint_pickaxe",
        Item::new,
        new Item.Properties().pickaxe(flint_tool.FLINT_TOOL_MATERIAL, 1f, 1f)
    );

    public static void initialize() {
        // Get the event for modifying entries in the ingredients group.
        // And register an event handler that adds items to the group oh your choice (INGREDIENTS).
        CreativeModeTabEvents.modifyOutputEvent(
            CreativeModeTabs.INGREDIENTS
        ).register(creativeTab -> creativeTab.accept(EpicItems.ROCK));
        CreativeModeTabEvents.modifyOutputEvent(
            CreativeModeTabs.INGREDIENTS
        ).register(creativeTab -> creativeTab.accept(EpicItems.FLINT_AXEHEAD));
        CreativeModeTabEvents.modifyOutputEvent(
            CreativeModeTabs.INGREDIENTS
        ).register(creativeTab ->
            creativeTab.accept(EpicItems.FLINT_PICKAXEHEAD)
        );
        CreativeModeTabEvents.modifyOutputEvent(
            CreativeModeTabs.INGREDIENTS
        ).register(creativeTab ->
            creativeTab.accept(EpicItems.FLINT_SHOVELHEAD)
        );
        CreativeModeTabEvents.modifyOutputEvent(
            CreativeModeTabs.INGREDIENTS
        ).register(creativeTab -> creativeTab.accept(EpicItems.BARK));
        CreativeModeTabEvents.modifyOutputEvent(
            CreativeModeTabs.TOOLS_AND_UTILITIES
        ).register(creativeTab -> creativeTab.accept(EpicItems.FLINT_AXE));
        CreativeModeTabEvents.modifyOutputEvent(
            CreativeModeTabs.TOOLS_AND_UTILITIES
        ).register(creativeTab -> creativeTab.accept(EpicItems.FLINT_SHOVEL));
        CreativeModeTabEvents.modifyOutputEvent(
            CreativeModeTabs.TOOLS_AND_UTILITIES
        ).register(creativeTab -> creativeTab.accept(EpicItems.FLINT_PICKAXE));
        CreativeModeTabEvents.modifyOutputEvent(
            CreativeModeTabs.BUILDING_BLOCKS
        ).register(creativeTab -> {
            creativeTab.accept(EpicBlocks.NEW_FLINT.asItem());
        });
        CreativeModeTabEvents.modifyOutputEvent(
            CreativeModeTabs.BUILDING_BLOCKS
        ).register(creativeTab -> {
            creativeTab.accept(EpicBlocks.NEW_FLINT_LARGE.asItem());
        });
        CreativeModeTabEvents.modifyOutputEvent(
            CreativeModeTabs.BUILDING_BLOCKS
        ).register(creativeTab -> {
            creativeTab.accept(EpicBlocks.NEW_FLINT_SMALL.asItem());
        });
        CreativeModeTabEvents.modifyOutputEvent(
            CreativeModeTabs.BUILDING_BLOCKS
        ).register(creativeTab -> {
            creativeTab.accept(EpicBlocks.BLANK_MOLD.asItem());
        });
        CreativeModeTabEvents.modifyOutputEvent(
            CreativeModeTabs.BUILDING_BLOCKS
        ).register(creativeTab -> {
            creativeTab.accept(EpicBlocks.MOLD_AXEHEAD.asItem());
        });
        CreativeModeTabEvents.modifyOutputEvent(
            CreativeModeTabs.BUILDING_BLOCKS
        ).register(creativeTab -> {
            creativeTab.accept(EpicBlocks.MOLD_SHOVELHEAD.asItem());
        });
        CreativeModeTabEvents.modifyOutputEvent(
            CreativeModeTabs.BUILDING_BLOCKS
        ).register(creativeTab -> {
            creativeTab.accept(EpicBlocks.MOLD_PICKHEAD.asItem());
        });
        CreativeModeTabEvents.modifyOutputEvent(
            CreativeModeTabs.BUILDING_BLOCKS
        ).register(creativeTab -> {
            creativeTab.accept(EpicBlocks.FIRED_CRUCIBLE.asItem());
        });
    }

    public static final ResourceKey<CreativeModeTab> CUSTOM_CREATIVE_TAB_KEY =
        ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(),
            Identifier.fromNamespaceAndPath(UberHardCraft.MOD_ID, "creative_tab")
        );
    public static final CreativeModeTab CUSTOM_CREATIVE_TAB =
        FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(EpicItems.FLINT_PICKAXE)
            .title(Component.translatable("creativeTab.example-mod"))
            .displayItems((params, output) -> {
                output.accept(EpicItems.ROCK);
                output.accept(EpicItems.FLINT_AXEHEAD);
                output.accept(EpicItems.FLINT_PICKAXEHEAD);
                output.accept(EpicItems.FLINT_SHOVELHEAD);
                output.accept(EpicItems.FLINT_AXE);
                output.accept(EpicItems.FLINT_SHOVEL);
                output.accept(EpicItems.FLINT_PICKAXE);

                // The tab builder also accepts Blocks
                output.accept(EpicBlocks.NEW_FLINT);
                output.accept(EpicBlocks.NEW_FLINT_LARGE);
                output.accept(EpicBlocks.NEW_FLINT_SMALL);
                output.accept(EpicBlocks.BLANK_MOLD);
                output.accept(EpicBlocks.MOLD_AXEHEAD);
                output.accept(EpicBlocks.MOLD_SHOVELHEAD);
                output.accept(EpicBlocks.MOLD_PICKHEAD);
                output.accept(EpicBlocks.FIRED_CRUCIBLE);


            })
            .build();

}
