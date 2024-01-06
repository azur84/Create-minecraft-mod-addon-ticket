
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.azur.createtrainticketaddon.init;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;

public class CreateTicketModTabs {
	public static CreativeModeTab TAB_CREATE_TICKET;

	public static void load() {
		TAB_CREATE_TICKET = new CreativeModeTab("tabcreate_ticket") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(CreateTicketModBlocks.TRAINTICKETTERMINAL.get());
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
}
