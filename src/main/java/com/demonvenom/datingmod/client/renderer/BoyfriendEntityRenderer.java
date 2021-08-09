package com.demonvenom.datingmod.client.renderer;


import com.demonvenom.datingmod.DatingMod;
import com.demonvenom.datingmod.client.model.BoyfriendEntityModel;
import com.demonvenom.datingmod.entity.BoyfriendEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

/*
 * A renderer is used to provide an entity model, shadow size, and texture.
 */

public class BoyfriendEntityRenderer extends MobEntityRenderer<BoyfriendEntity, BoyfriendEntityModel> {

    public BoyfriendEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new BoyfriendEntityModel(), 0.5f);
    }


    // Get Girlfriend texture by entering folder path in mod

    @Override
    public Identifier getTexture(BoyfriendEntity entity) {
        return new Identifier(DatingMod.MOD_ID, "textures/entity/boyfriend/boyfriend.png");
    }
}
