/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.entity.hostile;

import drzhark.mocreatures.MoCLootTables;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.MoCEntityMob;
import drzhark.mocreatures.entity.hunter.MoCEntityBear;
import drzhark.mocreatures.entity.hunter.MoCEntityBigCat;
import drzhark.mocreatures.entity.passive.MoCEntityHorse;
import drzhark.mocreatures.init.MoCSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

import java.util.List;

import javax.annotation.Nullable;

public class MoCEntityWWolf extends MoCEntityMob {

    public int mouthCounter;
    public int tailCounter;

    public MoCEntityWWolf(World world) {
        super(world);
        setSize(0.9F, 1.3F);
        setAdult(true);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
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
                return MoCreatures.proxy.getModelTexture("wild_wolf_black.png");
            case 3:
                return MoCreatures.proxy.getModelTexture("wild_wolf_timber.png");
            case 4:
                return MoCreatures.proxy.getModelTexture("wild_wolf_dark.png");
            case 5:
                return MoCreatures.proxy.getModelTexture("wild_wolf_bright.png");
            default:
                return MoCreatures.proxy.getModelTexture("wild_wolf_classic.png");
        }
    }

    private void openMouth() {
        this.mouthCounter = 1;
    }

    private void moveTail() {
        this.tailCounter = 1;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.rand.nextInt(200) == 0) {
            moveTail();
        }

        if (this.mouthCounter > 0 && ++this.mouthCounter > 15) {
            this.mouthCounter = 0;
        }

        if (this.tailCounter > 0 && ++this.tailCounter > 8) {
            this.tailCounter = 0;
        }
    }

    @Override
    public boolean checkSpawningBiome() {
        int i = MathHelper.floor(this.posX);
        int j = MathHelper.floor(getEntityBoundingBox().minY);
        int k = MathHelper.floor(this.posZ);

        Biome biome = MoCTools.Biomekind(this.world, new BlockPos(i, j, k));
        if (BiomeDictionary.hasType(biome, Type.SNOWY)) {
            setType(3);
        }
        selectType();
        return true;
    }

    //TODO move this
    public EntityLivingBase getClosestTarget(Entity entity, double d) {
        double d1 = -1D;
        EntityLivingBase entityliving = null;
        List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().grow(d));
        for (Entity entity1 : list) {
            if (!(entity1 instanceof EntityLivingBase) || (entity1 == entity) || (entity1 == entity.getRidingEntity())
                    || (entity1 == entity.getRidingEntity()) || (entity1 instanceof EntityPlayer) || (entity1 instanceof EntityMob)
                    || (entity1 instanceof MoCEntityBigCat) || (entity1 instanceof MoCEntityBear) || (entity1 instanceof EntityCow)
                    || ((entity1 instanceof EntityWolf) && !(MoCreatures.proxy.attackWolves))
                    || ((entity1 instanceof MoCEntityHorse) && !(MoCreatures.proxy.attackHorses))) {
                continue;
            }
            double d2 = entity1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
            if (((d < 0.0D) || (d2 < (d * d))) && ((d1 == -1D) || (d2 < d1)) && ((EntityLivingBase) entity1).canEntityBeSeen(entity)) {
                d1 = d2;
                entityliving = (EntityLivingBase) entity1;
            }
        }

        return entityliving;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return MoCSoundEvents.ENTITY_WOLF_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        openMouth();
        return MoCSoundEvents.ENTITY_WOLF_HURT;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        openMouth();
        return MoCSoundEvents.ENTITY_WOLF_AMBIENT;
    }

    @Nullable
    protected ResourceLocation getLootTable() {
        if (!getIsAdult()) {
            return null;
        }

        return MoCLootTables.WILD_WOLF;
    }

    @Override
    public void updatePassenger(Entity passenger) {
        double dist = (0.1D);
        double newPosX = this.posX + (dist * Math.sin(this.renderYawOffset / 57.29578F));
        double newPosZ = this.posZ - (dist * Math.cos(this.renderYawOffset / 57.29578F));
        passenger.setPosition(newPosX, this.posY + getMountedYOffset() + passenger.getYOffset(), newPosZ);
        passenger.rotationYaw = this.rotationYaw;
    }

    @Override
    public double getMountedYOffset() {
        return (this.height * 0.75D) - 0.1D;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.world.isRemote && !this.isBeingRidden() && this.rand.nextInt(100) == 0) {
            List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().grow(4D, 2D, 4D));
            for (Entity entity : list) {
                if (!(entity instanceof EntityMob)) {
                    continue;
                }
                EntityMob entitymob = (EntityMob) entity;
                if (entitymob.getRidingEntity() == null
                        && (entitymob instanceof EntitySkeleton || entitymob instanceof EntityZombie || entitymob instanceof MoCEntitySilverSkeleton)) {
                    entitymob.startRiding(this);
                    break;
                }
            }
        }
    }
}
