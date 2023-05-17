package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.block.BlockGlassJar;
import net.lepidodendron.entity.EntityPrehistoricFloraMeganeurites;
import net.lepidodendron.entity.model.entity.ModelMeganeuropsis;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderMeganeurites extends RenderLiving<EntityPrehistoricFloraMeganeurites> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/meganeurites.png");

    public RenderMeganeurites(RenderManager mgr) {
        super(mgr, new ModelMeganeuropsis(), 0.0f);
    }


    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraMeganeurites entity) {
        return RenderMeganeurites.TEXTURE;
    }

    public static float getScaler() {
        return 0.29f * 0.88F;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraMeganeurites entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
        switch (entityLiving.getAttachmentFacing()) {
            case DOWN:
            default:
                break;
            case EAST:
                GlStateManager.translate(0.40F, 0.05F, 0.0F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                break;
            case WEST:
                GlStateManager.translate(-0.40F, 0.05F, 0.0F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
                break;
            case NORTH:
                GlStateManager.translate(0.0F, 0.05F, -0.40F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                break;
            case SOUTH:
                GlStateManager.translate(0.0F, 0.05F, 0.40F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
                break;
            case UP:
                GlStateManager.translate(0.0F, 0.5F, 0.0F);
                GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
        }
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraMeganeurites entity, float f) {
        float scale = this.getScaler();
        if (entity.world.getBlockState(entity.getPosition()).getBlock() == BlockGlassJar.block) {
            scale = 0.6F;
        }
        GlStateManager.scale(scale, scale, scale);
    }

}