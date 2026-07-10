package cool.place;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public enum Ore implements StringRepresentable {
    raw_iron("iron"),
    raw_copper("copper"),
    air("none");

    private final String name;

    Ore(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.getSerializedName();
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
