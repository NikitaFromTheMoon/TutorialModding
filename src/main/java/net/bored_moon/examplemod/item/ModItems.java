package net.bored_moon.examplemod.item;

import net.bored_moon.examplemod.ExampleMod;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MOD_ID);

    public static final RegistryObject<Item> FIRE_GEM = ITEMS.register("fire_gem",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC )));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
