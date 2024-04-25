package net.bored_moon.examplemod.item;

import net.bored_moon.examplemod.ExampleMod;
import net.bored_moon.examplemod.block.ModBlocks;
import net.bored_moon.examplemod.fluid.ModFluids;
import net.bored_moon.examplemod.item.custom.ShapkaItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
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

    public static final RegistryObject<Item> CROSS = ITEMS.register("cross",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.FIRE_TAB)));

    public static final RegistryObject<Item> CHAOS_BLADE = ITEMS.register("chaos_blade",
            () -> new SwordItem(Tiers.NETHERITE, 2, -3.2f, new Item.Properties()
                    .tab(ModCreativeModeTab.FIRE_TAB).stacksTo(1).durability(1023)));

    public static final RegistryObject<Item> KVAS = ITEMS.register("kvass",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.FIRE_TAB)
                    .tab(ModCreativeModeTab.FIRE_TAB)
                    .food(new FoodProperties.Builder().nutrition(8).saturationMod(8.0f).build())));

    public static final RegistryObject<Item> FIREBERRIES = ITEMS.register("fireberries",
            () -> new ItemNameBlockItem(ModBlocks.FIREBERRY_BUSH.get(), new Item.Properties()
                    .tab(ModCreativeModeTab.FIRE_TAB)
                    .food(new FoodProperties.Builder().nutrition(3).saturationMod(2.0f).build() ) ) );

    public static final RegistryObject<Item> SOAP_WATER_BUCKET = ITEMS.register("soap_water_bucket",
            () -> new BucketItem(ModFluids.SOURCE_SOAP_WATER, new Item.Properties()
                    .tab(ModCreativeModeTab.FIRE_TAB).stacksTo(1).craftRemainder(Items.BUCKET)));

    public static final RegistryObject<Item> SHAPKA = ITEMS.register("shapka",
            () -> new ShapkaItem(ArmorMaterials.LEATHER, EquipmentSlot.HEAD,
                    new Item.Properties()
                            .tab(ModCreativeModeTab.FIRE_TAB)  .stacksTo(1)
                             .rarity(Rarity.RARE)  .durability(255)));

    public static void register(IEventBus eventBus) {

        ITEMS.register(eventBus);
    }

}
