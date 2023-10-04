package net.lepidodendron.entity.ai;

import net.ilexiconn.llibrary.server.animation.Animation;
import net.lepidodendron.entity.base.EntityPrehistoricFloraNautiloidBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

//public class FishWander extends EntityAIBase {
public class NautiloidWander extends AnimationAINoAnimation<EntityPrehistoricFloraNautiloidBase> {

    protected Animation animation;
    protected EntityPrehistoricFloraNautiloidBase PrehistoricFloraNautiloidBase;

    public NautiloidWander(EntityPrehistoricFloraNautiloidBase PrehistoricFloraNautiloidBase, Animation animation)
    {
        super(PrehistoricFloraNautiloidBase);
        setMutexBits(4);
        this.PrehistoricFloraNautiloidBase = PrehistoricFloraNautiloidBase;
        this.animation = animation;
    }

    @Override
    public Animation getAnimation()
    {
        return animation;
    }

    @Override
    public boolean isAutomatic() {
        return true;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
    }

    @Override
    public void updateTask() {
        super.updateTask();

    }

    @Override
    public boolean shouldExecute() {
        if (!this.PrehistoricFloraNautiloidBase.isInWater()) {
            return false;
        }
        if (this.PrehistoricFloraNautiloidBase.getRNG().nextFloat() < 0.5F) {
            Path path = this.PrehistoricFloraNautiloidBase.getNavigator().getPath();
            if (
                    ((!this.PrehistoricFloraNautiloidBase.getNavigator().noPath())
                            && (!isDirectPathBetweenPoints(this.PrehistoricFloraNautiloidBase, this.PrehistoricFloraNautiloidBase.getPositionVector(), new Vec3d(path.getFinalPathPoint().x, path.getFinalPathPoint().y, path.getFinalPathPoint().z))))
                            ||
                            (path != null && path.getFinalPathPoint() != null
                                    && this.PrehistoricFloraNautiloidBase.getDistanceSq(path.getFinalPathPoint().x, path.getFinalPathPoint().y, path.getFinalPathPoint().z) <= Math.pow(this.PrehistoricFloraNautiloidBase.width,2))
            )
            {
                this.PrehistoricFloraNautiloidBase.getNavigator().clearPath();
            }
            if (this.PrehistoricFloraNautiloidBase.getNavigator().noPath()) {
                Vec3d vec3 = this.findWaterTarget();
                if (vec3 != null) {
//                    double Xoffset = this.PrehistoricFloraNautiloidBase.posX - this.PrehistoricFloraNautiloidBase.getPosition().getX();
//                    double Zoffset = this.PrehistoricFloraNautiloidBase.posZ - this.PrehistoricFloraNautiloidBase.getPosition().getZ();

                    this.PrehistoricFloraNautiloidBase.getNavigator().tryMoveToXYZ(vec3.x, vec3.y, vec3.z, 1.0);

                    this.ticksAI = 600;
                    return true;
                }
            }
        }
        //System.err.println("No path found");
        return false;
    }

    public boolean isDirectPathBetweenPoints(Entity entity, Vec3d vec1, Vec3d vec2) {
        RayTraceResult movingobjectposition = entity.world.rayTraceBlocks(vec1, new Vec3d(vec2.x, vec2.y + (double) entity.height * 0.5D, vec2.z), false, true, false);
        return movingobjectposition == null || movingobjectposition.typeOfHit != RayTraceResult.Type.BLOCK;
    }

    @Override
    public boolean shouldContinueExecuting() {

        this.ticksAI --;
        if (!(this.ticksAI > 0)) {
            this.PrehistoricFloraNautiloidBase.getNavigator().clearPath();
        }

        return false;
    }

    public Vec3d findWaterTarget() {
        Random rand = this.PrehistoricFloraNautiloidBase.getRNG();
        if (this.PrehistoricFloraNautiloidBase.getAttackTarget() == null) {
            for (int i = 0; i < 10; i++) {
                Vec3d randPos = this.PrehistoricFloraNautiloidBase.getPositionVector().add(rand.nextInt(17) - 8, rand.nextInt(9) - 4, rand.nextInt(17) - 8);
                //System.err.println("Target " + randPos.getX() + " " + this.PrehistoricFloraFishBase.getPosition().getY() + " " + randPos.getZ());
                if (this.PrehistoricFloraNautiloidBase.world.getBlockState(new BlockPos(randPos)).getMaterial() == Material.WATER && this.PrehistoricFloraNautiloidBase.isDirectPathBetweenPoints(this.PrehistoricFloraNautiloidBase.getPositionVector(), new Vec3d(randPos.x, randPos.y, randPos.z))) {
                    if (!(randPos.y < 1 || randPos.y >= 254)) {
                        return randPos;
                    }
                }
            }
        } else {
            Vec3d blockpos1;
            blockpos1 = this.PrehistoricFloraNautiloidBase.getAttackTarget().getPositionVector();
            if (this.PrehistoricFloraNautiloidBase.world.getBlockState(new BlockPos(blockpos1)).getMaterial() == Material.WATER) {
                return blockpos1;
            }
        }
        return null;
    }
}