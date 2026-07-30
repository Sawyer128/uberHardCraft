package cool.place.common;

import cool.place.UberHardCraft;
import cool.place.block.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import java.util.function.Function;

public class UberBlocks {
    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties, boolean shouldRegisterItem) {
        // Create a registry key for the block
        ResourceKey<Block> blockKey = keyOfBlock(name);
        // Create the block instance
        Block block = blockFactory.apply(properties.setId(blockKey));

        // Sometimes, you may not want to register an item for the block.
        // Eg: if it's a technical block like `minecraft:moving_piston` or `minecraft:end_gateway`
        if (shouldRegisterItem) {
            // Items need to be registered with a different type of registry key, but the ID
            // can be the same.
            ResourceKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        }

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(UberHardCraft.MOD_ID, name));
    }

    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(UberHardCraft.MOD_ID, name));
    }

    public static final Block FLINT_PLATE = register(
            "flint_plate",
            FlatMediumBlock::new,
            BlockBehaviour.Properties.of().sound(SoundType.GLASS),
            true
    );

    public static final Block FLINT_PLATE_LARGE = register(
            "flint_plate_large",
            FlatLargeBlock::new,
            BlockBehaviour.Properties.of().sound(SoundType.GLASS),
            true
    );

    public static final Block FLINT_PLATE_SMALL = register(
            "flint_plate_small",
            FlatSmallBlock::new,
            BlockBehaviour.Properties.of().sound(SoundType.GLASS),
            true
    );

    public static final Block BLANK_MOLD = register(
            "blank_mold",
            FlatMediumBlock::new,
            BlockBehaviour.Properties.of().sound(SoundType.GRAVEL),
            true
    );

    public static final Block MOLD_AXEHEAD = register(
            "mold_axehead",
            FlatMediumBlock::new,
            BlockBehaviour.Properties.of().sound(SoundType.GRAVEL),
            true
    );

    public static final Block MOLD_PICKHEAD = register(
            "mold_pickhead",
            FlatMediumBlock::new,
            BlockBehaviour.Properties.of().sound(SoundType.GRAVEL),
            true
    );

    public static final Block MOLD_SHOVELHEAD = register(
            "mold_shovelhead",
            FlatMediumBlock::new,
            BlockBehaviour.Properties.of().sound(SoundType.GRAVEL),
            true
    );

    public static final Block FIRED_CRUCIBLE = register(
            "fired_crucible",
            CrucibleBlock::new,
            BlockBehaviour.Properties.of().sound(SoundType.STONE),
            true
    );

    public static final Block UNFIRED_CRUCIBLE = register(
            "unfired_crucible",
            UnfiredCrucibleBlock::new,
            BlockBehaviour.Properties.of().sound(SoundType.GRAVEL),
            true
    );

    public static final Block KILN_BLOCK = register(
            "kiln_block",
            KilnBlock::new,
            BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1.25F, 4.2F),
            true
    );

    public static final Block KILN_VENT = register(
            "kiln_vent",
            KilnBlock::new,
            BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1.25F, 4.2F),
            true
    );

    public static final Block KILN_BLOCK_SLAB = register(
            "kiln_block_slab",
            SlabBlock::new,
            BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1.25F, 4.2F),
            true
    );

    public static final Block KILN_HATCH = register(
            "kiln_hatch_block",
            settings -> new KilnHatchBlock(BlockSetType.OAK, settings),
            BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1.25F, 4.2F),
            true
    );

    public static final Block KILN_RACK = register(
            "kiln_rack",
            KilnRackBlock::new,
            BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1.25F, 4.2F),
            true
    );

    public static final Block KILN_FIRE = register(
            "kiln_fire",
            KilnFireBlock::new,
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1.25F, 4.2F).lightLevel(blockState -> blockState.getValue(KilnFireBlock.BURNING) ? 15 : 0),
            true
    );



    public static void initialize() {
    }
}
