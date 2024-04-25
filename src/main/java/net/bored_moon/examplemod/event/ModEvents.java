package net.bored_moon.examplemod.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.bored_moon.examplemod.ExampleMod;
import net.bored_moon.examplemod.block.ModBlocks;
import net.bored_moon.examplemod.madness.PlayerMadness;
import net.bored_moon.examplemod.madness.PlayerMadnessProvider;
import net.bored_moon.examplemod.networking.ModMessages;
import net.bored_moon.examplemod.networking.packets.MadnessDataSyncS2CPacket;
import net.bored_moon.examplemod.villager.ModVillagers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void addCustomtrades(VillagerTradesEvent event) {

        if(event.getType() == ModVillagers.FIRE_RUNE_MAKER.get()) {

            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            ItemStack stack = new ItemStack(ModBlocks.GOVNO.get().asItem(), 13);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    stack, 4, 4, 0.02f));
        }
    }
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player){
            if(!event.getObject().getCapability(PlayerMadnessProvider.PLAYER_MADNESS).isPresent()) {
                event.addCapability(new ResourceLocation(ExampleMod.MOD_ID, "properties"), new PlayerMadnessProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onRegisterCapability(RegisterCapabilitiesEvent event) {
        event.register(PlayerMadness.class);
    }

    @SubscribeEvent
    public static void  onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerMadnessProvider.PLAYER_MADNESS).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerMadnessProvider.PLAYER_MADNESS).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }
    @SubscribeEvent
    public static void  onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if(event.getLevel().isClientSide()) {
            if(event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(PlayerMadnessProvider.PLAYER_MADNESS).ifPresent(madness -> {
                    ModMessages.sendClient(new MadnessDataSyncS2CPacket(madness.getMadness()), player);
                });


            }
        }
    }
}
