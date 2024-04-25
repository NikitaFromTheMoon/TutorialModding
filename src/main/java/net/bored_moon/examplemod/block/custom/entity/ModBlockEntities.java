package net.bored_moon.examplemod.block.custom.entity;

//import com.google.common.eventbus.EventBus;
import net.bored_moon.examplemod.ExampleMod;
import net.bored_moon.examplemod.block.ModBlocks;
import net.bored_moon.examplemod.block.custom.IdolBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ExampleMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<IdolBlockEntity>> IDOL_BLOCK =
            BLOCK_ENTITIES.register("idol_block", () ->
                    BlockEntityType.Builder.of(IdolBlockEntity::new,
                            ModBlocks.YREMON_IDOL.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
