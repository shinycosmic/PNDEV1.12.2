
package net.lepidodendron.entity;

import com.google.common.base.Predicate;
import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.ilexiconn.llibrary.server.animation.AnimationHandler;
import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.ai.*;
import net.lepidodendron.entity.base.EntityPrehistoricFloraAgeableFishBase;
import net.lepidodendron.entity.base.EntityPrehistoricFloraFishBase;
import net.lepidodendron.entity.render.entity.RenderAkmonistion;
import net.lepidodendron.entity.render.tile.RenderDisplays;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;

public class EntityPrehistoricFloraAkmonistion extends EntityPrehistoricFloraAgeableFishBase {

	public BlockPos currentTarget;
	@SideOnly(Side.CLIENT)
	public ChainBuffer chainBuffer;

	public EntityPrehistoricFloraAkmonistion(World world) {
		super(world);
		setSize(0.4F, 0.4F);
		minWidth = 0.1F;
		maxWidth = 0.4F;
		maxHeight = 0.4F;
		maxHealthAgeable = 9.0D;
	}

	@Override
	public boolean isSmall() {
		return true;
	}

	public static String getPeriod() {return "Carboniferous";}

	public static String getSize() {return "M";}

	@Override
	public void playLivingSound() {
	}

	@Override
	public boolean dropsEggs() {
		return true;
	}
	
	@Override
	public boolean laysEggs() {
		return false;
	}

	@Override
	public int getAdultAge() {
		return 0;
	} //Only adults!

	@Override
	protected float getAISpeedFish() {
		float AIspeed = 0.224F;
		if (this.getIsFast()) {
			AIspeed = AIspeed * 1.5F;
		}
		return AIspeed;
	}

	@Override
	protected boolean isSlowAtBottom() {
		return false;
	}

	protected void initEntityAI() {
		tasks.addTask(0, new EntityMateAIAgeableBase(this, 1.0D));
		tasks.addTask(1, new EntityTemptAI(this, 1, false, true, (float) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue() * 0.33F));
		tasks.addTask(2, new AttackAI(this, 1.0D, false, this.getAttackLength()));
		tasks.addTask(3, new AgeableFishWander(this, NO_ANIMATION, 1D, 0));
		this.targetTasks.addTask(0, new EatFishItemsAI(this));
		this.targetTasks.addTask(0, new EatMeatItemsAI(this));
		this.targetTasks.addTask(1, new HuntAI(this, EntityPrehistoricFloraFishBase.class, true, (Predicate<Entity>) entity -> entity instanceof EntityLivingBase));
		this.targetTasks.addTask(1, new HuntAI(this, EntitySquid. class, true, (Predicate<Entity>) entity -> entity instanceof EntityLivingBase));
	}

	@Override
	public boolean isBreedingItem(ItemStack stack)
	{
		return (
			(OreDictionary.containsMatch(false, OreDictionary.getOres("listAllfishraw"), stack))
			|| (OreDictionary.containsMatch(false, OreDictionary.getOres("listAllmeatraw"), stack))
		);
	}

	@Override
	public boolean isAIDisabled() {
		return false;
	}

	@Override
	public String getTexture() {
		return this.getTexture();
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEFINED;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	}

	@Override
	public SoundEvent getAmbientSound() {
		return (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation(""));
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.generic.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.generic.death"));
	}

	@Override
	protected float getSoundVolume() {
		return 1.0F;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		this.renderYawOffset = this.rotationYaw;

		if (this.getAnimation() == ATTACK_ANIMATION && this.getAnimationTick() == 5 && this.getAttackTarget() != null) {
			launchAttack();
		}

		AnimationHandler.INSTANCE.updateAnimations(this);

	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (this.getAnimation() == NO_ANIMATION) {
			this.setAnimation(ATTACK_ANIMATION);
			//System.err.println("set attack");
		}
		return false;
	}

	@Override
	public float getAgeScale() {
		return 1;
	}

	public boolean isDirectPathBetweenPoints(Vec3d vec1, Vec3d vec2) {
		RayTraceResult movingobjectposition = this.world.rayTraceBlocks(vec1, new Vec3d(vec2.x, vec2.y, vec2.z), false, true, false);
		return movingobjectposition == null || movingobjectposition.typeOfHit != RayTraceResult.Type.BLOCK;
	}

	@Nullable
	protected ResourceLocation getLootTable() {
		return LepidodendronMod.AKMONISTION_LOOT;
	}

	//Rendering taxidermy:
	//--------------------

	public static double offsetWall() {
		return 0.03;
	}
	public static double upperfrontverticallinedepth() {
		return 1.4;
	}
	public static double upperbackverticallinedepth() {return 0.5;}
	public static double upperfrontlineoffset() {
		return 0.4;
	}
	public static double upperfrontlineoffsetperpendiular() {
		return -0F;
	}
	public static double upperbacklineoffset() {
		return 0.4;
	}
	public static double upperbacklineoffsetperpendiular() {
		return -0.15F;
	}
	public static double lowerfrontverticallinedepth() {
		return 0;
	}
	public static double lowerbackverticallinedepth() {
		return 0.5;
	}
	public static double lowerfrontlineoffset() {
		return 0;
	}
	public static double lowerfrontlineoffsetperpendiular() {
		return -0F;
	}
	public static double lowerbacklineoffset() {
		return 0;
	}
	public static double lowerbacklineoffsetperpendiular() {
		return -0F;
	}
	@SideOnly(Side.CLIENT)
	public static ResourceLocation textureDisplay() {
		return RenderDisplays.TEXTURE_AKMONISTION;
	}
	@SideOnly(Side.CLIENT)
	public static ModelBase modelDisplay() {
		return RenderDisplays.modelAkmonistion;
	}
	public static float getScaler() {
		return RenderAkmonistion.getScaler();
	}

}

