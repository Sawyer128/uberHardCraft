package place.uberhard.client.DataGenProviders;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemIds;
import net.minecraft.references.ItemIds;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import place.uberhard.UberHardCraft;
import place.uberhard.reg.UberItemIds;

import javax.swing.text.html.HTML;
import java.util.concurrent.CompletableFuture;

public class UberItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public UberItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    public static final TagKey<Item> HANDLES = TagKey.create(Registries.ITEM, UberHardCraft.id("handles"));

    public static final TagKey<Item> ATTACHERS = TagKey.create(Registries.ITEM, UberHardCraft.id("attachers"));

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {

        builder(HANDLES)
                .add(ItemIds.STICK);

        builder(ATTACHERS)
                .add(BlockItemIds.TRIPWIRE)
                .add(ItemIds.SLIME_BALL)
                .add(UberItemIds.BARK_FIBER);
    }
}
