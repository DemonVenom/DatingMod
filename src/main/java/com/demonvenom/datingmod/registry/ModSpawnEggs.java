package com.demonvenom.datingmod.registry;

import com.demonvenom.datingmod.DatingMod;

import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ModSpawnEggs {

    // Create new SpawnEgg that spawns GirlfriendEntity, sets the color to pink/white, sets it as a common item, allow it to stack in 64 stacks, and adds it to the ITEM_GROUP creative tab
    public static final Item GIRLFRIEND_EGG = new SpawnEggItem(DatingMod.GIRLFRIEND, 0xF7A8B8, 0xFFFFFF, new Item.Settings().rarity(Rarity.COMMON).maxCount(64).group(DatingMod.ITEM_GROUP));

    // Create new SpawnEgg that spawns BoyfriendEntity, sets the color to blue/white, sets it as a common item, allow it to stack in 64 stacks, and adds it to the ITEM_GROUP creative tab
    public static final Item BOYFRIEND_EGG = new SpawnEggItem(DatingMod.BOYFRIEND, 0x55CDFC, 0xFFFFFF, new Item.Settings().rarity(Rarity.COMMON).maxCount(64).group(DatingMod.ITEM_GROUP));


    public static void registerSpawnEggs() {

        // Create method that makes new identifier for GirlfriendEntity SpawnEgg under the id "mcdatingmod:girlfriend_egg"
        Registry.register(Registry.ITEM, new Identifier(DatingMod.MOD_ID, "girlfriend_egg"), GIRLFRIEND_EGG);

        // Create method that makes new identifier for BoyfriendEntity SpawnEgg under the id "mcdatingmod:boyfriend_egg"
        Registry.register(Registry.ITEM, new Identifier(DatingMod.MOD_ID, "boyfriend_egg"), BOYFRIEND_EGG);
    }


}