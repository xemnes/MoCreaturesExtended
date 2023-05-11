/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MoCTerrainEventHooks {

    public static List<Biome.SpawnListEntry> creatureList = new ArrayList<>();
    public static List<Biome.SpawnListEntry> waterCreatureList = new ArrayList<>();
    public static Object2ObjectOpenHashMap<Biome, List<Biome.SpawnListEntry>> creatureSpawnMap = new Object2ObjectOpenHashMap<>();
    public static Object2ObjectOpenHashMap<Biome, List<Biome.SpawnListEntry>> waterCreatureSpawnMap = new Object2ObjectOpenHashMap<>();

    @SubscribeEvent
    public void onPopulateChunkEvent(PopulateChunkEvent.Populate event) {
        if (event.getType() == PopulateChunkEvent.Populate.EventType.ANIMALS) {
            int chunkX = event.getChunkX() * 16;
            int chunkZ = event.getChunkZ() * 16;
            int centerX = chunkX + 8;
            int centerZ = chunkZ + 8;
            World world = event.getWorld();
            Random rand = event.getRand();
            BlockPos blockPos = new BlockPos(chunkX, 0, chunkZ);
            Biome biome = world.getBiome(blockPos.add(16, 0, 16));

            MoCTools.performCustomWorldGenSpawning(world, biome, centerX, centerZ, 16, 16, rand, creatureSpawnMap.get(biome), EntityLiving.SpawnPlacementType.ON_GROUND);
            MoCTools.performCustomWorldGenSpawning(world, biome, centerX, centerZ, 16, 16, rand, waterCreatureSpawnMap.get(biome), EntityLiving.SpawnPlacementType.IN_WATER);

            event.setResult(Event.Result.DENY);
        }
    }

    public static void buildWorldGenSpawnLists() {
        for (Biome biome : ForgeRegistries.BIOMES.getValuesCollection()) {
            creatureList = new ArrayList<>(biome.getSpawnableList(EnumCreatureType.CREATURE));
            creatureList.removeIf(entry -> entry.itemWeight == 0);
            creatureSpawnMap.put(biome, creatureList);

            waterCreatureList = new ArrayList<>(biome.getSpawnableList(EnumCreatureType.WATER_CREATURE));
            waterCreatureList.removeIf(entry -> entry.itemWeight == 0);
            waterCreatureSpawnMap.put(biome, waterCreatureList);
        }
    }
}
