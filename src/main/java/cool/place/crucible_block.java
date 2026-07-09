package cool.place;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class crucible_block extends BaseEntityBlock {

    public crucible_block(Properties settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(crucible_block::new);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new crucible_block_entity(pos, state);
    }

    /*public crucible_block(Properties properties) {
        super(properties);
    }*/

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {

        if (!(world.getBlockEntity(pos) instanceof crucible_block_entity crucibleBlockEntity)) {
            return InteractionResult.PASS;
        }

        if (!player.getItemInHand(hand).isEmpty() && crucibleBlockEntity.isEmpty()) {
            crucibleBlockEntity.setItem(0, player.getItemInHand(hand).copy());
            player.getItemInHand(hand).setCount(0);
        }

        return InteractionResult.SUCCESS;
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
