package net.nikdo53.bettercrops.items;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.nikdo53.bettercrops.blockentities.GiantCropBlockEntity;
import net.nikdo53.bettercrops.blocks.GiantCropBlock;
import net.nikdo53.bettercrops.init.ModBlocks;
import net.nikdo53.bettercrops.init.ModTags;
import org.jetbrains.annotations.Nullable;
import oshi.util.tuples.Pair;

import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

public class SuperBoneMeal extends Item {
    public final Map<Block, Pair<Block, Pair<IntegerProperty, Integer>>> MAP = Map.of(
            Blocks.CARROTS, new Pair<>(ModBlocks.GIANT_CARROT.get(), new Pair<>(CropBlock.AGE, CropBlock.MAX_AGE)),
            Blocks.POTATOES, new Pair<>(ModBlocks.GIANT_POTATO.get(), new Pair<>(CropBlock.AGE, CropBlock.MAX_AGE)),
            Blocks.NETHER_WART, new Pair<>(ModBlocks.GIANT_NETHERWART.get(), new Pair<>(NetherWartBlock.AGE, NetherWartBlock.MAX_AGE)),
            Blocks.BEETROOTS, new Pair<>(ModBlocks.GIANT_BEETROOT.get(), new Pair<>(BeetrootBlock.AGE, BeetrootBlock.MAX_AGE)),
            Blocks.WHEAT, new Pair<>(ModBlocks.GIANT_WHEAT.get(), new Pair<>(CropBlock.AGE, CropBlock.MAX_AGE))
    );

    public SuperBoneMeal(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Player player = pContext.getPlayer();
        Level level = pContext.getLevel();
        BlockPos clickedPos = pContext.getClickedPos();
        BlockState clickedState = level.getBlockState(clickedPos);
        if(!clickedState.is(ModTags.ModBlockTags.CROPS_FERTIABLE_BY_FBM)) return InteractionResult.PASS;
        Block crop = clickedState.getBlock();
        Block giantVersion = MAP.get(crop).getA();
        Iterable<BlockPos> blockPosList = BlockPos.betweenClosed(
                clickedPos.getX() - 1,
                clickedPos.getY() - 0,
                clickedPos.getZ() - 1,
                clickedPos.getX() + 1,
                clickedPos.getY() + 2,
                clickedPos.getZ() + 1
        );
        boolean flag = StreamSupport.stream(blockPosList.spliterator(), false).allMatch(pos -> {
            BlockState blockState = level.getBlockState(pos);
            int cropY = clickedPos.getY();
            var PROPERTY = MAP.get(crop).getB().getA();
            int MAX_AGE = MAP.get(crop).getB().getB();

            return pos.getY() == cropY ? blockState.is(ModTags.ModBlockTags.CROPS_FERTIABLE_BY_FBM) && blockState.getValue(PROPERTY) == MAX_AGE : blockState.is(Blocks.AIR);
        });

        if (pContext.getHand() != InteractionHand.MAIN_HAND) {
            return InteractionResult.PASS;
        }

        if (flag && !level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
            return placeLogic(blockPosList, ((ServerLevel) level), giantVersion, clickedPos, serverPlayer);
        }

        return InteractionResult.PASS;
    }

    private InteractionResult placeLogic(Iterable<BlockPos> blockPosList, ServerLevel level, Block giantVersion, BlockPos clickedPos, ServerPlayer serverPlayer) {
        blockPosList.forEach(pos -> {
            pos = pos.immutable();
            level.destroyBlock(pos, false);
            level.setBlockAndUpdate(pos, giantVersion.defaultBlockState().setValue(GiantCropBlock.MODEL_POSITION, evaulateModelPos(pos, clickedPos)));

            if(level.getBlockEntity(pos) instanceof GiantCropBlockEntity entity) {
                entity.pos1 = clickedPos.mutable().move(1, 2, 1);
                entity.pos2 = clickedPos.mutable().move(-1, 0, -1);
            }
        });

//        serverPlayer.getMainHandItem().shrink(1);
//        ModAdvancementCritters.USED_BONMEEL.trigger(serverPlayer);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    private GiantCropBlock.ModelPos evaulateModelPos(BlockPos pos, BlockPos posToCompare) {
        var value = GiantCropBlock.ModelPos.NONE;

        if(pos.equals(posToCompare.mutable().move(1, 1, 0))) {
            value = GiantCropBlock.ModelPos.X;
        }
        if(pos.equals(posToCompare.mutable().move(-1, 1, 0))) {
            value = GiantCropBlock.ModelPos.IX;
        }
        if(pos.equals(posToCompare.mutable().move(0, 1, 1))) {
            value = GiantCropBlock.ModelPos.Z;
        }
        if(pos.equals(posToCompare.mutable().move(0, 1, -1))) {
            value = GiantCropBlock.ModelPos.IZ;
        }
        if(pos.equals(posToCompare.mutable().move(0, 2, 0))) {
            value = GiantCropBlock.ModelPos.Y;
        }
        if(pos.equals(posToCompare.mutable().move(0, 0, 0))) {
            value = GiantCropBlock.ModelPos.IY;
        }

        return value;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(Component.translatableWithFallback("tooltip.jar_of_bonmeel.usage", "Can be applied to a 3x3 grid of the following crops: carrot, potato, wheat, beetroot and nether wart").withStyle(ChatFormatting.GOLD));
    }
}
