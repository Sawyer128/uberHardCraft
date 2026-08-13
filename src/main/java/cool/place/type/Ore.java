package cool.place.type;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

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

    public static final Codec<Ore> CODEC = StringRepresentable.fromEnum(Ore::values);
}
