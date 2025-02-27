package io.github.chaosawakens.common.items.extended;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import io.github.chaosawakens.api.IAutoEnchantable;

import io.github.chaosawakens.api.IUtilityHelper;
import io.github.chaosawakens.common.config.CACommonConfig;
import io.github.chaosawakens.common.util.EnumUtils;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.util.Lazy;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.UUID;

public class RoyalGuardianSwordItem extends SwordItem implements IAutoEnchantable, IVanishable, IAnimatable, IUtilityHelper {
	public AnimationFactory factory = new AnimationFactory(this);
	public static final UUID REACH_MODIFIER = UUID.fromString("AF69D588-EC5D-11EC-8EA0-0242AC120002");
	public static final UUID KB_MODIFIER = UUID.fromString("BB29C4D2-EC5D-11EC-8EA0-0242AC120002");
	public static int attackDamage;
	public static int attackSpeed;
	public static double attackReach;
	public static double attackKnockback;
	private final EnchantmentData[] enchantments;
	public static Lazy<? extends Multimap<Attribute, AttributeModifier>> LAZY = Lazy.of(() -> {
		Multimap<Attribute, AttributeModifier> map;
		ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", attackDamage, AttributeModifier.Operation.ADDITION));
		builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", attackSpeed, AttributeModifier.Operation.ADDITION));
		if (ForgeMod.REACH_DISTANCE.isPresent()) {
			builder.put(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(REACH_MODIFIER, "Weapon modifier", attackReach, AttributeModifier.Operation.ADDITION));
		}
		builder.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(KB_MODIFIER, "Weapon modifier", attackKnockback, AttributeModifier.Operation.ADDITION));
		map = builder.build();
		return map;
	});

    public RoyalGuardianSwordItem(EnumUtils.CAItemTier tierIn, int attackDamageIn, float attackSpeedIn, double attackReachIn, double attackKnockbackIn, Properties builderIn, EnchantmentData[] enchantments) {
        super(tierIn, attackDamage, attackSpeedIn, builderIn);
	    attackDamage = (int) ((float)attackDamageIn + tierIn.getAttackDamageBonus());
	    attackSpeed = (int) attackSpeedIn;
	    attackReach = attackReachIn;
	    attackKnockback = attackKnockbackIn;
        this.enchantments = enchantments;
    }

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
		return slot == EquipmentSlotType.MAINHAND ? LAZY.get() : super.getAttributeModifiers(slot, stack);
	}

	@Override
	public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
		InputEvent.ClickInputEvent inputEvent = ForgeHooksClient.onClickInput(1, new KeyBinding("key.attack", InputMappings.Type.MOUSE, 0, "key.categories.gameplay"), Hand.MAIN_HAND);
		if (entity instanceof PlayerEntity && inputEvent.isAttack()) {
			double reach = entity.getAttributeValue(ForgeMod.REACH_DISTANCE.get());
			double reachSqr = reach * reach;
			World world = entity.level;

			Vector3d viewVec = entity.getViewVector(1.0F);
			Vector3d eyeVec = entity.getEyePosition(1.0F);
			Vector3d targetVec = eyeVec.add(viewVec.x * reach, viewVec.y * reach, viewVec.z * reach);

			AxisAlignedBB bb = entity.getBoundingBox().expandTowards(viewVec.scale(reach)).inflate(4.0D, 4.0D, 4.0D);
			EntityRayTraceResult result = ProjectileHelper.getEntityHitResult(world, entity, eyeVec, targetVec, bb, EntityPredicates.NO_CREATIVE_OR_SPECTATOR);

			if (result == null || !(result.getEntity() instanceof LivingEntity)) return false;

			LivingEntity target = (LivingEntity) result.getEntity();

			double distanceToTargetSqr = entity.distanceToSqr(target);

			boolean resultBool = (result != null ? target : null) != null;

			if (resultBool) {
				if (reachSqr >= distanceToTargetSqr) {
					target.hurt(DamageSource.playerAttack((PlayerEntity) entity), attackDamage);
					this.hurtEnemy(stack, target, entity);
				}
			}
		}
		return super.onEntitySwing(stack, entity);
	}

	@Override
	public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
		if (allowdedIn(group)) {
			ItemStack stack = new ItemStack(this);
			if (CACommonConfig.COMMON.enableAutoEnchanting.get()) {
				for (EnchantmentData enchant : enchantments) {
					stack.enchant(enchant.enchantment, enchant.level);
				}
			}
			items.add(stack);
		}
	}

	@Override
	public void onCraftedBy(ItemStack itemStack, World world, PlayerEntity playerEntity) {
		if (CACommonConfig.COMMON.enableAutoEnchanting.get()) {
			for (EnchantmentData enchant : enchantments) {
				itemStack.enchant(enchant.enchantment, enchant.level);
			}
		}
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return CACommonConfig.COMMON.enableAutoEnchanting.get() && super.isFoil(stack) || super.isFoil(stack);
	}

	@Override
	public EnchantmentData[] enchantments() {
		return this.enchantments;
	}

	@Override
	public void registerControllers(AnimationData data) {
		// insert controllers here
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

	@Override
	public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
		return true;
	}
}
