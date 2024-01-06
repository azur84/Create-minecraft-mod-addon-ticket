package fr.azur.createtrainticketaddon.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import fr.azur.createtrainticketaddon.init.CreateTicketModBlocks;

public class TrainticketterminalConditionDePlacementValideDeBlocProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		BlockState block = Blocks.AIR.defaultBlockState();
		block = (world.getBlockState(new BlockPos(x, y + 1, z)));
		return block.getBlock() == CreateTicketModBlocks.TRAINTICKETTERMINALHITBOX.get() || block.getBlock() == Blocks.AIR || block.getBlock() == Blocks.VOID_AIR || block.getBlock() == Blocks.CAVE_AIR;
	}
}
