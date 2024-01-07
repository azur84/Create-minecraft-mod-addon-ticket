
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.azur.createtrainticketaddon.init;

import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.AbstractContainerMenu;

import java.util.List;
import java.util.ArrayList;

import fr.azur.createtrainticketaddon.world.inventory.TrainticketterminalguiMenu;
import fr.azur.createtrainticketaddon.world.inventory.AddtragetMenu;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreateTicketModMenus {
	private static final List<MenuType<?>> REGISTRY = new ArrayList<>();
	public static final MenuType<TrainticketterminalguiMenu> TRAINTICKETTERMINALGUI = register("trainticketterminalgui", (id, inv, extraData) -> new TrainticketterminalguiMenu(id, inv, extraData));
	public static final MenuType<AddtragetMenu> ADDTRAGET = register("addtraget", (id, inv, extraData) -> new AddtragetMenu(id, inv, extraData));

	private static <T extends AbstractContainerMenu> MenuType<T> register(String registryname, IContainerFactory<T> containerFactory) {
		MenuType<T> menuType = new MenuType<T>(containerFactory);
		menuType.setRegistryName(registryname);
		REGISTRY.add(menuType);
		return menuType;
	}

	@SubscribeEvent
	public static void registerContainers(RegistryEvent.Register<MenuType<?>> event) {
		event.getRegistry().registerAll(REGISTRY.toArray(new MenuType[0]));
	}
}
