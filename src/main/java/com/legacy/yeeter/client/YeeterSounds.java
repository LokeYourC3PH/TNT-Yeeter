package com.legacy.yeeter.client;

import com.legacy.yeeter.YeeterMod;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class YeeterSounds 
{

	private static IForgeRegistry<SoundEvent> iSoundRegistry;

	public static SoundEvent YEET;

	public static void initialization(IForgeRegistry<SoundEvent> registry)
	{
		iSoundRegistry = registry;

		YEET = register("entity.yeeter.yeet");
	}

	private static SoundEvent register(String name)
	{
		ResourceLocation location = YeeterMod.locate(name);

		SoundEvent sound = new SoundEvent(location);

		sound.setRegistryName(location);

		iSoundRegistry.register(sound);

		return sound;
	}

}