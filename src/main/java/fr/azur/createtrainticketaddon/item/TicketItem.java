
package fr.azur.createtrainticketaddon.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

import fr.azur.createtrainticketaddon.init.CreateTicketModTabs;

public class TicketItem extends Item {
	public TicketItem() {
		super(new Item.Properties().tab(CreateTicketModTabs.TAB_CREATE_TICKET).stacksTo(64).rarity(Rarity.COMMON));
	}
}
