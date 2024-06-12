package net.nikdo53.bettercrops.init;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nikdo53.bettercrops.BetterCrops;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, BetterCrops.MOD_ID);

    public static final RegistryObject<SimpleParticleType> GIANT_CROP = PARTICLES.register("giant_crop", () -> new SimpleParticleType(false));
}
