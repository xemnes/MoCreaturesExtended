/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.entity.hostile;

import drzhark.mocreatures.MoCreatures;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MoCEntityCaveOgre extends MoCEntityOgre {

    public MoCEntityCaveOgre(World world) {
        super(world);
    }

    @Override
    public ResourceLocation getTexture() {
        return MoCreatures.proxy.getTexture("ogre_cave.png");
    }

    /**
     * Returns the strength of the blasting power
     */
    @Override
    public float getDestroyForce() {
        return MoCreatures.proxy.ogreCaveStrength;
    }

    @Override
    protected boolean isHarmedByDaylight() {
        return true;
    }

    @Override
    public boolean getCanSpawnHere() {
        return super.getCanSpawnHere() && !this.world.canSeeSky(new BlockPos(this)) && (this.posY < 50.0D);
    }

    @Override
    public float calculateMaxHealth() {
        return 50F;
    }

    @Override
    protected Item getDropItem() {
        return Items.DIAMOND;
    }
}