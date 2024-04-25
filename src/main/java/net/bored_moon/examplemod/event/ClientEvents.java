package net.bored_moon.examplemod.event;


import com.mojang.datafixers.util.Function3;
import net.bored_moon.examplemod.ExampleMod;
import net.bored_moon.examplemod.client.MadnessHudOverlay;
import net.bored_moon.examplemod.networking.ModMessages;
import net.bored_moon.examplemod.networking.packets.ExampleC2SPacket;
import net.bored_moon.examplemod.util.Keybinding;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if(Keybinding.MADNESS_KEY.consumeClick()) {
                //Minecraft.getInstance().player.sendSystemMessage(Component.literal("Your madness level is "));
                ModMessages.sendServer(new ExampleC2SPacket());
            }
        }
    }

    @Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(Keybinding.MADNESS_KEY);
        }

        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("madness", MadnessHudOverlay.HUD_MADNESS);


        }
    }

}
