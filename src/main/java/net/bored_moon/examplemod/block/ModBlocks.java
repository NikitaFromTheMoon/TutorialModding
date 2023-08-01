package net.bored_moon.examplemod.block;

import net.bored_moon.examplemod.ExampleMod;
import net.bored_moon.examplemod.block.custom.FireBerryCropBlock;
import net.bored_moon.examplemod.block.custom.FireGemBlock;
import net.bored_moon.examplemod.block.custom.GovnoBlock;
import net.bored_moon.examplemod.item.ModCreativeModeTab;
import net.bored_moon.examplemod.item.ModItems;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ExampleMod.MOD_ID);

    public static final RegistryObject<Block> FIRE_GEM_BLOCK = registerBlock("fire_gem_block",
            () -> new FireGemBlock(BlockBehaviour.Properties.of(Material.AMETHYST)
                    .strength(20).requiresCorrectToolForDrops().
                    lightLevel(state -> state.getValue(FireGemBlock.LIT) ? 15 : 3)), ModCreativeModeTab.FIRE_TAB);

    public static final RegistryObject<Block> FIRE_GEM_ORE = registerBlock("fire_gem_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(15).requiresCorrectToolForDrops().explosionResistance(10000),
                    UniformInt.of(3, 7)), ModCreativeModeTab.FIRE_TAB);

    public static final RegistryObject<Block> GOVNO = registerBlock("govno",
            () -> new GovnoBlock(BlockBehaviour.Properties.of(Material.DIRT)
                    .strength(1)), ModCreativeModeTab.FIRE_TAB);

    public static final RegistryObject<Block> FIREBERRY_BUSH = BLOCKS.register("fireberry_bush",
            () -> new FireBerryCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));

    //Block and block item registration methods
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {

        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));

    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
