package net.bored_moon.examplemod.madness;

import net.minecraft.nbt.CompoundTag;

public class PlayerMadness {
    private float madness;
    private final float MIN_MADNESS = 0f;
    private final float MAX_MADNESS = 100f;

    public float getMadness() {
        return madness;
    }
//    public void addMadness(float add) {
//        this.madness = Math.min(madness + add, MAX_MADNESS);
//    }
//    public void subMadness(float sub) {
//        this.madness = Math.max(madness - sub, MIN_MADNESS);
//    }
//    public void multMadness(float mult) {
//        this.madness = Math.min(madness * mult, MIN_MADNESS);
//    }
//    public void divMadness(float div) {
//        this.madness = Math.max(madness * div, MIN_MADNESS);
//    }
    public void setMadness(float newMadness) {
        if(newMadness > MIN_MADNESS) {
            this.madness = Math.min(newMadness, MAX_MADNESS);
        } else {this.madness = MIN_MADNESS;}
    }
    public void copyFrom(PlayerMadness source) {
        this.madness = source.madness;
    }
    public void saveNBTData(CompoundTag nbt) {
        nbt.putFloat("madness", madness);
    }

    public void loadNBTData(CompoundTag nbt) {
        madness = nbt.getFloat("madness");
    }
}
