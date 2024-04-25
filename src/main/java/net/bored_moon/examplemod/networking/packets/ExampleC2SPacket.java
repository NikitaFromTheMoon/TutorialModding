package net.bored_moon.examplemod.networking.packets;

import net.bored_moon.examplemod.item.ModItems;
import net.bored_moon.examplemod.madness.PlayerMadnessProvider;

import net.bored_moon.examplemod.networking.ModMessages;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

import net.minecraft.world.InteractionHand;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ExampleC2SPacket {
    public ExampleC2SPacket() {

    }

    public ExampleC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();


            if(player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == ModItems.CROSS.get() | player.getItemInHand(InteractionHand.OFF_HAND).getItem() == ModItems.CROSS.get()) {
                player.getCapability(PlayerMadnessProvider.PLAYER_MADNESS).ifPresent(madness -> {
                    madness.setMadness(Math.round(madness.getMadness()) - Math.round(player.getXRot()));
                    player.sendSystemMessage(Component.literal(
                            "YOUR MADNESS NOW IS FUCKING "+ Math.round(madness.getMadness()))
                            .withStyle(ChatFormatting.DARK_PURPLE));
                    ModMessages.sendClient(new MadnessDataSyncS2CPacket(madness.getMadness()), player);
                });
            }


        });
        return true;
    }
}
