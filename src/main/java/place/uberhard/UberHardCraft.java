package place.uberhard;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.ItemComponentTooltipProviderRegistry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import place.uberhard.component.ComponentWithTooltip;
import place.uberhard.reg.UberComponents;
import place.uberhard.reg.UberItemIds;
import place.uberhard.reg.UberItems;
import place.uberhard.reg.UberRecipes;

public class UberHardCraft implements ModInitializer {
	public static final String MOD_ID = "uberhardcraft";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		UberItemIds.init();
		UberItems.init();
		UberComponents.init();
		UberRecipes.init();

		//LOGGER.info("Hello Fabric world!");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
