package cool.place;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class kiln_rack_block extends Block {

    public kiln_rack_block(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getCollisionShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return Block.box(0.0, 15.0, 0.0, 16.0, 16.0, 16.0);
    }

    @Override
    protected VoxelShape getShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return Block.box(0.0, 15.0, 0.0, 16.0, 16.0, 16.0);
    }
}
