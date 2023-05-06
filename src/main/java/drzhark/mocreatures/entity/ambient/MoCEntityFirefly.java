/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.entity.ambient;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.entity.MoCEntityInsect;
import drzhark.mocreatures.init.MoCSoundEvents;
import net.minecraft.entity.ai.EntityAIFollow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class MoCEntityFirefly extends MoCEntityInsect {

    private int soundCount;

    public MoCEntityFirefly(World world) {
        super(world);
        this.texture = "firefly.png";
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(3, new EntityAIFollow(this, 1.0D, 14.0F, 28.0F));
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (!this.world.isRemote) {
            EntityPlayer ep = this.world.getClosestPlayerToEntity(this, 5D);
            if (ep != null && getIsFlying() && --this.soundCount == -1) {
                MoCTools.playCustomSound(this, MoCSoundEvents.ENTITY_CRICKET_FLY);
                this.soundCount = 20;
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
        return 0.10F;
    }
}
