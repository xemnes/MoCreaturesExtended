/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.entity.hostile;

import drzhark.mocreatures.MoCLootTables;
import drzhark.mocreatures.MoCreatures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class MoCEntityGreenOgre extends MoCEntityOgre {

    public MoCEntityGreenOgre(World world) {
        super(world);
    }

    @Override
    public ResourceLocation getTexture() {
        return MoCreatures.proxy.getModelTexture("ogre_green.png");
    }

    /**
     * Returns the strength of the blasting power
     */
    @Override
    public float getDestroyForce() {
        return MoCreatures.proxy.ogreStrength;
    }

    @Nullable
    protected ResourceLocation getLootTable() {
        return MoCLootTables.GREEN_OGRE;
    }
}
