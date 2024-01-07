package fr.azur.createtrainticketaddon.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;
import net.minecraft.client.gui.components.EditBox;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

import fr.azur.createtrainticketaddon.network.CreateTicketModVariables;
import fr.azur.createtrainticketaddon.CreateTicketMod;

import com.google.gson.JsonObject;
import com.google.gson.Gson;

public class AddtragetbuttonProcedure {
	public static void execute(LevelAccessor world, Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		double terminal_x = 0;
		double terminal_y = 0;
		double terminal_z = 0;
		com.google.gson.JsonObject json = new com.google.gson.JsonObject();
		com.google.gson.JsonObject terminal = new com.google.gson.JsonObject();
		com.google.gson.JsonObject traget = new com.google.gson.JsonObject();
		com.google.gson.JsonObject item1 = new com.google.gson.JsonObject();
		com.google.gson.JsonObject item2 = new com.google.gson.JsonObject();
		terminal = new Object() {
			public JsonObject parse(String rawJson) {
				try {
					return new Gson().fromJson(rawJson, com.google.gson.JsonObject.class);
				} catch (Exception e) {
					CreateTicketMod.LOGGER.error(e);
					return new Gson().fromJson("{}", com.google.gson.JsonObject.class);
				}
			}
		}.parse(((entity.getCapability(CreateTicketModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CreateTicketModVariables.PlayerVariables())).terminal_edit));
		terminal_x = terminal.get("x").getAsDouble();
		terminal_y = terminal.get("y").getAsDouble();
		terminal_z = terminal.get("z").getAsDouble();
		json = new Object() {
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
		}.getValue(world, new BlockPos(terminal_x, terminal_y, terminal_z), "tragets")));
		traget.addProperty("from", (guistate.containsKey("text:gare_name") ? ((EditBox) guistate.get("text:gare_name")).getValue() : ""));
		traget.addProperty("to", (guistate.containsKey("text:second_gare_name") ? ((EditBox) guistate.get("text:second_gare_name")).getValue() : ""));
		item1.addProperty("id", (ForgeRegistries.ITEMS
				.getKey((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(1)).getItem() : ItemStack.EMPTY).getItem()).toString()));
		item1.addProperty("cost", (new Object() {
			public int getAmount(int sltid) {
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
					if (stack != null)
						return stack.getCount();
				}
				return 0;
			}
		}.getAmount(0)));
		item2.addProperty("id", (ForgeRegistries.ITEMS
				.getKey((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem()).toString()));
		item2.addProperty("cost", (new Object() {
			public int getAmount(int sltid) {
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
					if (stack != null)
						return stack.getCount();
				}
				return 0;
			}
		}.getAmount(2)));
		traget.add("item_1", item1);
		traget.add("item_2", item2);
		json.add((new java.text.DecimalFormat("##").format(json.size())), traget);
		if (!world.isClientSide()) {
			BlockPos _bp = new BlockPos(terminal_x, terminal_y, terminal_z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null)
				_blockEntity.getTileData().putString("tragets", json.toString());
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		if (entity instanceof Player _player)
			_player.closeContainer();
	}
}
