package com.legacy.yeeter;

import com.legacy.yeeter.entity.TNTYeeterEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(YeeterMod.MODID)
public class YeeterEntityTypes
{
	public static final EntityType<TNTYeeterEntity> TNT_YEETER = buildEntity("tnt_yeeter", EntityType.Builder.create(TNTYeeterEntity::new, EntityClassification.MONSTER).size(0.6F, 1.99F));

	public static void init(Register<EntityType<?>> event)
	{
		YeeterRegistryHandler.register(event.getRegistry(), "tnt_yeeter", TNT_YEETER);
	}
	
	private static <T extends Entity> EntityType<T> buildEntity(String key, EntityType.Builder<T> builder)
	{
		return builder.build(YeeterMod.find(key));
	}
}
