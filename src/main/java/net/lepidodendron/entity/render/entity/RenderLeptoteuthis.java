package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraLeptoteuthis;
import net.lepidodendron.entity.model.entity.ModelLeptoteuthis;
import net.lepidodendron.entity.render.RenderLivingBaseWithBook;
import net.lepidodendron.entity.render.tile.RenderDisplays;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderLeptoteuthis extends RenderLivingBaseWithBook<EntityPrehistoricFloraLeptoteuthis> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/leptoteuthis.png");

    public static float getScaler() {
        return 0.5f;
    }
    public RenderLeptoteuthis(RenderManager mgr) {
        super(mgr, new ModelLeptoteuthis(), RenderDisplays.modelLeptoteuthisBook, 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraLeptoteuthis entity) {
        return RenderLeptoteuthis.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraLeptoteuthis entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraLeptoteuthis entity, float f) {
        float scale = this.getScaler()*entity.getAgeScale();
        if (scale < 0.1f) {
            scale = 0.1f;
        }
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = 0;
    }

}