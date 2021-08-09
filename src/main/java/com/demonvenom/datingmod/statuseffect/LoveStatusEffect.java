package com.demonvenom.datingmod.statuseffect;

import com.demonvenom.datingmod.DatingMod;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.player.PlayerEntity;

public class LoveStatusEffect extends StatusEffect {

    public LoveStatusEffect() {

        // Set Love status effect as beneficial type and particle effect color as red
        super(StatusEffectType.BENEFICIAL, 0xFF0000);
    }

    // Create method that delays the status effect update
    // When the return boolean variable is true, the game can update the effect
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {

        // Assign the game's measurement of time, tickDelay, as 20
        // The tickDelay increases significantly with shift right when amplifier is used
        // (amplifier is 0 in this case, so it irrelevant, but may be useful for future amplifier development)
        int tickDelay = 20 >> amplifier;

        // When the condition that the tickDelay is less than/equal to 0 or duration divided by tickDelay is even, send true and apply the status effect
        // When both condition are not met, send false and do not apply status effect
        return tickDelay <= 0 || duration % tickDelay == 0;
    }

    // Create method that assigns what status effects are done to the entity affected by it
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {

        // On the condition that the PlayerEntity is the one afflicted...
        if (entity instanceof PlayerEntity) {

            // Give the player experience. Higher amplifier gives experience faster
            ((PlayerEntity) entity).addExperience(1 << amplifier);

            // Animate the damage animation
            ((PlayerEntity) entity).animateDamage();

            // Play hurt sound effect from player
            ((PlayerEntity) entity).playSound(DatingMod.OOF, 0.5f, 1f);
        }
    }

}
