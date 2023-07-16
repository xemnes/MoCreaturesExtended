/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.entity.aquatic;

import javax.annotation.Nullable;

import drzhark.mocreatures.MoCLootTables;
import drzhark.mocreatures.MoCreatures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MoCEntityClownFish extends MoCEntitySmallFish {

    public MoCEntityClownFish(World world) {
        super(world);
        this.setType(4);
    }

    @Override
    public ResourceLocation getTexture() {
        return MoCreatures.proxy.getModelTexture("fish_clownfish.png");
    }

    @Nullable
    protected ResourceLocation getLootTable() {
        return MoCLootTables.CLOWNFISH;
    }
}
