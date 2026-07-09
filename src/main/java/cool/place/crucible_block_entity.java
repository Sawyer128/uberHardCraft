package cool.place;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.ticks.ContainerSingleItem;

public class crucible_block_entity extends BlockEntity implements ContainerSingleItem.BlockContainerSingleItem {

    private ItemStack item = ItemStack.EMPTY;

    public crucible_block_entity(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState) {
        super(type, worldPosition, blockState);
    }

    public crucible_block_entity(BlockPos pos, BlockState state) {
        super(EpicBlockEntities.FIRED_CRUCIBLE_ENTITY, pos, state);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        if (!this.item.isEmpty()) {
            output.store("item", ItemStack.CODEC, this.item);
        }
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.item = input.read("item", ItemStack.CODEC).orElse(ItemStack.EMPTY);
    }

    @Override
    public BlockEntity getContainerBlockEntity() {
        return this;
    }

    @Override
    public ItemStack getTheItem() {
        return this.item;
    }

    @Override
    public void setTheItem(ItemStack itemStack) {
        this.item = itemStack;
    }
}
