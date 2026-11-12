package place.uberhard.component;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;

import java.util.function.Consumer;

public record ComponentWithTooltip(int quality) implements TooltipProvider {

    public static final Codec<ComponentWithTooltip> CODEC = ExtraCodecs.NON_NEGATIVE_INT.xmap(ComponentWithTooltip::new, ComponentWithTooltip::quality);
    public static final StreamCodec<ByteBuf, ComponentWithTooltip> STREAM_CODEC = ByteBufCodecs.VAR_INT.map(ComponentWithTooltip::new, ComponentWithTooltip::quality);

    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter components) {
        consumer.accept(Component.translatable("item.uberhardcraft.toolinfo", this.quality).withStyle(ChatFormatting.GOLD));
    }
}

