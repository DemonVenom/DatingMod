package com.demonvenom.datingmod.client.renderer;


import com.demonvenom.datingmod.DatingMod;
import com.demonvenom.datingmod.client.model.GirlfriendEntityModel;
import com.demonvenom.datingmod.entity.GirlfriendEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

/*
 * A renderer is used to provide an entity model, shadow size, and texture.
 */

public class GirlfriendEntityRenderer extends MobEntityRenderer<GirlfriendEntity, GirlfriendEntityModel> {

    public GirlfriendEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new GirlfriendEntityModel(), 0.5f);
    }


    // Get Girlfriend texture by entering folder path in mod

    @Override
    public Identifier getTexture(GirlfriendEntity entity) {
        return new Identifier(DatingMod.MOD_ID, "textures/entity/girlfriend/girlfriend.png");
    }
}
