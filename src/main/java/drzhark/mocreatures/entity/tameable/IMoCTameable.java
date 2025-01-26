/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.entity.tameable;

import drzhark.mocreatures.entity.IMoCEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nullable;
import java.util.UUID;

public interface IMoCTameable extends IMoCEntity {

    boolean isRiderDisconnecting();

    void setRiderDisconnecting(boolean flag);

    void playTameEffect(boolean par1);

    void setTamed(boolean par1);

    void setDead();

    void writeEntityToNBT(NBTTagCompound nbttagcompound);

    void readEntityFromNBT(NBTTagCompound nbttagcompound);

    void setOwnerId(@Nullable UUID uuid);

    float getPetHealth();

    void spawnHeart();

    boolean readytoBreed();

    String getOffspringClazz(IMoCTameable mate);

    int getOffspringTypeInt(IMoCTameable mate);

    boolean compatibleMate(Entity mate);

    boolean getHasEaten();

    void setHasEaten(boolean flag);

    int getGestationTime();

    void setGestationTime(int time);

}
