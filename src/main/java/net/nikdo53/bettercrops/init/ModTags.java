package net.nikdo53.bettercrops.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.nikdo53.bettercrops.BetterCrops;

public class ModTags {
    public static class ModBlockTags {
        public static final TagKey<Block> GIANT_CROPS = create(Registries.BLOCK, "giant_crops");
        public static final TagKey<Block> CROPS_FERTIABLE_BY_FBM = create(Registries.BLOCK, "crops_fertiable_by_fbm");

    }
    private static <T extends Object> TagKey<T> create(ResourceKey<Registry<T>> registry, String name){
        return TagKey.create(registry, BetterCrops.loc(name));
    }
}
