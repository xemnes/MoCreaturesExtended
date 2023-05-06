/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.entity.ambient;

import drzhark.mocreatures.entity.MoCEntityInsect;
import drzhark.mocreatures.entity.ai.EntityAIFleeFromEntityMoC;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIFollow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MoCEntityRoach extends MoCEntityInsect {

    public MoCEntityRoach(World world) {
        super(world);
        this.texture = "roach.png";
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAIFleeFromEntityMoC(this, entity -> !(entity instanceof MoCEntityCrab) && (entity.height > 0.3F || entity.width > 0.3F), 6.0F, 0.8D, 1.3D));
        this.tasks.addTask(3, new EntityAIFollow(this, 1.0D, 14.0F, 28.0F));
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
    }

    @Override
    public boolean entitiesToInclude(Entity entity) {
        return !(entity instanceof MoCEntityInsect) && super.entitiesToInclude(entity);
    }

    @Override
    public boolean isFlyer() {
        return true;
    }

    @Override
    public boolean isMyFavoriteFood(ItemStack stack) {
        return !stack.isEmpty() && stack.getItem() == Items.ROTTEN_FLESH;
    }

    @Override
    public float getAIMoveSpeed() {
        if (getIsFlying()) {
            return 0.1F;
        }
        return 0.25F;
    }

    @Override
    public boolean isNotScared() {
        return getIsFlying();
    }
}
