
package fr.azur.createtrainticketaddon.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;

public class TicketItem extends Item {
	public TicketItem() {
		super(new Item.Properties().tab(CreativeModeTab.TAB_TRANSPORTATION).stacksTo(64).rarity(Rarity.COMMON));
	}
}
