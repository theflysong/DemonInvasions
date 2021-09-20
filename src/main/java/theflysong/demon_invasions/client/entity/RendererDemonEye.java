package theflysong.demon_invasions.client.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import theflysong.demon_invasions.DemonInvasions;
import theflysong.demon_invasions.entity.EntityDemonEye;

public class RendererDemonEye extends MobRenderer<EntityDemonEye, ModelDemonEye> {
    public RendererDemonEye(EntityRendererManager renderManager) {
        super(renderManager, new ModelDemonEye(), 1.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityDemonEye entity) {
        return new ResourceLocation(DemonInvasions.MODID, "textures/entity/demon_eye.png");
    }

    @Override
    public void render(EntityDemonEye entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.push();
        matrixStackIn.rotate(new Quaternion(Vector3f.XP, entityIn.rotationPitch, true));
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.pop();
    }
}