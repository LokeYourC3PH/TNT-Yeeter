package com.legacy.yeeter;

import com.legacy.yeeter.client.YeeterClient;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("yeeter")
public class YeeterMod
{
	public YeeterMod()
	{
		FMLJavaModLoadingContext.get().getModEventBus().addListener(YeeterClient::initialization);
	}

	public static ResourceLocation locate(String name)
	{
		return new ResourceLocation("yeeter", name);
	}

	public static String find(String key)
	{
		return new String("yeeter:" + key);
	}
}
