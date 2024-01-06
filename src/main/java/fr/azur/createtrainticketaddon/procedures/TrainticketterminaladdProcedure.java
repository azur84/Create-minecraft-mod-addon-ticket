package fr.azur.createtrainticketaddon.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import fr.azur.createtrainticketaddon.init.CreateTicketModBlocks;

public class TrainticketterminaladdProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		world.setBlock(new BlockPos(x, y + 1, z), CreateTicketModBlocks.TRAINTICKETTERMINALHITBOX.get().defaultBlockState(), 3);
	}
}
