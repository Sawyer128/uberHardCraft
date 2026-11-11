package place.uberhard.client.DataGenProviders;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import place.uberhard.UberHardCraft;
import place.uberhard.data.UberToolParts;

import java.util.concurrent.CompletableFuture;

public class UberItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public UberItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    public static final TagKey<Item> HANDLES = TagKey.create(Registries.ITEM, UberHardCraft.id("handles"));
    public static final TagKey<Item> ATTACHERS = TagKey.create(Registries.ITEM, UberHardCraft.id("attachers"));

    //public static final TagKey<Item> TOOL_HEADS = TagKey.create(Registries.ITEM, UberHardCraft.id("tool_heads"));

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {


        builder(HANDLES).addAll(UberToolParts.HANDLE_QUALITIES.keySet());
        builder(ATTACHERS).addAll(UberToolParts.ATTACHER_QUALITIES.keySet());

        //builder(TOOL_HEADS).addAll(UberToolParts.TOOL_HEAD_QUALITIES.keySet());
    }
}
