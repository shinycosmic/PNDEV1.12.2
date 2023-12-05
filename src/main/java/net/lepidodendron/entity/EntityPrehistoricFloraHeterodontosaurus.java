
package net.lepidodendron.entity;

import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationHandler;
import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.block.base.IAdvancementGranter;
import net.lepidodendron.entity.ai.*;
import net.lepidodendron.entity.base.EntityPrehistoricFloraAgeableBase;
import net.lepidodendron.entity.base.EntityPrehistoricFloraLandBase;
import net.lepidodendron.entity.util.IScreamer;
import net.lepidodendron.util.CustomTrigger;
import net.lepidodendron.util.ModTriggers;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.entity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nullable;
import java.util.List;

public class EntityPrehistoricFloraHeterodontosaurus extends EntityPrehistoricFloraLandBase implements IScreamer, IAdvancementGranter {

	public BlockPos currentTarget;
	@SideOnly(Side.CLIENT)
	public ChainBuffer chainBuffer;
	private int inPFLove;
	public ChainBuffer tailBuffer;

	public Animation STAND_ANIMATION;
	public Animation DIG_ANIMATION;
	public Animation CHATTER_ANIMATION;
	private int standCooldown;
	private boolean screaming;
	private int alarmCooldown;

	private static final DataParameter<Boolean> HOPPING = EntityDataManager.createKey(EntityPrehistoricFloraHeterodontosaurus.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> HOPCOOLDOWN = EntityDataManager.createKey(EntityPrehistoricFloraHeterodontosaurus.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> WANDERCOOLDOWN = EntityDataManager.createKey(EntityPrehistoricFloraHeterodontosaurus.class, DataSerializers.VARINT);


	public EntityPrehistoricFloraHeterodontosaurus(World world) {
		super(world);
		setSize(0.375F, 0.5F);
		minWidth = 0.12F;
		maxWidth = 0.375F;
		maxHeight = 0.5F;
		maxHealthAgeable = 10.0D;
		STAND_ANIMATION = Animation.create(60);
		DIG_ANIMATION = Animation.create(50);
		CHATTER_ANIMATION = Animation.create(20);
		if (FMLCommonHandler.instance().getSide().isClient()) {
			tailBuffer = new ChainBuffer();
		}
	}

	//***********************
	//Hopping managers:

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(HOPPING, false);
		this.dataManager.register(HOPCOOLDOWN, 0);
		this.dataManager.register(WANDERCOOLDOWN, 0);
	}

	public int getWanderCooldown() {
		return this.dataManager.get(WANDERCOOLDOWN);
	}

	public void setWanderCooldown(int WanderCooldown) {
		this.dataManager.set(WANDERCOOLDOWN, WanderCooldown);
	}

	public int getHopCooldown() {
		return this.dataManager.get(HOPCOOLDOWN);
	}

	public void setHopCooldown(int HopCooldown) {
		this.dataManager.set(HOPCOOLDOWN, HopCooldown);
	}

	public boolean getIsHopping() {
		return this.dataManager.get(HOPPING);
	}

	public void setHopping(boolean hopping) {
		this.dataManager.set(HOPPING, hopping);
	}

	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		this.setHopCooldown(compound.getInteger("HopCooldown"));
		this.setWanderCooldown(compound.getInteger("WanderCooldown"));
	}

	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setInteger("HopCooldown", this.getHopCooldown());
		compound.setInteger("WanderCooldown", this.getWanderCooldown());
	}

	public int defaultHopCooldown() {
		return 250;
	}

	public int defaultWanderCooldown() {
		return 1000;
	}

	@Override
	public void onEntityUpdate() {
		if (this.alarmCooldown > 0) {
			this.alarmCooldown -= 1;
		}
		if (this.getScreaming() && alarmCooldown <= 0) {
			this.playAlarmSound();
		}
		super.onEntityUpdate();

		//Sometimes stand up and look around:
		if ((!this.world.isRemote) && this.getEatTarget() == null && this.getAttackTarget() == null && this.getRevengeTarget() == null
				&& !this.getIsMoving() && this.getAnimation() == NO_ANIMATION && standCooldown == 0) {

			int next = rand.nextInt(2);
			if (next < 1) {
				this.setAnimation(STAND_ANIMATION);
			} else {
				this.setAnimation(DIG_ANIMATION);
			}
			this.standCooldown = 2000;
		}
		//forces animation to return to base pose by grabbing the last tick and setting it to that.
		if ((!this.world.isRemote) && this.getAnimation() == STAND_ANIMATION && this.getAnimationTick() == STAND_ANIMATION.getDuration() - 1) {
			this.standCooldown = 2000;
			this.setAnimation(NO_ANIMATION);
		}
		if ((!this.world.isRemote) && this.getAnimation() == DIG_ANIMATION && this.getAnimationTick() == DIG_ANIMATION.getDuration() - 1) {
			this.standCooldown = 2000;
			this.setAnimation(NO_ANIMATION);
		}

		if (!this.world.isRemote) {
			if (this.getIsHopping() && this.getWanderCooldown() <= 0) {
				this.setHopping(false);
				this.setHopCooldown(this.defaultHopCooldown() + rand.nextInt(500));
				this.setWanderCooldown(this.defaultWanderCooldown() + rand.nextInt(500));
			} else {
				if (this.getHopCooldown() >= 0 && !this.getIsHopping()) {
					this.setHopCooldown(this.getHopCooldown() - 1);
				}
				if (this.getWanderCooldown() >= 0 && this.getIsHopping()) {
					this.setWanderCooldown(this.getWanderCooldown() - 1);
				}
			}
		}
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (this.getAnimation() == ATTACK_ANIMATION && this.getAnimationTick() == 11 && this.getAttackTarget() != null) {
			launchAttack();
		}

		if (this.standCooldown > 0) {
			this.standCooldown -= rand.nextInt(3) + 1;
		}
		if (this.standCooldown < 0) {
			this.standCooldown = 0;
		}

		if (!this.world.isRemote) {
			if (this.getHopCooldown() <= 0) {
				setHopping(true);
				this.setHopCooldown(this.defaultHopCooldown() + rand.nextInt(500));
				this.setWanderCooldown(this.defaultWanderCooldown() + rand.nextInt(500));
			}

			if (this.getWanderCooldown() <= 0) {
				setHopping(false);
				this.setHopCooldown(this.defaultHopCooldown() + rand.nextInt(500));
				this.setWanderCooldown(this.defaultWanderCooldown() + rand.nextInt(500));
			}

			if (this.getIsHopping() && (this.getIsFast() || this.isReallyInWater() || this.getScreaming())) {
				setHopping(false);
				this.setHopCooldown(this.defaultHopCooldown() + rand.nextInt(500));
				this.setWanderCooldown(this.defaultWanderCooldown() + rand.nextInt(500));
			}

		}

		AnimationHandler.INSTANCE.updateAnimations(this);

	}

	//End of hopping managers
	//***********************

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (world.isRemote && !this.isAIDisabled()) {
			tailBuffer.calculateChainSwingBuffer(60, 10, 5F, this);
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource ds, float i) {
		Entity e = ds.getTrueSource();
		if (e instanceof EntityLivingBase) {
			EntityLivingBase ee = (EntityLivingBase) e;
			List<EntityPrehistoricFloraHeterodontosaurus> Heterodontosaurus = this.world.getEntitiesWithinAABB(EntityPrehistoricFloraHeterodontosaurus.class, new AxisAlignedBB(this.getPosition().add(-8, -4, -8), this.getPosition().add(8, 4, 8)));
			for (EntityPrehistoricFloraHeterodontosaurus currentHeterodontosaurus : Heterodontosaurus) {
				currentHeterodontosaurus.setRevengeTarget(ee);
				currentHeterodontosaurus.alarmCooldown = rand.nextInt(20);
			}
		}
		return super.attackEntityFrom(ds, i);
	}

	@Override
	public int getEggType(@Nullable String variantIn) {
		return 0; //small
	}

	public void setScreaming(boolean screaming) {
		this.screaming = screaming;
	}

	public boolean getScreaming() {
		return this.screaming;
	}

	@Nullable
	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}

	public static String getPeriod() {
		return "Jurassic";
	}

	//public static String getHabitat() {return "Terrestrial Therapsid";}

	@Override
	public boolean hasNest() {
		return true;
	}

	@Override
	public int getAttackLength() {
		return 18;
	}

	@Override
	public int getRoarLength() {
		return 20;
	}

	@Override
	public String getTexture() {
		return this.getTexture();
	}

	@Override
	public boolean dropsEggs() {
		return false;
	}

	@Override
	public boolean laysEggs() {
		return true;
	}

	public float getAISpeedLand() {
		float speedBase = 0.22F;
		if (this.getTicks() < 0) {
			return 0.0F; //Is laying eggs
		}
		if (this.getAnimation() == DRINK_ANIMATION || this.getAnimation() == MAKE_NEST_ANIMATION ||this.getAnimation() == DIG_ANIMATION ||this.getAnimation() == STAND_ANIMATION) {
			return 0.0F;
		}
		if (this.getIsFast()) {
			speedBase = speedBase * 2.65F;
		}
		if (this.getIsHopping()) {
			if (!this.getMovingOnLand()) {
				speedBase = 0.03F; //static moment of the hop animation
			} else {
				speedBase = speedBase * 1.8F; //The moving part of the hop animation
			}
		}
		return speedBase * 1.6F;
	}

	public boolean getMovingOnLand() {
		int animCycle = 10;
		double tickAnim = (this.ticksExisted + this.getTickOffset()) - (int) (Math.floor((double) (this.ticksExisted + this.getTickOffset()) / (double) animCycle) * (double) animCycle);
		if ((tickAnim >= 3 && tickAnim <= 9)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getTalkInterval() {
		return 80;
	}

	@Override
	public int getAdultAge() {
		return 64000;
	}

	public AxisAlignedBB getAttackBoundingBox() {
		float size = this.getRenderSizeModifier() * 0.25F;
		return this.getEntityBoundingBox().grow(1.0F + size, 1.0F + size, 1.0F + size);
	}

	@Override
	public float getEyeHeight() {
		return Math.max(super.getEyeHeight(), this.height * 1.05F);
	}

	protected void initEntityAI() {
		tasks.addTask(0, new EntityMateAIAgeableBase(this, 1.0D));
		tasks.addTask(1, new EntityTemptAI(this, 1, false, true, 0));
		tasks.addTask(2, new LandEntitySwimmingAI(this, 0.75, false));
		tasks.addTask(3, new AttackAI(this, 1.0D, false, this.getAttackLength()));
		tasks.addTask(4, new PanicScreamAI(this, 1.0));
		tasks.addTask(5, new LandWanderNestAI(this));
		tasks.addTask(6, new LandWanderFollowParent(this, 1.05D));
		tasks.addTask(7, new LandWanderAvoidWaterAI(this, 1.0D, 60));
		tasks.addTask(8, new EntityWatchClosestAI(this, EntityPlayer.class, 6.0F));
		tasks.addTask(9, new EntityWatchClosestAI(this, EntityPrehistoricFloraAgeableBase.class, 8.0F));
		tasks.addTask(10, new EntityLookIdleAI(this));
		this.targetTasks.addTask(0, new EatItemsEntityPrehistoricFloraAgeableBaseAI(this, 1));
		this.targetTasks.addTask(1, new EntityHurtByTargetSmallerThanMeAI(this, false));
	}

	@Override
	public String[] getFoodOreDicts() {
		return ArrayUtils.addAll(DietString.PLANTS);
	}
  
	public boolean panics() {
		return true;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEFINED;
	}

	@Override
	public boolean drinksWater() {
		return true; //grazes, does not drink
	}

	@Override
	public int getDrinkLength() {
		return 40;  //grazes, does not drink
	}

	@Override
	public int getDrinkCooldown() {
		return 400;
	}

	@Override
	public boolean isDrinking() {
		boolean test = (this.getPFDrinking() <= 0
				&& !world.isRemote
				&& !this.getIsFast()
				&& !this.getIsMoving()
				&& this.DRINK_ANIMATION.getDuration() > 0
				&& this.getAnimation() == NO_ANIMATION
				&& !this.isReallyInWater()
				&&
				(this.world.getBlockState(this.getPosition().north().down()).getMaterial() == Material.WATER
						|| this.world.getBlockState(this.getPosition().south().down()).getMaterial() == Material.WATER
						|| this.world.getBlockState(this.getPosition().east().down()).getMaterial() == Material.WATER
						|| this.world.getBlockState(this.getPosition().west().down()).getMaterial() == Material.WATER
				)
		);
		if (test) {
			//Which one is water?
			EnumFacing facing = null;
			if (this.world.getBlockState(this.getPosition().north().down()).getMaterial() == Material.WATER) {
				facing = EnumFacing.NORTH;
			} else if (this.world.getBlockState(this.getPosition().south().down()).getMaterial() == Material.WATER) {
				facing = EnumFacing.SOUTH;
			} else if (this.world.getBlockState(this.getPosition().east().down()).getMaterial() == Material.WATER) {
				facing = EnumFacing.EAST;
			} else if (this.world.getBlockState(this.getPosition().west().down()).getMaterial() == Material.WATER) {
				facing = EnumFacing.WEST;
			}
			if (facing != null) {
				this.setDrinkingFrom(this.getPosition().offset(facing));
				this.faceBlock(this.getDrinkingFrom(), 10F, 10F);
			}
		}
		return test;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	}

	@Override
	public Animation[] getAnimations() {
		return new Animation[]{CHATTER_ANIMATION, DRINK_ANIMATION, GRAZE_ANIMATION, ATTACK_ANIMATION, ROAR_ANIMATION, LAY_ANIMATION, EAT_ANIMATION, MAKE_NEST_ANIMATION, DIG_ANIMATION, STAND_ANIMATION};
	}

	@Override
	public SoundEvent getAmbientSound() {
		return (SoundEvent) SoundEvent.REGISTRY
				.getObject(new ResourceLocation("lepidodendron:heterodontosaurus_idle"));
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return (SoundEvent) SoundEvent.REGISTRY
				.getObject(new ResourceLocation("lepidodendron:heterodontosaurus_hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return (SoundEvent) SoundEvent.REGISTRY
				.getObject(new ResourceLocation("lepidodendron:heterodontosaurus_death"));
	}

	public SoundEvent getAlarmSound() {
		return (SoundEvent) SoundEvent.REGISTRY
				.getObject(new ResourceLocation("lepidodendron:heterodontosaurus_alarm"));
	}

	public void playAlarmSound() {
		SoundEvent soundevent = this.getAlarmSound();
		//System.err.println("looking for alarm sound");
		if (soundevent != null) {
			//System.err.println("playing alarm sound");
			this.setAnimation(CHATTER_ANIMATION);
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch());
			this.alarmCooldown = 20;
		}
	}

	@Override
	protected float getSoundVolume() {
		return 1.0F;
	}

	@Override
	public boolean getCanSpawnHere() {
		return this.posY < (double) this.world.getSeaLevel() && this.isInWater();
	}

	public static final PropertyDirection FACING = BlockDirectional.FACING;

	public boolean testLay(World world, BlockPos pos) {
		//System.err.println("Testing laying conditions");
		BlockPos posNest = pos;
		if (isLayableNest(world, posNest)) {
			String eggRenderType = new Object() {
				public String getValue(BlockPos posNest, String tag) {
					TileEntity tileEntity = world.getTileEntity(posNest);
					if (tileEntity != null)
						return tileEntity.getTileData().getString(tag);
					return "";
				}
			}.getValue(new BlockPos(posNest), "egg");

			//System.err.println("eggRenderType " + eggRenderType);

			if (eggRenderType.equals("")) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (this.getAnimation() == NO_ANIMATION) {
			this.setAnimation(ATTACK_ANIMATION);
			//System.err.println("set attack");
		}
		return false;
	}

	public boolean isDirectPathBetweenPoints(Vec3d vec1, Vec3d vec2) {
		RayTraceResult movingobjectposition = this.world.rayTraceBlocks(vec1, new Vec3d(vec2.x, vec2.y, vec2.z), false, true, false);
		return movingobjectposition == null || movingobjectposition.typeOfHit != RayTraceResult.Type.BLOCK;
	}

	@Nullable
	@Override
	public CustomTrigger getModTrigger() {
		return ModTriggers.CLICK_HETERODONTOSAURUS;
	}
	@Nullable
	protected ResourceLocation getLootTable() {
		if (!this.isPFAdult()) {
			return LepidodendronMod.HETERODONTOSAURUS_LOOT_YOUNG;
		}
		return LepidodendronMod.HETERODONTOSAURUS_LOOT;
	}

	//Rendering taxidermy:
	//--------------------
	
}
