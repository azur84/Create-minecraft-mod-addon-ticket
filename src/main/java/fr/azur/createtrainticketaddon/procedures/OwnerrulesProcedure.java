package fr.azur.createtrainticketaddon.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.world.BlockEvent;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.TextComponent;

import javax.annotation.Nullable;

import fr.azur.createtrainticketaddon.init.CreateTicketModBlocks;

@Mod.EventBusSubscriber
public class OwnerrulesProcedure {
	@SubscribeEvent
	public static void onBlockBreak(BlockEvent.BreakEvent event) {
		execute(event, event.getState(), event.getPlayer());
	}

	public static void execute(BlockState blockstate, Entity entity) {
		execute(null, blockstate, entity);
	}

	private static void execute(@Nullable Event event, BlockState blockstate, Entity entity) {
		if (entity == null)
			return;
		if (blockstate.getBlock() == CreateTicketModBlocks.TRAINTICKETTERMINALHITBOX.get() || blockstate.getBlock() == CreateTicketModBlocks.TRAINTICKETTERMINAL.get()) {
			if (!((entity.getStringUUID()).equals(blockstate.getBlock().getStateDefinition().getProperty("owner") instanceof EnumProperty _getep6 ? blockstate.getValue(_getep6).toString() : "")
					|| ("").equals(blockstate.getBlock().getStateDefinition().getProperty("owner") instanceof EnumProperty _getep8 ? blockstate.getValue(_getep8).toString() : ""))) {
				if (entity instanceof Player _player && !_player.level.isClientSide())
					_player.displayClientMessage(new TextComponent("you are not the owner"), true);
				if (event != null && event.isCancelable()) {
					event.setCanceled(true);
				}
			}
		}
	}
}
