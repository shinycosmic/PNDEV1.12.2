package net.lepidodendron.entity.model.entity;

import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.lepidodendron.entity.EntityPrehistoricFloraOvatoscutum;
import net.lepidodendron.entity.model.ModelBasePalaeopedia;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelOvatoscutum extends ModelBasePalaeopedia {
    private final AdvancedModelRenderer main;

    public ModelOvatoscutum() {
        textureWidth = 40;
        textureHeight = 20;

        main = new AdvancedModelRenderer(this);
        main.setRotationPoint(0.0F, 25.0F, 3.0F);
        main.cubeList.add(new ModelBox(main, 0, 7, -3.0F, -1.5F, -9.0F, 6, 1, 3, 0.0F, false));
        main.cubeList.add(new ModelBox(main, 10, 11, 1.0F, -1.5F, 0.0F, 2, 1, 3, 0.0F, false));
        main.cubeList.add(new ModelBox(main, 0, 11, -3.0F, -1.5F, 0.0F, 2, 1, 3, 0.0F, false));
        main.cubeList.add(new ModelBox(main, 0, 0, -6.0F, -1.5F, -6.0F, 12, 1, 6, 0.0F, false));
        main.cubeList.add(new ModelBox(main, 16, 15, 3.0F, -1.5F, -8.0F, 2, 1, 2, 0.0F, false));
        main.cubeList.add(new ModelBox(main, 15, 7, 3.0F, -1.5F, 0.0F, 2, 1, 2, 0.0F, false));
        main.cubeList.add(new ModelBox(main, 0, 3, -1.0F, -1.5F, 0.0F, 2, 1, 1, -0.0001F, false));
        main.cubeList.add(new ModelBox(main, 8, 15, -5.0F, -1.5F, -8.0F, 2, 1, 2, 0.0F, false));
        main.cubeList.add(new ModelBox(main, 0, 15, -5.0F, -1.5F, 0.0F, 2, 1, 2, 0.0F, false));

        updateDefaultPose();
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.main.render(f5);
    }

    @Override
    public void renderStaticBook(float f) {
        //Rotations, positions and sizing:
        this.main.offsetY = -1.4F;
        this.main.offsetX = -0.3F;
        this.main.rotateAngleY = (float)Math.toRadians(200);
        this.main.rotateAngleX = (float)Math.toRadians(25);
        this.main.rotateAngleZ = (float)Math.toRadians(-8);
        this.main.scaleChildren = true;
        float scaler = 2.8F;
        this.main.setScale(scaler, scaler, scaler);
        //Start of pose:
        this.setRotateAngle(main, 0.6F, 3.8F, -0.2F);
        //End of pose, now render the model:
        this.main.render(f);
        //Reset rotations, positions and sizing:
        this.main.setScale(1.0F, 1.0F, 1.0F);
        this.main.scaleChildren = false;
        resetToDefaultPose();
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        this.resetToDefaultPose();

        //this.Body.offsetY = 0.383F;

        EntityPrehistoricFloraOvatoscutum ee = (EntityPrehistoricFloraOvatoscutum) e;

        this.main.scaleChildren = true;
        float scaler = ((float)(((double)ee.getSlitherStage())/10D) * 0.07F) + 1F;
        this.main.setScaleZ(scaler * 0.65F);
        float scaler2 = 2F - (float)((((double)ee.getSlitherStage())/10D) * 0.07F);
        this.main.setScaleX(scaler2 * 0.325F);

    }
}
