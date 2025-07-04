package net.lepidodendron.entity.model.entity;

import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.lepidodendron.entity.model.ModelBasePalaeopedia;
import net.minecraft.client.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

public class ModelAcrognathus extends ModelBasePalaeopedia {
    private final AdvancedModelRenderer main;
    private final AdvancedModelRenderer cube_r1;
    private final AdvancedModelRenderer cube_r2;
    private final AdvancedModelRenderer cube_r3;
    private final AdvancedModelRenderer cube_r4;
    private final AdvancedModelRenderer cube_r5;
    private final AdvancedModelRenderer cube_r6;
    private final AdvancedModelRenderer cube_r7;
    private final AdvancedModelRenderer cube_r8;
    private final AdvancedModelRenderer cube_r9;
    private final AdvancedModelRenderer cube_r10;
    private final AdvancedModelRenderer cube_r11;
    private final AdvancedModelRenderer frontLeftFin;
    private final AdvancedModelRenderer frontRightFin;
    private final AdvancedModelRenderer backLeftFin;
    private final AdvancedModelRenderer backRightFin;
    private final AdvancedModelRenderer tail;
    private final AdvancedModelRenderer cube_r12;
    private final AdvancedModelRenderer cube_r13;
    private final AdvancedModelRenderer tail2;
    private final AdvancedModelRenderer cube_r14;
    private final AdvancedModelRenderer cube_r15;
    private final AdvancedModelRenderer tail3;
    private final AdvancedModelRenderer jaw;
    private final AdvancedModelRenderer cube_r16;

    public ModelAcrognathus() {
        this.textureWidth = 48;
        this.textureHeight = 48;

        this.main = new AdvancedModelRenderer(this);
        this.main.setRotationPoint(0.0F, 25.0F, 0.0F);
        this.main.cubeList.add(new ModelBox(main, 13, 9, -1.5F, -6.0F, -4.0F, 3, 3, 3, 0.003F, false));
        this.main.cubeList.add(new ModelBox(main, 33, 22, -1.0F, -5.5F, -6.0F, 2, 2, 2, 0.006F, false));
        this.main.cubeList.add(new ModelBox(main, 13, 0, -1.5F, -6.5F, -2.1F, 3, 4, 4, 0.0F, false));

        this.cube_r1 = new AdvancedModelRenderer(this);
        this.cube_r1.setRotationPoint(-1.0F, -5.3288F, -7.3046F);
        this.main.addChild(cube_r1);
        this.setRotateAngle(cube_r1, 0.768F, -0.0964F, 0.0887F);
        this.cube_r1.cubeList.add(new ModelBox(cube_r1, 25, 36, 0.0F, 0.0F, -0.5F, 1, 3, 1, 0.0F, true));

        this.cube_r2 = new AdvancedModelRenderer(this);
        this.cube_r2.setRotationPoint(1.0F, -5.3288F, -7.3046F);
        this.main.addChild(cube_r2);
        this.setRotateAngle(cube_r2, 0.768F, 0.0964F, -0.0887F);
        this.cube_r2.cubeList.add(new ModelBox(cube_r2, 25, 36, -1.0F, 0.0F, -0.5F, 1, 3, 1, 0.0F, false));

        this.cube_r3 = new AdvancedModelRenderer(this);
        this.cube_r3.setRotationPoint(-0.5F, -5.6975F, -6.9669F);
        this.main.addChild(cube_r3);
        this.setRotateAngle(cube_r3, 0.829F, 0.0F, 0.0F);
        this.cube_r3.cubeList.add(new ModelBox(cube_r3, 35, 15, -0.5F, 0.0F, -1.0F, 2, 3, 1, 0.0F, false));

        this.cube_r4 = new AdvancedModelRenderer(this);
        this.cube_r4.setRotationPoint(-0.5F, -6.7235F, -4.1478F);
        this.main.addChild(cube_r4);
        this.setRotateAngle(cube_r4, 0.3491F, 0.0F, 0.0F);
        this.cube_r4.cubeList.add(new ModelBox(cube_r4, 0, 27, -0.5F, 0.0F, -3.0F, 2, 2, 3, -0.003F, false));

        this.cube_r5 = new AdvancedModelRenderer(this);
        this.cube_r5.setRotationPoint(-1.5F, -7.5097F, -1.2642F);
        this.main.addChild(cube_r5);
        this.setRotateAngle(cube_r5, -0.4014F, 0.0F, 0.0F);
        this.cube_r5.cubeList.add(new ModelBox(cube_r5, 0, 0, 1.5F, -6.0F, 0.0F, 0, 6, 6, 0.0F, false));

        this.cube_r6 = new AdvancedModelRenderer(this);
        this.cube_r6.setRotationPoint(-0.5F, -7.5F, -1.25F);
        this.main.addChild(cube_r6);
        this.setRotateAngle(cube_r6, -0.1396F, 0.0F, 0.0F);
        this.cube_r6.cubeList.add(new ModelBox(cube_r6, 11, 16, -0.5F, 0.0F, 0.0F, 2, 2, 4, -0.003F, false));

        this.cube_r7 = new AdvancedModelRenderer(this);
        this.cube_r7.setRotationPoint(-0.5F, -7.5F, -1.25F);
        this.main.addChild(cube_r7);
        this.setRotateAngle(cube_r7, 0.2618F, 0.0F, 0.0F);
        this.cube_r7.cubeList.add(new ModelBox(cube_r7, 26, 9, -0.5F, 0.0F, -3.0F, 2, 2, 3, 0.0F, false));

        this.cube_r8 = new AdvancedModelRenderer(this);
        this.cube_r8.setRotationPoint(-0.05F, -5.075F, -5.375F);
        this.main.addChild(cube_r8);
        this.setRotateAngle(cube_r8, 0.4363F, 0.0F, 0.0F);
        this.cube_r8.cubeList.add(new ModelBox(cube_r8, 28, 6, -1.0F, -0.5F, -0.5F, 1, 1, 1, 0.0F, true));
        this.cube_r8.cubeList.add(new ModelBox(cube_r8, 28, 6, 0.1F, -0.5F, -0.5F, 1, 1, 1, 0.0F, false));

        this.cube_r9 = new AdvancedModelRenderer(this);
        this.cube_r9.setRotationPoint(-0.5F, -1.6615F, -3.9886F);
        this.main.addChild(cube_r9);
        this.setRotateAngle(cube_r9, -0.3927F, 0.0F, 0.0F);
        this.cube_r9.cubeList.add(new ModelBox(cube_r9, 33, 27, -0.5F, -2.0F, -2.0F, 2, 2, 2, 0.003F, false));

        this.cube_r10 = new AdvancedModelRenderer(this);
        this.cube_r10.setRotationPoint(-0.5F, -1.4F, -1.0F);
        this.main.addChild(cube_r10);
        this.setRotateAngle(cube_r10, 0.0436F, 0.0F, 0.0F);
        this.cube_r10.cubeList.add(new ModelBox(cube_r10, 11, 30, -0.5F, -2.0F, 0.0F, 2, 2, 3, 0.003F, false));

        this.cube_r11 = new AdvancedModelRenderer(this);
        this.cube_r11.setRotationPoint(-0.5F, -1.4F, -1.0F);
        this.main.addChild(cube_r11);
        this.setRotateAngle(cube_r11, -0.0873F, 0.0F, 0.0F);
        this.cube_r11.cubeList.add(new ModelBox(cube_r11, 28, 0, -0.5F, -2.0F, -3.0F, 2, 2, 3, 0.0F, false));

        this.frontLeftFin = new AdvancedModelRenderer(this);
        this.frontLeftFin.setRotationPoint(0.95F, -2.5F, -3.0F);
        this.main.addChild(frontLeftFin);
        this.setRotateAngle(frontLeftFin, -0.4233F, 0.5609F, -0.1809F);
        this.frontLeftFin.cubeList.add(new ModelBox(frontLeftFin, 37, 11, 0.0F, -0.5F, 0.0F, 0, 1, 2, 0.0F, false));

        this.frontRightFin = new AdvancedModelRenderer(this);
        this.frontRightFin.setRotationPoint(-0.95F, -2.5F, -3.0F);
        this.main.addChild(frontRightFin);
        this.setRotateAngle(frontRightFin, -0.4233F, -0.5609F, 0.1809F);
        this.frontRightFin.cubeList.add(new ModelBox(frontRightFin, 37, 11, 0.0F, -0.5F, 0.0F, 0, 1, 2, 0.0F, true));

        this.backLeftFin = new AdvancedModelRenderer(this);
        this.backLeftFin.setRotationPoint(1.0F, -1.7F, 0.25F);
        this.main.addChild(backLeftFin);
        this.setRotateAngle(backLeftFin, 0.5564F, 0.1166F, -0.403F);
        this.backLeftFin.cubeList.add(new ModelBox(backLeftFin, 37, 6, 0.0F, 0.0F, 0.0F, 0, 2, 2, 0.0F, false));

        this.backRightFin = new AdvancedModelRenderer(this);
        this.backRightFin.setRotationPoint(-1.0F, -1.7F, 0.25F);
        this.main.addChild(backRightFin);
        this.setRotateAngle(backRightFin, 0.5564F, -0.1166F, 0.403F);
        this.backRightFin.cubeList.add(new ModelBox(backRightFin, 37, 6, 0.0F, 0.0F, 0.0F, 0, 2, 2, 0.0F, true));

        this.tail = new AdvancedModelRenderer(this);
        this.tail.setRotationPoint(0.0F, -4.5F, 1.5F);
        this.main.addChild(tail);
        this.tail.cubeList.add(new ModelBox(tail, 11, 23, -1.0F, -1.5F, 0.0F, 2, 3, 3, 0.003F, false));

        this.cube_r12 = new AdvancedModelRenderer(this);
        this.cube_r12.setRotationPoint(-0.5F, 2.9911F, 0.0139F);
        this.tail.addChild(cube_r12);
        this.setRotateAngle(cube_r12, 0.1745F, 0.0F, 0.0F);
        this.cube_r12.cubeList.add(new ModelBox(cube_r12, 22, 30, -0.5F, -2.0F, 0.2F, 2, 2, 3, 0.0F, false));

        this.cube_r13 = new AdvancedModelRenderer(this);
        this.cube_r13.setRotationPoint(-0.5F, -3.0F, -2.75F);
        this.tail.addChild(cube_r13);
        this.setRotateAngle(cube_r13, -0.1745F, 0.0F, 0.0F);
        this.cube_r13.cubeList.add(new ModelBox(cube_r13, 0, 33, -0.5F, 0.0F, 3.2F, 2, 2, 3, 0.0F, false));

        this.tail2 = new AdvancedModelRenderer(this);
        this.tail2.setRotationPoint(0.0F, 0.1F, 2.5F);
        this.tail.addChild(tail2);
        this.tail2.cubeList.add(new ModelBox(tail2, 22, 23, -0.5F, -0.95F, 0.0F, 1, 2, 4, 0.003F, false));

        this.cube_r14 = new AdvancedModelRenderer(this);
        this.cube_r14.setRotationPoint(0.0F, -3.1F, -5.25F);
        this.tail2.addChild(cube_r14);
        this.setRotateAngle(cube_r14, -0.1745F, 0.0F, 0.0F);
        this.cube_r14.cubeList.add(new ModelBox(cube_r14, 24, 16, -0.5F, 0.1F, 5.575F, 1, 1, 4, 0.0F, false));

        this.cube_r15 = new AdvancedModelRenderer(this);
        this.cube_r15.setRotationPoint(-0.5F, 2.2136F, 0.1971F);
        this.tail2.addChild(cube_r15);
        this.setRotateAngle(cube_r15, 0.3491F, 0.0F, 0.0F);
        this.cube_r15.cubeList.add(new ModelBox(cube_r15, 11, 36, 0.5F, -0.25F, 0.35F, 0, 1, 3, 0.0F, false));
        this.cube_r15.cubeList.add(new ModelBox(cube_r15, 33, 32, 0.0F, -1.05F, 0.25F, 1, 1, 3, 0.0F, false));

        this.tail3 = new AdvancedModelRenderer(this);
        this.tail3.setRotationPoint(0.0F, -0.175F, 3.75F);
        this.tail2.addChild(tail3);
        this.tail3.cubeList.add(new ModelBox(tail3, 0, 13, 0.0F, -4.0F, 0.0F, 0, 8, 5, 0.0F, false));

        this.jaw = new AdvancedModelRenderer(this);
        this.jaw.setRotationPoint(0.0F, -3.0524F, -5.351F);
        this.main.addChild(jaw);


        this.cube_r16 = new AdvancedModelRenderer(this);
        this.cube_r16.setRotationPoint(-0.5F, -2.645F, -1.6159F);
        this.jaw.addChild(cube_r16);
        this.setRotateAngle(cube_r16, 0.829F, 0.0F, 0.0F);
        this.cube_r16.cubeList.add(new ModelBox(cube_r16, 18, 36, -0.5F, 0.05F, -1.65F, 2, 3, 1, -0.003F, false));

        updateDefaultPose();
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.main.render(f5);
    }

    @Override
    public void renderStaticBook(float f) {
        //Rotations, positions and sizing:
        this.main.offsetY = -0.25F;
        this.main.offsetX = 1.4F;
        this.main.offsetZ = 2.0F;
        this.main.rotateAngleY = (float)Math.toRadians(120);
        this.main.rotateAngleX = (float)Math.toRadians(1);
        this.main.rotateAngleZ = (float)Math.toRadians(0);
        this.main.scaleChildren = true;
        float scaler = 5.0F;
        this.main.setScale(scaler, scaler, scaler);
        //Start of pose:

        //End of pose, now render the model:
        this.main.render(f);
        //Reset rotations, positions and sizing:
        this.main.setScale(1.0F, 1.0F, 1.0F);
        this.main.scaleChildren = false;
        resetToDefaultPose();

    }

    public void renderStaticWall(float f) {
        this.main.rotateAngleY = (float) Math.toRadians(90);
        this.main.offsetY = -0.125F;
        this.main.render(0.01F);
        this.resetToDefaultPose();
    }
    public void renderStaticFloor(float f) {
        this.resetToDefaultPose();
    }

    public void setRotateAngle(AdvancedModelRenderer AdvancedModelRenderer, float x, float y, float z) {
        AdvancedModelRenderer.rotateAngleX = x;
        AdvancedModelRenderer.rotateAngleY = y;
        AdvancedModelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        this.resetToDefaultPose();
        //this.body.offsetY = 1.35F;

        //this.Tailfin.setScale(1.1F, 1.1F, 1.1F);
        AdvancedModelRenderer[] fishTail = {this.tail, this.tail2, this.tail3};
        float speed = 0.5F;
        if (!e.isInWater()) {
            speed = 0.8F;
        }
        if (e instanceof EntityLiving && !((EntityLiving) e).isAIDisabled()) {
            this.chainSwing(fishTail, speed, 0.35F, 0, f2, 1);
            this.walk(jaw, (float) (speed * 0.75), 0.1F, true, 0, 0, f2, 1);

            this.walk(frontLeftFin, (float) (speed * 0.75), 0.15F, true, 0, 0, f2, 1);
            this.swing(frontLeftFin, (float) (speed * 0.75), 0.15F, true, 0, 0, f2, 1);
            this.walk(frontRightFin, (float) (speed * 0.75), 0.15F, true, 0, 0, f2, 1);
            this.swing(frontRightFin, (float) (speed * 0.75), 0.15F, true, 0, 0, f2, 1);
            this.walk(backLeftFin, (float) (speed * 0.75), 0.11F, true, 1.5F, 0, f2, 1);
            this.swing(backLeftFin, (float) (speed * 0.75), 0.11F, true, 1.5F, 0, f2, 1);
            this.walk(backRightFin, (float) (speed * 0.75), 0.11F, true, 1.5F, 0, f2, 1);
            this.swing(backRightFin, (float) (speed * 0.75), 0.11F, true, 1.5F, 0, f2, 1);


            this.swing(main, speed, 0.3F, true, 0, 0, f2, 1);
             if (!e.isInWater()) {
                this.main.rotateAngleZ = (float) Math.toRadians(270);
                this.main.offsetY = -0.10F;
                this.bob(main, -speed, 5F, false, f2, 1);
            }
        }
    }
}
