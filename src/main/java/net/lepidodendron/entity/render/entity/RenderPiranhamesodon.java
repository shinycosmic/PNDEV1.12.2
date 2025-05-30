package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraPiranhamesodon;
import net.lepidodendron.entity.model.entity.ModelPiranhamesodon;
import net.lepidodendron.entity.render.RenderLivingBaseWithBook;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderPiranhamesodon extends RenderLivingBaseWithBook<EntityPrehistoricFloraPiranhamesodon> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/piranhamesodon.png");

    public static float getScaler() {
        return 0.7F * 0.22F;
    }
    public RenderPiranhamesodon(RenderManager mgr) {
        super(mgr, new ModelPiranhamesodon(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraPiranhamesodon entity) {
        return RenderPiranhamesodon.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraPiranhamesodon entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
    @Override
    protected void preRenderCallback(EntityPrehistoricFloraPiranhamesodon entity, float f) {
        float scale = this.getScaler();
        if (scale < 0.1f) {
            scale = 0.1f;
        }
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = 0;
    }

}