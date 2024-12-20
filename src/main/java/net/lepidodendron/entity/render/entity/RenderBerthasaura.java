package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraBerthasaura;
import net.lepidodendron.entity.model.entity.ModelBerthasaura;
import net.lepidodendron.entity.render.RenderLivingBaseWithBook;
import net.lepidodendron.entity.render.tile.RenderDisplays;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBerthasaura extends RenderLivingBaseWithBook<EntityPrehistoricFloraBerthasaura> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/berthasaura.png");

    public RenderBerthasaura(RenderManager mgr) {
        super(mgr, new ModelBerthasaura(), 0.3f);
    }

    public static float getScaler() {
        return 0.2711F;
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraBerthasaura entity) {
        return RenderBerthasaura.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraBerthasaura entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraBerthasaura entity, float f) {
        float scale = entity.getAgeScale() * this.getScaler();
        if (scale < 0.1f) {scale = 0.1f;}
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = entity.width * scale * 0.35F;
    }

}