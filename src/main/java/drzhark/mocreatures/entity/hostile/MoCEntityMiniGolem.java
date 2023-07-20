/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.entity.hostile;

import javax.annotation.Nullable;

import drzhark.mocreatures.MoCLootTables;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.entity.MoCEntityMob;
import drzhark.mocreatures.entity.item.MoCEntityThrowableRock;
import drzhark.mocreatures.init.MoCSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MoCEntityMiniGolem extends MoCEntityMob {

    private static final DataParameter<Boolean> ANGRY = EntityDataManager.createKey(MoCEntityMiniGolem.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> HAS_ROCK = EntityDataManager.createKey(MoCEntityMiniGolem.class, DataSerializers.BOOLEAN);
    public int tCounter;
    public MoCEntityThrowableRock tempRock;

    public MoCEntityMiniGolem(World world) {
        super(world);
        this.texture = "mini_golem.png";
        setSize(0.9F, 1.2F);
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
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(ANGRY, Boolean.FALSE);
        this.dataManager.register(HAS_ROCK, Boolean.FALSE);
    }

    public boolean getIsAngry() {
        return this.dataManager.get(ANGRY);
    }

    public void setIsAngry(boolean flag) {
        this.dataManager.set(ANGRY, flag);
    }

    public boolean getHasRock() {
        return this.dataManager.get(HAS_ROCK);
    }

    public void setHasRock(boolean flag) {
        this.dataManager.set(HAS_ROCK, flag);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (!this.world.isRemote) {
            setIsAngry(this.getAttackTarget() != null);

            if (getIsAngry() && this.getAttackTarget() != null) {
                if (!getHasRock() && this.rand.nextInt(30) == 0) {
                    acquireTRock();
                }

                if (getHasRock()) {
                    this.getNavigator().clearPath();
                    attackWithTRock();
                }
            }
        }
    }

    @Override
    public void onDeath(DamageSource cause) {
        if (getHasRock() && this.tempRock != null) this.tempRock.transformToItem();
        super.onDeath(cause);
    }

    protected void acquireTRock() {
        IBlockState tRockState = MoCTools.destroyRandomBlockWithIBlockState(this, 3D);
        if (tRockState == null) {
            this.tCounter = 1;
            setHasRock(false);
            return;
        }

        //creates a dummy TRock on top of it
        MoCEntityThrowableRock tRock = new MoCEntityThrowableRock(this.world, this, this.posX, this.posY + 1.5D, this.posZ);
        this.world.spawnEntity(tRock);
        tRock.setState(tRockState);
        tRock.setBehavior(1);
        this.tempRock = tRock;
        setHasRock(true);
    }

    @Override
    public boolean isMovementCeased() {
        return getHasRock() && this.getAttackTarget() != null;
    }

    /**
     *
     */
    protected void attackWithTRock() {
        this.tCounter++;

        if (this.tCounter < 50) {
            //maintains position of TRock above head
            this.tempRock.posX = this.posX;
            this.tempRock.posY = (this.posY + 1.0D);
            this.tempRock.posZ = this.posZ;
        }

        if (this.tCounter >= 50) {
            //throws a newly spawned TRock and destroys the held TRock
            if (this.getAttackTarget() != null && this.getDistance(this.getAttackTarget()) < 48F) {
                MoCTools.throwStone(this, this.getAttackTarget(), this.tempRock.getState(), 10D, 0.25D);
            }

            this.tempRock.setDead();
            setHasRock(false);
            this.tCounter = 0;
        }
    }

    /**
     * Stretches the model to that size
     */
    @Override
    public float getSizeFactor() {
        return 1.0F;
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    @Override
    protected void playStepSound(BlockPos pos, Block block) {
        MoCTools.playCustomSound(this, MoCSoundEvents.ENTITY_GOLEM_WALK);
    }

    @Override
    protected SoundEvent getDeathSound() {
        return MoCSoundEvents.ENTITY_GOLEM_DYING;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return MoCSoundEvents.ENTITY_GOLEM_HURT;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return MoCSoundEvents.ENTITY_GOLEM_AMBIENT;
    }

    @Nullable
    protected ResourceLocation getLootTable() {
        return MoCLootTables.MINI_GOLEM;
    }

    @Override
    protected boolean isHarmedByDaylight() {
        return true;
    }

    public float getEyeHeight() {
        return this.height * 0.92F;
    }
}
