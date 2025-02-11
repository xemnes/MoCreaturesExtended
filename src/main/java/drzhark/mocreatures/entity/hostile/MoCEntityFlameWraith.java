/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.entity.hostile;

import javax.annotation.Nullable;

import drzhark.mocreatures.MoCLootTables;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class MoCEntityFlameWraith extends MoCEntityWraith implements IMob {

    protected int burningTime;

    public MoCEntityFlameWraith(World world) {
        super(world);
        this.texture = "flame_wraith.png";
        this.isImmuneToFire = true;
        this.burningTime = 30;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
    }

    @Nullable
    protected ResourceLocation getLootTable() {
        return MoCLootTables.FLAME_WRAITH;
    }

    @Override
    public void onLivingUpdate() {
        if (!this.world.isRemote) {
            if (this.world.isDaytime()) {
                float f = getBrightness();
                if ((f > 0.5F) && this.world.canBlockSeeSky(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.posY), MathHelper.floor(this.posZ))) && ((this.rand.nextFloat() * 30F) < ((f - 0.4F) * 2.0F))) {
                    this.setHealth(getHealth() - 2);
                }
            }
        } else {
            for (int i = 0; i < 2; ++i) {
                this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX + (this.rand.nextDouble() - 0.5D) * (double) this.width, this.posY + this.rand.nextDouble() * (double) this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double) this.width, 0.0D, 0.0D, 0.0D);
            }
        }
        super.onLivingUpdate();
    }

    //TODO TEST
    /*@Override
    public float getMoveSpeed() {
        return 1.1F;
    }*/

    @Override
    protected void applyEnchantments(EntityLivingBase entityLivingBaseIn, Entity entityIn) {
        if (!this.world.isRemote && !this.world.provider.doesWaterVaporize()) {
            entityLivingBaseIn.setFire(this.burningTime);
        }
        super.applyEnchantments(entityLivingBaseIn, entityIn);
    }
}
