package theflysong.demon_invasions.client.entity;// Made with Blockbench 3.9.3
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import theflysong.demon_invasions.entity.EntityCthulhulEye;

public class ModelCthulhulEyeStage2 extends ModelCthulhulEye {
	private final ModelRenderer teeth;
	private final ModelRenderer body;
	private final ModelRenderer misc;

	public ModelCthulhulEyeStage2() {
		textureWidth = 64;
		textureHeight = 64;

		teeth = new ModelRenderer(this);
		teeth.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(teeth, 0.0F, 3.1416F, 0.0F);
		teeth.setTextureOffset(0, 42).addBox(7.0F, -5.0F, 4.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		teeth.setTextureOffset(0, 42).addBox(7.0F, -13.0F, 2.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		teeth.setTextureOffset(0, 42).addBox(7.0F, -13.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		teeth.setTextureOffset(0, 42).addBox(7.0F, -5.0F, 1.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		teeth.setTextureOffset(0, 42).addBox(-8.0F, -5.0F, -1.0F, 1.0F, 2.0F, 3.0F, 0.0F, false);
		teeth.setTextureOffset(0, 42).addBox(-8.0F, -5.0F, 4.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		teeth.setTextureOffset(0, 42).addBox(-6.0F, -5.0F, 7.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);
		teeth.setTextureOffset(0, 42).addBox(3.0F, -5.0F, 7.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		teeth.setTextureOffset(0, 42).addBox(0.0F, -5.0F, 7.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		teeth.setTextureOffset(0, 42).addBox(-3.0F, -13.0F, 7.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);
		teeth.setTextureOffset(0, 42).addBox(1.0F, -13.0F, 7.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		teeth.setTextureOffset(0, 42).addBox(5.0F, -13.0F, 7.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		teeth.setTextureOffset(0, 42).addBox(-3.0F, -13.0F, 7.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);
		teeth.setTextureOffset(0, 42).addBox(-8.0F, -13.0F, 2.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(body, 0.0F, 3.1416F, 0.0F);
		body.setTextureOffset(0, 0).addBox(-8.0F, -16.0F, -1.0F, 16.0F, 3.0F, 9.0F, 0.0F, false);
		body.setTextureOffset(0, 12).addBox(-8.0F, -3.0F, -1.0F, 16.0F, 3.0F, 9.0F, 0.0F, false);
		body.setTextureOffset(18, 24).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 7.0F, 0.0F, false);

		misc = new ModelRenderer(this);
		misc.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(misc, 0.0F, 3.1416F, 0.0F);
		misc.setTextureOffset(0, 62).addBox(-6.0F, -12.0F, -15.0F, 1.0F, 1.0F, 7.0F, 0.0F, false);
		misc.setTextureOffset(0, 62).addBox(4.0F, -7.0F, -13.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
		misc.setTextureOffset(0, 62).addBox(2.0F, -13.0F, -20.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
		misc.setTextureOffset(0, 62).addBox(-2.0F, -10.0F, -12.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		misc.setTextureOffset(0, 62).addBox(2.0F, -14.0F, -17.0F, 1.0F, 1.0F, 9.0F, 0.0F, false);
		misc.setTextureOffset(0, 62).addBox(4.0F, -8.0F, -20.0F, 1.0F, 1.0F, 9.0F, 0.0F, false);
		misc.setTextureOffset(0, 62).addBox(2.0F, -5.0F, -14.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		misc.setTextureOffset(0, 62).addBox(2.0F, -4.0F, -17.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		misc.setTextureOffset(0, 62).addBox(7.0F, -10.0F, -14.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		misc.setTextureOffset(0, 62).addBox(7.0F, -9.0F, -17.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
		misc.setTextureOffset(0, 57).addBox(-6.0F, -4.0F, -13.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
		misc.setTextureOffset(0, 62).addBox(-1.0F, -10.0F, -16.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		misc.setTextureOffset(0, 57).addBox(-7.0F, -12.0F, -17.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
		misc.setTextureOffset(0, 57).addBox(-6.0F, -5.0F, -16.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(EntityCthulhulEye entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		teeth.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		misc.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}