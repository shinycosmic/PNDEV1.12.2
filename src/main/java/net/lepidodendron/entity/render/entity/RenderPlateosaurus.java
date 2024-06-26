package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraPlateosaurus;
import net.lepidodendron.entity.model.entity.ModelPlateosaurus;
import net.lepidodendron.entity.render.RenderLivingBaseWithBook;
import net.lepidodendron.entity.render.tile.RenderDisplays;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderPlateosaurus extends RenderLivingBaseWithBook<EntityPrehistoricFloraPlateosaurus> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/plateosaurus.png");

    public static float getScaler() {return 0.88F;}

    public RenderPlateosaurus(RenderManager mgr) {
        super(mgr, new ModelPlateosaurus(), RenderDisplays.modelPlateosaurusBook, 0.5f);
        //EDIT NEEDED WHEN MODEL IS FIXED!
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraPlateosaurus entity) {
        return RenderPlateosaurus.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraPlateosaurus entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraPlateosaurus entity, float f) {
        float scale = entity.getAgeScale()*getScaler();
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = entity.width * scale * 0.40F;
    }

}