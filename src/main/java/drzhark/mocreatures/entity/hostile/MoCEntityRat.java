/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.entity.hostile;

import drzhark.mocreatures.MoCLootTables;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.MoCEntityMob;
import drzhark.mocreatures.init.MoCSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nullable;
import java.util.List;

public class MoCEntityRat extends MoCEntityMob {

    private static final DataParameter<Byte> CLIMBING = EntityDataManager.createKey(MoCEntityScorpion.class, DataSerializers.BYTE);

    public MoCEntityRat(World world) {
        super(world);
        setSize(0.58F, 0.455F);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new MoCEntityRat.AIRatAttack(this));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new MoCEntityRat.AIRatTarget<>(this, EntityPlayer.class));
        this.targetTasks.addTask(3, new MoCEntityRat.AIRatTarget<>(this, EntityIronGolem.class));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
    }

    @Override
    protected PathNavigate createNavigator(World worldIn) {
        return new PathNavigateClimber(this, worldIn);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(CLIMBING, (byte) 0);
    }

    @Override
    public void selectType() {
        checkSpawningBiome();

        if (getType() == 0) {
            int i = this.rand.nextInt(100);
            if (i <= 65) {
                setType(1);
            } else if (i <= 98) {
                setType(2);
            } else {
                setType(3);
            }
        }
    }

    @Override
    protected double getAttackStrenght() {
        return 1D;
    }

    @Override
    public ResourceLocation getTexture() {
        switch (getType()) {
            case 2:
                return MoCreatures.proxy.getModelTexture("rat_black.png");
            case 3:
                return MoCreatures.proxy.getModelTexture("rat_white.png");
            default:
                return MoCreatures.proxy.getModelTexture("rat_brown.png");
        }
    }

    @Override
    public boolean checkSpawningBiome() {
        BlockPos pos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(getEntityBoundingBox().minY), this.posZ);
        Biome currentbiome = MoCTools.biomeKind(this.world, pos);

        try {
            if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.MESA)) {
                setType(1); // only brown rats
            }

            if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.SNOWY)) {
                setType(3); // only white rats
            }
        } catch (Exception ignored) {
        }
        return true;
    }

    @Override
    protected SoundEvent getFallSound(int heightIn) {
        return null;
    }

    @Override
    public void fall(float distance, float damageMultiplier) {
    }

    @Override
    public boolean attackEntityFrom(DamageSource damagesource, float i) {
        Entity entity = damagesource.getTrueSource();
        if (entity instanceof EntityLivingBase) {
            setAttackTarget((EntityLivingBase) entity);
            if (!this.world.isRemote) {
                List<MoCEntityRat> list = this.world.getEntitiesWithinAABB(MoCEntityRat.class, new AxisAlignedBB(this.posX, this.posY, this.posZ, this.posX + 1.0D, this.posY + 1.0D, this.posZ + 1.0D).grow(16D, 4D, 16D));
                for (MoCEntityRat entityrat : list) {
                    if ((entityrat != null) && (entityrat.getAttackTarget() == null)) {
                        entityrat.setAttackTarget((EntityLivingBase) entity);
                    }
                }
            }
        }
        return super.attackEntityFrom(damagesource, i);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if ((this.rand.nextInt(100) == 0) && (this.getBrightness() > 0.5F)) {
            setAttackTarget(null);
        }
    }

    @Override
    protected SoundEvent getDeathSound() {
        return MoCreatures.proxy.legacyRatDeathSound ? MoCSoundEvents.ENTITY_RAT_DEATH_LEGACY : MoCSoundEvents.ENTITY_RAT_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return MoCSoundEvents.ENTITY_RAT_HURT;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return MoCSoundEvents.ENTITY_RAT_AMBIENT;
    }

    @Nullable
    protected ResourceLocation getLootTable() {
        return MoCLootTables.RAT;
    }

    @Override
    public boolean isOnLadder() {
        return this.isBesideClimbableBlock();
    }

    public boolean isBesideClimbableBlock() {
        return (this.dataManager.get(CLIMBING) & 1) != 0;
    }

    public void setBesideClimbableBlock(boolean climbing) {
        byte b0 = this.dataManager.get(CLIMBING);

        if (climbing) {
            b0 = (byte) (b0 | 1);
        } else {
            b0 = (byte) (b0 & -2);
        }

        this.dataManager.set(CLIMBING, b0);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (!this.world.isRemote) {
            this.setBesideClimbableBlock(this.collidedHorizontally);
        }
    }

    static class AIRatAttack extends EntityAIAttackMelee {
        public AIRatAttack(MoCEntityRat rat) {
            super(rat, 1.0D, true);
        }

        @Override
        public boolean shouldContinueExecuting() {
            float f = this.attacker.getBrightness();

            if (f >= 0.5F && this.attacker.getRNG().nextInt(100) == 0) {
                this.attacker.setAttackTarget(null);
                return false;
            } else {
                return super.shouldContinueExecuting();
            }
        }

        @Override
        protected double getAttackReachSqr(EntityLivingBase attackTarget) {
            return 4.0F + attackTarget.width;
        }
    }

    static class AIRatTarget<T extends EntityLivingBase> extends EntityAINearestAttackableTarget<T> {
        public AIRatTarget(MoCEntityRat rat, Class<T> classTarget) {
            super(rat, classTarget, true);
        }

        @Override
        public boolean shouldExecute() {
            float f = this.taskOwner.getBrightness();
            return f < 0.5F && super.shouldExecute();
        }
    }

    public float getEyeHeight() {
        return this.height * 0.5F;
    }
}
