package place.uberhard.reg;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;
import place.uberhard.UberHardCraft;

public class UberComponents {

    public static final DataComponentType<Integer> UBER_TOOL_QUALITY = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            UberHardCraft.id("uber_tool_quality"),
            DataComponentType.<Integer>builder().persistent(ExtraCodecs.NON_NEGATIVE_INT).networkSynchronized(ByteBufCodecs.VAR_INT).build()
    );

    public static void init() { }

}
