package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraHelenodora;
import net.lepidodendron.entity.model.entity.ModelHelenodora;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHelenodora extends RenderLiving<EntityPrehistoricFloraHelenodora> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/helenodora.png");
    public static float getScaler() {
        return 0.7F * 0.395F;
    }
    public RenderHelenodora(RenderManager mgr) {
        super(mgr, new ModelHelenodora(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraHelenodora entity) {
        return RenderHelenodora.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraHelenodora entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
    @Override
    protected void preRenderCallback(EntityPrehistoricFloraHelenodora entity, float f) {
        float scale = this.getScaler();
        if (scale < 0.1f) {
            scale = 0.1f;
        }
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = 0;
    }

}