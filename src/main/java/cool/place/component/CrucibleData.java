package cool.place.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import cool.place.type.Ore;

public record CrucibleData(boolean liquid, int level, Ore ore) {

    public static final Codec<CrucibleData> CODEC = RecordCodecBuilder.create(builder ->
            builder.group(
                    Codec.BOOL.fieldOf("liquid").forGetter(CrucibleData::liquid),
                    Codec.INT.fieldOf("level").forGetter(CrucibleData::level),
                    Ore.CODEC.fieldOf("ore").forGetter(CrucibleData::ore)
            ).apply(builder, CrucibleData::new)
    );

}
