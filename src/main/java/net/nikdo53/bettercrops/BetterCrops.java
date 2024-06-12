package net.nikdo53.bettercrops;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nikdo53.bettercrops.init.ModBlockEntities;
import net.nikdo53.bettercrops.init.ModBlocks;
import net.nikdo53.bettercrops.init.ModItems;
import net.nikdo53.bettercrops.init.ModParticles;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BetterCrops.MOD_ID)
public class BetterCrops
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "bettercrops";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public BetterCrops()
    {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
//        ModEntityTypes.ENTITIES.register(modEventBus);
//        ModMobEffects.EFFECTS.register(modEventBus);
//        ModCreativeTabs.TABS.register(modEventBus);
//        ModLootModifiers.LOOT_MODIFIERS.register(modEventBus);
//        ModBannerPatterns.BANNER_PATTERNS.register(modEventBus);
        ModParticles.PARTICLES.register(modEventBus);
//        ModRecipeTypes.RECIPE_TYPES.register(modEventBus);
//        ModRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);
//        ModMenuTypes.MENU_TYPES.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    public static ResourceLocation loc(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
