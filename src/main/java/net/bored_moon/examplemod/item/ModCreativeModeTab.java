package net.bored_moon.examplemod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab FIRE_TAB = new CreativeModeTab("fire_tab") {

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.FIRE_GEM.get());
        }
    };
}