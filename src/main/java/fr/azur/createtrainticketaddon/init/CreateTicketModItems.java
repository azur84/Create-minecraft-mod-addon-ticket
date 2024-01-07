
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.BlockItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.item.ItemProperties;

import fr.azur.createtrainticketaddon.procedures.TicketFournisseurDeLaValeurDeLaProprieteProcedure;
import fr.azur.createtrainticketaddon.item.TicketItem;
import fr.azur.createtrainticketaddon.CreateTicketMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CreateTicketModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, CreateTicketMod.MODID);
	public static final RegistryObject<Item> TRAINTICKETTERMINALHITBOX = block(CreateTicketModBlocks.TRAINTICKETTERMINALHITBOX, null);
	public static final RegistryObject<Item> TRAINTICKETTERMINAL = block(CreateTicketModBlocks.TRAINTICKETTERMINAL, CreateTicketModTabs.TAB_CREATE_TICKET);
	public static final RegistryObject<Item> TICKET = REGISTRY.register("ticket", () -> new TicketItem());

	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			ItemProperties.register(TICKET.get(), new ResourceLocation("create_ticket:ticket_color"), (itemStackToRender, clientWorld, entity, itemEntityId) -> (float) TicketFournisseurDeLaValeurDeLaProprieteProcedure.execute(itemStackToRender));
		});
	}

	private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}
}
