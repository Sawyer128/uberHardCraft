package cool.place.blockentity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import cool.place.block.CrucibleBlock;
import cool.place.common.UberBlockEntities;
import cool.place.component.CrucibleData;
import cool.place.type.Ore;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.ticks.ContainerSingleItem;

public class CrucibleBlockEntity extends BlockEntity implements ContainerSingleItem.BlockContainerSingleItem {

    private ItemStack item = ItemStack.EMPTY;

    public CrucibleBlockEntity(BlockPos pos, BlockState state) {
        super(UberBlockEntities.FIRED_CRUCIBLE_ENTITY, pos, state);
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

    public CrucibleData getCrucibleData() {
        return new CrucibleData( this.getBlockState().getValue(CrucibleBlock.LIQUID), this.getBlockState().getValue(CrucibleBlock.LEVEL), this.getBlockState().getValue(CrucibleBlock.ORE) );
    }

    /*public void setCrucibleData(CrucibleData crucibleData) {

        if (this.level == null) return;

        this.level.setBlockAndUpdate(this.worldPosition, this.getBlockState().setValue(CrucibleBlock.LIQUID, crucibleData.liquid()));
        this.level.setBlockAndUpdate(this.worldPosition, this.getBlockState().setValue(CrucibleBlock.LEVEL, crucibleData.level()));
        this.level.setBlockAndUpdate(this.worldPosition, this.getBlockState().setValue(CrucibleBlock.ORE, crucibleData.ore()));

        this.item = switch (crucibleData.ore()) {
            case raw_copper -> new ItemStack(Items.RAW_COPPER, crucibleData.level());
            case raw_iron ->  new ItemStack(Items.RAW_IRON, crucibleData.level());
            case air -> new ItemStack(Items.AIR);
        };

    }*/

}
