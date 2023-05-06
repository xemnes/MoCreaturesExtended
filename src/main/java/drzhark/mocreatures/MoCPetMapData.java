/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures;

import com.google.common.collect.Maps;
import drzhark.mocreatures.entity.IMoCTameable;
import it.unimi.dsi.fastutil.objects.Object2ObjectMaps;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.DimensionManager;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.UUID;

public class MoCPetMapData extends WorldSavedData {

    private final Object2ObjectOpenHashMap<UUID, MoCPetData> petMap = new Object2ObjectOpenHashMap<>();

    public MoCPetMapData(String par1Str) {
        super(par1Str);
        this.markDirty();
    }

    /**
     * Get a list of pets.
     */
    public MoCPetData getPetData(UUID ownerUniqueId) {
        return this.petMap.get(ownerUniqueId);
    }

    public Object2ObjectOpenHashMap<UUID, MoCPetData> getPetMap() {
        return this.petMap;
    }

    public void removeOwnerPet(IMoCTameable pet, int petId) {
        if (this.petMap.get(pet.getOwnerId()) != null) // required since getInteger will always return 0 if no key is found
        {
            if (this.petMap.get(pet.getOwnerId()).removePet(petId)) {
                this.markDirty();
                pet.setOwnerPetId(-1);
            }
        }
    }

    public void updateOwnerPet(IMoCTameable pet) {
        this.markDirty();
        if (pet.getOwnerPetId() == -1 || this.petMap.get(pet.getOwnerId()) == null) {
            UUID owner = MoCreatures.isServer() ? pet.getOwnerId() : Minecraft.getMinecraft().player.getUniqueID();
            MoCPetData petData;
            int id;
            if (this.petMap.containsKey(owner)) {
                petData = this.petMap.get(owner);
                id = petData.addPet(pet);
            } else // create new pet data
            {
                petData = new MoCPetData(pet);
                id = petData.addPet(pet);
                this.petMap.put(owner, petData);
            }
            pet.setOwnerPetId(id);
        } else {
            // update pet data
            UUID owner = pet.getOwnerId();
            MoCPetData petData = this.getPetData(owner);
            NBTTagCompound rootNBT = petData.getOwnerRootNBT();
            NBTTagList tag = rootNBT.getTagList("TamedList", 10);
            int id;
            id = pet.getOwnerPetId();

            for (int i = 0; i < tag.tagCount(); i++) {
                NBTTagCompound nbt = tag.getCompoundTagAt(i);
                if (nbt.getInteger("PetId") == id) {
                    // Update what we need for commands
                    nbt.setTag("Pos", this.newDoubleNBTList(((Entity) pet).posX, ((Entity) pet).posY, ((Entity) pet).posZ));
                    nbt.setInteger("ChunkX", ((Entity) pet).chunkCoordX);
                    nbt.setInteger("ChunkY", ((Entity) pet).chunkCoordY);
                    nbt.setInteger("ChunkZ", ((Entity) pet).chunkCoordZ);
                    nbt.setInteger("Dimension", ((Entity) pet).world.provider.getDimensionType().getId());
                    nbt.setInteger("PetId", pet.getOwnerPetId());
                }
            }
        }
    }

    protected NBTTagList newDoubleNBTList(double... par1ArrayOfDouble) {
        NBTTagList nbttaglist = new NBTTagList();
        for (double d1 : par1ArrayOfDouble) {
            nbttaglist.appendTag(new NBTTagDouble(d1));
        }
        return nbttaglist;
    }

    public boolean isExistingPet(UUID owner, IMoCTameable pet) {
        MoCPetData petData = MoCreatures.instance.mapData.getPetData(owner);
        if (petData != null) {
            NBTTagList tag = petData.getTamedList();
            for (int i = 0; i < tag.tagCount(); i++) {
                NBTTagCompound nbt = tag.getCompoundTagAt(i);
                if (nbt.getInteger("PetId") == pet.getOwnerPetId()) {
                    // found existing pet
                    return true;
                }
            }
        }
        return false;
    }

    public void forceSave() {
        if (DimensionManager.getWorld(0) != null) {
            ISaveHandler saveHandler = DimensionManager.getWorld(0).getSaveHandler();
            try {
                File file1 = saveHandler.getMapFileFromName("mocreatures");
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                this.writeToNBT(nbttagcompound);
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setTag("data", nbttagcompound);
                FileOutputStream fileoutputstream = new FileOutputStream(file1);
                CompressedStreamTools.writeCompressed(nbttagcompound1, fileoutputstream);
                fileoutputstream.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * reads in data from the NBTTagCompound into this MapDataBase
     */
    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        for (String s : par1NBTTagCompound.getKeySet()) {
            NBTTagCompound nbt = (NBTTagCompound) par1NBTTagCompound.getTag(s);
            UUID ownerUniqueId = UUID.fromString(s);
            if (!this.petMap.containsKey(ownerUniqueId)) {
                this.petMap.put(ownerUniqueId, new MoCPetData(nbt, ownerUniqueId));
            }
        }
    }

    /**
     * write data to NBTTagCompound from this MapDataBase, similar to Entities
     * and TileEntities
     */
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound par1NBTTagCompound) {
        for (Map.Entry<UUID, MoCPetData> ownerEntry : this.petMap.entrySet()) {
            try {
                if (ownerEntry.getKey() != null) {
                    par1NBTTagCompound.setTag(ownerEntry.getKey().toString(), ownerEntry.getValue().getOwnerRootNBT());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return par1NBTTagCompound;
    }
}
