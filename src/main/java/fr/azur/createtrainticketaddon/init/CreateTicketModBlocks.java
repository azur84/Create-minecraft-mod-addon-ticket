
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.azur.createtrainticketaddon.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;

import fr.azur.createtrainticketaddon.block.TrainticketterminalhitboxBlock;
import fr.azur.createtrainticketaddon.block.TrainticketterminalBlock;
import fr.azur.createtrainticketaddon.CreateTicketMod;

public class CreateTicketModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, CreateTicketMod.MODID);
	public static final RegistryObject<Block> TRAINTICKETTERMINALHITBOX = REGISTRY.register("trainticketterminalhitbox", () -> new TrainticketterminalhitboxBlock());
	public static final RegistryObject<Block> TRAINTICKETTERMINAL = REGISTRY.register("trainticketterminal", () -> new TrainticketterminalBlock());

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientSideHandler {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
			TrainticketterminalhitboxBlock.registerRenderLayer();
			TrainticketterminalBlock.registerRenderLayer();
		}
	}
}
