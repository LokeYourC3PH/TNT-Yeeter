package com.legacy.yeeter;

import com.legacy.yeeter.client.YeeterClient;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(YeeterMod.MODID)
public class YeeterMod
{
	public static final String MODID = "yeeter";

	public YeeterMod()
	{
		FMLJavaModLoadingContext.get().getModEventBus().addListener(YeeterClient::initialization);
	}

	public static ResourceLocation locate(String name)
	{
		return new ResourceLocation(MODID, name);
	}

	public static String find(String key)
	{
		return new String(MODID + ":" + key);
	}
}
