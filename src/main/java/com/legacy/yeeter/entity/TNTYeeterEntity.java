package com.legacy.yeeter.entity;

import com.legacy.yeeter.client.YeeterSounds;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NonTamedTargetGoal;
import net.minecraft.entity.ai.goal.OwnerHurtByTargetGoal;
import net.minecraft.entity.ai.goal.OwnerHurtTargetGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.ai.goal.SitGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class TNTYeeterEntity extends TameableEntity implements IRangedAttackMob
{
	private static final DataParameter<Boolean> ARMS_RAISED = EntityDataManager.<Boolean>createKey(TNTYeeterEntity.class, DataSerializers.BOOLEAN);

	private static final DataParameter<Boolean> TNT_SHOWN = EntityDataManager.<Boolean>createKey(TNTYeeterEntity.class, DataSerializers.BOOLEAN);

	private int tntTicks;

	public TNTYeeterEntity(EntityType<? extends TNTYeeterEntity> p_i50205_1_, World p_i50205_2_)
	{
		super(p_i50205_1_, p_i50205_2_);
	}

	protected void registerGoals()
	{
		this.sitGoal = new SitGoal(this);
		this.goalSelector.addGoal(2, this.sitGoal);
		this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, WolfEntity.class, 6.0F, 1.0D, 1.2D));
		this.goalSelector.addGoal(3, new RangedAttackGoal(this, 1.25D, 60, 20.0F));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
		this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
		this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setCallsForHelp());
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NonTamedTargetGoal<>(this, PlayerEntity.class, true, null));
		this.targetSelector.addGoal(3, new NonTamedTargetGoal<>(this, IronGolemEntity.class, true, null));
		this.targetSelector.addGoal(3, new NonTamedTargetGoal<>(this, CowEntity.class, true, null));
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn)
	{
		return 1.5F;
	}

	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return SoundEvents.ENTITY_SKELETON_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return SoundEvents.ENTITY_SKELETON_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return SoundEvents.ENTITY_SKELETON_DEATH;
	}

	@Override
	public void setAttackTarget(LivingEntity entitylivingbaseIn)
	{
		super.setAttackTarget(entitylivingbaseIn);

		if (entitylivingbaseIn != null)
		{
			this.setArmsRaised(true);

			if (this.tntTicks == 0)
				this.setTNTShown(true);
		}
		else
		{
			this.setArmsRaised(false);
			this.setTNTShown(false);
		}
	}

	@Override
	public void tick()
	{
		super.tick();

		if (this.tntTicks > 0)
			--this.tntTicks;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if (this.isInvulnerableTo(source))
		{
			return false;
		}
		else if (source.getImmediateSource() instanceof TNTYeeterEntity)
		{
			return false;
		}
		else
		{
			return super.attackEntityFrom(source, amount);
		}
	}

	@Override
	public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor)
	{
		double xPos = this.getPosition().getX();
		double yPos = this.getPosition().getY() + 2.0F;
		double zPos = this.getPosition().getZ();

		TNTEntity llamaspitentity = new TNTEntity(this.world, xPos, yPos, zPos, this);

		double d0 = target.getPosition().getX() - this.getPosition().getX();
		double d1 = target.getBoundingBox().minY + (double) (target.getHeight() / 3.0F - llamaspitentity.getPosition().getY() + 15.0F);
		double d2 = target.getPosition().getZ() - this.getPosition().getZ();

		this.world.playSound((PlayerEntity) null, this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getZ(), SoundEvents.ENTITY_TNT_PRIMED, this.getSoundCategory(), 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
		this.world.playSound((PlayerEntity) null, this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getZ(), YeeterSounds.YEET, this.getSoundCategory(), 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);

		float inaccuracy = 1.0F;

		float velocity = 0.7F;

		Vec3d vec3d = (new Vec3d(d0, d1, d2)).normalize().add(this.rand.nextGaussian() * (double) 0.0075F * (double) inaccuracy, this.rand.nextGaussian() * (double) 0.0075F * (double) inaccuracy, this.rand.nextGaussian() * (double) 0.0075F * (double) inaccuracy).scale((double) velocity);
		llamaspitentity.setMotion(vec3d);
		float f1 = MathHelper.sqrt(horizontalMag(vec3d));
		llamaspitentity.rotationYaw = (float) (MathHelper.atan2(vec3d.x, d2) * (double) (180F / (float) Math.PI));
		llamaspitentity.rotationPitch = (float) (MathHelper.atan2(vec3d.y, (double) f1) * (double) (180F / (float) Math.PI));
		llamaspitentity.prevRotationYaw = this.rotationYaw;
		llamaspitentity.prevRotationPitch = this.rotationPitch;

		llamaspitentity.setFuse(40);

		this.setTNTShown(false);

		this.tntTicks = 20;

		this.world.addEntity(llamaspitentity);

	}

	@Override
	protected void registerData()
	{
		super.registerData();
		this.dataManager.register(ARMS_RAISED, Boolean.valueOf(false));
		this.dataManager.register(TNT_SHOWN, Boolean.valueOf(false));
	}

	@Override
	public void writeAdditional(CompoundNBT compound)
	{
		super.writeAdditional(compound);
		compound.putBoolean("ArmsRaised", this.getArmsRaised());
		compound.putBoolean("TNTShown", this.getTNTShown());
	}

	@Override
	public void read(CompoundNBT compound)
	{
		super.read(compound);
		this.setArmsRaised(compound.getBoolean("ArmsRaised"));
		this.setTNTShown(compound.getBoolean("TNTShown"));
	}

	public boolean getArmsRaised()
	{
		return ((Boolean) this.dataManager.get(ARMS_RAISED)).booleanValue();
	}

	public void setArmsRaised(boolean raised)
	{
		this.dataManager.set(ARMS_RAISED, Boolean.valueOf(raised));
	}

	public boolean getTNTShown()
	{
		return ((Boolean) this.dataManager.get(TNT_SHOWN)).booleanValue();
	}

	public void setTNTShown(boolean raised)
	{
		this.dataManager.set(TNT_SHOWN, Boolean.valueOf(raised));
	}

	@Override
	public boolean processInteract(PlayerEntity player, Hand hand)
	{
		ItemStack itemstack = player.getHeldItem(hand);
		Item item = itemstack.getItem();
		if (this.isTamed())
		{
			if (this.isOwner(player) && !this.world.isRemote)
			{
				this.sitGoal.setSitting(!this.isSitting());

				this.world.playSound((PlayerEntity) null, this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getZ(), SoundEvents.BLOCK_FIRE_EXTINGUISH, this.getSoundCategory(), 1.0F, this.isSitting() ? 1.3F : 0.7F);

				this.isJumping = false;
				this.navigator.clearPath();
				this.setAttackTarget((LivingEntity) null);
			}
		}
		else if (item == Items.GUNPOWDER)
		{
			if (!player.abilities.isCreativeMode)
			{
				itemstack.shrink(1);
			}

			if (!this.world.isRemote)
			{
				if (this.rand.nextInt(3) == 0 && !ForgeEventFactory.onAnimalTame(this, player))
				{
					this.setTamedBy(player);
					this.navigator.clearPath();
					this.setAttackTarget((LivingEntity) null);
					this.sitGoal.setSitting(true);
					this.setHealth(this.getMaxHealth());
					this.playTameEffect(true);
					this.world.setEntityState(this, (byte) 7);
					this.world.playSound((PlayerEntity) null, this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getZ(), SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, this.getSoundCategory(), 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
				}
				else
				{
					this.playTameEffect(false);
					this.world.setEntityState(this, (byte) 6);
				}
			}

			return true;
		}

		return super.processInteract(player, hand);
	}

	@Override
	public AgeableEntity createChild(AgeableEntity ageable)
	{
		return null;
	}
}