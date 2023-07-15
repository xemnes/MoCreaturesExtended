/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.entity.aquatic;

import javax.annotation.Nullable;

import drzhark.mocreatures.MoCLootTables;
import drzhark.mocreatures.MoCreatures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MoCEntityCod extends MoCEntityMediumFish {

    public MoCEntityCod(World world) {
        super(world);
        this.setType(2);
    }

    @Override
    public ResourceLocation getTexture() {
        return MoCreatures.proxy.getModelTexture("mediumfish_cod.png");
    }

    @Nullable
    protected ResourceLocation getLootTable() {
        return MoCLootTables.COD;
    }
}
