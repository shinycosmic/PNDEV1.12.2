package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraFurcaster;
import net.lepidodendron.entity.EntityPrehistoricFloraTurboscinetes;
import net.lepidodendron.entity.model.entity.ModelFurcaster;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderFurcaster extends RenderLiving<EntityPrehistoricFloraFurcaster> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/furcaster.png");

    public static float getScaler() {
        return 0.08F;
    }

    public RenderFurcaster(RenderManager mgr) {
        super(mgr, new ModelFurcaster(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraFurcaster entity) {
        return RenderFurcaster.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraFurcaster entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraFurcaster entity, float f) {
        float scale = this.getScaler();
        if (scale < 0.1f) {
            scale = 0.1f;
        }
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = 0;
    }

}