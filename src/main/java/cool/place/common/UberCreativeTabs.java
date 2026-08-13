package cool.place.common;

import cool.place.UberHardCraft;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class UberCreativeTabs {

    public static final ResourceKey<CreativeModeTab> UBER_CREATIVE_TAB_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(UberHardCraft.MOD_ID, "creative_tab")
    );

    public static final CreativeModeTab EARLY_GAME_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(UberItems.FLINT_AXE))
            .title(Component.translatable("creative_tab.uberhardcraft.early_game_tab"))
            .displayItems((parameters, output) -> {
                output.accept(UberItems.BARK);
                output.accept(UberItems.ROCK);
                output.accept(UberItems.HARD_CLAY_BALL);
                output.accept(UberItems.FLINT_PICKAXEHEAD);
                output.accept(UberItems.FLINT_AXEHEAD);
                output.accept(UberItems.FLINT_SHOVELHEAD);
                output.accept(UberItems.COPPER_PICKHEAD);
                output.accept(UberItems.COPPER_AXEHEAD);
                output.accept(UberItems.COPPER_SHOVELHEAD);
                output.accept(UberBlocks.FLINT_PLATE_LARGE);
                output.accept(UberBlocks.FLINT_PLATE);
                output.accept(UberBlocks.FLINT_PLATE_SMALL);
                output.accept(UberItems.WOODEN_SIFT);
                output.accept(UberItems.WOODEN_TONGS);
                output.accept(UberItems.METAL_TONGS);
                output.accept(UberItems.FIRESTARTER);
                output.accept(UberItems.FLINT_PICKAXE);
                output.accept(UberItems.FLINT_AXE);
                output.accept(UberItems.FLINT_SHOVEL);
                output.accept(UberBlocks.BLANK_MOLD);
                output.accept(UberBlocks.MOLD_PICKHEAD);
                output.accept(UberBlocks.MOLD_AXEHEAD);
                output.accept(UberBlocks.MOLD_SHOVELHEAD);
                output.accept(UberBlocks.UNFIRED_CRUCIBLE);
                output.accept(UberBlocks.FIRED_CRUCIBLE);
                output.accept(UberBlocks.KILN_BLOCK);
                output.accept(UberBlocks.KILN_BLOCK_SLAB);
                output.accept(UberBlocks.KILN_VENT);
                output.accept(UberBlocks.KILN_RACK);
                output.accept(UberBlocks.KILN_HATCH);
                output.accept(UberBlocks.KILN_FIRE);
            })
            .build();


    public static void initialize() {

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, UBER_CREATIVE_TAB_KEY, EARLY_GAME_TAB);

    }
}
