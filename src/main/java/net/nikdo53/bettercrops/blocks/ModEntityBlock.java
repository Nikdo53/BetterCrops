package net.nikdo53.bettercrops.blocks;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.nikdo53.bettercrops.blockentities.ModBlockEntity;
import org.jetbrains.annotations.Nullable;

public interface ModEntityBlock extends EntityBlock {
    @Nullable
     default <T extends BlockEntity> BlockEntityTicker<T> tickerHelper(Level pLevel) {
        if(pLevel.isClientSide) return null;
        return (pLevel1, pPos, pState1, pBlockEntity) -> ((ModBlockEntity) pBlockEntity).tick(pLevel);
    }
}
