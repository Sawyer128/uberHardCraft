package cool.place;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.block.Block;

public class flint_tool {

    public static final TagKey<Item> REPAIRS_FLINT_TOOL = TagKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(UberHardCraft.MOD_ID, "repairs_flint_tool"));

    public static final TagKey<Block> INCORRECT_FOR_FLINT_TOOL = TagKey.create(BuiltInRegistries.BLOCK.key(),
            Identifier.fromNamespaceAndPath(UberHardCraft.MOD_ID, "incorrect_for_flint_tool"));

    public static final ToolMaterial FLINT_TOOL_MATERIAL = new ToolMaterial(
            INCORRECT_FOR_FLINT_TOOL,
            32,
            1.4F,
            1.5F,
            5,
            REPAIRS_FLINT_TOOL
    );


}
