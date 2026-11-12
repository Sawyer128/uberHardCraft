package place.uberhard.reg;

import net.fabricmc.fabric.api.item.v1.ItemComponentTooltipProviderRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;
import place.uberhard.UberHardCraft;
import place.uberhard.component.ComponentWithTooltip;

public class UberComponents {

    public static final DataComponentType<Integer> UBER_TOOL_QUALITY = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            UberHardCraft.id("uber_tool_quality"),
            DataComponentType.<Integer>builder().persistent(ExtraCodecs.NON_NEGATIVE_INT).networkSynchronized(ByteBufCodecs.VAR_INT).build()
    );

    public static final DataComponentType<ComponentWithTooltip> COMPONENT_WITH_TOOLTIP = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            UberHardCraft.id("quality_with_tooltip"),
            DataComponentType.<ComponentWithTooltip>builder().persistent(ComponentWithTooltip.CODEC).networkSynchronized(ComponentWithTooltip.STREAM_CODEC).build()
    );

    public static void init() {

        ItemComponentTooltipProviderRegistry.addAfter(DataComponents.DAMAGE, COMPONENT_WITH_TOOLTIP);

    }

}
