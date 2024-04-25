package net.bored_moon.examplemod.world.feature;

import net.bored_moon.examplemod.ExampleMod;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURE =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, ExampleMod.MOD_ID);

    public static final RegistryObject<PlacedFeature> FIRE_GEM_ORE_PLACED = PLACED_FEATURE.register("fire_gem_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.FIRE_GEM_ORE.getHolder().get(),
                    commonOrePlacement(5, //veins per chunk
                    HeightRangePlacement.triangle(
                        VerticalAnchor.absolute(-63), //borders of ore generation
                        VerticalAnchor.absolute(-60)))));
    private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int veinsAmount, PlacementModifier modifier) {
        return orePlacement(CountPlacement.of(veinsAmount), modifier);
    }

    private static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }
    public static void register(IEventBus bus){
        PLACED_FEATURE.register(bus);
    }

}
