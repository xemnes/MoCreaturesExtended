/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures;

import com.google.common.primitives.Ints;
import drzhark.mocreatures.entity.IMoCTameable;
import drzhark.mocreatures.entity.passive.MoCEntityKitty;
import drzhark.mocreatures.util.CMSUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class MoCEventHooks {

    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event) {
        // if overworld has been deleted or unloaded, reset our flag
        if (event.getWorld().provider.getDimensionType().getId() == 0) {
            MoCreatures.proxy.worldInitDone = false;
        }
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        if (DimensionManager.getWorld(0) != null && !MoCreatures.proxy.worldInitDone) // if overworld has loaded, use its mapstorage
        {
            MoCPetMapData data = (MoCPetMapData) DimensionManager.getWorld(0).getMapStorage().getOrLoadData(MoCPetMapData.class, "mocreatures");
            if (data == null) {
                data = new MoCPetMapData("mocreatures");
            }

            DimensionManager.getWorld(0).getMapStorage().setData("mocreatures", data);
            DimensionManager.getWorld(0).getMapStorage().saveAllData();
            MoCreatures.instance.mapData = data;
            MoCreatures.proxy.worldInitDone = true;
        }

        // Make sure doMobSpawning is on if CMS is not installed
        GameRules gameRule = event.getWorld().getGameRules();
        if (!MoCreatures.isCustomSpawnerLoaded) {
            gameRule.setOrCreateGameRule("doMobSpawning", "true");
            if (MoCreatures.proxy.debug) {
                MoCreatures.LOGGER.debug("Changed doMobSpawning to true since CMS was not loaded!");
            }
        }
    }

    @SubscribeEvent
    public void onPopulateVillage(PopulateChunkEvent.Post event) {
        // Village spawning
        if (event.isHasVillageGenerated()) {
            MoCEntityData data = MoCreatures.entityMap.get(MoCEntityKitty.class);
            if (data == null) return;
            World world = event.getWorld();
            List<Integer> dimensionIDs = Ints.asList(data.getDimensions());
            if (!dimensionIDs.contains(world.provider.getDimension()) || data.getFrequency() <= 0 || !data.getCanSpawn())
                return;
            if (event.getRand().nextInt(100) < MoCreatures.proxy.kittyVillageChance) {
                BlockPos pos = new BlockPos(event.getChunkX() * 16, 100, event.getChunkZ() * 16);
                MoCEntityKitty kitty = new MoCEntityKitty(world);
                BlockPos spawnPos = getSafeSpawnPos(kitty, pos.add(8, 0, 8));
                if (spawnPos == null) return;
                kitty.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(kitty)), null);
                kitty.setPosition(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
                world.spawnEntity(kitty);
            }
        }
    }

    @SubscribeEvent
    public void onLivingSpawnEvent(LivingSpawnEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        Class<? extends EntityLivingBase> entityClass = entity.getClass();
        MoCEntityData data = MoCreatures.entityMap.get(entityClass);
        if (data == null) return; // not a MoC entity
        World world = event.getWorld();
        List<Integer> dimensionIDs = Ints.asList(data.getDimensions());
        if (!dimensionIDs.contains(world.provider.getDimension())) {
            event.setResult(Result.DENY);
        } else if (data.getFrequency() <= 0) {
            event.setResult(Result.DENY);
        } else if (dimensionIDs.contains(MoCreatures.proxy.wyvernDimension) && world.provider.getDimension() == MoCreatures.proxy.wyvernDimension) {
            event.setResult(Result.ALLOW);
        }
    }

    @SubscribeEvent
    public void onLivingDeathEvent(LivingDeathEvent event) {
        if (!event.getEntity().world.isRemote) {
            if (IMoCTameable.class.isAssignableFrom(event.getEntityLiving().getClass())) {
                IMoCTameable mocEntity = (IMoCTameable) event.getEntityLiving();
                if (mocEntity.getIsTamed() && mocEntity.getPetHealth() > 0 && !mocEntity.isRiderDisconnecting()) {
                    return;
                }

                if (mocEntity.getOwnerPetId() != -1) // required since getInteger will always return 0 if no key is found
                {
                    MoCreatures.instance.mapData.removeOwnerPet(mocEntity, mocEntity.getOwnerPetId());
                }
            }
        }
    }

    // used for Despawner
    @SubscribeEvent
    public void onLivingDespawn(LivingSpawnEvent.AllowDespawn event) {
        if (MoCreatures.proxy.forceDespawns && !MoCreatures.isCustomSpawnerLoaded) {
            // try to guess what we should ignore
            // Monsters
            if ((IMob.class.isAssignableFrom(event.getEntityLiving().getClass()) || IRangedAttackMob.class.isAssignableFrom(event.getEntityLiving().getClass())) || event.getEntityLiving().isCreatureType(EnumCreatureType.MONSTER, false)) {
                return;
            }
            // Tameable
            if (event.getEntityLiving() instanceof EntityTameable) {
                if (((EntityTameable) event.getEntityLiving()).isTamed()) {
                    return;
                }
            }
            // Farm animals
            if (event.getEntityLiving() instanceof EntitySheep || event.getEntityLiving() instanceof EntityPig || event.getEntityLiving() instanceof EntityCow || event.getEntityLiving() instanceof EntityChicken) {
                // check lightlevel
                if (isValidDespawnLightLevel(event.getEntity(), event.getWorld(), MoCreatures.proxy.minDespawnLightLevel, MoCreatures.proxy.maxDespawnLightLevel)) {
                    return;
                }
            }
            // Others
            NBTTagCompound nbt = new NBTTagCompound();
            event.getEntityLiving().writeToNBT(nbt);
            if (nbt.hasKey("Owner") && !nbt.getString("Owner").equals("")) {
                return; // ignore
            }
            if (nbt.hasKey("Tamed") && nbt.getBoolean("Tamed")) {
                return; // ignore
            }
            // Deny Rest
            if (event.getEntityLiving().getIdleTime() > 600) {
                event.setResult(Result.ALLOW);
            }

            if (MoCreatures.proxy.debug) {
                int x = MathHelper.floor(event.getEntity().posX);
                int y = MathHelper.floor(event.getEntity().getEntityBoundingBox().minY);
                int z = MathHelper.floor(event.getEntity().posZ);
                MoCreatures.LOGGER.info("Forced Despawn of entity " + event.getEntityLiving() + " at " + x + ", " + y + ", " + z + ". To prevent forced despawns, use /moc forceDespawns false.");
            }
        }
    }

    private boolean isValidDespawnLightLevel(Entity entity, World world, int minDespawnLightLevel, int maxDespawnLightLevel) {
        int x = MathHelper.floor(entity.posX);
        int y = MathHelper.floor(entity.getEntityBoundingBox().minY);
        int z = MathHelper.floor(entity.posZ);
        int blockLightLevel = 0;
        if (y >= 0) {
            if (y >= 256) {
                y = 255;
            }
            blockLightLevel = CMSUtils.getLightFromNeighbors(world.getChunk(x >> 4, z >> 4), x & 15, y, z & 15);
        }
        if (blockLightLevel < minDespawnLightLevel && maxDespawnLightLevel != -1) {
            return false;
        } else return blockLightLevel <= maxDespawnLightLevel || maxDespawnLightLevel == -1;
    }

    private BlockPos getSafeSpawnPos(EntityLivingBase entity, BlockPos near) {
        int radius = 6;
        int maxTries = 24;
        BlockPos testing;
        for (int i = 0; i < maxTries; i++) {
            int x = near.getX() + entity.getEntityWorld().rand.nextInt(radius * 2) - radius;
            int z = near.getZ() + entity.getEntityWorld().rand.nextInt(radius * 2) - radius;
            int y = entity.getEntityWorld().getHeight(x, z) + 16;
            testing = new BlockPos(x, y, z);
            while (entity.getEntityWorld().isAirBlock(testing) && testing.getY() > 0) {
                testing = testing.down(1);
            }
            IBlockState iblockstate = entity.getEntityWorld().getBlockState(testing);
            if (iblockstate.canEntitySpawn(entity)) {
                return testing.up(1);
            }
        }
        return null;
    }
}
