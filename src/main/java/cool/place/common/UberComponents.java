package cool.place.common;

import cool.place.UberHardCraft;
import cool.place.component.CrucibleData;
import net.minecraft.advancements.triggers.SimpleCriterionTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class UberComponents {

    public static final DataComponentType<CrucibleData> CRUCIBLE_DATA = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(UberHardCraft.MOD_ID, "crucible_data"),
            DataComponentType.<CrucibleData>builder().persistent(CrucibleData.CODEC).build()
    );

    public static void initialize() { }
}
