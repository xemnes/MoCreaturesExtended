/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;
import java.util.Random;

public class MoCTerrainEventHooks {

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
            List<Biome.SpawnListEntry> creatureList = biome.getSpawnableList(EnumCreatureType.CREATURE);
            List<Biome.SpawnListEntry> waterCreatureList = biome.getSpawnableList(EnumCreatureType.WATER_CREATURE);

            for (int i = 0; i < MoCreatures.proxy.spawnMultiplier; i++) {
                MoCTools.performCustomWorldGenSpawning(world, biome, centerX, centerZ, 16, 16, rand, creatureList, EntityLiving.SpawnPlacementType.ON_GROUND);
            }
            for (int i = 0; i < MoCreatures.proxy.spawnMultiplier; i++) {
                MoCTools.performCustomWorldGenSpawning(world, biome, centerX, centerZ, 16, 16, rand, waterCreatureList, EntityLiving.SpawnPlacementType.IN_WATER);
            }
        }
    }
}
