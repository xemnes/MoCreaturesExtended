/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.entity.hostile;

import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.init.MoCItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MoCEntityFireOgre extends MoCEntityOgre {

    public MoCEntityFireOgre(World world) {
        super(world);
        this.isImmuneToFire = true;
    }

    @Override
    public ResourceLocation getTexture() {
        return MoCreatures.proxy.getModelTexture("ogre_fire.png");
    }

    @Override
    public boolean isFireStarter() {
        return true;
    }

    @Override
    public float getDestroyForce() {
        return MoCreatures.proxy.ogreFireStrength;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (this.isWet()) {
            this.attackEntityFrom(DamageSource.DROWN, 1.0F);
        }
    }

    @Override
    protected Item getDropItem() {
        boolean flag = (this.rand.nextInt(100) < MoCreatures.proxy.rareItemDropChance);
        if (!flag) {
            return Item.getItemFromBlock(Blocks.FIRE);
        }
        return MoCItems.heartfire;
    }
}
