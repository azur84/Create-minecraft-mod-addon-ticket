package fr.azur.createtrainticketaddon.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.client.gui.components.EditBox;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

import fr.azur.createtrainticketaddon.network.CreateTicketModVariables;
import fr.azur.createtrainticketaddon.CreateTicketMod;

import com.google.gson.JsonObject;
import com.google.gson.Gson;

public class TrainticketterminalguitickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		String search = "";
		double re = 0;
		com.google.gson.JsonObject tragets = new com.google.gson.JsonObject();
		com.google.gson.JsonObject tragets_target = new com.google.gson.JsonObject();
		com.google.gson.JsonObject curentjson = new com.google.gson.JsonObject();
		com.google.gson.JsonObject tragets_visible = new com.google.gson.JsonObject();
		com.google.gson.JsonObject currentitem = new com.google.gson.JsonObject();
		if (!(guistate.containsKey("text:search") ? ((EditBox) guistate.get("text:search")).getValue() : "")
				.equals((entity.getCapability(CreateTicketModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CreateTicketModVariables.PlayerVariables())).terminal_search)) {
			{
				String _setval = guistate.containsKey("text:search") ? ((EditBox) guistate.get("text:search")).getValue() : "";
				entity.getCapability(CreateTicketModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.terminal_edit = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			search = guistate.containsKey("text:search") ? ((EditBox) guistate.get("text:search")).getValue() : "";
			{
				double _setval = 0;
				entity.getCapability(CreateTicketModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.terminal_page = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			tragets = new Object() {
				public JsonObject parse(String rawJson) {
					try {
						return new Gson().fromJson(rawJson, com.google.gson.JsonObject.class);
					} catch (Exception e) {
						CreateTicketMod.LOGGER.error(e);
						return new Gson().fromJson("{}", com.google.gson.JsonObject.class);
					}
				}
			}.parse((new Object() {
				public String getValue(LevelAccessor world, BlockPos pos, String tag) {
					BlockEntity blockEntity = world.getBlockEntity(pos);
					if (blockEntity != null)
						return blockEntity.getTileData().getString(tag);
					return "";
				}
			}.getValue(world, new BlockPos(x, y, z), "tragets")));
			re = 0;
			for (int index0 = 0; index0 < (int) tragets.size(); index0++) {
				curentjson = tragets.get((new java.text.DecimalFormat("##").format(re))).getAsJsonObject();
				if (curentjson.get("from").getAsString().startsWith(search) || curentjson.get("to").getAsString().startsWith(search)) {
					tragets_target.add((new java.text.DecimalFormat("##").format(tragets_target.size())), curentjson);
				}
				re = re + 1;
			}
			{
				String _setval = tragets_target.toString();
				entity.getCapability(CreateTicketModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.terminal_traget_list = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			re = -1;
			for (int index1 = 0; index1 < (int) tragets_target.size(); index1++) {
				re = re + 1;
				if ((entity.getCapability(CreateTicketModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CreateTicketModVariables.PlayerVariables())).terminal_page * 4 > re) {
					continue;
				}
				if ((entity.getCapability(CreateTicketModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CreateTicketModVariables.PlayerVariables())).terminal_page * 4 + 4 <= re) {
					break;
				}
				curentjson = tragets_target.get((new java.text.DecimalFormat("##").format(re))).getAsJsonObject();
				tragets_visible.add((new java.text.DecimalFormat("##").format(tragets_visible.size())), curentjson);
			}
			{
				String _setval = tragets_visible.toString();
				entity.getCapability(CreateTicketModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.terminal_visible_list = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			re = -1;
			for (int index2 = 0; index2 < (int) tragets_visible.size(); index2++) {
				re = re + 1;
				curentjson = tragets_visible.get((new java.text.DecimalFormat("##").format(re))).getAsJsonObject();
				currentitem = curentjson.get("item_1").getAsJsonObject();
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack _setstack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation((currentitem.get("id").getAsString()).toLowerCase(java.util.Locale.ENGLISH))));
					_setstack.setCount((int) currentitem.get("cost").getAsDouble());
					((Slot) _slots.get((int) (2 * re))).set(_setstack);
					_player.containerMenu.broadcastChanges();
				}
				currentitem = curentjson.get("item_2").getAsJsonObject();
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack _setstack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation((currentitem.get("id").getAsString()).toLowerCase(java.util.Locale.ENGLISH))));
					_setstack.setCount((int) currentitem.get("cost").getAsDouble());
					((Slot) _slots.get((int) (2 * re + 1))).set(_setstack);
					_player.containerMenu.broadcastChanges();
				}
			}
		}
	}
}
