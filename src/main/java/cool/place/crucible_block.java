package cool.place;

import com.mojang.serialization.MapCodec;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class crucible_block extends BaseEntityBlock {

    public static final BooleanProperty LIQUID = BooleanProperty.create("liquid");
    public static final IntegerProperty LEVEL = IntegerProperty.create("level", 0, 5);
    // public static final IntegerProperty ORE = IntegerProperty.create("ore", 0, 2);
    public static final EnumProperty<Ore> ORE = EnumProperty.create("ore", Ore.class);

    // ORE: 0 is none, 1 is copper, 2 is iron...

    public static final TagKey<Item> MELTABLE = TagKey.create(Registries.ITEM,
            Identifier.fromNamespaceAndPath(UberHardCraft.MOD_ID, "meltable"));

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIQUID);
        builder.add(LEVEL);
        builder.add(ORE);
    }

    public crucible_block(Properties settings) {
        super(settings);
        registerDefaultState(defaultBlockState().setValue(LIQUID, false).setValue(LEVEL, 0).setValue(ORE, Ore.air));
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

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {

        if (!(world.getBlockEntity(pos) instanceof crucible_block_entity crucibleBlockEntity)) {
            return InteractionResult.PASS;
        }

        boolean liquid = state.getValue(LIQUID);
        int model_height = state.getValue(LEVEL);
        /*int ore_type = state.getValue(ORE);*/
        ItemStack crucibleItem = crucibleBlockEntity.getTheItem();

        if (!player.getItemInHand(hand).isEmpty() && stack.is(MELTABLE) && !liquid && !(crucibleItem.count() == 5) && (crucibleItem.isEmpty() || ItemStack.isSameItemSameComponents(crucibleItem, stack) && crucibleItem.getCount() < crucibleItem.getMaxStackSize())) {
            ItemStack givenItem = stack.consumeAndReturn(1, player);

            if (crucibleBlockEntity.isEmpty()) {
                crucibleBlockEntity.setTheItem(givenItem);

                int i = 0;
                while (i < Ore.values().length) {
                    if (stack.getItemName().toString().contains(Ore.values()[i].toString())) {
                        world.setBlockAndUpdate(pos, state.setValue(ORE, Ore.values()[i]).setValue(LEVEL, 1));
                    }
                    i += 1;
                }

            } else {
                world.setBlockAndUpdate(pos, state.setValue(LEVEL, model_height + 1));
                crucibleItem.grow(1);
            }

            world.playSound(null, pos, SoundEvents.DECORATED_POT_INSERT, SoundSource.BLOCKS, 1.0F, 0.7F + 0.5F);
            crucibleBlockEntity.setChanged();
            world.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.TRY_WITH_EMPTY_HAND;
        }
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
