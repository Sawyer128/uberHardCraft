package cool.place;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import java.util.function.Function;

public class EpicBlocks {
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

    public static final Block NEW_FLINT = register(
            "new_flint",
            flint_block::new,
            BlockBehaviour.Properties.of().sound(SoundType.GLASS),
            true
    );

    public static final Block NEW_FLINT_LARGE = register(
            "new_flint_large",
            flint_block_large::new,
            BlockBehaviour.Properties.of().sound(SoundType.GLASS),
            true
    );

    public static final Block NEW_FLINT_SMALL = register(
            "new_flint_small",
            flint_block_small::new,
            BlockBehaviour.Properties.of().sound(SoundType.GLASS),
            true
    );

    public static final Block BLANK_MOLD = register(
            "blank_mold",
            flint_block::new,
            BlockBehaviour.Properties.of().sound(SoundType.GRAVEL),
            true
    );

    public static final Block MOLD_AXEHEAD = register(
            "mold_axehead",
            flint_block::new,
            BlockBehaviour.Properties.of().sound(SoundType.GRAVEL),
            true
    );

    public static final Block MOLD_PICKHEAD = register(
            "mold_pickhead",
            flint_block::new,
            BlockBehaviour.Properties.of().sound(SoundType.GRAVEL),
            true
    );

    public static final Block MOLD_SHOVELHEAD = register(
            "mold_shovelhead",
            flint_block::new,
            BlockBehaviour.Properties.of().sound(SoundType.GRAVEL),
            true
    );

    public static final Block FIRED_CRUCIBLE = register(
            "fired_crucible",
            crucible_block::new,
            BlockBehaviour.Properties.of().sound(SoundType.STONE),
            true
    );

    public static final Block UNFIRED_CRUCIBLE = register(
            "unfired_crucible",
            unfired_crucible_block::new,
            BlockBehaviour.Properties.of().sound(SoundType.GRAVEL),
            true
    );

    public static final Block KILN_BLOCK = register(
            "kiln_block",
            Block::new,
            BlockBehaviour.Properties.of().sound(SoundType.STONE),
            true
    );

    public static final Block KILN_VENT = register(
            "kiln_vent",
            Block::new,
            BlockBehaviour.Properties.of().sound(SoundType.STONE),
            true
    );

    public static final Block KILN_BLOCK_SLAB = register(
            "kiln_block_slab",
            SlabBlock::new,
            BlockBehaviour.Properties.of().sound(SoundType.STONE),
            true
    );

    public static final Block KILN_HATCH = register(
            "kiln_hatch",
            settings -> new TrapDoorBlock(BlockSetType.OAK, settings),
            BlockBehaviour.Properties.of().sound(SoundType.STONE),
            true
    );

    public static final Block KILN_RACK = register(
            "kiln_rack",
            kiln_rack_block::new,
            BlockBehaviour.Properties.of().sound(SoundType.STONE),
            true
    );



    public static void initialize() {
    }
}
