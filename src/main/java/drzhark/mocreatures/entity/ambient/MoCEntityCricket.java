/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.entity.ambient;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.MoCEntityInsect;
import drzhark.mocreatures.init.MoCSoundEvents;
import net.minecraft.entity.ai.EntityAIFollow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class MoCEntityCricket extends MoCEntityInsect {

    private int jumpCounter;
    private int soundCounter;

    public MoCEntityCricket(World world) {
        super(world);
        this.texture = "cricketa.png";
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(3, new EntityAIFollow(this, 1.0D, 14.0F, 28.0F));
    }

    @Override
    public void selectType() {
        if (getType() == 0) {
            int i = this.rand.nextInt(100);
            if (i <= 50) {
                setType(1);
            } else {
                setType(2);
            }
        }
    }

    @Override
    public ResourceLocation getTexture() {
        if (getType() == 1) {
            return MoCreatures.proxy.getModelTexture("cricket_bright_green.png");
        } else {
            return MoCreatures.proxy.getModelTexture("cricket_green.png");
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.world.isRemote) {
            if (getIsFlying() || !this.onGround) {
                EntityPlayer ep = this.world.getClosestPlayerToEntity(this, 5D);
                if (ep != null && --this.soundCounter == -1) {
                    MoCTools.playCustomSound(this, MoCSoundEvents.ENTITY_CRICKET_FLY);
                    this.soundCounter = 10;
                }
            } else if (!DimensionManager.getWorld(0).isDaytime()) {
                EntityPlayer ep = this.world.getClosestPlayerToEntity(this, 12D);
                if (ep != null && --this.soundCounter == -1) {
                    MoCTools.playCustomSound(this, MoCSoundEvents.ENTITY_CRICKET_AMBIENT);
                    this.soundCounter = 20;
                }
            }

            if (this.jumpCounter > 0 && ++this.jumpCounter > 30) {
                this.jumpCounter = 0;
            }
        }
    }

    @Override
    protected SoundEvent getDeathSound() {
        return MoCSoundEvents.ENTITY_CRICKET_HURT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return MoCSoundEvents.ENTITY_CRICKET_HURT;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!this.world.isRemote) {
            if (onGround && ((motionX > 0.05D) || (motionZ > 0.05D) || (motionX < -0.05D) || (motionZ < -0.05D)))
                if (this.jumpCounter == 0) {
                    this.motionY = 0.45D;
                    this.motionX *= 5D;
                    this.motionZ *= 5D;
                    this.jumpCounter = 1;
                }
        }
    }

    @Override
    public boolean isFlyer() {
        return true;
    }

    @Override
    public float getAIMoveSpeed() {
        if (getIsFlying()) {
            return 0.12F;
        }
        return 0.15F;
    }

    @Override
    public float getEyeHeight() {
        return 0.15F;
    }
}
