package net.nikdo53.bettercrops.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nikdo53.bettercrops.BetterCrops;
import net.nikdo53.bettercrops.blocks.GiantCropBlock;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, BetterCrops.MOD_ID);

    public static final RegistryObject<Block> GIANT_CARROT = registerBlockNoItem("giant_carrot", () ->  new GiantCropBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.BANJO).strength(3.0F).sound(SoundType.MOSS_CARPET).noOcclusion().pushReaction(PushReaction.BLOCK)));
    public static final RegistryObject<Block> GIANT_POTATO = registerBlockNoItem("giant_potato", () ->  new GiantCropBlock(BlockBehaviour.Properties.copy(ModBlocks.GIANT_CARROT.get()).noOcclusion()));
    public static final RegistryObject<Block> GIANT_NETHERWART = registerBlockNoItem("giant_netherwart", () ->  new GiantCropBlock(BlockBehaviour.Properties.copy(ModBlocks.GIANT_CARROT.get()).noOcclusion()));
    public static final RegistryObject<Block> GIANT_BEETROOT = registerBlockNoItem("giant_beetroot", () ->  new GiantCropBlock(BlockBehaviour.Properties.copy(ModBlocks.GIANT_CARROT.get()).noOcclusion()));
    public static final RegistryObject<Block> GIANT_WHEAT = registerBlockNoItem("giant_wheat", () ->  new GiantCropBlock(BlockBehaviour.Properties.copy(ModBlocks.GIANT_CARROT.get()).noOcclusion()));

    private static <T extends Block> RegistryObject<T> registerBlockNoItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> registerBlockWithItem(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties()));
    }


}
