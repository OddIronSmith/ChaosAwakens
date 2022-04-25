package io.github.chaosawakens.common.blocks;

import io.github.chaosawakens.common.registry.CABlocks;
import net.minecraft.block.*;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.lighting.LightEngine;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class SpreadableDenseDirtBlock extends Block {
	protected SpreadableDenseDirtBlock(Properties properties) {
		super(properties);
	}

	private static boolean canBeGrass(BlockState state, IWorldReader worldReader, BlockPos pos) {
		BlockPos blockpos = pos.above();
		BlockState blockstate = worldReader.getBlockState(blockpos);
		if (blockstate.getFluidState().getAmount() == 8) {
			return false;
		} else {
			int i = LightEngine.getLightBlockInto(worldReader, state, pos, blockstate, blockpos, Direction.UP,
					blockstate.getLightBlock(worldReader, blockpos));
			return i < worldReader.getMaxLightLevel();
		}
	}

	private static boolean canPropagate(BlockState state, IWorldReader worldReader, BlockPos pos) {
		BlockPos blockpos = pos.above();
		return canBeGrass(state, worldReader, pos) && !worldReader.getFluidState(blockpos).is(FluidTags.WATER);
	}

	public void randomTick(BlockState state, ServerWorld serverWorld, BlockPos pos, Random random) {
		if (!canBeGrass(state, serverWorld, pos)) {
			if (!serverWorld.isAreaLoaded(pos, 3))
				return; // Forge: prevent loading unloaded chunks when checking neighbor's light and
						// spreading
			serverWorld.setBlockAndUpdate(pos, CABlocks.DENSE_DIRT.get().defaultBlockState());
		} else {
			if (serverWorld.getMaxLocalRawBrightness(pos.above()) >= 9) {
				BlockState blockstate = this.defaultBlockState();

				for (int i = 0; i < 4; ++i) {
					BlockPos blockpos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
					if (serverWorld.getBlockState(blockpos).is(CABlocks.DENSE_DIRT.get())
							&& canPropagate(blockstate, serverWorld, blockpos)) {
						serverWorld.setBlockAndUpdate(blockpos, blockstate);
					}
				}
			}

		}
	}
}
