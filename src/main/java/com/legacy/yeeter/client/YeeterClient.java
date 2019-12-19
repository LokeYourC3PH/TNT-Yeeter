package com.legacy.yeeter.client;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class YeeterClient
{
	public static void initialization(FMLClientSetupEvent event)
	{
		YeeterEntityRendering.init();
	}
}