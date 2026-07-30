package cool.place.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class UnfiredCrucibleBlock extends Block {

    public UnfiredCrucibleBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getCollisionShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return Block.box(4.0, 0.0, 4.0, 12.0, 7.0, 12.0);
    }

    @Override
    protected VoxelShape getShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return Block.box(4.0, 0.0, 4.0, 12.0, 7.0, 12.0);
    }
}
