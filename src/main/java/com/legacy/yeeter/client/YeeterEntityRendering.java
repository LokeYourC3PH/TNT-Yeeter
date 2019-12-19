package com.legacy.yeeter.client;

import com.legacy.yeeter.YeeterEntityTypes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class YeeterEntityRendering
{
	public static void init()
	{
		register(YeeterEntityTypes.TNT_YEETER, YeeterRenderer::new);
	}

	private static <T extends Entity> void register(EntityType<T> entityClass, IRenderFactory<? super T> renderFactory)
	{
		RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
	}
}