package place.uberhard.client.DataGenProviders;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import place.uberhard.reg.UberItems;

public class UberModelProvider extends FabricModelProvider {

    public UberModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(UberItems.COPPER_ROD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(UberItems.COPPER_HANDLE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(UberItems.GOLD_ROD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(UberItems.GOLD_HANDLE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(UberItems.IRON_ROD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(UberItems.IRON_HANDLE, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(UberItems.FLINT_PICKHEAD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(UberItems.BARK_FIBER, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(UberItems.FLINT_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
    }

    @Override
    public String getName() {
        return "UberModelProvider";
    }


}
