
package fr.azur.createtrainticketaddon.block;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.Containers;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;

import java.util.List;
import java.util.Collections;

import fr.azur.createtrainticketaddon.procedures.TrainticketterminaladdProcedure;
import fr.azur.createtrainticketaddon.procedures.TrainticketterminalQuandLeBlocEstPlaceProcedure;
import fr.azur.createtrainticketaddon.procedures.TrainticketterminalLorsDunClicDroitSurLeBlocProcedure;
import fr.azur.createtrainticketaddon.procedures.TrainticketterminalConditionDePlacementValideDeBlocProcedure;
import fr.azur.createtrainticketaddon.init.CreateTicketModBlocks;
import fr.azur.createtrainticketaddon.block.entity.TrainticketterminalBlockEntity;

public class TrainticketterminalBlock extends Block implements EntityBlock {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public TrainticketterminalBlock() {
		super(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.GRAVEL).strength(1f, 6f).lightLevel(s -> 1).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
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
			default -> Shapes.or(box(0.5, 0, 0.5, 15.5, 12, 15.5), box(0.5, 12, 0.5, 15.5, 26, 15.5), box(-0.5, 26, -0.5, 16.5, 27, 16.5), box(0.5, 24, 14.5, 15.5, 26, 15.5), box(0, 27, 15, 16, 32, 16), box(0, 0, 0, 2, 26, 2),
					box(14, 0, 0, 16, 26, 2), box(14, 0, 14, 16, 26, 16), box(0, 0, 14, 2, 26, 16), box(4, 10, 15.5, 6, 10.4, 16), box(3.5, 10, 15.5, 4, 11.4, 16), box(4, 11, 15.5, 6, 11.4, 16), box(6, 10, 15.5, 6.5, 11.4, 16),
					box(1.5, 13.41421, 13.08579, 14.5, 23.41421, 14.08579), box(1.5, 16.28033, 10.50951, 14.5, 18.28033, 12.50951));
			case NORTH -> Shapes.or(box(0.5, 0, 0.5, 15.5, 12, 15.5), box(0.5, 12, 0.5, 15.5, 26, 15.5), box(-0.5, 26, -0.5, 16.5, 27, 16.5), box(0.5, 24, 0.5, 15.5, 26, 1.5), box(0, 27, 0, 16, 32, 1), box(14, 0, 14, 16, 26, 16),
					box(0, 0, 14, 2, 26, 16), box(0, 0, 0, 2, 26, 2), box(14, 0, 0, 16, 26, 2), box(10, 10, 0, 12, 10.4, 0.5), box(12, 10, 0, 12.5, 11.4, 0.5), box(10, 11, 0, 12, 11.4, 0.5), box(9.5, 10, 0, 10, 11.4, 0.5),
					box(1.5, 13.41421, 1.91421, 14.5, 23.41421, 2.91421), box(1.5, 16.28033, 3.49049, 14.5, 18.28033, 5.49049));
			case EAST -> Shapes.or(box(0.5, 0, 0.5, 15.5, 12, 15.5), box(0.5, 12, 0.5, 15.5, 26, 15.5), box(-0.5, 26, -0.5, 16.5, 27, 16.5), box(14.5, 24, 0.5, 15.5, 26, 15.5), box(15, 27, 0, 16, 32, 16), box(0, 0, 14, 2, 26, 16),
					box(0, 0, 0, 2, 26, 2), box(14, 0, 0, 16, 26, 2), box(14, 0, 14, 16, 26, 16), box(15.5, 10, 10, 16, 10.4, 12), box(15.5, 10, 12, 16, 11.4, 12.5), box(15.5, 11, 10, 16, 11.4, 12), box(15.5, 10, 9.5, 16, 11.4, 10),
					box(13.08579, 13.41421, 1.5, 14.08579, 23.41421, 14.5), box(10.50951, 16.28033, 1.5, 12.50951, 18.28033, 14.5));
			case WEST -> Shapes.or(box(0.5, 0, 0.5, 15.5, 12, 15.5), box(0.5, 12, 0.5, 15.5, 26, 15.5), box(-0.5, 26, -0.5, 16.5, 27, 16.5), box(0.5, 24, 0.5, 1.5, 26, 15.5), box(0, 27, 0, 1, 32, 16), box(14, 0, 0, 16, 26, 2),
					box(14, 0, 14, 16, 26, 16), box(0, 0, 14, 2, 26, 16), box(0, 0, 0, 2, 26, 2), box(0, 10, 4, 0.5, 10.4, 6), box(0, 10, 3.5, 0.5, 11.4, 4), box(0, 11, 4, 0.5, 11.4, 6), box(0, 10, 6, 0.5, 11.4, 6.5),
					box(1.91421, 13.41421, 1.5, 2.91421, 23.41421, 14.5), box(3.49049, 16.28033, 1.5, 5.49049, 18.28033, 14.5));
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
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
			return TrainticketterminalConditionDePlacementValideDeBlocProcedure.execute(world, x, y, z);
		}
		return super.canSurvive(blockstate, worldIn, pos);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
		return !state.canSurvive(world, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, world, currentPos, facingPos);
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState state) {
		return PushReaction.IGNORE;
	}

	@Override
	public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
		if (player.getInventory().getSelected().getItem() instanceof PickaxeItem tieredItem)
			return tieredItem.getTier().getLevel() >= 1;
		return false;
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		List<ItemStack> dropsOriginal = super.getDrops(state, builder);
		if (!dropsOriginal.isEmpty())
			return dropsOriginal;
		return Collections.singletonList(new ItemStack(this, 1));
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		TrainticketterminaladdProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void setPlacedBy(Level world, BlockPos pos, BlockState blockstate, LivingEntity entity, ItemStack itemstack) {
		super.setPlacedBy(world, pos, blockstate, entity, itemstack);
		TrainticketterminalQuandLeBlocEstPlaceProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
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
		TrainticketterminalLorsDunClicDroitSurLeBlocProcedure.execute(world, x, y, z, entity);
		return InteractionResult.SUCCESS;
	}

	@Override
	public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
		BlockEntity tileEntity = worldIn.getBlockEntity(pos);
		return tileEntity instanceof MenuProvider menuProvider ? menuProvider : null;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new TrainticketterminalBlockEntity(pos, state);
	}

	@Override
	public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int eventID, int eventParam) {
		super.triggerEvent(state, world, pos, eventID, eventParam);
		BlockEntity blockEntity = world.getBlockEntity(pos);
		return blockEntity == null ? false : blockEntity.triggerEvent(eventID, eventParam);
	}

	@Override
	public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof TrainticketterminalBlockEntity be) {
				Containers.dropContents(world, pos, be);
				world.updateNeighbourForOutputSignal(pos, this);
			}
			super.onRemove(state, world, pos, newState, isMoving);
		}
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState blockState, Level world, BlockPos pos) {
		BlockEntity tileentity = world.getBlockEntity(pos);
		if (tileentity instanceof TrainticketterminalBlockEntity be)
			return AbstractContainerMenu.getRedstoneSignalFromContainer(be);
		else
			return 0;
	}

	@OnlyIn(Dist.CLIENT)
	public static void registerRenderLayer() {
		ItemBlockRenderTypes.setRenderLayer(CreateTicketModBlocks.TRAINTICKETTERMINAL.get(), renderType -> renderType == RenderType.cutout());
	}
}
