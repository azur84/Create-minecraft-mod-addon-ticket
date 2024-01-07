package fr.azur.createtrainticketaddon.procedures;

import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.MenuProvider;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import io.netty.buffer.Unpooled;

import fr.azur.createtrainticketaddon.world.inventory.AddtragetMenu;
import fr.azur.createtrainticketaddon.network.CreateTicketModVariables;

public class ButtonsettingterminalProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		com.google.gson.JsonObject json = new com.google.gson.JsonObject();
		json.addProperty("y", x);
		json.addProperty("z", y);
		json.addProperty("x", z);
		{
			String _setval = json.toString();
			entity.getCapability(CreateTicketModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.terminal_edit = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			if (entity instanceof ServerPlayer _ent) {
				BlockPos _bpos = new BlockPos(x, y, z);
				NetworkHooks.openGui((ServerPlayer) _ent, new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return new TextComponent("Addtraget");
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						return new AddtragetMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
					}
				}, _bpos);
			}
		}
	}
}
