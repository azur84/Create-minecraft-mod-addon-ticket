package fr.azur.createtrainticketaddon.procedures;

import net.minecraft.world.entity.Entity;

import fr.azur.createtrainticketaddon.network.CreateTicketModVariables;

public class VisibleupProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return (entity.getCapability(CreateTicketModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CreateTicketModVariables.PlayerVariables())).terminal_page < (entity.getCapability(CreateTicketModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new CreateTicketModVariables.PlayerVariables())).terminal_max_page;
	}
}
