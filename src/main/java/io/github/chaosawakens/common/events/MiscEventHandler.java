package io.github.chaosawakens.common.events;

import com.mojang.brigadier.CommandDispatcher;
import io.github.chaosawakens.ChaosAwakens;
import io.github.chaosawakens.api.IUtilityHelper;
import io.github.chaosawakens.common.config.CACommonConfig;
import io.github.chaosawakens.common.entity.robo.RoboPounderEntity;
import io.github.chaosawakens.common.entity.robo.RoboSniperEntity;
import io.github.chaosawakens.common.entity.robo.RoboWarriorEntity;
import io.github.chaosawakens.common.registry.CACommand;
import io.github.chaosawakens.common.registry.CADimensions;
import io.github.chaosawakens.common.registry.CAItems;
import net.minecraft.block.Blocks;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraft.entity.monster.GiantEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Util;
import net.minecraft.util.text.*;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.EndPodiumFeature;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DerivedWorldInfo;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.SleepFinishedTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.VersionChecker;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class MiscEventHandler {
	@SubscribeEvent
	public static void onRegisterCommandEvent(RegisterCommandsEvent event) {
		CommandDispatcher<CommandSource> commandDispatcher = event.getDispatcher();
		CACommand.register(commandDispatcher);
	}

	public static void livingDeathEvent(LivingDeathEvent event) {
		Entity entity = event.getEntity();
		MinecraftServer server = entity.getServer();
		Random random = new Random();
		if (server == null) return;
		if (entity instanceof PlayerEntity) {
			// Make myself (Blackout03_) drop Ink Sacs any time I die. Even if I have none on me.
			if (IUtilityHelper.isUserOrEntityUUIDEqualTo(entity, UUID.fromString("89cd9d1b-9d50-4502-8bd4-95b9e63ff589"))) { // UUID of Blackout03_
				((PlayerEntity) entity).drop(new ItemStack(Items.INK_SAC, random.nextInt(3)), true, false);
			}
		}
		if (CACommonConfig.COMMON.enableDragonEggRespawns.get()) {
			if (entity.getCommandSenderWorld().equals(server.getLevel(World.END))) {
				if (entity instanceof EnderDragonEntity) {
					EnderDragonEntity dragon = (EnderDragonEntity) entity;
					if (dragon.getDragonFight() != null && dragon.getDragonFight().hasPreviouslyKilledDragon()) {
						entity.getCommandSenderWorld().setBlockAndUpdate(entity.getCommandSenderWorld().getHeightmapPos(Heightmap.Type.MOTION_BLOCKING, EndPodiumFeature.END_PODIUM_LOCATION), Blocks.DRAGON_EGG.defaultBlockState());
					}
				}
			}
		}
	}

	public static void onMobDrops(LivingDropsEvent event) {
		ItemStack stack;
		ItemEntity drop;

		// ENDER DRAGON
		if (event.getEntityLiving() instanceof EnderDragonEntity) {
			EnderDragonEntity dragon = (EnderDragonEntity) event.getEntityLiving();

			// Drop #1: Ender Dragon Scales
			int amount = 8 + (int) (Math.random() * 6) + (int) (Math.random() * event.getLootingLevel() * 4);
			if (Objects.requireNonNull(dragon.getDragonFight()).hasPreviouslyKilledDragon()) amount /= 2; // Amount is halved with repeat kills.
			stack = new ItemStack(CAItems.ENDER_DRAGON_SCALE.get(), amount);
			drop = new ItemEntity(event.getEntityLiving().level, 0, 90, 0, stack);
			event.getDrops().add(drop);

			// Drop #2: Ender Dragon Head
			double chance = 0.1D + event.getLootingLevel() * 0.1D;
			if (Math.random() < chance && CACommonConfig.COMMON.enderDragonHeadDrop.get()) {
				stack = new ItemStack(Items.DRAGON_HEAD, 1);
				drop = new ItemEntity(event.getEntityLiving().level, 0, 90, 0, stack);
				event.getDrops().add(drop);
			}
		}
	}

	public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		Entity entity = event.getEntity();
		if (entity == null) return;
		if (CACommonConfig.COMMON.showUpdateMessage.get() && VersionChecker.getResult(ModList.get().getModContainerById(ChaosAwakens.MODID).get().getModInfo()).status == VersionChecker.Status.OUTDATED) {
			entity.sendMessage(new StringTextComponent("A new version of ").withStyle(TextFormatting.WHITE)
					.append(new StringTextComponent(ChaosAwakens.MODNAME).withStyle(TextFormatting.BOLD, TextFormatting.GOLD))
					.append(new StringTextComponent(" is now available from: ").withStyle(TextFormatting.WHITE))
					.append(new StringTextComponent("https://chaosawakens.github.io/#downloadsDiv").withStyle((style) -> style.withColor(TextFormatting.GOLD).withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://chaosawakens.github.io/#downloadsDiv")))), Util.NIL_UUID);
		}
		if (IUtilityHelper.isUserOrEntityUUIDEqualTo(entity, UUID.fromString("89cd9d1b-9d50-4502-8bd4-95b9e63ff589"))) { // UUID of Blackout03_
			Objects.requireNonNull(entity.getServer()).getPlayerList().broadcastMessage(new StringTextComponent("The Developer, ").withStyle(TextFormatting.GREEN)
					.append(new StringTextComponent("Blackout03_").withStyle(TextFormatting.BOLD, TextFormatting.DARK_GREEN))
					.append(new StringTextComponent(" has joined the Server!").withStyle(TextFormatting.GREEN)), ChatType.SYSTEM, Util.NIL_UUID);
		} else if (IUtilityHelper.isUserOrEntityUUIDEqualTo(entity, UUID.fromString("29aa413b-d714-46f1-a3f5-68b9c67a4923"))) { // UUID of Ninjaguy169
			Objects.requireNonNull(entity.getServer()).getPlayerList().broadcastMessage(new StringTextComponent("The Developer, ").withStyle(TextFormatting.BLUE)
					.append(new StringTextComponent("Ninjaguy169").withStyle(TextFormatting.BOLD, TextFormatting.DARK_BLUE))
					.append(new StringTextComponent(" has joined the Server!").withStyle(TextFormatting.BLUE)), ChatType.SYSTEM, Util.NIL_UUID);
		} else if (IUtilityHelper.isUserOrEntityUUIDEqualTo(entity, UUID.fromString("2668a475-2166-4539-9935-00f087818c4a"))) { // UUID of T40ne
			Objects.requireNonNull(entity.getServer()).getPlayerList().broadcastMessage(new StringTextComponent("The Owner, ").withStyle(TextFormatting.GOLD)
					.append(new StringTextComponent("T40ne").withStyle(TextFormatting.BOLD, TextFormatting.YELLOW))
					.append(new StringTextComponent(" has joined the Server!").withStyle(TextFormatting.GOLD)), ChatType.SYSTEM, Util.NIL_UUID);
		} else if (IUtilityHelper.isUserOrEntityUUIDEqualTo(entity, UUID.fromString("8c89a0d3-3271-459d-a8c1-a9d34d53365b"))) { // UUID of FunkyMonk127
			Objects.requireNonNull(entity.getServer()).getPlayerList().broadcastMessage(new StringTextComponent("The Owner, ").withStyle(TextFormatting.RED)
					.append(new StringTextComponent("FunkyMonk127").withStyle(TextFormatting.BOLD, TextFormatting.DARK_RED))
					.append(new StringTextComponent(" has joined the Server!").withStyle(TextFormatting.RED)), ChatType.SYSTEM, Util.NIL_UUID);
		}
	}

	public static void onEntityJoin(EntityJoinWorldEvent event) {
		Entity entity = event.getEntity();
		// Make villagers afraid of our entities
		if (entity instanceof VillagerEntity) {
			VillagerEntity villager = (VillagerEntity) entity;
			villager.goalSelector.addGoal(1, new AvoidEntityGoal<>(villager, RoboPounderEntity.class, 24.0F, 0.5D, 0.5D));
			villager.goalSelector.addGoal(1, new AvoidEntityGoal<>(villager, RoboSniperEntity.class, 24.0F, 0.5D, 0.5D));
			villager.goalSelector.addGoal(1, new AvoidEntityGoal<>(villager, RoboWarriorEntity.class, 24.0F, 0.5D, 0.5D));
			villager.goalSelector.addGoal(1, new AvoidEntityGoal<>(villager, RoboPounderEntity.class, 40.0F, 0.5D, 0.5D));
			villager.goalSelector.addGoal(1, new AvoidEntityGoal<>(villager, GiantEntity.class, 32.0F, 0.5D, 0.5D));
		}
		if (entity instanceof WanderingTraderEntity) {
			WanderingTraderEntity wanderingTrader = (WanderingTraderEntity) entity;
			wanderingTrader.goalSelector.addGoal(1, new AvoidEntityGoal<>(wanderingTrader, RoboPounderEntity.class, 24.0F, 0.5D, 0.5D));
			wanderingTrader.goalSelector.addGoal(1, new AvoidEntityGoal<>(wanderingTrader, RoboSniperEntity.class, 24.0F, 0.5D, 0.5D));
			wanderingTrader.goalSelector.addGoal(1, new AvoidEntityGoal<>(wanderingTrader, RoboWarriorEntity.class, 24.0F, 0.5D, 0.5D));
			wanderingTrader.goalSelector.addGoal(1, new AvoidEntityGoal<>(wanderingTrader, RoboPounderEntity.class, 40.0F, 0.5D, 0.5D));
			wanderingTrader.goalSelector.addGoal(1, new AvoidEntityGoal<>(wanderingTrader, GiantEntity.class, 32.0F, 0.5D, 0.5D));
		}
		if (entity instanceof GiantEntity) {
			GiantEntity giant = (GiantEntity) entity;
			giant.goalSelector.addGoal(8, new LookAtGoal(giant, PlayerEntity.class, 24.0F));
			giant.goalSelector.addGoal(8, new LookRandomlyGoal(giant));

			giant.goalSelector.addGoal(2, new MeleeAttackGoal(giant, 1.0F, false));
			giant.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(giant, 1.0F));
			giant.targetSelector.addGoal(2, new HurtByTargetGoal(giant));
			giant.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(giant, PlayerEntity.class, true));
			giant.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(giant, AbstractVillagerEntity.class, false));
			giant.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(giant, IronGolemEntity.class, true));

			Objects.requireNonNull(giant.getAttribute(Attributes.FOLLOW_RANGE)).setBaseValue(100); // FOLLOW_RANGE
			Objects.requireNonNull(giant.getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(0.15F); // MOVEMENT_SPEED
			Objects.requireNonNull(giant.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(20.0D); // ATTACK_DAMAGE
			Objects.requireNonNull(giant.getAttribute(Attributes.ARMOR)).setBaseValue(10.0D); // ARMOR
		}
	}

	public static void onSleepFinished(SleepFinishedTimeEvent event) {
		IWorld world = event.getWorld();
		if (world instanceof ServerWorld) {
			if (world.getLevelData() instanceof DerivedWorldInfo) {
				ServerWorld serverWorld = (ServerWorld) world;
				DerivedWorldInfo derivedWorldInfo = (DerivedWorldInfo) world.getLevelData();
				if (serverWorld.dimension() == CADimensions.CRYSTAL_WORLD || serverWorld.dimension() == CADimensions.MINING_PARADISE || serverWorld.dimension() == CADimensions.VILLAGE_MANIA) {
					derivedWorldInfo.wrapped.setDayTime(event.getNewTime());
				}
			}
		}
	}
}
