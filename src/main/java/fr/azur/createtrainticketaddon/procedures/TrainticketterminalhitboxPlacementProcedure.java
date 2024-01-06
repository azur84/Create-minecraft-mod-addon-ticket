package fr.azur.createtrainticketaddon.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import fr.azur.createtrainticketaddon.init.CreateTicketModBlocks;

public class TrainticketterminalhitboxPlacementProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		return (world.getBlockState(new BlockPos(x, y - 1, z))).getBlock() == CreateTicketModBlocks.TRAINTICKETTERMINAL.get();
	}
}
