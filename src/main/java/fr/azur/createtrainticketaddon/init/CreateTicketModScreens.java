
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.azur.createtrainticketaddon.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import fr.azur.createtrainticketaddon.client.gui.TrainticketterminalguiScreen;
import fr.azur.createtrainticketaddon.client.gui.AddtragetScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CreateTicketModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(CreateTicketModMenus.TRAINTICKETTERMINALGUI, TrainticketterminalguiScreen::new);
			MenuScreens.register(CreateTicketModMenus.ADDTRAGET, AddtragetScreen::new);
		});
	}
}
