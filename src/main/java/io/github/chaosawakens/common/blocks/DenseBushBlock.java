package io.github.chaosawakens.common.blocks;

import io.github.chaosawakens.common.registry.CABlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.pathfinding.PathType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.IPlantable;

public class DenseBushBlock extends Block implements IPlantable {
	public DenseBushBlock(Properties properties) {
		super(properties);
	}

	protected boolean mayPlaceOn(BlockState state, IBlockReader world, BlockPos pos) {
		return state.is(CABlocks.DENSE_GRASS_BLOCK.get()) || state.is(CABlocks.DENSE_DIRT.get());
	}

	public BlockState updateShape(BlockState state, Direction direction, BlockState state1, IWorld world, BlockPos pos,
			BlockPos pos1) {
		return !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState()
				: super.updateShape(state, direction, state1, world, pos, pos1);
	}

	public boolean canSurvive(BlockState state, IWorldReader worldReader, BlockPos pos) {
		BlockPos blockpos = pos.below();
		if (state.getBlock() == this) // Forge: This function is called during world gen and placement, before this
										// block is set, so if we are not 'here' then assume it's the pre-check.
			return worldReader.getBlockState(blockpos).canSustainPlant(worldReader, blockpos, Direction.UP, this);
		return this.mayPlaceOn(worldReader.getBlockState(blockpos), worldReader, blockpos);
	}

	public boolean propagatesSkylightDown(BlockState state, IBlockReader p_200123_2_, BlockPos p_200123_3_) {
		return state.getFluidState().isEmpty();
	}

	public boolean isPathfindable(BlockState state, IBlockReader world, BlockPos pos, PathType pathType) {
		return pathType == PathType.AIR && !this.hasCollision || super.isPathfindable(state, world, pos, pathType);
	}

	@Override
	public BlockState getPlant(IBlockReader world, BlockPos pos) {
		BlockState state = world.getBlockState(pos);
		if (state.getBlock() != this)
			return defaultBlockState();
		return state;
	}
}
