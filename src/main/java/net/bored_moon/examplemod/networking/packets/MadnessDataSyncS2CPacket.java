package net.bored_moon.examplemod.networking.packets;

import net.bored_moon.examplemod.client.ClientMadnessData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MadnessDataSyncS2CPacket {
    private final float madness;

    public MadnessDataSyncS2CPacket(float madness) {
        this.madness = madness;
    }

    public MadnessDataSyncS2CPacket(FriendlyByteBuf buf) {
        this.madness = buf.readFloat();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFloat(madness);
    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {

            ClientMadnessData.set(madness);


        });
        return true;
    }
}
