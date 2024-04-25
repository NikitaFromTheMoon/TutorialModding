package net.bored_moon.examplemod.madness;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.antlr.v4.runtime.misc.NotNull;

import javax.annotation.Nullable;


public class PlayerMadnessProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerMadness> PLAYER_MADNESS = CapabilityManager.get(new CapabilityToken<PlayerMadness>() {});

    private PlayerMadness madness = null;
    private final LazyOptional<PlayerMadness> optional = LazyOptional.of(this::createPlayerMadness);

    private PlayerMadness createPlayerMadness() {
        if(this.madness == null) {
            this.madness = new PlayerMadness();
        }
        return  this.madness;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_MADNESS) {
            return optional.cast();
        }

        return LazyOptional.empty();

    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerMadness().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerMadness().loadNBTData(nbt);

    }
}
