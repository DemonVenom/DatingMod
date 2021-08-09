package com.demonvenom.datingmod.client.model;

import com.demonvenom.datingmod.entity.BoyfriendEntity;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

/*
 * Create BoyfriendEntity Object Model
 *
 * Extends from the BoyfriendEntity.
 * Includes entity head, headwear, body, arms, and legs. Designed in BlockBench with ZombieEntity model
 */


public class BoyfriendEntityModel extends EntityModel<BoyfriendEntity> {

    // Initialize ModelPart objects
    private final ModelPart head;
    private final ModelPart headwear;
    private final ModelPart body;
    private final ModelPart left_arm;
    private final ModelPart right_arm;
    private final ModelPart left_leg;
    private final ModelPart right_leg;



    // Create BoyfriendEntity Object ModelPart lengths, widths, and pivots, and offsets

    public BoyfriendEntityModel() {

        textureWidth = 64;
        textureHeight = 64;

        head = new ModelPart(this);
        head.setPivot(0.0F, 0.0F, 0.0F);
        head.setTextureOffset(0, 0).addCuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

        headwear = new ModelPart(this);
        headwear.setPivot(0.0F, 0.0F, 0.0F);
        headwear.setTextureOffset(32, 0).addCuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.25F, false);

        body = new ModelPart(this);
        body.setPivot(0.0F, 0.0F, 0.0F);
        body.setTextureOffset(16, 16).addCuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);

        left_arm = new ModelPart(this);
        left_arm.setPivot(-5.0F, 2.0F, 0.0F);
        left_arm.setTextureOffset(40, 16).addCuboid(9.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);

        right_arm = new ModelPart(this);
        right_arm.setPivot(5.0F, 2.0F, 0.0F);
        right_arm.setTextureOffset(40, 16).addCuboid(-13.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        left_leg = new ModelPart(this);
        left_leg.setPivot(-1.9F, 12.0F, 0.0F);
        left_leg.setTextureOffset(0, 16).addCuboid(1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);

        right_leg = new ModelPart(this);
        right_leg.setPivot(1.9F, 12.0F, 0.0F);
        right_leg.setTextureOffset(0, 16).addCuboid(-5.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
    }


    // Create GirlfriendEntity Object ModelPart angles and pitches

    @Override
    public void setAngles(BoyfriendEntity entity, float limbAngle, float limbDistance, float customAngle, float headYaw, float headPitch) {
        head.yaw = headYaw * 0.017453292F;
        head.pitch = headPitch * 0.017453292F;

        right_arm.pitch = -.1f - (float) Math.sin(this.handSwingProgress * 3.1415927F);
        left_arm.pitch = -.1f - (float) Math.sin(this.handSwingProgress * 3.1415927F);

        right_leg.pitch = (float) Math.sin(limbAngle) * .3f;
        left_leg.pitch = -(float) Math.sin(limbAngle) * .3f;
    }


    // Render GirlfriendEntity Object ModelParts

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {

        matrices.translate(0, 0, 0);

        body.render(matrices, vertexConsumer, light, overlay);
        left_leg.render(matrices, vertexConsumer, light, overlay);
        right_leg.render(matrices, vertexConsumer, light, overlay);
        left_arm.render(matrices, vertexConsumer, light, overlay);
        right_arm.render(matrices, vertexConsumer, light, overlay);
        head.render(matrices, vertexConsumer, light, overlay);
    }


}
