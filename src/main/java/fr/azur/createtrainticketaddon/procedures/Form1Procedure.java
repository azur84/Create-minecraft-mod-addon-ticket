package fr.azur.createtrainticketaddon.procedures;

import net.minecraft.world.entity.Entity;

import fr.azur.createtrainticketaddon.network.CreateTicketModVariables;
import fr.azur.createtrainticketaddon.CreateTicketMod;

import com.google.gson.JsonObject;
import com.google.gson.Gson;

public class Form1Procedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		com.google.gson.JsonObject currentjson = new com.google.gson.JsonObject();
		currentjson = new Object() {
			public JsonObject parse(String rawJson) {
				try {
					return new Gson().fromJson(rawJson, com.google.gson.JsonObject.class);
				} catch (Exception e) {
					CreateTicketMod.LOGGER.error(e);
					return new Gson().fromJson("{}", com.google.gson.JsonObject.class);
				}
			}
		}.parse(((entity.getCapability(CreateTicketModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CreateTicketModVariables.PlayerVariables())).terminal_visible_list));
		currentjson = currentjson.get("0").getAsJsonObject();
		return currentjson.get("from").getAsString();
	}
}
