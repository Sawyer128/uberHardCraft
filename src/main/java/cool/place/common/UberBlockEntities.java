package cool.place.common;

import cool.place.UberHardCraft;
import cool.place.blockentity.CrucibleBlockEntity;
import cool.place.blockentity.KilnFireBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class UberBlockEntities {

    private static <T extends BlockEntity> BlockEntityType<T> register(
            String name,
            FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
            Block... blocks
    ) {
        Identifier id = Identifier.fromNamespaceAndPath(UberHardCraft.MOD_ID, name);
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
    }

    public static final BlockEntityType<CrucibleBlockEntity> FIRED_CRUCIBLE_ENTITY =
            register("crucible", CrucibleBlockEntity::new, UberBlocks.FIRED_CRUCIBLE);

    public static final BlockEntityType<KilnFireBlockEntity> KILN_FIRE_ENTITY =
            register("kiln_fire", KilnFireBlockEntity::new, UberBlocks.KILN_FIRE);


    public static void initialize() {
    }

}
