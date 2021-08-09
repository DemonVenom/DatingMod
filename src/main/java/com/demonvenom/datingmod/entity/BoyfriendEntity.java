package com.demonvenom.datingmod.entity;

import com.demonvenom.datingmod.DatingMod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.Random;

/*
 * Create GirlfriendEntity Object attributes so the self-titled file can register the entity
 * Use VillagerEntity parent object class as template
 *
 * Attributes include basic builder properties, spawn properties, and interaction logic
 */

public class BoyfriendEntity extends VillagerEntity {

    // Create string array of 300 male names (https://namecensus.com/male_names.htm)
    public static String[] maleNames = new String[] {"JAMES" , "JOHN" , "ROBERT" , "MICHAEL" , "WILLIAM" , "DAVID" , "RICHARD" , "CHARLES" , "JOSEPH" , "THOMAS" , "CHRISTOPHER" , "DANIEL" , "PAUL" , "MARK" , "DONALD" , "GEORGE" , "KENNETH" , "STEVEN" , "EDWARD" , "BRIAN" , "RONALD" , "ANTHONY" , "KEVIN" , "JASON" , "MATTHEW" , "GARY" , "TIMOTHY" , "JOSE" , "LARRY" , "JEFFREY" , "FRANK" , "SCOTT" , "ERIC" , "STEPHEN" , "ANDREW" , "RAYMOND" , "GREGORY" , "JOSHUA" , "JERRY" , "DENNIS" , "WALTER" , "PATRICK" , "PETER" , "HAROLD" , "DOUGLAS" , "HENRY" , "CARL" , "ARTHUR" , "RYAN" , "ROGER" , "JOE" , "JUAN" , "JACK" , "ALBERT" , "JONATHAN" , "JUSTIN" , "TERRY" , "GERALD" , "KEITH" , "SAMUEL" , "WILLIE" , "RALPH" , "LAWRENCE" , "NICHOLAS" , "ROY" , "BENJAMIN" , "BRUCE" , "BRANDON" , "ADAM" , "HARRY" , "FRED" , "WAYNE" , "BILLY" , "STEVE" , "LOUIS" , "JEREMY" , "AARON" , "RANDY" , "HOWARD" , "EUGENE" , "CARLOS" , "RUSSELL" , "BOBBY" , "VICTOR" , "MARTIN" , "ERNEST" , "PHILLIP" , "TODD" , "JESSE" , "CRAIG" , "ALANn" , "SHAWN" , "CLARENCE" , "SEAN" , "PHILIP" , "CHRIS" , "JOHNNY" , "EARL" , "JIMMY" , "ANTONIO" , "DANNY" , "BRYAN" , "TONY" , "LUIS" , "MIKE" , "STANLEY" , "LEONARD" , "NATHAN" , "DALE" , "MANUEL" , "RODNEY" , "CURTIS" , "NORMAN" , "ALLEN" , "MARVIN" , "VINCENT" , "GLENN" , "JEFFERY" , "TRAVIS" , "JEFF" , "CHAD" , "JACOB" , "LEE" , "MELVIN" , "ALFRED" , "KYLE" , "FRANCIS" , "BRADLEY" , "JESUS" , "HERBERT" , "FREDERICK" , "RAY" , "JOEL" , "EDWIN" , "DON" , "EDDIE" , "RICKY" , "TROY" , "RANDALL" , "BARRY" , "ALEXANDER" , "BERNARD" , "MARIO" , "LEROY" , "FRANCISCO" , "MARCUS" , "MICHEAL" , "THEODORE" , "CLIFFORD" , "MIGUEL" , "OSCAR" , "JAY" , "JIM" , "TOM" , "CALVIN" , "ALEX" , "JON" , "RONNIE" , "BILL" , "LLOYD" , "TOMMY" , "LEON" , "DEREK" , "WARREN" , "DARRELL" , "JEROME" , "FLOYD" , "LEO" , "ALVIN" , "TIM" , "WESLEY" , "GORDON" , "DEAN" , "GREG" , "JORGE" , "DUSTIN" , "PEDRO" , "DERRICK" , "DAN" , "LEWIS" , "ZACHARY" , "COREY" , "HERMAN" , "MAURICE" , "VERNON" , "ROBERTO" , "CLYDE" , "GLEN" , "HECTOR" , "SHANE" , "RICARDO" , "SAM" , "RICK" , "LESTER" , "BRENT" , "RAMON" , "CHARLIE" , "TYLER" , "GILBERT" , "GENE" , "MARC" , "REGINALD" , "RUBEN" , "BRETT" , "ANGEL" , "NATHANIEL" , "RAFAEL" , "LESLIE" , "EDGAR" , "MILTON" , "RAUL" , "BEN" , "CHESTER" , "CECIL" , "DUANE" , "FRANKLIN" , "ANDRE" , "ELMER" , "BRAD" , "GABRIEL" , "RON" , "MITCHELL" , "ROLAND" , "ARNOLD" , "HARVEY" , "JARED" , "ADRIAN" , "KARL" , "CORY" , "CLAUDE" , "ERIK" , "DARRYL" , "JAMIE" , "NEIL" , "JESSIE" , "CHRISTIAN" , "JAVIER" , "FERNANDO" , "CLINTON" , "TED" , "MATHEW" , "TYRONE" , "DARREN" , "LONNIE" , "LANCE" , "CODY" , "JULIO" , "KELLY" , "KURT" , "ALLAN" , "NELSON" , "GUY" , "CLAYTON" , "HUGH" , "MAX" , "DWAYNE" , "DWIGHT" , "ARMANDO" , "FELIX" , "JIMMIE" , "EVERETT" , "JORDAN" , "IAN" , "WALLACE" , "KEN" , "BOB" , "JAIME" , "CASEY" , "ALFREDO" , "ALBERTO" , "DAVE" , "IVAN" , "JOHNNIE" , "SIDNEY" , "BYRON" , "JULIAN" , "ISAAC" , "MORRIS" , "CLIFTON" , "WILLARD" , "DARYL" , "ROSS" , "VIRGIL" , "ANDY" , "MARSHALL" , "SALVADOR" , "PERRY" , "KIRK" , "SERGIO" , "MARION" , "TRACY" , "SETH" , "KENT" , "TERRANCE" , "RENE" , "EDUARDO" , "TERRENCE" , "ENRIQUE" , "FREDDIE" , "WADE"};

    // Create boolean to allow/prevent Boyfriend Entity from spawning
    public boolean BoyfriendCanSpawn = true;

    // Create method to refer the parent class
    public BoyfriendEntity(EntityType<? extends VillagerEntity> entityType, World world) {

        // Call parent constructor using arguments entityType and world
        super(entityType, world);
    }


    // Use entity builder to create basic builder properties (movement speed and health)
    public static DefaultAttributeContainer.Builder createBoyfriendAttributes() {

        // Use villager entity as template for adding builder attributes
        return VillagerEntity.createVillagerAttributes()

                // Make relatively slow like villagers
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)

                // Make health relatively high so poison effect during "date mode" doesn't kill entity
                // Also hopefully discourages players from killing entity due to high health bar
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 40);
    }


    // Create method to change the riding position of the entity
    @Override
    public void updatePassengerPosition(Entity passenger) {

        super.updatePassengerPosition(passenger);

        // Change the position that the passenger rides the entity to the entity's x-coordinate plus half a block ahead,
        // the entity's y-coordinate minus a block ahead, plus the mount height change, plus the passenger height offset,
        // and the entity's z-coordinate
        passenger.updatePosition(this.getX() + 0.5, this.getY() - 1 + this.getMountedHeightOffset() + passenger.getHeightOffset(), this.getZ());
    }

    // Create method to find if GirlfriendEntity can spawn at a certain position
    @Override
    public boolean canSpawn(WorldView view) {

        // Make new block position, represented as the XYZ position directly under the entity (1 below it in the y direction)
        BlockPos BlockUnderEntity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());

        // Make new block position, represented as the position directly at the entity
        BlockPos PosEntity = new BlockPos(this.getX(), this.getY(), this.getZ());

        // Return boolean
        // If return boolean is true, all conditions must be met
        // If return boolean is false, one of the conditions is false
        return view.intersectsEntities(this) && this.world.isDay() && !world.containsFluid(this.getBoundingBox())
                && this.world.getBlockState(PosEntity).getBlock().canMobSpawnInside()
                && this.world.getBlockState(BlockUnderEntity).allowsSpawning(view, BlockUnderEntity, DatingMod.BOYFRIEND)
                && BoyfriendCanSpawn;
    }


    // Create method to make player-entity interaction logic
    public final ActionResult interactMob(PlayerEntity player, Hand hand) {

        // Under the condition that the world is not run on the player's client-side...
        if (!world.isClient()) {

            // Under the condition that the player right clicks on the selected entity with an empty hand...
            if(player.getMainHandStack().isEmpty()) {

                // Under the condition that the selected entity's AI is disabled (flag is raised)...
                if (this.isAiDisabled()) {

                    // ...make the selected entity's AI enabled again
                    this.setAiDisabled(false);

                    // ...clear the selected entity's status effect (to get it out of date mode)
                    this.clearStatusEffects();

                    // ...give the selected entity's health back
                    // (the entity's damage sound and animation is only necessary)
                    this.heal(100f);

                }

                // Otherwise, under the condition that the selected entity's AI is enabled...
                else {

                    // ...Under the condition that the entity does not have a custom name
                    if (!this.hasCustomName()) {

                        // ...make a new random object
                        Random random = new Random();

                        // ...select a random integer between 0 and 299 (the range of the names array)
                        // and assign it to the randomInt integer variable
                        int randomInt = random.nextInt(299);

                        // Set the selected entity's name as a String from the name array
                        // at the position of the random integer
                        this.setCustomName(new LiteralText(maleNames[randomInt]));
                    }

                    // Have the player start riding the selected entity
                    player.startRiding(this);

                    // Have the entity look at the player's last position before teleporting to entity ride position
                    this.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET, player.getPos());

                    // Give the selected entity the poison status effect for long time at the lowest power level (zero)
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 1000000, 0));
                    // The entity continuing to get hurt encourages players to set the entity out of date mode
                    // and reactivate the AI

                    // Set the entity AI as disabled
                    // (this prevents the entity from moving while in position,
                    // while also acting as a flag that can be raised to get the entity to stand back up)
                    this.setAiDisabled(true);

                    // Give the player the love status effect for 200 ticks at the lowest power level (zero)
                    player.addStatusEffect(new StatusEffectInstance(DatingMod.LOVE, 200, 0));

                    // Play ambient mood music coming from the selected entity
                    world.playSoundFromEntity(null, this, SoundEvents.MUSIC_DISC_FAR, SoundCategory.AMBIENT, 1f, 1f);


                    /*
                     * Developer's Note:
                     *
                     * In an effort to teach players about responsible dating activity, there should be a consequence
                     * for them having many encounters. Everytime the player "interacts" with an entity,
                     * they gain experience levels. Once they have a high enough experience level
                     * (ie: a high enough "interaction" count), this means that they are responsible enough to
                     * bear a child.
                     *
                     * This also serves as a goal for the player to work towards if they choose to do so,
                     * a funny easter egg, and a soft cap on experience level farming or a mass amount of casual
                     * "interaction" count, as everytime the player interacts with an entity above a certain level,
                     * they will have to hear the annoying baby crying sound effect.
                     */

                    // Under the condition that the player's experience level is above 25...
                    if (player.experienceLevel > 25) {

                        // Create a new sound manager object
                        SoundManager soundManager = MinecraftClient.getInstance().getSoundManager();
                        // Make the sound manager play the "baby crying" sound effect
                        soundManager.play(PositionedSoundInstance.master(DatingMod.BABYCRYING, 1f));

                        // Have the selected entity drop a villager egg (acting as the baby)
                        this.dropItem(Items.VILLAGER_SPAWN_EGG);
                    }
                }
            }

            // Otherwise, when the player does have something in their hand...
            else {

                // ...the selected entity will respond with disapproval
                this.playSound(SoundEvents.ENTITY_VILLAGER_NO, 1f, 1f);
            }
        }

        // The particle effects were not working in the previous long "else" statement for some reason, so here it goes
        // Under the condition that the selected entity's AI is not disabled...
        if (!this.isAiDisabled()) {

            // ...make a for loop statement that iterates 100 times that does the following...
            for (int i = 1; i <= 100; i++) {

                // Make new random object
                Random rand = new Random();

                // Make minimumX position coordinate value at 1 block behind the entity
                float minX = -1f;
                // Make maximumX position coordinate value at 1 block ahead the entity
                float maxX = 1f;

                // Have the random object pick a float between the minX/maxX values and assign to finalX float variable
                float finalX = rand.nextFloat() * (maxX - minX) + minX;


                // Make minimumY position coordinate value at 0.5 blocks behind the entity
                float minY = -0.5f;
                // Make minimumY position coordinate value at 0.5 blocks behind the entity
                float maxY = 0.5f;

                // Have the random object pick a float between the minY/maxY values and assign to finalY float variable
                float finalY = rand.nextFloat() * (maxY - minY) + minY;


                // Make minimumZ position coordinate value at 1 block behind the entity
                float minZ = -1;
                // Make maximumZ position coordinate value at 1 block ahead the entity
                float maxZ = 1f;

                // Have the random object pick a float between the minZ/maxZ values and assign to finalX float variable
                float finalZ = rand.nextFloat() * (maxZ - minZ) + minZ;


                // Add smoke particle at the entity's X position +/- the final randomX position addition,
                // the entity's Y position +/- the final randomZ position addition + 0.5 of a block above the ground,
                // the entity's Z position +/- the final randomZ position addition,
                // floating at a speed of 0 in neither the X, Y or Z direction
                world.addImportantParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, true, this.getX() + finalX, this.getY() + finalY + 0.5, this.getZ() + finalZ, 0.0, 0.0, 0.0);
            }
        }

        // Return the result of the player's interaction done by their hand
        return super.interactMob(player, hand);
    }


}
