package com.demonvenom.datingmod.spawns;

import com.demonvenom.datingmod.DatingMod;

import com.google.common.base.Preconditions;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;

import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.util.registry.Registry;

import java.util.function.Predicate;


@SuppressWarnings("deprecation")
public class SpawnInit {

    public static int SpawnRate = 50;

    public static void addSpawn(Predicate<BiomeSelectionContext> BiomeSelector, SpawnGroup spawngroup, SpawnSettings.SpawnEntry se) {
        Preconditions.checkArgument(se.type.getSpawnGroup() != SpawnGroup.MISC, "MISC spawns pigs");

        Identifier id = Registry.ENTITY_TYPE.getId(se.type);
        Preconditions.checkState(id != Registry.ENTITY_TYPE.getDefaultId(), "Unregistered entity type: bf", se.type);

        BiomeModifications.create(id).add(ModificationPhase.ADDITIONS, BiomeSelector, context -> {
            context.getSpawnSettings().addSpawn(spawngroup, se);
        });
    }

    private static void normalspawn() {

        Predicate<BiomeSelectionContext> biomeSelector = (context) -> {

            Biome.Category category = context.getBiome().getCategory();
            return category != Biome.Category.NETHER && category != Biome.Category.THEEND
                    && category != Biome.Category.MESA && category != Biome.Category.DESERT
                    && category != Biome.Category.MUSHROOM && category != Biome.Category.EXTREME_HILLS
                    && category != Biome.Category.ICY && category != Biome.Category.OCEAN;
        };


        addSpawn(biomeSelector, DatingMod.BOYFRIEND.getSpawnGroup(),
                new SpawnSettings.SpawnEntry(DatingMod.BOYFRIEND, SpawnRate, 1, 2));

        addSpawn(biomeSelector, DatingMod.GIRLFRIEND.getSpawnGroup(),
                new SpawnSettings.SpawnEntry(DatingMod.GIRLFRIEND, SpawnRate, 1, 2));


    }

    public static void init() {

        normalspawn();

    }


}
