/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.entity.aquatic;

import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.entity.tameable.MoCEntityTameableAquatic;
import drzhark.mocreatures.init.MoCSoundEvents;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class MoCEntityRay extends MoCEntityTameableAquatic {

    public MoCEntityRay(World world) {
        super(world);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(2, new EntityAIWanderMoC2(this, 1.0D, 80));
    }

    public boolean isPoisoning() {
        return false;
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        final Boolean tameResult = this.processTameInteract(player, hand);
        if (tameResult != null) {
            return tameResult;
        }

        if (!this.isBeingRidden() && getType() == 1) {
            if (!this.world.isRemote && player.startRiding(this)) {
                player.rotationYaw = this.rotationYaw;
                player.rotationPitch = this.rotationPitch;
                player.posY = this.posY;
            }

            return true;
        }

        return super.processInteract(player, hand);
    }

    @Override
    public float getAdjustedYOffset() {
        if (!this.isInWater()) {
            return 0.09F;
        }
        return 0.15F;
    }

    @Override
    public int nameYOffset() {
        return -25;
    }

    @Override
    public boolean canBeTrappedInNet() {
        return true;
    }

    @Override
    public double getMountedYOffset() {
        return this.height * 0.15D * getSizeFactor();
    }

    @Override
    public float getSizeFactor() {
        float f = getAge() * 0.01F;
        if (f > 1.5F) {
            f = 1.5F;
        }
        return f;
    }

    @Override
    protected boolean usesNewAI() {
        return true;
    }

    @Override
    public float getAIMoveSpeed() {
        return 0.06F;
    }

    @Override
    public boolean isMovementCeased() {
        return !isInWater();
    }

    @Override
    protected double minDivingDepth() {
        return 3D;
    }

    @Override
    protected double maxDivingDepth() {
        return 6.0D;
    }

    @Override
    public int getMaxAge() {
        return 90;
    }

    public boolean isMantaRay() {
        return false;
    }
    
    @Override
    protected SoundEvent getDeathSound() {
        return MoCSoundEvents.ENTITY_FISH_FLOP;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return MoCSoundEvents.ENTITY_FISH_HURT;
    }

    @Override
    protected SoundEvent getSwimSound() {
        return MoCSoundEvents.ENTITY_FISH_SWIM;
    }
}
