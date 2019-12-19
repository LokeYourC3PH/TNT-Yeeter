package com.legacy.yeeter.client;

import com.legacy.yeeter.entity.TNTYeeterEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class YeeterRenderer extends MobRenderer<TNTYeeterEntity, YeeterModel<TNTYeeterEntity>>
{

	private static final ResourceLocation TEXTURE = new ResourceLocation("yeeter", "textures/entity/yeeter.png");

	public YeeterRenderer(EntityRendererManager rendermanagerIn)
	{
		super(rendermanagerIn, new YeeterModel<>(), 0.5F);
	}

	@Override
	public ResourceLocation getEntityTexture(TNTYeeterEntity entity)
	{
		return TEXTURE;
	}
}