package net.bored_moon.examplemod.villager;

import com.google.common.collect.ImmutableSet;
import com.ibm.icu.impl.locale.XCldrStub;
import net.bored_moon.examplemod.ExampleMod;
import net.bored_moon.examplemod.block.ModBlocks;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.sound.midi.Soundbank;
import java.lang.reflect.InvocationTargetException;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, ExampleMod.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, ExampleMod.MOD_ID);

    public static final RegistryObject<PoiType> FIRE_GEM_BLOCK_POI = POI_TYPES.register("fire_gem_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.FIRE_GEM_BLOCK.get().getStateDefinition().getPossibleStates()),
                    2, 2));

    public static final RegistryObject<VillagerProfession> FIRE_RUNE_MAKER = VILLAGER_PROFESSIONS.register(
            "fire_rune_maker", () -> new VillagerProfession(
                    "fire_rune_maker", x -> x.get() == FIRE_GEM_BLOCK_POI.get(),
                    x -> x.get() == FIRE_GEM_BLOCK_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_MASON));
    public static void registerPOIs() {

        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class,
                    "registerBlockstates", PoiType.class).invoke(null, FIRE_GEM_BLOCK_POI.get());
        }
        catch (InvocationTargetException | IllegalAccessException exception) {
                exception.printStackTrace();
            }
    }


    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
