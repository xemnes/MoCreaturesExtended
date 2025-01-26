/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.entity.aquatic;

import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.init.MoCLootTables;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class MoCEntityAngelFish extends MoCEntitySmallFish {

    public MoCEntityAngelFish(World world) {
        super(world);
        this.setType(2);
    }

    @Override
    public ResourceLocation getTexture() {
        return MoCreatures.proxy.getModelTexture("smallfish_angelfish.png");
    }

    @Nullable
    protected ResourceLocation getLootTable() {
        return MoCLootTables.ANGELFISH;
    }
}
