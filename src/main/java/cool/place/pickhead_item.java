package cool.place;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class pickhead_item extends Item {

    public pickhead_item(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(final UseOnContext context) {

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState targetState = level.getBlockState(pos);
        ItemStack itemStack = context.getItemInHand();
        if (!targetState.is(EpicBlocks.BLANK_MOLD)) {
            return InteractionResult.PASS;
        }

        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        level.setBlock(pos, EpicBlocks.MOLD_PICKHEAD.defaultBlockState(), 0);

        return InteractionResult.SUCCESS;
    }

}
