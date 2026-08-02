package cool.place.block;

import com.mojang.serialization.MapCodec;
import cool.place.UberHardCraft;
import cool.place.blockentity.KilnFireBlockEntity;
import cool.place.common.UberBlockEntities;
import cool.place.common.UberItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class KilnFireBlock extends BaseEntityBlock {

    public static final BooleanProperty BURNING = BooleanProperty.create("burning");

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BURNING);
    }

    public KilnFireBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(BURNING, false));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(KilnFireBlock::new);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new KilnFireBlockEntity(pos, state);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof KilnFireBlockEntity fire) {
            ItemStack itemInHand = player.getItemInHand(hand);
            if (itemInHand.getItem() == UberItems.FIRESTARTER) {
                if (Math.random() <= 0.3) {

                    if (!level.isClientSide()) {

                        if (!fire.burning) {
                            fire.light();
                        }

                    }

                }
                itemInHand.hurtAndBreak(1, player, hand);
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.TRY_WITH_EMPTY_HAND;
    }

    @Override
    protected VoxelShape getCollisionShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return Block.box(0.0, 0.0, 0.0, 16.0, 5.5, 16.0);
    }

    @Override
    protected VoxelShape getShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return Block.box(0.0, 0.0, 0.0, 16.0, 5.5, 16.0);
    }

    @Override
    protected void tick(final BlockState state, final ServerLevel level, final BlockPos pos, final RandomSource random) {
        if (level.isClientSide()) return;

        if (level.getBlockEntity(pos) instanceof KilnFireBlockEntity fire) {
            fire.consumeFuel(level);
            fire.burnTick += 1;

            if (fire.burning) {
                fire.kiln();
                //level.scheduleTick(pos, state.getBlock(), 600);
            }
        }


    }

}
