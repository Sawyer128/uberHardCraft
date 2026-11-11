package place.uberhard.client.DataGenProviders;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import place.uberhard.items.FlintTool;

import java.util.concurrent.CompletableFuture;

public class UberBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
    public UberBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {

        builder(FlintTool.INCORRECT_FOR_FLINT_TOOL)
                .forceAddTag(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .removeTag(BlockTags.COPPER_ORES);

        //builder(TOOL_HEADS).addAll(UberToolParts.TOOL_HEAD_QUALITIES.keySet());
    }
}
