package io.github.chaosawakens.common.blocks;

import io.github.chaosawakens.common.registry.CABlocks;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.Tags;

import java.util.List;
import java.util.Random;

public class DenseGrassBlock extends SpreadableDenseDirtBlock implements IGrowable {
	public DenseGrassBlock(Properties properties) {
		super(properties);
	}

	public boolean isValidBonemealTarget(IBlockReader blockReader, BlockPos pos, BlockState state,
			boolean p_176473_4_) {
		return blockReader.getBlockState(pos.above()).isAir();
	}

	public boolean isBonemealSuccess(World world, Random random, BlockPos pos, BlockState state) {
		return true;
	}

	public void performBonemeal(ServerWorld serverWorld, Random random, BlockPos pos, BlockState state) {
		BlockPos blockpos = pos.above();
		BlockState blockstate = CABlocks.DENSE_GRASS.get().defaultBlockState();

		label48: for (int i = 0; i < 128; ++i) {
			BlockPos blockpos1 = blockpos;

			for (int j = 0; j < i / 16; ++j) {
				blockpos1 = blockpos1.offset(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2,
						random.nextInt(3) - 1);
				if (!serverWorld.getBlockState(blockpos1.below()).is(this)
						|| serverWorld.getBlockState(blockpos1).isCollisionShapeFullBlock(serverWorld, blockpos1)) {
					continue label48;
				}
			}

			BlockState blockstate2 = serverWorld.getBlockState(blockpos1);
			if (blockstate2.is(blockstate.getBlock()) && random.nextInt(10) == 0) {
				((IGrowable) blockstate.getBlock()).performBonemeal(serverWorld, random, blockpos1, blockstate2);
			}

			if (blockstate2.isAir()) {
				BlockState blockstate1;
				if (random.nextInt(8) == 0) {
					List<ConfiguredFeature<?, ?>> list = serverWorld.getBiome(blockpos1).getGenerationSettings()
							.getFlowerFeatures();
					if (list.isEmpty()) {
						continue;
					}

					ConfiguredFeature<?, ?> configuredfeature = list.get(0);
					FlowersFeature flowersfeature = (FlowersFeature) configuredfeature.feature;
					blockstate1 = flowersfeature.getRandomFlower(random, blockpos1, configuredfeature.config());
				} else {
					blockstate1 = blockstate;
				}

				if (blockstate1.canSurvive(serverWorld, blockpos1)) {
					serverWorld.setBlock(blockpos1, blockstate1, 3);
				}
			}
		}
	}

	@Override
	public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing,
			IPlantable plantable) {
		BlockState plant = plantable.getPlant(world, pos.relative(facing));
		PlantType type = plantable.getPlantType(world, pos.relative(facing));

		if (plant.getBlock() == Blocks.SUGAR_CANE && this == Blocks.SUGAR_CANE)
			return true;

		if (plantable instanceof DenseBushBlock && ((DenseBushBlock) plantable).mayPlaceOn(state, world, pos))
			return true;

		if (PlantType.PLAINS.equals(type)) {
			return this.getBlock() == Blocks.GRASS_BLOCK || Tags.Blocks.DIRT.contains(this);
		}
		return false;
	}
}