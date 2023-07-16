/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.entity.aquatic;

import javax.annotation.Nullable;

import drzhark.mocreatures.MoCLootTables;
import drzhark.mocreatures.MoCreatures;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MoCEntityMantaRay extends MoCEntityRay {

    public MoCEntityMantaRay(World world) {
        super(world);
        setSize(1.8F, 1F);
        setAge(80 + (this.rand.nextInt(100)));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20D);
    }

    @Override
    public int getMaxAge() {
        return 180;
    }

    @Override
    public ResourceLocation getTexture() {
        return MoCreatures.proxy.getModelTexture("manta_ray.png");
    }

    @Nullable
    protected ResourceLocation getLootTable() {
        return MoCLootTables.MANTA_RAY;
    }

    @Override
    public boolean isMantaRay() {
        return true;
    }
}
