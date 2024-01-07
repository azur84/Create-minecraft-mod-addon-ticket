
package fr.azur.createtrainticketaddon.block;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;

import java.util.Random;

import fr.azur.createtrainticketaddon.procedures.TrainticketterminalhitboxTickProcedure;
import fr.azur.createtrainticketaddon.procedures.TrainticketterminalhitboxPlacementProcedure;
import fr.azur.createtrainticketaddon.procedures.TrainticketterminalhitboxLorsDunClicDroitSurLeBlocProcedure;
import fr.azur.createtrainticketaddon.procedures.TrainticketterminalhitboxDestroyProcedure;
import fr.azur.createtrainticketaddon.init.CreateTicketModBlocks;

public class TrainticketterminalhitboxBlock extends Block {
	public static final DirectionProperty FACING = DirectionalBlock.FACING;

	public TrainticketterminalhitboxBlock() {
		super(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1f, 6f).lightLevel(s -> 1).noOcclusion().isRedstoneConductor((bs, br, bp) -> false).noDrops());
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(FACING)) {
			default -> Shapes.or(box(0.5, -16, 0.5, 15.5, -4, 15.5), box(0.5, -4, 0.5, 15.5, 10, 15.5), box(-0.5, 10, -0.5, 16.5, 11, 16.5), box(0.5, 8, 14.5, 15.5, 10, 15.5), box(0, 11, 15, 16, 16, 16), box(0, -16, 0, 2, 10, 2),
					box(14, -16, 0, 16, 10, 2), box(14, -16, 14, 16, 10, 16), box(0, -16, 14, 2, 10, 16), box(4, -6, 15.5, 6, -5.6, 16), box(3.5, -6, 15.5, 4, -4.6, 16), box(4, -5, 15.5, 6, -4.6, 16), box(6, -6, 15.5, 6.5, -4.6, 16),
					box(1.5, -2.58579, 13.08579, 14.5, 7.41421, 14.08579), box(1.5, 0.28033, 10.50951, 14.5, 2.28033, 12.50951));
			case NORTH -> Shapes.or(box(0.5, -16, 0.5, 15.5, -4, 15.5), box(0.5, -4, 0.5, 15.5, 10, 15.5), box(-0.5, 10, -0.5, 16.5, 11, 16.5), box(0.5, 8, 0.5, 15.5, 10, 1.5), box(0, 11, 0, 16, 16, 1), box(14, -16, 14, 16, 10, 16),
					box(0, -16, 14, 2, 10, 16), box(0, -16, 0, 2, 10, 2), box(14, -16, 0, 16, 10, 2), box(10, -6, 0, 12, -5.6, 0.5), box(12, -6, 0, 12.5, -4.6, 0.5), box(10, -5, 0, 12, -4.6, 0.5), box(9.5, -6, 0, 10, -4.6, 0.5),
					box(1.5, -2.58579, 1.91421, 14.5, 7.41421, 2.91421), box(1.5, 0.28033, 3.49049, 14.5, 2.28033, 5.49049));
			case EAST -> Shapes.or(box(0.5, -16, 0.5, 15.5, -4, 15.5), box(0.5, -4, 0.5, 15.5, 10, 15.5), box(-0.5, 10, -0.5, 16.5, 11, 16.5), box(14.5, 8, 0.5, 15.5, 10, 15.5), box(15, 11, 0, 16, 16, 16), box(0, -16, 14, 2, 10, 16),
					box(0, -16, 0, 2, 10, 2), box(14, -16, 0, 16, 10, 2), box(14, -16, 14, 16, 10, 16), box(15.5, -6, 10, 16, -5.6, 12), box(15.5, -6, 12, 16, -4.6, 12.5), box(15.5, -5, 10, 16, -4.6, 12), box(15.5, -6, 9.5, 16, -4.6, 10),
					box(13.08579, -2.58579, 1.5, 14.08579, 7.41421, 14.5), box(10.50951, 0.28033, 1.5, 12.50951, 2.28033, 14.5));
			case WEST -> Shapes.or(box(0.5, -16, 0.5, 15.5, -4, 15.5), box(0.5, -4, 0.5, 15.5, 10, 15.5), box(-0.5, 10, -0.5, 16.5, 11, 16.5), box(0.5, 8, 0.5, 1.5, 10, 15.5), box(0, 11, 0, 1, 16, 16), box(14, -16, 0, 16, 10, 2),
					box(14, -16, 14, 16, 10, 16), box(0, -16, 14, 2, 10, 16), box(0, -16, 0, 2, 10, 2), box(0, -6, 4, 0.5, -5.6, 6), box(0, -6, 3.5, 0.5, -4.6, 4), box(0, -5, 4, 0.5, -4.6, 6), box(0, -6, 6, 0.5, -4.6, 6.5),
					box(1.91421, -2.58579, 1.5, 2.91421, 7.41421, 14.5), box(3.49049, 0.28033, 1.5, 5.49049, 2.28033, 14.5));
			case UP -> Shapes.or(box(0.5, 0.5, -16, 15.5, 15.5, -4), box(0.5, 0.5, -4, 15.5, 15.5, 10), box(-0.5, -0.5, 10, 16.5, 16.5, 11), box(0.5, 14.5, 8, 15.5, 15.5, 10), box(0, 15, 11, 16, 16, 16), box(14, 0, -16, 16, 2, 10),
					box(0, 0, -16, 2, 2, 10), box(0, 14, -16, 2, 16, 10), box(14, 14, -16, 16, 16, 10), box(10, 15.5, -6, 12, 16, -5.6), box(12, 15.5, -6, 12.5, 16, -4.6), box(10, 15.5, -5, 12, 16, -4.6), box(9.5, 15.5, -6, 10, 16, -4.6),
					box(1.5, 13.08579, -2.58579, 14.5, 14.08579, 7.41421), box(1.5, 10.50951, 0.28033, 14.5, 12.50951, 2.28033));
			case DOWN -> Shapes.or(box(0.5, 0.5, 20, 15.5, 15.5, 32), box(0.5, 0.5, 6, 15.5, 15.5, 20), box(-0.5, -0.5, 5, 16.5, 16.5, 6), box(0.5, 0.5, 6, 15.5, 1.5, 8), box(0, 0, 0, 16, 1, 5), box(14, 14, 6, 16, 16, 32), box(0, 14, 6, 2, 16, 32),
					box(0, 0, 6, 2, 2, 32), box(14, 0, 6, 16, 2, 32), box(10, 0, 21.6, 12, 0.5, 22), box(12, 0, 20.6, 12.5, 0.5, 22), box(10, 0, 20.6, 12, 0.5, 21), box(9.5, 0, 20.6, 10, 0.5, 22), box(1.5, 1.91421, 8.58579, 14.5, 2.91421, 18.58579),
					box(1.5, 3.49049, 13.71967, 14.5, 5.49049, 15.71967));
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getNearestLookingDirection().getOpposite());
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public boolean canSurvive(BlockState blockstate, LevelReader worldIn, BlockPos pos) {
		if (worldIn instanceof LevelAccessor world) {
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			return TrainticketterminalhitboxPlacementProcedure.execute(world, x, y, z);
		}
		return super.canSurvive(blockstate, worldIn, pos);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
		return !state.canSurvive(world, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, world, currentPos, facingPos);
	}

	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
		return new ItemStack(CreateTicketModBlocks.TRAINTICKETTERMINAL.get());
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState state) {
		return PushReaction.IGNORE;
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		TrainticketterminalhitboxTickProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, Random random) {
		super.tick(blockstate, world, pos, random);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		TrainticketterminalhitboxTickProcedure.execute(world, x, y, z);
	}

	@Override
	public boolean onDestroyedByPlayer(BlockState blockstate, Level world, BlockPos pos, Player entity, boolean willHarvest, FluidState fluid) {
		boolean retval = super.onDestroyedByPlayer(blockstate, world, pos, entity, willHarvest, fluid);
		TrainticketterminalhitboxDestroyProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
		return retval;
	}

	@Override
	public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
		super.use(blockstate, world, pos, entity, hand, hit);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		double hitX = hit.getLocation().x;
		double hitY = hit.getLocation().y;
		double hitZ = hit.getLocation().z;
		Direction direction = hit.getDirection();
		TrainticketterminalhitboxLorsDunClicDroitSurLeBlocProcedure.execute(x, y, z, entity);
		return InteractionResult.SUCCESS;
	}

	@OnlyIn(Dist.CLIENT)
	public static void registerRenderLayer() {
		ItemBlockRenderTypes.setRenderLayer(CreateTicketModBlocks.TRAINTICKETTERMINALHITBOX.get(), renderType -> renderType == RenderType.cutout());
	}
}
