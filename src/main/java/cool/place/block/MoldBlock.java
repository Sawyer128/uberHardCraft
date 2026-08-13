package cool.place.block;

import cool.place.type.Ore;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class MoldBlock extends FlatMediumBlock {

    public static final EnumProperty<Ore> ORE = EnumProperty.create("ore", Ore.class);

    public MoldBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(ORE, Ore.air));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ORE);
    }

}
