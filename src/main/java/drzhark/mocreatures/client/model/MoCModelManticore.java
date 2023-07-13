/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.client.model;

import drzhark.mocreatures.entity.hostile.MoCEntityManticore;
import net.minecraft.entity.Entity;

public class MoCModelManticore extends MoCModelBigCat {

    @Override
    public void updateAnimationModifiers(Entity entity) {
        MoCEntityManticore bigcat = (MoCEntityManticore) entity;
        this.isFlyer = bigcat.isFlyer();
        this.isSaddled = bigcat.getIsRideable();
        this.flapwings = true;
        this.floating = (this.isFlyer && bigcat.isOnAir() && !bigcat.onGround);
        this.poisoning = bigcat.swingingTail();
        this.isRidden = (bigcat.isBeingRidden());
        this.hasMane = true;
        this.hasSaberTeeth = true;
        this.onAir = (bigcat.isOnAir());
        this.hasStinger = true;
        this.isMovingVertically = bigcat.motionY != 0 && !bigcat.onGround;
        this.hasChest = false;
        this.isTamed = false;
    }
}
