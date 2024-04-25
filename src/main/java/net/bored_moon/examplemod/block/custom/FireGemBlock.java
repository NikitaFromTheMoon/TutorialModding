package net.bored_moon.examplemod.block.custom;

import net.bored_moon.examplemod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;


import javax.annotation.Nullable;

public class FireGemBlock extends Block {

    public static final BooleanProperty LIT = BooleanProperty.create("lit");

    public FireGemBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos blockPos, BlockState state,
                            @Nullable LivingEntity entity, ItemStack stack) {
        if(entity.getType() == EntityType.PLAYER) {
            level.setBlock(blockPos, state.cycle(LIT), 3);
        }
        //super.setPlacedBy(level, blockPos, state, entity, stack);
    }

    @Override
    public InteractionResult use(BlockState state, Level level,
                                 BlockPos blockPos, Player player,
                                 InteractionHand hand, BlockHitResult blockHitResult) {
        if (!level.isClientSide && hand == InteractionHand.MAIN_HAND &&
                player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == ModItems.FIRE_GEM.get()) {
            level.setBlock(blockPos, state.cycle(LIT), 3);

        }

        return super.use(state, level, blockPos, player, hand, blockHitResult);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {

        builder.add(LIT);
    }
}
