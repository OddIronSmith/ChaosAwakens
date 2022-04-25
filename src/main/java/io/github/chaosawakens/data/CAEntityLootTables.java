package io.github.chaosawakens.data;

import io.github.chaosawakens.ChaosAwakens;
import io.github.chaosawakens.common.registry.CAEntityTypes;
import io.github.chaosawakens.common.registry.CAItems;
import net.minecraft.block.Blocks;
import net.minecraft.data.loot.EntityLootTables;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.EntityHasProperty;
import net.minecraft.loot.conditions.KilledByPlayer;
import net.minecraft.loot.conditions.RandomChance;
import net.minecraft.loot.functions.EnchantWithLevels;
import net.minecraft.loot.functions.LootingEnchantBonus;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.loot.functions.Smelt;

import java.util.HashSet;
import java.util.Set;

public class CAEntityLootTables extends EntityLootTables {
	private final Set<EntityType<?>> knownEntities = new HashSet<>();

	@Override
	public void add(EntityType<?> entity, LootTable.Builder builder) {
		super.add(entity, builder);
		knownEntities.add(entity);
	}

	public String getName() {
		return ChaosAwakens.MODNAME + ": Entity Loot Tables";
	}

	@Override
	protected void addTables() {
		add(CAEntityTypes.ACACIA_ENT.get(),
				LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(Items.ACACIA_LOG)
								.apply(SetCount.setCount(RandomValueRange.between(18.0F, 24.0F)))
								.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(1.0F, 3.0F)))
								.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.ACACIA_LEAVES)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(Items.ACACIA_SAPLING)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.GRASS)
										.apply(SetCount.setCount(RandomValueRange.between(0.0F, 2.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(RandomValueRange.between(1, 2))
								.add(ItemLootEntry.lootTableItem(Items.DIAMOND).when(RandomChance.randomChance(0.85F))
										.when(KilledByPlayer.killedByPlayer()))));
		add(CAEntityTypes.BIRCH_ENT.get(),
				LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(Items.BIRCH_LOG)
								.apply(SetCount.setCount(RandomValueRange.between(18.0F, 24.0F)))
								.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(1.0F, 3.0F)))
								.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.BIRCH_LEAVES)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(Items.BIRCH_SAPLING)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(RandomValueRange.between(1, 2))
								.add(ItemLootEntry.lootTableItem(Items.DIAMOND).when(RandomChance.randomChance(0.85F))
										.when(KilledByPlayer.killedByPlayer()))));
		add(CAEntityTypes.CRIMSON_ENT.get(),
				LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(Items.CRIMSON_STEM)
								.apply(SetCount.setCount(RandomValueRange.between(18.0F, 24.0F)))
								.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(1.0F, 3.0F)))
								.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.CRIMSON_NYLIUM)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(Items.CRIMSON_FUNGUS)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.CRIMSON_FUNGUS)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(Items.CRIMSON_ROOTS)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(RandomValueRange.between(1, 2))
								.add(ItemLootEntry.lootTableItem(Items.DIAMOND).when(RandomChance.randomChance(0.85F))
										.when(KilledByPlayer.killedByPlayer()))));
		add(CAEntityTypes.DARK_OAK_ENT.get(),
				LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(Items.DARK_OAK_LOG)
								.apply(SetCount.setCount(RandomValueRange.between(18.0F, 24.0F)))
								.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(1.0F, 3.0F)))
								.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.DARK_OAK_LEAVES)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(Items.DARK_OAK_SAPLING)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.RED_MUSHROOM)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(Items.BROWN_MUSHROOM)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(
								ItemLootEntry.lootTableItem(Items.CORNFLOWER).when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(RandomValueRange.between(1, 2))
								.add(ItemLootEntry.lootTableItem(Items.DIAMOND).when(RandomChance.randomChance(0.85F))
										.when(KilledByPlayer.killedByPlayer()))));
		add(CAEntityTypes.JUNGLE_ENT.get(),
				LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(Items.JUNGLE_LOG)
								.apply(SetCount.setCount(RandomValueRange.between(18.0F, 24.0F)))
								.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(1.0F, 3.0F)))
								.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.JUNGLE_LEAVES)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(Items.JUNGLE_SAPLING)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.VINE)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(Items.COCOA_BEANS)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(RandomValueRange.between(1, 2))
								.add(ItemLootEntry.lootTableItem(Items.DIAMOND).when(RandomChance.randomChance(0.85F))
										.when(KilledByPlayer.killedByPlayer()))));
		add(CAEntityTypes.OAK_ENT.get(),
				LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(Items.OAK_LOG)
								.apply(SetCount.setCount(RandomValueRange.between(18.0F, 24.0F)))
								.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(1.0F, 3.0F)))
								.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.OAK_LEAVES)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(Items.OAK_SAPLING)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.RED_MUSHROOM)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(Items.BROWN_MUSHROOM)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(RandomValueRange.between(1, 2))
								.add(ItemLootEntry.lootTableItem(Items.DIAMOND).when(RandomChance.randomChance(0.85F))
										.when(KilledByPlayer.killedByPlayer()))));
		add(CAEntityTypes.SPRUCE_ENT.get(),
				LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(Items.SPRUCE_LOG)
								.apply(SetCount.setCount(RandomValueRange.between(18.0F, 24.0F)))
								.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(1.0F, 3.0F)))
								.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.SPRUCE_LEAVES)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(Items.SPRUCE_SAPLING)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.PODZOL)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(Items.COARSE_DIRT)
										.apply(SetCount.setCount(RandomValueRange.between(0.0F, 2.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.FERN)
										.apply(SetCount.setCount(RandomValueRange.between(0.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(Blocks.MOSSY_COBBLESTONE)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 8.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(RandomValueRange.between(1, 2))
								.add(ItemLootEntry.lootTableItem(Items.DIAMOND).when(RandomChance.randomChance(0.85F))
										.when(KilledByPlayer.killedByPlayer()))));
		add(CAEntityTypes.WARPED_ENT.get(),
				LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(Items.WARPED_STEM)
								.apply(SetCount.setCount(RandomValueRange.between(18.0F, 24.0F)))
								.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(1.0F, 3.0F)))
								.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.WARPED_NYLIUM)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(Items.WARPED_FUNGUS)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.WARPED_FUNGUS)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(Items.WARPED_ROOTS)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(RandomValueRange.between(1, 2))
								.add(ItemLootEntry.lootTableItem(Items.DIAMOND).when(RandomChance.randomChance(0.85F))
										.when(KilledByPlayer.killedByPlayer()))));
		add(CAEntityTypes.HERCULES_BEETLE.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(
						ItemLootEntry.lootTableItem(CAItems.BIG_HAMMER.get()).when(KilledByPlayer.killedByPlayer())))
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(Items.DIAMOND_HELMET)
								.apply(EnchantWithLevels.enchantWithLevels(RandomValueRange.between(3.0F, 9.0F)))
								.when(KilledByPlayer.killedByPlayer()))
						.add(ItemLootEntry.lootTableItem(Items.DIAMOND_CHESTPLATE)
								.apply(EnchantWithLevels.enchantWithLevels(RandomValueRange.between(3.0F, 9.0F)))
								.when(KilledByPlayer.killedByPlayer()))
						.add(ItemLootEntry.lootTableItem(Items.DIAMOND_LEGGINGS)
								.apply(EnchantWithLevels.enchantWithLevels(RandomValueRange.between(3.0F, 9.0F)))
								.when(KilledByPlayer.killedByPlayer()))
						.add(ItemLootEntry.lootTableItem(Items.DIAMOND_BOOTS)
								.apply(EnchantWithLevels.enchantWithLevels(RandomValueRange.between(3.0F, 9.0F)))
								.when(KilledByPlayer.killedByPlayer())))
				.withPool(LootPool.lootPool().setRolls(RandomValueRange.between(1.0F, 3.0F))
						.add(ItemLootEntry.lootTableItem(CAItems.TIGERS_EYE_HELMET.get())
								.apply(EnchantWithLevels.enchantWithLevels(RandomValueRange.between(18.0F, 26.0F)))
								.when(KilledByPlayer.killedByPlayer()))
						.add(ItemLootEntry.lootTableItem(CAItems.TIGERS_EYE_CHESTPLATE.get())
								.apply(EnchantWithLevels.enchantWithLevels(RandomValueRange.between(18.0F, 26.0F)))
								.when(KilledByPlayer.killedByPlayer()))
						.add(ItemLootEntry.lootTableItem(CAItems.TIGERS_EYE_LEGGINGS.get())
								.apply(EnchantWithLevels.enchantWithLevels(RandomValueRange.between(18.0F, 26.0F)))
								.when(KilledByPlayer.killedByPlayer()))
						.add(ItemLootEntry.lootTableItem(CAItems.TIGERS_EYE_BOOTS.get())
								.apply(EnchantWithLevels.enchantWithLevels(RandomValueRange.between(18.0F, 26.0F)))
								.when(KilledByPlayer.killedByPlayer())))
				.withPool(LootPool.lootPool().setRolls(RandomValueRange.between(1, 2))
						.add(ItemLootEntry.lootTableItem(Items.DIAMOND).when(RandomChance.randomChance(0.975F))
								.when(KilledByPlayer.killedByPlayer()))
						.add(ItemLootEntry.lootTableItem(Items.EMERALD).when(RandomChance.randomChance(0.75F))
								.when(KilledByPlayer.killedByPlayer()))
						.add(ItemLootEntry.lootTableItem(CAItems.TIGERS_EYE.get()).when(RandomChance.randomChance(0.5F))
								.when(KilledByPlayer.killedByPlayer()))
						.add(ItemLootEntry.lootTableItem(CAItems.AMETHYST.get())
								.apply(SetCount.setCount(RandomValueRange.between(0.0F, 1.0F)))
								.when(RandomChance.randomChance(0.35F)).when(KilledByPlayer.killedByPlayer()))
						.add(ItemLootEntry.lootTableItem(CAItems.RUBY.get())
								.apply(SetCount.setCount(RandomValueRange.between(0.0F, 1.0F)))
								.when(RandomChance.randomChance(0.2F)).when(KilledByPlayer.killedByPlayer()))));
		add(CAEntityTypes.RUBY_BUG.get(),
				LootTable.lootTable()
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(CAItems.RUBY.get())
										.when(RandomChance.randomChance(0.1F)).when(KilledByPlayer.killedByPlayer()))));
		add(CAEntityTypes.EMERALD_GATOR.get(),
				LootTable.lootTable()
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.EMERALD).when(RandomChance.randomChance(0.4F))
										.when(KilledByPlayer.killedByPlayer()))));
		add(CAEntityTypes.APPLE_COW.get(),
				LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(Items.BEEF)
								.apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F)))
								.apply(Smelt.smelted().when(
										EntityHasProperty.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))
								.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.LEATHER)
										.apply(SetCount.setCount(RandomValueRange.between(0.0F, 2.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.APPLE)
										.apply(SetCount.setCount(RandomValueRange.between(2.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer()))));
		add(CAEntityTypes.GOLDEN_APPLE_COW.get(),
				LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(Items.BEEF)
								.apply(Smelt.smelted().when(
										EntityHasProperty.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))
								.apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F)))
								.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.LEATHER)
										.apply(SetCount.setCount(RandomValueRange.between(0.0F, 2.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.APPLE)
										.apply(SetCount.setCount(RandomValueRange.between(2.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.GOLDEN_APPLE)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
										.when(KilledByPlayer.killedByPlayer()))));
		add(CAEntityTypes.ENCHANTED_GOLDEN_APPLE_COW.get(),
				LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(Items.BEEF)
								.apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F)))
								.apply(Smelt.smelted().when(
										EntityHasProperty.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))
								.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.LEATHER)
										.apply(SetCount.setCount(RandomValueRange.between(0.0F, 2.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.APPLE)
										.apply(SetCount.setCount(RandomValueRange.between(2.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.GOLDEN_APPLE)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
										.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry
								.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE).when(KilledByPlayer.killedByPlayer()))));
		add(CAEntityTypes.CRYSTAL_APPLE_COW.get(),
				LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(Items.BEEF)
								.apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F)))
								.apply(Smelt.smelted().when(
										EntityHasProperty.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))
								.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.LEATHER)
										.apply(SetCount.setCount(RandomValueRange.between(0.0F, 2.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.APPLE)
										.apply(SetCount.setCount(RandomValueRange.between(2.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(CAItems.CRYSTAL_APPLE.get())
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
										.when(KilledByPlayer.killedByPlayer()))));
		add(CAEntityTypes.CARROT_PIG.get(),
				LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(Items.PORKCHOP)
								.apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F)))
								.apply(Smelt.smelted().when(
										EntityHasProperty.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))
								.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry
								.lootTableItem(Items.CARROT)
								.apply(SetCount.setCount(RandomValueRange.between(2.0F, 4.0F)))
								.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F))))));
		add(CAEntityTypes.GOLDEN_CARROT_PIG.get(),
				LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(Items.PORKCHOP)
								.apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F)))
								.apply(Smelt.smelted().when(
										EntityHasProperty.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))
								.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.CARROT)
										.apply(SetCount.setCount(RandomValueRange.between(2.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.GOLDEN_CARROT)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
										.when(KilledByPlayer.killedByPlayer()))));
		add(CAEntityTypes.ENCHANTED_GOLDEN_CARROT_PIG.get(),
				LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(Items.PORKCHOP)
								.apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F)))
								.apply(Smelt.smelted().when(
										EntityHasProperty.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))
								.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.CARROT)
										.apply(SetCount.setCount(RandomValueRange.between(2.0F, 4.0F)))
										.apply(LootingEnchantBonus
												.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F)))))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.GOLDEN_CARROT)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
										.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(CAItems.ENCHANTED_GOLDEN_CARROT.get())
										.when(KilledByPlayer.killedByPlayer()))));
		add(CAEntityTypes.CRYSTAL_CARROT_PIG.get(),
				LootTable.lootTable()
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(CAItems.CRYSTAL_CARROT.get())
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
										.when(KilledByPlayer.killedByPlayer()))));
		add(CAEntityTypes.BEAVER.get(),
				LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(Items.PORKCHOP)
								.apply(SetCount.setCount(RandomValueRange.between(0.0F, 2.0F)))
								.apply(Smelt.smelted().when(
										EntityHasProperty.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))
								.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry
								.lootTableItem(Items.LEATHER)
								.apply(SetCount.setCount(RandomValueRange.between(0.0F, 1.0F)))
								.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))));
		add(CAEntityTypes.ROBO_POUNDER.get(),
				LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(Items.REDSTONE_BLOCK)
								.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
								.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))
								.when(KilledByPlayer.killedByPlayer()))
						.add(ItemLootEntry.lootTableItem(Items.REDSTONE)
								.apply(SetCount.setCount(RandomValueRange.between(20.0F, 32.0F)))
								.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))
								.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.REPEATER)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(Items.COMPARATOR)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
										.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.PISTON)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(Items.STICKY_PISTON)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
										.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.DROPPER)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(Items.DISPENSER)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
										.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.HOPPER)
										.apply(SetCount.setCount(ConstantRange.exactly(1)))
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(Items.OBSERVER)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
										.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.REDSTONE_TORCH)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(Items.REDSTONE_LAMP)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
										.when(KilledByPlayer.killedByPlayer()))));
		add(CAEntityTypes.ROBO_SNIPER.get(),
				LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(Items.REDSTONE_BLOCK)
								.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
								.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))
								.when(KilledByPlayer.killedByPlayer()))
						.add(ItemLootEntry.lootTableItem(Items.REDSTONE)
								.apply(SetCount.setCount(RandomValueRange.between(20.0F, 32.0F)))
								.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))
								.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.REPEATER)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(Items.COMPARATOR)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
										.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.PISTON)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(Items.STICKY_PISTON)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
										.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.DROPPER)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(Items.DISPENSER)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
										.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.HOPPER)
										.apply(SetCount.setCount(ConstantRange.exactly(1)))
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(Items.OBSERVER)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
										.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.REDSTONE_TORCH)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(Items.REDSTONE_LAMP)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
										.when(KilledByPlayer.killedByPlayer()))));
		add(CAEntityTypes.ROBO_WARRIOR.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(CAItems.RAY_GUN.get()).when(KilledByPlayer.killedByPlayer())))
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(Items.REDSTONE_BLOCK)
								.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
								.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))
								.when(KilledByPlayer.killedByPlayer()))
						.add(ItemLootEntry.lootTableItem(Items.REDSTONE)
								.apply(SetCount.setCount(RandomValueRange.between(20.0F, 32.0F)))
								.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))
								.when(KilledByPlayer.killedByPlayer())))
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(Items.REPEATER)
								.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
								.when(KilledByPlayer.killedByPlayer()))
						.add(ItemLootEntry.lootTableItem(Items.COMPARATOR)
								.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
								.when(KilledByPlayer.killedByPlayer())))
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(Items.PISTON)
								.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
								.when(KilledByPlayer.killedByPlayer()))
						.add(ItemLootEntry.lootTableItem(Items.STICKY_PISTON)
								.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
								.when(KilledByPlayer.killedByPlayer())))
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(Items.DROPPER)
								.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
								.when(KilledByPlayer.killedByPlayer()))
						.add(ItemLootEntry.lootTableItem(Items.DISPENSER)
								.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
								.when(KilledByPlayer.killedByPlayer())))
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(Items.HOPPER)
								.apply(SetCount.setCount(ConstantRange.exactly(1)))
								.when(KilledByPlayer.killedByPlayer()))
						.add(ItemLootEntry.lootTableItem(Items.OBSERVER)
								.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
								.when(KilledByPlayer.killedByPlayer())))
				.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(Items.REDSTONE_TORCH)
								.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
								.when(KilledByPlayer.killedByPlayer()))
						.add(ItemLootEntry.lootTableItem(Items.REDSTONE_LAMP)
								.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
								.when(KilledByPlayer.killedByPlayer()))));
		add(CAEntityTypes.STINK_BUG.get(),
				LootTable.lootTable()
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(CAItems.DEAD_STINK_BUG.get())
										.apply(SetCount.setCount(ConstantRange.exactly(1))))));
		add(CAEntityTypes.WASP.get(),
				LootTable.lootTable()
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.GOLD_NUGGET)
										.apply(SetCount.setCount(RandomValueRange.between(12.0F, 24.0F)))
										.when(KilledByPlayer.killedByPlayer()))));
		add(CAEntityTypes.GREEN_FISH.get(),
				LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry
						.lootTableItem(CAItems.GREEN_FISH.get()).apply(SetCount.setCount(ConstantRange.exactly(1))))));
		add(CAEntityTypes.ROCK_FISH.get(),
				LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry
						.lootTableItem(CAItems.ROCK_FISH.get()).apply(SetCount.setCount(ConstantRange.exactly(1))))));
		add(CAEntityTypes.SPARK_FISH.get(),
				LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry
						.lootTableItem(CAItems.SPARK_FISH.get()).apply(SetCount.setCount(ConstantRange.exactly(1))))));
		add(CAEntityTypes.WOOD_FISH.get(),
				LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry
						.lootTableItem(CAItems.WOOD_FISH.get()).apply(SetCount.setCount(ConstantRange.exactly(1))))));
		add(CAEntityTypes.LAVA_EEL.get(),
				LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry
						.lootTableItem(CAItems.LAVA_EEL.get()).apply(SetCount.setCount(ConstantRange.exactly(1))))));
		add(EntityType.ILLUSIONER,
				LootTable.lootTable()
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.EMERALD)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F)))
										.when(KilledByPlayer.killedByPlayer()))));
		add(EntityType.GIANT,
				LootTable.lootTable()
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.ROTTEN_FLESH)
										.apply(SetCount.setCount(RandomValueRange.between(14.0F, 31.0F))))));
		add(CAEntityTypes.WHALE.get(),
				LootTable.lootTable()
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.GOLD_NUGGET)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 7.0F)))
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(Items.DIAMOND)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
										.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(CAItems.AMETHYST.get())
										.apply(SetCount.setCount(RandomValueRange.between(0.0F, 1.0F)))
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(CAItems.SALT.get())
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 5.0F)))
										.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.IRON_NUGGET)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 9.0F)))
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(Items.EMERALD)
										.apply(SetCount.setCount(RandomValueRange.between(0.0F, 2.0F)))
										.when(KilledByPlayer.killedByPlayer()))));
		add(CAEntityTypes.GAZELLE.get(),
				LootTable.lootTable()
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(Items.LEATHER)
										.apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))
										.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry
								.lootTableItem(CAItems.VENISON.get())
								.apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F)))
								.apply(Smelt.smelted().when(
										EntityHasProperty.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))
								.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(1.0F, 1.0F))))));
	}

	@Override
	public Set<EntityType<?>> getKnownEntities() {
		return knownEntities;
	}
}