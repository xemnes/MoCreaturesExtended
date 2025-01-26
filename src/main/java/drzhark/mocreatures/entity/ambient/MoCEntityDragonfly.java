/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.entity.ambient;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.MoCEntityInsect;
import drzhark.mocreatures.init.MoCLootTables;
import drzhark.mocreatures.init.MoCSoundEvents;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class MoCEntityDragonfly extends MoCEntityInsect {

    private int soundCount;

    public MoCEntityDragonfly(World world) {
        super(world);
        this.texture = "dragonflya.png";
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(1.0D);
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData par1EntityLivingData) {
        if (this.world.provider.getDimension() == MoCreatures.proxy.wyvernDimension) this.enablePersistence();
        return super.onInitialSpawn(difficulty, par1EntityLivingData);
    }

    @Override
    public void selectType() {
        if (getType() == 0) {
            setType(this.rand.nextInt(4) + 1);
        }
    }

    @Override
    public ResourceLocation getTexture() {
        switch (getType()) {
            case 1:
                return MoCreatures.proxy.getModelTexture("dragonfly_green.png");
            case 2:
                return MoCreatures.proxy.getModelTexture("dragonfly_cyan.png");
            case 3:
                return MoCreatures.proxy.getModelTexture("dragonfly_red.png");
            default:
                return MoCreatures.proxy.getModelTexture("dragonfly_blue.png");
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (!this.world.isRemote) {
            EntityPlayer ep = this.world.getClosestPlayerToEntity(this, 5D);
            if (ep != null && getIsFlying() && --this.soundCount == -1) {
                MoCTools.playCustomSound(this, MoCSoundEvents.ENTITY_DRAGONFLY_AMBIENT);
                this.soundCount = 20;
            }
        }
    }

    @Override
    protected SoundEvent getDeathSound() {
        return MoCSoundEvents.ENTITY_DRAGONFLY_HURT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return MoCSoundEvents.ENTITY_DRAGONFLY_HURT;
    }

    @Nullable
    protected ResourceLocation getLootTable() {
        return MoCLootTables.DRAGONFLY;
    }

    @Override
    public boolean isFlyer() {
        return true;
    }

    @Override
    public float getAIMoveSpeed() {
        if (getIsFlying()) {
            return 0.25F;
        }
        return 0.12F;
    }
    
    @Override
    public int getMaxSpawnedInChunk() {
        return 4;
    }
}
