package net.lepidodendron.entity.ai;

import net.lepidodendron.entity.base.EntityPrehistoricFloraAgeableBase;
import net.lepidodendron.entity.base.EntityPrehistoricFloraAgeableFlyingBase;
import net.lepidodendron.entity.base.EntityPrehistoricFloraLandBase;
import net.lepidodendron.entity.base.EntityPrehistoricFloraLandClimbingFlyingBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.EnumFacing;

public class EntityLookIdleAI extends EntityAIBase
{
    private final EntityLiving idleEntity;
    private double lookX;
    private double lookZ;
    private int idleTime;
    private boolean noSpin;

    public EntityLookIdleAI(EntityLiving entitylivingIn)
    {
        this.idleEntity = entitylivingIn;
        this.setMutexBits(3);
        this.noSpin = false;
    }

    public EntityLookIdleAI(EntityLiving entitylivingIn, boolean nospinIn)
    {
        this.idleEntity = entitylivingIn;
        this.setMutexBits(3);
        this.noSpin = nospinIn;
    }

    public boolean shouldExecute()
    {
        if (noSpin && this.idleEntity instanceof EntityPrehistoricFloraAgeableBase) {
            if (!((EntityPrehistoricFloraAgeableBase)this.idleEntity).getIsMoving()) {
                return false;
            }
        }
        if (this.idleEntity instanceof EntityPrehistoricFloraLandBase) {
            EntityPrehistoricFloraLandBase landbase = (EntityPrehistoricFloraLandBase) this.idleEntity;
            if (landbase.getAnimation() != landbase.NO_ANIMATION) {
                return false;
            }
        }
        if (this.idleEntity instanceof EntityPrehistoricFloraAgeableFlyingBase) {
            EntityPrehistoricFloraAgeableFlyingBase flybase = (EntityPrehistoricFloraAgeableFlyingBase) this.idleEntity;
            if (flybase.isReallyFlying()) {
                return false;
            }
        }

        if (this.idleEntity instanceof EntityPrehistoricFloraLandClimbingFlyingBase) {
            EntityPrehistoricFloraLandClimbingFlyingBase flybase = (EntityPrehistoricFloraLandClimbingFlyingBase) this.idleEntity;
            if (flybase.getAttachmentPos() != null) {
                if (flybase.getAttachmentFacing() != EnumFacing.UP && flybase.getAttachmentFacing() != EnumFacing.DOWN)
                return false;
            }
        }

        return this.idleEntity.getRNG().nextFloat() < 0.02F;
    }

    public boolean shouldContinueExecuting()
    {
        return this.idleTime >= 0;
    }

    public void startExecuting()
    {
        double d0 = (Math.PI * 2D) * this.idleEntity.getRNG().nextDouble();
        this.lookX = Math.cos(d0);
        this.lookZ = Math.sin(d0);
        this.idleTime = 20 + this.idleEntity.getRNG().nextInt(20);
    }

    public void updateTask()
    {
        --this.idleTime;
        this.idleEntity.getLookHelper().setLookPosition(this.idleEntity.posX + this.lookX, this.idleEntity.posY + (double)this.idleEntity.getEyeHeight(), this.idleEntity.posZ + this.lookZ, (float)this.idleEntity.getHorizontalFaceSpeed(), (float)this.idleEntity.getVerticalFaceSpeed());
    }
}
