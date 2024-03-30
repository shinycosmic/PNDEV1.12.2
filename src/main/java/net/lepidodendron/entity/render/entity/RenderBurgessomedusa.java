package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraBurgessomedusa;
import net.lepidodendron.entity.EntityPrehistoricFloraCyclomedusa;
import net.lepidodendron.entity.model.entity.ModelCambrianJelly;
import net.lepidodendron.entity.model.entity.ModelCombJelly;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBurgessomedusa extends RenderLiving<EntityPrehistoricFloraBurgessomedusa> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/burgessomedusa.png");

    public static float getScaler() {return 0.3F;}

    public RenderBurgessomedusa(RenderManager mgr) {
        super(mgr, new ModelCambrianJelly(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraBurgessomedusa entity) {
        return RenderBurgessomedusa.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraBurgessomedusa entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraBurgessomedusa entity, float f) {
        float scale = getScaler();
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = entity.width * scale * 0.50F;
    }

}