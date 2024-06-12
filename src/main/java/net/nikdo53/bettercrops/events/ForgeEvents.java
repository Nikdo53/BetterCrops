package net.nikdo53.bettercrops.events;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nikdo53.bettercrops.BetterCrops;
import net.nikdo53.bettercrops.init.ModTags;
import net.nikdo53.bettercrops.blockentities.GiantCropBlockEntity;
import net.nikdo53.bettercrops.items.SuperBoneMeal;

@Mod.EventBusSubscriber(modid = BetterCrops.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeEvents {
    @SubscribeEvent
    public static void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        var item = event.getEntity().getItemInHand(event.getHand()).getItem();
        var isItem = item instanceof SuperBoneMeal;
        var isBlock = event.getLevel().getBlockState(event.getPos()).is(ModTags.ModBlockTags.CROPS_FERTIABLE_BY_FBM);

        if(isItem && isBlock) {
            var context = new UseOnContext(event.getLevel(), event.getEntity(), event.getHand(), event.getItemStack(), event.getHitVec());

            event.setCanceled(true);
            ((SuperBoneMeal) item).useOn(context);
        }
    }
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {

        if(event.getLevel().getBlockEntity(event.getPos()) instanceof GiantCropBlockEntity entity) {
            BlockPos.betweenClosed(entity.pos1, entity.pos2).forEach(blockPos -> {
                event.getLevel().destroyBlock(blockPos, true);
            });
        }
    }
}
