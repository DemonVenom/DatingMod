package com.demonvenom.datingmod.mixin;

import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


/*
 * Redirects PlayerEntity interactions so that Boyfriend/Girlfriend entities (and all other MobEntities)
 * can be leashed and lead to their desired location by the PlayerEntity
 *
 */

@Mixin(MobEntity.class)
public abstract class MobEntityMixin {

    @Redirect(method = "method_29506", at = @At (value = "INVOKE", target = "Lnet/minecraft/entity/mob/MobEntity;canBeLeashedBy(Lnet/minecraft/entity/player/PlayerEntity;)Z"))
    private boolean canBeLeashed(MobEntity mobEntity, PlayerEntity player) {

        return !mobEntity.isLeashed();
    }
}