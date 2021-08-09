package com.demonvenom.datingmod;

import com.demonvenom.datingmod.entity.GirlfriendEntity;
import com.demonvenom.datingmod.entity.BoyfriendEntity;
import com.demonvenom.datingmod.registry.ModSpawnEggs;
import com.demonvenom.datingmod.spawns.SpawnInit;
import com.demonvenom.datingmod.statuseffect.LoveStatusEffect;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


/*
 * Initializes and Registers Objects formatted in other class files
 *
 * Includes: ITEM_GROUP (item group), GIRLFRIEND (entity), BOYFRIEND (entity),
 * OOF (sound event), BABYCRYING (sound event), LOVE (sttus effect)
 */

public class DatingMod implements ModInitializer {

    // Make mod id and assign it to final string
    public static final String MOD_ID = "mcdatingmod";

    /*
     * Registers ITEM_GROUP for Boyfriend Egg and Girlfriend Egg items.
     *
     * This allows both items to appear in a new Creative Mode tab
     * Sets the Boyfriend Egg as the icon
     */

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "general"),
            () -> new ItemStack(ModSpawnEggs.BOYFRIEND_EGG));


    /*
     * Registers GirlfriendEntity under the ID "mcdatingmod:girlfriend" and BoyfriendEntity under the ID "mcdatingmod:boyfriend".
     *
     * The entity is registered under the SpawnGroup#MONSTER category to allow spawning to happen
     * It has a hitbox size of .6x1.95, the size of a zombie
     */

    public static final EntityType<GirlfriendEntity> GIRLFRIEND = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "girlfriend"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, GirlfriendEntity::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build()
    );

    public static final EntityType<BoyfriendEntity> BOYFRIEND = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "boyfriend"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, BoyfriendEntity::new).dimensions(EntityDimensions.changing(0.6f, 1.95f)).build()
    );


    // Create hurt sound effect identifier and SoundEvent object under the id "mcdatingmod:oof"
    public static final Identifier OOF_ID = new Identifier(MOD_ID, "oof");
    public static SoundEvent OOF = new SoundEvent(OOF_ID);

    // Create baby crying sound effect identifier and SoundEvent object under the id "mcdatingmod:babycrying"
    public static final Identifier BABYCRYING_ID = new Identifier(MOD_ID, "babycrying");
    public static SoundEvent BABYCRYING = new SoundEvent(BABYCRYING_ID);

    // Create status effect object
    public static final StatusEffect LOVE = new LoveStatusEffect();


    @Override
    public void onInitialize() {

        // Register Boyfriend and Girlfriend entities using create[EntityType]Attribute method from entity class
        FabricDefaultAttributeRegistry.register(GIRLFRIEND, GirlfriendEntity.createGirlfriendAttributes());
        FabricDefaultAttributeRegistry.register(BOYFRIEND, BoyfriendEntity.createBoyfriendAttributes());

        // Register eggs using registerSpawnEggs method from ModSpawnEggs class
        ModSpawnEggs.registerSpawnEggs();

        // Register SoundEvents previously created
        Registry.register(Registry.SOUND_EVENT, DatingMod.OOF_ID, OOF);
        Registry.register(Registry.SOUND_EVENT, DatingMod.BABYCRYING_ID, BABYCRYING);

        // Register Love status effect and create it under new identifier "mcdatingmod:love"
        Registry.register(Registry.STATUS_EFFECT, new Identifier(MOD_ID, "love"), LOVE);


        // Initialize entities using init method from SpawnInit class
        SpawnInit.init();

    }

}