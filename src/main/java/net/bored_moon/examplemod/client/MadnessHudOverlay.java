package net.bored_moon.examplemod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.bored_moon.examplemod.ExampleMod;
import net.bored_moon.examplemod.item.ModItems;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class MadnessHudOverlay {
    protected String abcde = "abc";
    int bcde = abcde.toCharArray().length;
    private static final ResourceLocation FILLED_MADNESS = new ResourceLocation(ExampleMod.MOD_ID,
            "textures/madness/madness_filled.png");
    private static final ResourceLocation EMPTY_MADNESS = new ResourceLocation(ExampleMod.MOD_ID,
            "textures/madness/madness_empty.png");


    public static final IGuiOverlay HUD_MADNESS = ((gui, poseStack, partialTick, width, height) -> {

        if (!gui.getMinecraft().options.hideGui && !gui.getMinecraft().options.renderDebug &&
                gui.getMinecraft().player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == ModItems.CROSS.get() |
                        gui.getMinecraft().player.getItemInHand(InteractionHand.OFF_HAND).getItem() == ModItems.CROSS.get()) {

            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            RenderSystem.setShaderTexture(0, EMPTY_MADNESS);

            for (int i = 0; i < 10; i++) {
                GuiComponent.blit(poseStack, 4, 214 - (i * 21), 0, 0,
                        24, 24, 24, 24);
            }

            RenderSystem.setShaderTexture(0, FILLED_MADNESS);
            for (float i = 0; i < 10; i++) {
                if ((ClientMadnessData.getPlayerMadness() / 10) - 1 > i) {
                    GuiComponent.blit(poseStack, 4, 214 - (int) (i * 21), 0, 0,
                            24, 24, 24, 24);
                } else {
                    break;
                }
            }
        }

    });
}
