package theflysong.demon_invasions.client.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import theflysong.demon_invasions.DemonInvasions;
import theflysong.demon_invasions.entity.EntityCthulhulEye;

public class RendererCthulhulEye  extends MobRenderer<EntityCthulhulEye, ModelCthulhulEye> {
    public ModelCthulhulEye model1;
    public ModelCthulhulEye model2 = new ModelCthulhulEyeStage2();

    public RendererCthulhulEye(EntityRendererManager renderManager) {
        super(renderManager, new ModelCthulhulEyeStage1(), 1.0f);
        model1 = this.entityModel;
    }

    @Override
    public ResourceLocation getEntityTexture(EntityCthulhulEye entity) {
        if(entity.getHealth() >= 50.0f) {
            return new ResourceLocation(DemonInvasions.MODID, "textures/entity/cthulhul_eye1.png");
        }
        else {
            return new ResourceLocation(DemonInvasions.MODID, "textures/entity/cthulhul_eye2.png");
        }
    }

    @Override
    public void render(EntityCthulhulEye entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        if(entityIn.getHealth() >= 50.0f) {
            this.entityModel = model1;
        }
        else {
            this.entityModel = model2;
        }
        matrixStackIn.push();
        matrixStackIn.scale(3, 3, 3);
        matrixStackIn.rotate(new Quaternion(Vector3f.XP, entityIn.rotationPitch, true));
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.pop();
    }
}
