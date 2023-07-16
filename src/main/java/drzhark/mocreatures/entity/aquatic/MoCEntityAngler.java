/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.entity.aquatic;

import javax.annotation.Nullable;

import drzhark.mocreatures.MoCLootTables;
import drzhark.mocreatures.MoCreatures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MoCEntityAngler extends MoCEntitySmallFish {

    public MoCEntityAngler(World world) {
        super(world);
        this.setType(3);
    }

    @Override
    public ResourceLocation getTexture() {
        return MoCreatures.proxy.getModelTexture("smallfish_anglerfish.png");
    }

    @Nullable
    protected ResourceLocation getLootTable() {
        return MoCLootTables.ANGLERFISH;
    }
}
