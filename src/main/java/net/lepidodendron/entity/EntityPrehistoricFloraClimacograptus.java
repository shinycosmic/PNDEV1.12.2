
package net.lepidodendron.entity;

import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.lepidodendron.block.base.IAdvancementGranter;
import net.lepidodendron.entity.ai.DietString;
import net.lepidodendron.entity.ai.EatItemsEntityPrehistoricFloraJellyfishBaseAI;
import net.lepidodendron.entity.ai.EntityMateAIJellyfishBase;
import net.lepidodendron.entity.ai.JellyfishWander;
import net.lepidodendron.entity.base.EntityPrehistoricFloraJellyfishBase;
import net.lepidodendron.entity.util.EnumCreatureAttributePN;
import net.lepidodendron.entity.util.ITrappableWater;
import net.lepidodendron.util.CustomTrigger;
import net.lepidodendron.util.ModTriggers;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class EntityPrehistoricFloraClimacograptus extends EntityPrehistoricFloraJellyfishBase implements ITrappableWater, IAdvancementGranter {

    public BlockPos currentTarget;
    @SideOnly(Side.CLIENT)
    public ChainBuffer chainBuffer;
    private int animationTick;
    private Animation animation = NO_ANIMATION;
    private int rotationDegree;

    public EntityPrehistoricFloraClimacograptus(World world) {
        super(world);
        setSize(0.1F, 0.15F);
    }

    @Override
    public EnumCreatureAttributePN getPNCreatureAttribute() {
        return EnumCreatureAttributePN.INVERTEBRATE;
    }

    @Override
    public boolean isSmall() {
        return true;
    }

    public static String getPeriod() {return "Ordovician - Silurian";}

    //public static String getHabitat() {return "Aquatic";}

    @Override
    public boolean dropsEggs() {
        return true;
    }

    //Arbitrary for jellyfish as there is no specific AI animation:
    public static final Animation ANIMATION_JELLYFISH_WANDER = Animation.create(0);

    protected void initEntityAI() {
        tasks.addTask(0, new EntityMateAIJellyfishBase(this, 1));
        tasks.addTask(1, new JellyfishWander(this, ANIMATION_JELLYFISH_WANDER));
        this.targetTasks.addTask(0, new EatItemsEntityPrehistoricFloraJellyfishBaseAI(this));
    }

    @Override
    public String[] getFoodOreDicts() {
        return DietString.FISHFOOD;
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
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    protected float getAISpeedJelly() {
        return 0.025f;
    }

    @Override
    public int getAnimationTick() {
        return getAnimationTick();
    }

    @Override
    public void setAnimationTick(int tick)
    {
        animationTick = tick;
    }

    @Override
    public Animation getAnimation()
    {
        return null;
    }

    @Override
    public void setAnimation(Animation animation)
    {
        this.animation = animation;
    }

    @Override
    public Animation[] getAnimations()
    {
        return null;
    }

    @Override
    public boolean isInWater() {
        return super.isInWater() || this.isInsideOfMaterial(Material.WATER) || this.isInsideOfMaterial(Material.CORAL);
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
        //this.renderYawOffset = this.rotationYaw;
    }

    public void onEntityUpdate()
    {

        if (!(rotationDegree > 0)) {
            rotationDegree = 360;
        }
        if (rotationDegree < 1) {
            rotationDegree = 360;
        } else {
            rotationDegree = rotationDegree - 1;
        }

        super.onEntityUpdate();
    }

    public int getRotationDegree() {
        //System.err.println((int) Math.round((double)this.rotationDegree / 1D));
        return (int) Math.round((double)this.rotationDegree / 1D);
    }

    @Override
    protected Item getDropItem() {
        //return new ItemStack(ItemJellyfishMeat.block, (int) (1)).getItem();
        return null;
    }

    @Override
    protected void collideWithEntity(Entity entityIn) {
        super.collideWithEntity(entityIn);
    }

    @Nullable
    @Override
    public CustomTrigger getModTrigger() {
        return ModTriggers.CLICK_CLIMACOGRAPTUS;
    }
}