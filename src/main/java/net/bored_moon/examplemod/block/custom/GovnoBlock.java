package net.bored_moon.examplemod.block.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class GovnoBlock extends Block {
    public GovnoBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if(entity instanceof LivingEntity livingEntity) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 77));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 77));
        }
        super.stepOn(level, pos, state, entity);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable BlockGetter blockGetter, List<Component> components, TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.literal("It smells awful, i never gonna step on it... Or touch it somehow else").withStyle(ChatFormatting.GRAY));
        } else {
            components.add(Component.literal("Press shift to see a tooltip!").withStyle(ChatFormatting.YELLOW));
        }

        super.appendHoverText(itemStack, blockGetter, components, tooltipFlag);
    }

    @Override
    public boolean isFertile(BlockState state, BlockGetter level, BlockPos pos) {
        return super.isFertile(state, level, pos);
    }
}
