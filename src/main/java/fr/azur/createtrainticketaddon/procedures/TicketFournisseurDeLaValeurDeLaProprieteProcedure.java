package fr.azur.createtrainticketaddon.procedures;

import net.minecraft.world.item.ItemStack;

public class TicketFournisseurDeLaValeurDeLaProprieteProcedure {
	public static double execute(ItemStack itemstack) {
		return itemstack.getOrCreateTag().getDouble("color");
	}
}
