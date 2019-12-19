package com.legacy.yeeter;

import com.legacy.yeeter.client.YeeterSounds;

import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = "yeeter", bus = Bus.MOD)
public class YeeterRegistryHandler
{

	@SubscribeEvent
	public static void onRegisterSounds(RegistryEvent.Register<SoundEvent> event)
	{
		YeeterSounds.initialization(event.getRegistry());
	}

	@SubscribeEvent
	public static void onRegisterItems(Register<Item> event)
	{
		register(event.getRegistry(), "tnt_yeeter_spawn_egg", new SpawnEggItem(YeeterEntityTypes.TNT_YEETER, 0x919191, 0x936038, new Item.Properties().group(ItemGroup.MISC)));
	}

	@SubscribeEvent
	public static void onRegisterEntityTypes(Register<EntityType<?>> event)
	{
		YeeterEntityTypes.init(event);
		EntitySpawnPlacementRegistry.register(YeeterEntityTypes.TNT_YEETER, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canSpawnOn);
	}
	
	static <T extends IForgeRegistryEntry<T>> void register(IForgeRegistry<T> registry, String name, T object)
	{
		object.setRegistryName(YeeterMod.locate(name));
		registry.register(object);
	}
}