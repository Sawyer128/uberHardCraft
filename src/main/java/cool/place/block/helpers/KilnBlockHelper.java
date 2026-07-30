package cool.place.block.helpers;

import cool.place.UberHardCraft;
import cool.place.blockentity.KilnFireBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class KilnBlockHelper {

    public static void searchFires (final Level level, final BlockPos pos) {
        for (BlockPos searchPos : BlockPos.betweenClosed(
                pos.offset(-2, -6, -2),
                pos.offset(2, 1, 2))) {

            BlockEntity searchBlockEntity = level.getBlockEntity(searchPos);

            if (searchBlockEntity instanceof KilnFireBlockEntity fire) {
                UberHardCraft.LOGGER.info("Fire found, calling kiln()");
                fire.kiln();
            }
        }
    }

}
