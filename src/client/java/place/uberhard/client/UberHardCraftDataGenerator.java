package place.uberhard.client;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import place.uberhard.client.DataGenProviders.UberEnglishLangProvider;
import place.uberhard.client.DataGenProviders.UberItemTagProvider;
import place.uberhard.client.DataGenProviders.UberModelProvider;
import place.uberhard.client.DataGenProviders.UberRecipeProvider;

public class UberHardCraftDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {

		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(UberModelProvider::new);

		pack.addProvider(UberEnglishLangProvider::new);

		pack.addProvider(UberItemTagProvider::new);

		pack.addProvider(UberRecipeProvider::new);

	}
}
