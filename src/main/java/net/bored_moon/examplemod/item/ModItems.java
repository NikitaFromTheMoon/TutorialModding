package net.bored_moon.examplemod.item;

import net.bored_moon.examplemod.ExampleMod;
import net.bored_moon.examplemod.item.custom.ShapkaItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MOD_ID);

    public static final RegistryObject<Item> FIRE_GEM = ITEMS.register("fire_gem",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.FIRE_TAB)));

    public static final RegistryObject<Item> SHAPKA = ITEMS.register("shapka",
            () -> new ShapkaItem(ArmorMaterials.LEATHER, EquipmentSlot.HEAD,
                    new Item.Properties()
                            .tab(ModCreativeModeTab.FIRE_TAB)  .stacksTo(1)
                            .rarity(Rarity.RARE)  .durability(255)));

    public static void register(IEventBus eventBus) {

        ITEMS.register(eventBus);
    }

}
