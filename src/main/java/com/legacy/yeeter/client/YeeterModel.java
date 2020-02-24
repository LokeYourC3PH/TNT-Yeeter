package com.legacy.yeeter.client;

import com.google.common.collect.ImmutableList;
import com.legacy.yeeter.entity.TNTYeeterEntity;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class YeeterModel<T extends TNTYeeterEntity> extends SegmentedModel<T>
{
	public ModelRenderer head;
	public ModelRenderer stonecutter_top;
	public ModelRenderer right_arm2;
	public ModelRenderer right_arm1;
	public ModelRenderer stonecutter_bottom;
	public ModelRenderer left_arm1;
	public ModelRenderer left_arm2;
	public ModelRenderer left_leg1;
	public ModelRenderer right_leg1;
	public ModelRenderer right_leg2;
	public ModelRenderer left_leg2;
	public ModelRenderer tnt;

	public YeeterModel()
	{
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 0.0F, -6.0F);
		this.head.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, 0.0F);
		this.stonecutter_bottom = new ModelRenderer(this, 0, 16);
		this.stonecutter_bottom.setRotationPoint(0.0F, 8.0F, 0.0F);
		this.stonecutter_bottom.addBox(-4.0F, 0.0F, -6.0F, 8, 12, 12, 0.0F);
		this.left_leg1 = new ModelRenderer(this, 0, 40);
		this.left_leg1.mirror = true;
		this.left_leg1.setRotationPoint(4.0F, 14.0F, -0.5F);
		this.left_leg1.addBox(0.0F, -3.0F, -2.5F, 2, 6, 6, 0.0F);
		this.stonecutter_top = new ModelRenderer(this, 0, 16);
		this.stonecutter_top.setRotationPoint(0.0F, 8.0F, 0.0F);
		this.stonecutter_top.addBox(-4.0F, -12.0F, -6.0F, 8, 12, 12, 0.0F);
		this.right_leg1 = new ModelRenderer(this, 0, 40);
		this.right_leg1.mirror = true;
		this.right_leg1.setRotationPoint(-4.0F, 14.0F, -0.5F);
		this.right_leg1.addBox(-2.0F, -3.0F, -2.5F, 2, 6, 6, 0.0F);
		this.right_arm2 = new ModelRenderer(this, 28, 17);
		this.right_arm2.setRotationPoint(-4.0F, 1.5F, -0.5F);
		this.right_arm2.addBox(-2.0F, -10.0F, -1.5F, 2, 7, 4, 0.0F);
		this.right_arm1 = new ModelRenderer(this, 0, 40);
		this.right_arm1.setRotationPoint(-4.0F, 1.5F, -0.5F);
		this.right_arm1.addBox(-2.0F, -3.0F, -2.5F, 2, 6, 6, 0.0F);
		this.left_leg2 = new ModelRenderer(this, 28, 17);
		this.left_leg2.mirror = true;
		this.left_leg2.setRotationPoint(4.0F, 14.0F, -0.5F);
		this.left_leg2.addBox(0.0F, 3.0F, -1.5F, 2, 7, 4, 0.0F);
		this.left_arm1 = new ModelRenderer(this, 0, 40);
		this.left_arm1.setRotationPoint(4.0F, 1.5F, -0.5F);
		this.left_arm1.addBox(0.0F, -3.0F, -2.5F, 2, 6, 6, 0.0F);
		this.left_arm2 = new ModelRenderer(this, 28, 17);
		this.left_arm2.mirror = true;
		this.left_arm2.setRotationPoint(4.0F, 1.5F, -0.5F);
		this.left_arm2.addBox(0.0F, -10.0F, -1.5F, 2, 7, 4, 0.0F);
		this.right_leg2 = new ModelRenderer(this, 28, 17);
		this.right_leg2.setRotationPoint(-4.0F, 14.0F, -0.5F);
		this.right_leg2.addBox(-2.0F, 3.0F, -1.5F, 2, 7, 4, 0.0F);
		this.tnt = new ModelRenderer(this, 40, 0);
		this.tnt.setRotationPoint(0.0F, -8.5F, 0.0F);
		this.tnt.addBox(-8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F);
	}

	@Override
	public Iterable<ModelRenderer> getParts()
	{
		return ImmutableList.of(this.head, this.stonecutter_bottom, this.left_leg1, this.stonecutter_top, this.right_leg1, this.right_arm2, this.right_arm1, this.left_leg2, this.left_arm1, this.left_arm2, this.right_leg2, this.tnt);
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.rotateAngleX = headPitch * 0.017453292F;
		this.head.rotateAngleY = netHeadYaw * 0.017453292F;

		this.stonecutter_top.rotateAngleX = 0;
		this.stonecutter_bottom.rotateAngleX = 0;

		this.right_leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.left_leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

		this.right_leg2.rotateAngleX = this.right_leg1.rotateAngleX;
		this.left_leg2.rotateAngleX = this.left_leg1.rotateAngleX;

		this.left_arm1.rotateAngleX = !((TNTYeeterEntity) entityIn).getArmsRaised() ? MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount + 3.15F : 0.0F;
		this.right_arm1.rotateAngleX = !((TNTYeeterEntity) entityIn).getArmsRaised() ? MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount + 3.15F : 0.0F;

		this.right_arm2.rotateAngleX = this.right_arm1.rotateAngleX;
		this.left_arm2.rotateAngleX = this.left_arm1.rotateAngleX;

		this.tnt.showModel = entityIn.getTNTShown();
	}
}
