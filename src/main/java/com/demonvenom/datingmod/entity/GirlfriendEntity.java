package com.demonvenom.datingmod.entity;

import com.demonvenom.datingmod.DatingMod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
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

public class GirlfriendEntity extends VillagerEntity {

    // Create string array of top 300 female names (taken from top 300 at https://namecensus.com/female_names.htm)
    public static String[] femaleNames = new String[] {"MARY" , "PATRICIA" , "LINDA" , "BARBARA" , "ELIZABETH" , "JENNIFER" , "MARIA" , "SUSAN" , "MARGARET" , "DOROTHY" , "LISA" , "NANCY" , "KAREN" , "BETTY" , "HELEN" , "SANDRA" , "DONNA" , "CAROL" , "RUTH" , "SHARON" , "MICHELLE" , "LAURA" , "SARAH" , "KIMBERLY" , "DEBORAH" , "JESSICA" , "SHIRLEY" , "CYNTHIA" , "ANGELA" , "MELISSA" , "BRENDA" , "AMY" , "ANNA" , "REBECCA" , "VIRGINIA" , "KATHLEEN" , "PAMELA" , "MARTHA" , "DEBRA" , "AMANDA" , "STEPHANIE" , "CAROLYN" , "CHRISTINE" , "MARIE" , "JANET" , "CATHERINE" , "FRANCES" , "ANN" , "JOYCE" , "DIANE" , "ALICE" , "JULIE" , "HEATHER" , "TERESA" , "DORIS" , "GLORIA" , "EVELYN" , "JEAN" , "CHERYL" , "MILDRED" , "KATHERINE" , "JOAN" , "ASHLEY" , "JUDITH" , "ROSE" , "JANICE" , "KELLY" , "NICOLE" , "JUDY" , "CHRISTINA" , "KATHY" , "THERESA" , "BEVERLY" , "DENISE" , "TAMMY" , "IRENE" , "JANE" , "LORI" , "RACHEL" , "MARILYN" , "ANDREA" , "KATHRYN" , "LOUISE" , "SARA" , "ANNE" , "JACQUELINE" , "WANDA" , "BONNIE" , "JULIA" , "RUBY" , "LOIS" , "TINA" , "PHYLLIS" , "NORMA" , "PAULA" , "DIANA" , "ANNIE" , "LILLIAN" , "EMILY" , "ROBIN" , "PEGGY" , "CRYSTAL" , "GLADYS" , "RITA" , "DAWN" , "CONNIE" , "FLORENCE" , "TRACY" , "EDNA" , "TIFFANY" , "CARMEN" , "ROSA" , "CINDY" , "GRACE" , "WENDY" , "VICTORIA" , "EDITH" , "KIM" , "SHERRY" , "SYLVIA" , "JOSEPHINE" , "THELMA" , "SHANNON" , "SHEILA" , "ETHEL" , "ELLEN" , "ELAINE" , "MARJORIE" , "CARRIE" , "CHARLOTTE" , "MONICA" , "ESTHER" , "PAULINE" , "EMMA" , "JUANITA" , "ANITA" , "RHONDA" , "HAZEL" , "AMBER" , "EVA" , "DEBBIE" , "APRIL" , "LESLIE" , "CLARA" , "LUCILLE" , "JAMIE" , "JOANNE" , "ELEANOR" , "VALERIE" , "DANIELLE" , "MEGAN" , "ALICIA" , "SUZANNE" , "MICHELE" , "GAIL" , "BERTHA" , "DARLENE" , "VERONICA" , "JILL" , "ERIN" , "GERALDINE" , "LAUREN" , "CATHY" , "JOANN" , "LORRAINE" , "LYNN" , "SALLY" , "REGINA" , "ERICA" , "BEATRICE" , "DOLORES" , "BERNICE" , "AUDREY" , "YVONNE" , "ANNETTE" , "JUNE" , "SAMANTHA" , "MARION" , "DANA" , "STACY" , "ANA" , "RENEE" , "IDA" , "VIVIAN" , "ROBERTA" , "HOLLY" , "BRITTANY" , "MELANIE" , "LORETTA" , "YOLANDA" , "JEANETTE" , "LAURIE" , "KATIE" , "KRISTEN" , "VANESSA" , "ALMA" , "SUE" , "ELSIE" , "BETH" , "JEANNE" , "VICKI" , "CARLA" , "TARA" , "ROSEMARY" , "EILEEN" , "TERRI" , "GERTRUDE" , "LUCY" , "TONYA" , "ELLA" , "STACEY" , "WILMA" , "GINA" , "KRISTIN" , "JESSIE" , "NATALIE" , "AGNES" , "VERA" , "WILLIE" , "CHARLENE" , "BESSIE" , "DELORES" , "MELINDA" , "PEARL" , "ARLENE" , "MAUREEN" , "COLLEEN" , "ALLISON" , "TAMARA" , "JOY" , "GEORGIA" , "CONSTANCE" , "LILLIE" , "CLAUDIA" , "JACKIE" , "MARCIA" , "TANYA" , "NELLIE" , "MINNIE" , "MARLENE" , "HEIDI" , "GLENDA" , "LYDIA" , "VIOLA" , "COURTNEY" , "MARIAN" , "STELLA" , "CAROLINE" , "DORA" , "JO" , "VICKIE" , "MATTIE" , "TERRY" , "MAXINE" , "IRMA" , "MABEL" , "MARSHA" , "MYRTLE" , "LENA" , "CHRISTY" , "DEANNA" , "PATSY" , "HILDA" , "GWENDOLYN" , "JENNIE" , "NORA" , "MARGIE" , "NINA" , "CASSANDRA" , "LEAH" , "PENNY" , "KAY" , "PRISCILLA" , "NAOMI" , "CAROLE" , "BRANDY" , "OLGA" , "BILLIE" , "DIANNE" , "TRACEY" , "LEONA" , "JENNY" , "FELICIA" , "SONIA" , "MIRIAM" , "VELMA" , "BECKY" , "BOBBIE" , "VIOLET" , "KRISTINA" , "TONI" , "MISTY" , "MAE" , "SHELLY" , "DAISY" , "RAMONA" , "SHERRI" , "ERIKA" , "KATRINA" , "CLAIRE"};

    // Create boolean to allow/prevent Girlfriend Entity from spawning
    public boolean GirlfriendCanSpawn = true;

    // Create method to refer the parent class
    public GirlfriendEntity(EntityType<? extends VillagerEntity> entityType, World world) {

        // Call parent constructor using arguments entityType and world
        super(entityType, world);
    }


    // Use entity builder to create basic builder properties (movement speed and health)
    public static DefaultAttributeContainer.Builder createGirlfriendAttributes() {

        // Use villager entity as template for adding builder attributes
        return VillagerEntity.createVillagerAttributes()

                // Make relatively slow like villagers
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)

                // Make health relatively high so poison effect during "date mode" doesn't kill entity
                // Also hopefully discourages players from killing entity due to high health bar
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 40);
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
                && this.world.getBlockState(BlockUnderEntity).allowsSpawning(view, BlockUnderEntity, DatingMod.GIRLFRIEND)
                && GirlfriendCanSpawn;
    }


    // Create method to make player-entity interaction logic
    public final ActionResult interactMob(PlayerEntity player, Hand hand) {

        // Under the condition that the world is not run on the player's client-side...
        if (!world.isClient()) {

            // Under the condition that the player right clicks on the selected entity with an empty hand...
            if (player.getMainHandStack().isEmpty()) {

                // Under the condition that the selected entity's AI is disabled (flag is raised)...
                if (this.isAiDisabled()) {

                    // ...make the selected entity stand up
                    this.setPose(EntityPose.STANDING);

                    // ...make the selected entity's AI enabled again
                    this.setAiDisabled(false);
                }

                // Otherwise, under the condition that the selected entity's AI is enabled...
                else {

                    // ...Under the condition that the entity does not have a custom name...
                    if (!this.hasCustomName()) {

                        // ...make a new random object
                        Random random = new Random();

                        // ...select a random integer between 0 and 299 (the range of the names array)
                        // and assign it to the randomInt integer variable
                        int randomInt = random.nextInt(299);

                        // Set the selected entity's name as a String from the name array
                        // at the position of the random integer
                        this.setCustomName(new LiteralText(femaleNames[randomInt]));
                    }

                    // Have the player start riding the selected entity
                    player.startRiding(this);

                    // Place the selected entity in the sleeping position
                    this.setPose(EntityPose.SLEEPING);

                    // Set the entity AI as disabled
                    // (this prevents the entity from moving while in sleep position,
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

            // ...make a for loop statement that iterates 60 times that does the following...
            for (int i = 1; i <= 60; i++) {

                // Make new random object
                Random rand = new Random();


                // Make minimumX position coordinate value at 0.75 blocks behind the entity
                float minX = -0.75f;
                // Make maximumX position coordinate value at 0.75 blocks ahead the entity
                float maxX = 0.75f;

                // Have the random object pick a float between the minX/maxX values and assign to finalX float variable
                float finalX = rand.nextFloat() * (maxX - minX) + minX;


                // Make minimumZ position coordinate value at 0.75 blocks behind the entity
                float minZ = -0.75f;
                // Make minimumZ position coordinate value at 0.75 blocks ahead the entity
                float maxZ = 0.75f;

                // Have the random object pick a float between the minZ/maxZ values and assign to finalZ float variable
                float finalZ = rand.nextFloat() * (maxZ - minZ) + minZ;


                // Add smoke particle at the entity's X position +/- the final randomX position addition,
                // the entity's Y position + 0.25 of a block above the ground,
                // the entity's Z position +/- the final randomZ position addition,
                // floating at a speed of 0 in neither the X, Y or Z direction
                world.addImportantParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, true, this.getX() + finalX, this.getY() + 0.25, this.getZ() + finalZ, 0.0, 0.0, 0.0);
            }
        }

        // Return the result of the player's interaction done by their hand
        return super.interactMob(player, hand);
    }


}
