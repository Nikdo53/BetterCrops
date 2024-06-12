package net.nikdo53.bettercrops.init;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nikdo53.bettercrops.BetterCrops;
import net.nikdo53.bettercrops.items.SuperBoneMeal;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS, BetterCrops.MOD_ID);

    public static final RegistryObject<Item> SUPER_BONE_MEAL = ITEMS.register("super_bone_meal", () -> new SuperBoneMeal(new Item.Properties()));

}
