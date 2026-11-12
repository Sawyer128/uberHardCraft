package place.uberhard.items;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.block.state.BlockState;
import place.uberhard.reg.UberComponents;

public class UberToolItem extends Item {
    public UberToolItem(Properties properties) {
        super(properties);
    }

    public int getQuality(ItemStack stack) {
        return stack.getOrDefault(UberComponents.UBER_TOOL_QUALITY, 0);
    }

    @Override
    public float getDestroySpeed(ItemStack itemStack, BlockState state) {
        Tool tool = itemStack.get(DataComponents.TOOL);
        if (tool == null) return 1.0F;
        return tool.getMiningSpeed(state) + ((float) getQuality(itemStack) / 10);
    }

}
