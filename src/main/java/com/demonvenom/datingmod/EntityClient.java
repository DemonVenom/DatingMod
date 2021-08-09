package com.demonvenom.datingmod;

import com.demonvenom.datingmod.client.renderer.GirlfriendEntityRenderer;
import com.demonvenom.datingmod.client.renderer.BoyfriendEntityRenderer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;


/*
 * Registers the Entities' renderer, which provides a model and texture for the entity.
 *
 * Entity Renderers can also manipulate the model before it renders
 * based on entity context
 */

@Environment(EnvType.CLIENT)
public class EntityClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.INSTANCE.register(DatingMod.GIRLFRIEND, (dispatcher, context) -> {
            return new GirlfriendEntityRenderer(dispatcher);
        });

        EntityRendererRegistry.INSTANCE.register(DatingMod.BOYFRIEND, (dispatcher, context) -> {
            return new BoyfriendEntityRenderer(dispatcher);
        });

    }
}
