package cool.place.item;

import cool.place.UberHardCraft;
import cool.place.block.CrucibleBlock;
import cool.place.block.MoldBlock;
import cool.place.blockentity.CrucibleBlockEntity;
import cool.place.common.UberBlocks;
import cool.place.common.UberComponents;
import cool.place.component.CrucibleData;
import cool.place.type.Ore;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TongsItem extends Item {

    public TongsItem(Properties properties) { super(properties); }

    @Override
    public InteractionResult useOn(UseOnContext context) {


        Level level = context.getLevel();

        if (level.isClientSide()) {
            return InteractionResult.PASS;
        }

        BlockPos pos = context.getClickedPos();
        BlockPos lookingPos = context.getClickedPos().relative(context.getClickedFace());
        BlockState targetState = level.getBlockState(pos);
        ItemStack itemStack = context.getItemInHand();
        LivingEntity owner = context.getPlayer();

        if (itemStack.has(UberComponents.CRUCIBLE_DATA)) {

            CrucibleData data = itemStack.get(UberComponents.CRUCIBLE_DATA);

            if ((targetState.is(UberBlocks.MOLD_AXEHEAD) || targetState.is(UberBlocks.MOLD_PICKHEAD) || targetState.is(UberBlocks.MOLD_SHOVELHEAD)) && data.liquid()) {

                itemStack.remove(UberComponents.CRUCIBLE_DATA);

                level.setBlockAndUpdate(pos, level.getBlockState(pos).setValue(MoldBlock.ORE, data.ore()));

                itemStack.set(UberComponents.CRUCIBLE_DATA, new CrucibleData(false, 0, Ore.air));

                level.playSound(null, pos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);

            } else {

                itemStack.remove(UberComponents.CRUCIBLE_DATA);

                BlockState newState = UberBlocks.FIRED_CRUCIBLE.defaultBlockState()
                        .setValue(CrucibleBlock.LIQUID, data.liquid())
                        .setValue(CrucibleBlock.LEVEL, data.level())
                        .setValue(CrucibleBlock.ORE, data.ore());

                level.setBlock(lookingPos, newState, 3);

                CrucibleBlockEntity blockEntity = (CrucibleBlockEntity) level.getBlockEntity(lookingPos);

                blockEntity.setTheItem(switch (data.ore()) {
                    case raw_copper -> new ItemStack(Items.RAW_COPPER, data.level());
                    case raw_iron ->  new ItemStack(Items.RAW_IRON, data.level());
                    case air -> new ItemStack(Items.AIR);
                });

                level.playSound(null, lookingPos, SoundEvents.DECORATED_POT_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);

                itemStack.hurtAndBreak(1, owner, EquipmentSlot.MAINHAND);

            }

        } else {

            if (!targetState.is(UberBlocks.FIRED_CRUCIBLE)) {
                return InteractionResult.PASS;
            }

            CrucibleBlockEntity blockEntity = (CrucibleBlockEntity) level.getBlockEntity(pos);

            itemStack.set(UberComponents.CRUCIBLE_DATA, blockEntity.getCrucibleData());

            blockEntity.setTheItem(new ItemStack(Items.AIR));
            level.destroyBlock(pos, false);

        }

        return InteractionResult.SUCCESS;
    }
}
