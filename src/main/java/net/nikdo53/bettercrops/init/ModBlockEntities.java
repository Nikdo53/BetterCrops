package net.nikdo53.bettercrops.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nikdo53.bettercrops.BetterCrops;
import net.nikdo53.bettercrops.blockentities.GiantCropBlockEntity;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BetterCrops.MOD_ID);

    public static final RegistryObject<BlockEntityType<GiantCropBlockEntity>> GIANT_CROP = BLOCK_ENTITIES.register("giant_crop", () -> BlockEntityType.Builder.of(GiantCropBlockEntity::new, ModBlocks.GIANT_CARROT.get(), ModBlocks.GIANT_POTATO.get(), ModBlocks.GIANT_NETHERWART.get(), ModBlocks.GIANT_BEETROOT.get(), ModBlocks.GIANT_WHEAT.get()).build(null));
}
