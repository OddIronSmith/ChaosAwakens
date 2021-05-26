package io.github.chaosawakens.common.registry;

import io.github.chaosawakens.ChaosAwakens;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CASoundEvents {
	
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ChaosAwakens.MODID);
	
	public static final RegistryObject<SoundEvent> ROBO_SHOOT = SOUND_EVENTS.register("entity.robo.shoot", () -> new SoundEvent( new ResourceLocation(ChaosAwakens.MODID, "entity.robo.shoot")));
	public static final RegistryObject<SoundEvent> ROBO_HURT = SOUND_EVENTS.register("entity.robo.hurt", () -> new SoundEvent( new ResourceLocation(ChaosAwakens.MODID, "entity.robo.hurt")));
	public static final RegistryObject<SoundEvent> ROBO_DEATH = SOUND_EVENTS.register("entity.robo.death", () -> new SoundEvent( new ResourceLocation(ChaosAwakens.MODID, "entity.robo.death")));
}