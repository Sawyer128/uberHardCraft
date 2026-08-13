package cool.place.blockentity;

import cool.place.block.CrucibleBlock;
import cool.place.common.UberBlockEntities;
import cool.place.common.UberBlocks;
import cool.place.UberHardCraft;
import cool.place.block.KilnFireBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KilnFireBlockEntity extends BlockEntity {

    public KilnFireBlockEntity(BlockPos pos, BlockState state) {
        super(UberBlockEntities.KILN_FIRE_ENTITY, pos, state);
    }

    public static final TagKey<Block> VALID_KILN_SUPERSTRUCTURE = TagKey.create(BuiltInRegistries.BLOCK.key(),
            Identifier.fromNamespaceAndPath(UberHardCraft.MOD_ID, "valid_kiln_superstructure"));

    List<BlockPos> vents = new ArrayList<>();
    List<BlockPos> fuel = new ArrayList<>();
    BlockPos position = this.getBlockPos();
    BlockPos cruciblePosition;
    BlockPos unfiredCruciblePosition;
    int crucibleTime;
    BlockState blockState;
    Level world;
    public boolean burning = false;
    boolean Assembled = false;
    public int burnTick = 0;

    public void light () {

        if (this.getLevel() == null) return;

        world = this.getLevel();
        blockState = this.getLevel().getBlockState(position);

        fuel = identifyFuel(position, world);

        if (!fuel.isEmpty()) {
            //UberHardCraft.LOGGER.info("Fuel found! Blocks found: " + fuel.toArray().length);
            burning = true;
            world.setBlockAndUpdate(position, world.getBlockState(position).setValue(KilnFireBlock.BURNING, true));
            kiln();
        }

    }

    public void consumeFuel (final Level level) {

        if (!fuel.isEmpty()) {

            Random rand = new Random();
            int randIndex = rand.nextInt(fuel.size());
            BlockPos randFuelPos = fuel.get(randIndex);
            fuel.remove(randIndex);

            level.setBlock(randFuelPos, Blocks.COAL_BLOCK.defaultBlockState(), 3);

        } else {
            List<BlockPos> checkFuel = identifyFuel(position, world);
            if (!checkFuel.isEmpty()) {
                fuel = checkFuel;
            } else {
                burning = false;
                world.setBlockAndUpdate(position, world.getBlockState(position).setValue(KilnFireBlock.BURNING, false));
            }

        }

    }

    public void assembly () {
        Assembled = kilnAssembled();
        kiln();
        //UberHardCraft.LOGGER.info("kiln: status " + Assembled);
    }

    public void kiln () {

        if (burning) {

            //UberHardCraft.LOGGER.info("kiln: current burntick " + burnTick);

            if (!world.getBlockTicks().hasScheduledTick(position, blockState.getBlock())) {

                world.scheduleTick(position, blockState.getBlock(), 180);

            }

            if (cruciblePosition != null && world.getBlockState(cruciblePosition).is(UberBlocks.FIRED_CRUCIBLE)) {

                if (Assembled && !(world.getBlockState(cruciblePosition).getValue(CrucibleBlock.LIQUID))) {

                    int timeToBurn;

                    // Prolly should change this its hardcoded haha

                    if (world.getBlockState(cruciblePosition).getValue(CrucibleBlock.ORE).toString() == "copper") {
                        timeToBurn = 5;
                    } else if (world.getBlockState(cruciblePosition).getValue(CrucibleBlock.ORE).toString() == "iron") {
                        timeToBurn = 9;
                    } else {
                        timeToBurn = 999999999;
                    }

                    CrucibleBlock crucible = (CrucibleBlock) world.getBlockState(cruciblePosition).getBlock();

                    if ((burnTick - crucibleTime) == timeToBurn) {
                        world.setBlockAndUpdate(cruciblePosition, world.getBlockState(cruciblePosition).setValue(CrucibleBlock.LIQUID, true));
                    }
                }
            } else {
                cruciblePosition = null;
            }

            if (unfiredCruciblePosition != null && world.getBlockState(unfiredCruciblePosition).is(UberBlocks.UNFIRED_CRUCIBLE)) {

                if (Assembled) {

                    int timeToBurn = 3;

                    if ((burnTick - crucibleTime) == timeToBurn) {
                        world.setBlock(unfiredCruciblePosition, UberBlocks.FIRED_CRUCIBLE.defaultBlockState(), 3);
                    }

                }

            } else {
                unfiredCruciblePosition = null;
            }

        }

    }

    private boolean kilnAssembled () {

        BlockPos pos = this.getBlockPos();
        BlockPos topPos = this.getBlockPos().above();
        Level level = this.getLevel();
        int height = 1;

        Direction[] directions = {
                Direction.EAST,
                Direction.SOUTH,
                Direction.NORTH,
                Direction.WEST,
                Direction.UP,
                Direction.DOWN
        };

        if (level == null) {
            return false;
        }

        // Initially, loop all four sides for kiln blocks
        // Proceed to checking layers. Loop up, if top block is valid, stop. If top block is not valid, check if surrounding sides are valid. If one is not, stop.
        // Continue that process until complete or until 6 blocks above the fire have been searched.

        if (checkCardinals(pos, level, directions)) return false;

        while (height <= 6) {

            if ((level.getBlockState(topPos).is(VALID_KILN_SUPERSTRUCTURE)) || (level.getBlockState(topPos).is(UberBlocks.KILN_HATCH)) && !level.getBlockState(topPos).getValue(TrapDoorBlock.OPEN) && !(level.getBlockState(topPos).getValue(TrapDoorBlock.HALF) == Half.TOP)) {

                vents.clear();
                return checkVent(pos, level, directions);

            } else if (level.getBlockState(topPos).is(UberBlocks.KILN_HATCH) && !level.getBlockState(topPos).getValue(TrapDoorBlock.OPEN) && (level.getBlockState(topPos).getValue(TrapDoorBlock.HALF) == Half.TOP)) {

                vents.clear();
                if (!checkCardinals(topPos, level, directions)) return checkVent(pos, level, directions);

            } else if (level.getBlockState(topPos).is(UberBlocks.FIRED_CRUCIBLE) || level.getBlockState(topPos).is(UberBlocks.UNFIRED_CRUCIBLE) || level.getBlockState(topPos).is(UberBlocks.KILN_RACK) || level.getBlockState(topPos).is(Blocks.AIR)) {

                if (level.getBlockState(topPos).is(UberBlocks.FIRED_CRUCIBLE) && !(topPos.equals(cruciblePosition))) {
                    cruciblePosition = topPos; crucibleTime = burnTick; //UberHardCraft.LOGGER.info("crucible found!!! found at burn tick: " + crucibleTime);
                } else if (level.getBlockState(topPos).is(UberBlocks.UNFIRED_CRUCIBLE) && !(topPos.equals(unfiredCruciblePosition))) {
                    unfiredCruciblePosition = topPos; crucibleTime = burnTick; //UberHardCraft.LOGGER.info("UNFIRED crucible found!!! found at burn tick: " + crucibleTime);
                }

                if (checkCardinals(topPos, level, directions)) return false;

            } else {
                return false;
            }

            topPos = topPos.above();
            height += 1;

        }

        return false;
    }

    private boolean checkCardinals (final BlockPos pos, final Level level, final Direction[] directions) {

        // Helper function that checks if 4 sides are valid.
        // Special case added for trapdoor to check if its open and in the correct orientation.

        int i = 0;

        while (i < 4) {

            BlockState state = level.getBlockState(pos.relative(directions[i]));

            //UberHardCraft.LOGGER.info("kiln: CARDINAL CHECK: for " + directions[i]);


            if ((!state.is(UberBlocks.KILN_HATCH) && !state.is(VALID_KILN_SUPERSTRUCTURE)) || ((state.is(UberBlocks.KILN_HATCH) && (!state.getValue(TrapDoorBlock.OPEN) || (state.getValue(TrapDoorBlock.OPEN) && !(state.getValue(TrapDoorBlock.FACING) == directions[i])))))) {

                return true;

            }
            i += 1;
        }

        return false;

    }

    private boolean checkVent (final BlockPos pos, final Level level, final Direction[] directions) {

        // Recursively checks the whole directions list for an air block, if none found, an adjacently searches for a vent block.

        int i = 0;
        int x = 6;

        if (level.getBlockState(pos).is(UberBlocks.KILN_FIRE)) x = 4;
        else if (level.getBlockState(pos).is(UberBlocks.KILN_VENT)) vents.add(pos);

        while (i < x) {


            BlockState state = level.getBlockState(pos.relative(directions[i]));
            if (state.is(Blocks.AIR)) return true;
            else if (state.is(UberBlocks.KILN_VENT) && !(vents.contains(pos.relative(directions[i])))) return checkVent(pos.relative(directions[i]), level, directions);

            i += 1;
        }

        return false;
    }

    private List<BlockPos> identifyFuel (final BlockPos pos, final Level level) {

        List<BlockPos> skbdHi = new ArrayList<>();
        BlockState state;

        for (BlockPos searchPos : BlockPos.betweenClosed(
                pos.offset(-1, -1, -1),
                pos.offset(1, -1, 1))) {

            if (level != null) {
                state = level.getBlockState(searchPos);

                if (state.is(BlockTags.LOGS)) {
                    skbdHi.add(searchPos.immutable());
                }

            }
        }

        return skbdHi;
    }


}
