package place.uberhard.client.DataGenProviders;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import place.uberhard.UberHardCraft;
import place.uberhard.reg.UberItemIds;

import java.util.concurrent.CompletableFuture;

public class UberEnglishLangProvider extends FabricLanguageProvider {

    public UberEnglishLangProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider holderLookup, TranslationBuilder translationBuilder) {

        String item = "item." + UberHardCraft.MOD_ID + ".";

        translationBuilder.add(item + UberItemIds.FLINT_PICKHEAD.identifier().getPath(), "Flint Pickaxe Head");
        translationBuilder.add(item + UberItemIds.BARK_FIBER.identifier().getPath(), "Bark Fiber");
        translationBuilder.add(item + UberItemIds.FLINT_PICKAXE.identifier().getPath(), "Flint Pickaxe");
    }
}
