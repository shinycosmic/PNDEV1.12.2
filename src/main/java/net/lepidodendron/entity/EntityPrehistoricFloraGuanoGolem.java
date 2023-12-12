
package net.lepidodendron.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.block.BlockGuano;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityPrehistoricFloraGuanoGolem extends EntityGolem implements IRangedAttackMob {

	public EntityPrehistoricFloraGuanoGolem(World world) {
		super(world);
		this.setSize(0.95F, 2.2F);
	}

	public static void registerFixesSnowman(DataFixer fixer)
	{
		EntityLiving.registerFixesMob(fixer, EntitySnowman.class);
	}

	protected void initEntityAI()
	{
		this.tasks.addTask(1, new EntityAIAttackRanged(this, 0.7D, 20, 10.0F));
		this.tasks.addTask(2, new EntityAIWanderAvoidWater(this, 0.5D, 1.0000001E-5F));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLiving.class, 10, true, false, IMob.MOB_SELECTOR));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(18.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.055D);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.2D);
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (!this.world.isRemote)
		{
			int i = MathHelper.floor(this.posX);
			int j = MathHelper.floor(this.posY);
			int k = MathHelper.floor(this.posZ);

			if (this.isWet())
			{
				this.attackEntityFrom(DamageSource.DROWN, 1.0F);
			}

			if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this))
			{
				return;
			}

			if (world.getGameRules().getBoolean("mobGriefing")) {
				for (int l = 0; l < 4; ++l) {
					i = MathHelper.floor(this.posX + (double) ((float) (l % 2 * 2 - 1) * 0.25F));
					j = MathHelper.floor(this.posY);
					k = MathHelper.floor(this.posZ + (double) ((float) (l / 2 % 2 * 2 - 1) * 0.25F));
					BlockPos blockpos = new BlockPos(i, j, k);

					if (this.world.getBlockState(blockpos).getMaterial() == Material.AIR && BlockGuano.block.canPlaceBlockAt(this.world, blockpos)) {
						this.world.setBlockState(blockpos, BlockGuano.block.getDefaultState());
					}
				}
			}
		}
	}

	@Nullable
	protected ResourceLocation getLootTable()
	{
		return LepidodendronMod.GUANO_GOLEM_LOOT;
	}

	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor)
	{
		EntityPrehistoricFloraGuanoBall entityguanoball = new EntityPrehistoricFloraGuanoBall(this.world, this);
		double d0 = target.posY + (double)target.getEyeHeight() - 1.100000023841858D;
		double d1 = target.posX - this.posX;
		double d2 = d0 - entityguanoball.posY;
		double d3 = target.posZ - this.posZ;
		float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
		entityguanoball.shoot(d1, d2 + (double)f, d3, 1.6F, 12.0F);
		this.playSound(SoundEvents.ENTITY_SNOWMAN_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
		this.world.spawnEntity(entityguanoball);
	}

	public float getEyeHeight()
	{
		return 1.7F;
	}

	@Nullable
	protected SoundEvent getAmbientSound()
	{
		return SoundEvents.ENTITY_SNOWMAN_AMBIENT;
	}

	@Nullable
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return SoundEvents.ENTITY_SNOWMAN_HURT;
	}

	@Nullable
	protected SoundEvent getDeathSound()
	{
		return SoundEvents.ENTITY_SNOWMAN_DEATH;
	}

	public void setSwingingArms(boolean swingingArms)
	{
	}

}