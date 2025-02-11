/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.entity;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.ai.EntityAIMoverHelperMoC;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public abstract class MoCEntityAquatic extends EntityCreature implements IMoCEntity {

    protected static final DataParameter<Boolean> ADULT = EntityDataManager.createKey(MoCEntityAquatic.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Integer> TYPE = EntityDataManager.createKey(MoCEntityAquatic.class, DataSerializers.VARINT);
    protected static final DataParameter<Integer> AGE = EntityDataManager.createKey(MoCEntityAquatic.class, DataSerializers.VARINT);
    protected static final DataParameter<String> NAME_STR = EntityDataManager.createKey(MoCEntityAquatic.class, DataSerializers.STRING);
    public boolean fishHooked;
    protected boolean divePending;
    protected boolean jumpPending;
    protected boolean isEntityJumping;
    protected int outOfWater;
    protected boolean riderIsDisconnecting;
    protected float moveSpeed;
    protected String texture;
    protected PathNavigate navigatorWater;
    protected int temper;
    private boolean diving;
    private int divingCount;
    private int mountCount;
    private boolean updateDivingDepth = false;
    private double divingDepth;

    public MoCEntityAquatic(World world) {
        super(world);
        this.outOfWater = 0;
        setTemper(50);
        this.setNewDivingDepth();
        this.riderIsDisconnecting = false;
        this.texture = "blank.jpg";
        this.navigatorWater = new PathNavigateSwimmer(this, world);
        this.moveHelper = new EntityAIMoverHelperMoC(this);
    }

    @Override
    public String getName() {
        String entityString = EntityList.getEntityString(this);
        if (!MoCreatures.proxy.verboseEntityNames || entityString == null) return super.getName();
        String translationKey = "entity." + entityString + ".verbose.name";
        String translatedString = I18n.format(translationKey);
        return !translatedString.equals(translationKey) ? translatedString : super.getName();
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getMoveSpeed());
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
    }

    @Override
    public ResourceLocation getTexture() {
        return MoCreatures.proxy.getModelTexture(this.texture);
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData par1EntityLivingData) {
        selectType();
        return super.onInitialSpawn(difficulty, par1EntityLivingData);
    }

    @Override
    public void selectType() {
        setType(1);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(ADULT, false);
        this.dataManager.register(TYPE, 0);
        this.dataManager.register(AGE, 45);
        this.dataManager.register(NAME_STR, "");
    }

    @Override
    public int getType() {
        return this.dataManager.get(TYPE);
    }

    @Override
    public void setType(int i) {
        this.dataManager.set(TYPE, i);
    }

    @Override
    public int getOwnerPetId() {
        return -1;
    }

    @Override
    public void setOwnerPetId(int i) {
    }

    @Nullable
    public UUID getOwnerId() {
        return null;
    }

    @Override
    public boolean getIsTamed() {
        return false;
    }

    @Override
    public boolean getIsAdult() {
        return this.dataManager.get(ADULT);
    }

    @Override
    public void setAdult(boolean flag) {
        this.dataManager.set(ADULT, flag);
    }

    @Override
    public String getPetName() {
        return this.dataManager.get(NAME_STR);
    }

    @Override
    public void setPetName(String name) {
        this.dataManager.set(NAME_STR, name);
    }

    @Override
    public int getAge() {
        return this.dataManager.get(AGE);
    }

    @Override
    public void setAge(int i) {
        this.dataManager.set(AGE, i);
        if (getAge() >= getMaxAge()) {
            setAdult(true);
        }
    }

    public int getTemper() {
        return this.temper;
    }

    public void setTemper(int i) {
        this.temper = i;
    }

    /**
     * How difficult is the creature to be tamed? the Higher the number, the
     * more difficult
     */
    public int getMaxTemper() {
        return 100;
    }

    public float b(float f, float f1, float f2) {
        float f3;
        for (f3 = f1 - f; f3 < -180F; f3 += 360F) {
        }
        for (; f3 >= 180F; f3 -= 360F) {
        }
        if (f3 > f2) {
            f3 = f2;
        }
        if (f3 < -f2) {
            f3 = -f2;
        }
        return f + f3;
    }

    public void faceItem(int i, int j, int k, float f) {
        double d = i - this.posX;
        double d1 = k - this.posZ;
        double d2 = j - this.posY;
        double d3 = MathHelper.sqrt((d * d) + (d1 * d1));
        float f1 = (float) ((Math.atan2(d1, d) * 180D) / 3.1415927410125728D) - 90F;
        float f2 = (float) ((Math.atan2(d2, d3) * 180D) / 3.1415927410125728D);
        this.rotationPitch = -b(this.rotationPitch, f2, f);
        this.rotationYaw = b(this.rotationYaw, f1, f);
    }

    @Override
    protected boolean canDespawn() {
        if (MoCreatures.proxy.forceDespawns) {
            return !getIsTamed();
        } else {
            return false;
        }
    }

    @Override
    public boolean checkSpawningBiome() {
        return true;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block par4) {
    }

    @Override
    public void fall(float f, float f1) {
    }

    public EntityItem getClosestFish(Entity entity, double d) {
        double d1 = -1D;
        EntityItem entityitem = null;
        List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().grow(d));
        for (Entity entity1 : list) {
            if (!(entity1 instanceof EntityItem)) continue;
            EntityItem entityitem1 = (EntityItem) entity1;
            if ((entityitem1.getItem().getItem() != Items.FISH) || !entityitem1.isInWater()) continue;
            double d2 = entityitem1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
            if (((d < 0.0D) || (d2 < (d * d))) && ((d1 == -1D) || (d2 < d1))) {
                d1 = d2;
                entityitem = entityitem1;
            }
        }
        return entityitem;
    }

    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }

    public boolean gettingOutOfWater() {
        int i = (int) this.posX;
        int j = (int) this.posY;
        int k = (int) this.posZ;
        return this.world.isAirBlock(new BlockPos(i, j + 1, k));
    }

    /**
     * mount jumping power
     */
    public double getCustomJump() {
        return 0.4D;
    }

    public boolean getIsJumping() {
        return this.isEntityJumping;
    }

    public void setIsJumping(boolean flag) {
        this.isEntityJumping = flag;
    }

    /**
     * Sets a flag that will make the Entity "jump" in the next onGround
     * moveEntity update
     */
    @Override
    public void makeEntityJump() {
        this.jumpPending = true;
    }

    /*@Override
    public boolean handleWaterMovement() {
        return this.world.handleMaterialAcceleration(this.getEntityBoundingBox(), Material.WATER, this);
    }*/

    protected boolean MoveToNextEntity(Entity entity) {
        if (entity != null) {
            int i = MathHelper.floor(entity.posX);
            int j = MathHelper.floor(entity.posY);
            int k = MathHelper.floor(entity.posZ);
            faceItem(i, j, k, 30F);
            if (this.posX < i) {
                double d = entity.posX - this.posX;
                if (d > 0.5D) {
                    this.motionX += 0.050000000000000003D;
                }
            } else {
                double d1 = this.posX - entity.posX;
                if (d1 > 0.5D) {
                    this.motionX -= 0.050000000000000003D;
                }
            }
            if (this.posZ < k) {
                double d2 = entity.posZ - this.posZ;
                if (d2 > 0.5D) {
                    this.motionZ += 0.050000000000000003D;
                }
            } else {
                double d3 = this.posZ - entity.posZ;
                if (d3 > 0.5D) {
                    this.motionZ -= 0.050000000000000003D;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Speed used to move the mob around
     */
    public double getCustomSpeed() {
        return 1.5D;
    }

    @Override
    public boolean isInWater() {
        return this.world.handleMaterialAcceleration(this.getEntityBoundingBox().grow(0.0D, -0.2D, 0.0D), Material.WATER, this);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public boolean isDiving() {
        return this.diving;
    }

    @Override
    protected void jump() {

    }

    // used to pick up objects while riding an entity
    public void Riding() {
        if ((this.isBeingRidden()) && (this.getRidingEntity() instanceof EntityPlayer)) {
            EntityPlayer entityplayer = (EntityPlayer) this.getRidingEntity();
            List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().grow(1.0D, 0.0D, 1.0D));
            for (Entity entity : list) {
                if (entity.isDead) continue;
                entity.onCollideWithPlayer(entityplayer);
                if (!(entity instanceof EntityMob)) continue;
                float f = getDistance(entity);
                if (f < 2.0F && this.rand.nextInt(10) == 0) {
                    attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase) entity), (float) ((EntityMob) entity).getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
                }
            }
            /*
             if (entityplayer.isSneaking()) {
                 this.makeEntityDive();
             }
             */
        }
    }

    @Override
    public boolean isMovementCeased() {
        return ((!isSwimming() && !this.isBeingRidden()) || this.isBeingRidden() || this.getIsSitting());
    }

    @Override
    public void onLivingUpdate() {
        if (!this.world.isRemote) {
            if (this.isBeingRidden()) {
                Riding();
                this.mountCount = 1;
            }

            if (this.mountCount > 0) {
                if (++this.mountCount > 50) {
                    this.mountCount = 0;
                }
            }
            if (getAge() == 0) setAge(getMaxAge() - 10); //fixes tiny creatures spawned by error
            if (!getIsAdult() && (this.rand.nextInt(300) == 0)) {
                setAge(getAge() + 1);
                if (getAge() >= getMaxAge()) {
                    setAdult(true);
                }
            }

            this.getNavigator().onUpdateNavigation();

            //updates diving depth after finishing movement
            if (!this.getNavigator().noPath())// && !updateDivingDepth)
            {
                if (!this.updateDivingDepth) {
                    float targetDepth =
                            (MoCTools.distanceToSurface(this.moveHelper.getX(), this.moveHelper.getY(), this.moveHelper.getZ(), this.world));
                    setNewDivingDepth(targetDepth);
                    this.updateDivingDepth = true;
                }
            } else {
                this.updateDivingDepth = false;
            }

            if (isMovementCeased() || rand.nextInt(200) == 0) {
                this.getNavigator().clearPath();
            }

            /*if (this.isInWater() && this.onGroundHorizontally && rand.nextInt(10) == 0)
            {
                this.motionY += 0.05D;
            }*/

            /*
             if (getIsTamed() && rand.nextInt(100) == 0) {
                 MoCServerPacketHandler.sendHealth(this.getEntityId(),
                 this.world.provider.getDimensionType().getId(), this.getHealth());
             }
             */

            /*if (forceUpdates() && this.rand.nextInt(500) == 0) {
                MoCTools.forceDataSync(this);
            }*/

            if (isFisheable() && !this.fishHooked && this.rand.nextInt(30) == 0) {
                getFished();
            }

            if (this.fishHooked && this.rand.nextInt(200) == 0) {
                this.fishHooked = false;

                List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().grow(2));
                for (Entity entity1 : list) {
                    if (entity1 instanceof EntityFishHook) {
                        if (((EntityFishHook) entity1).caughtEntity == this) {
                            ((EntityFishHook) entity1).caughtEntity = null;
                        }
                    }
                }
            }
        }

        this.moveSpeed = getMoveSpeed();

        if (isSwimming()) {
            //floating();
            this.outOfWater = 0;
            this.setAir(800);
        } else {
            this.outOfWater++;
            this.motionY -= 0.1D;
            if (this.outOfWater > 20) {
                this.getNavigator().clearPath();
            }
            if (this.outOfWater > 300 && (this.outOfWater % 40) == 0) {
                this.motionY += 0.3D;
                this.motionX = (float) (Math.random() * 0.2D - 0.1D);
                this.motionZ = (float) (Math.random() * 0.2D - 0.1D);
                attackEntityFrom(DamageSource.DROWN, 1);
            }
        }
        if (!this.diving) {
            if (!this.isBeingRidden() && getAttackTarget() == null && !this.navigator.noPath() && this.rand.nextInt(500) == 0) {
                this.diving = true;
            }
        } else {
            this.divingCount++;
            if (this.divingCount > 100 || this.isBeingRidden()) {
                this.diving = false;
                this.divingCount = 0;
            }
        }
        super.onLivingUpdate();
    }

    public boolean isSwimming() {
        return ((isInsideOfMaterial(Material.WATER)));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        super.writeEntityToNBT(nbttagcompound);
        //nbttagcompound = MoCTools.getEntityData(this);
        nbttagcompound.setBoolean("Adult", getIsAdult());
        nbttagcompound.setInteger("Edad", getAge());
        nbttagcompound.setString("Name", getPetName());
        nbttagcompound.setInteger("TypeInt", getType());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        super.readEntityFromNBT(nbttagcompound);
        //nbttagcompound = MoCTools.getEntityData(this);
        setAdult(nbttagcompound.getBoolean("Adult"));
        setAge(nbttagcompound.getInteger("Edad"));
        setPetName(nbttagcompound.getString("Name"));
        setType(nbttagcompound.getInteger("TypeInt"));
    }

    public void setTypeInt(int i) {
        setType(i);
        selectType();
    }

    /**
     * Used to synchronize the attack animation between server and client
     */
    @Override
    public void performAnimation(int attackType) {
    }

    /**
     * Makes the entity despawn if requirements are reached changed to the
     * entities now last longer
     */
    @Override
    protected void despawnEntity() {
        EntityPlayer var1 = this.world.getClosestPlayerToEntity(this, -1.0D);
        if (var1 != null) {
            double var2 = var1.posX - this.posX;
            double var4 = var1.posY - this.posY;
            double var6 = var1.posZ - this.posZ;
            double var8 = var2 * var2 + var4 * var4 + var6 * var6;

            if (this.canDespawn() && var8 > 16384.0D) {
                this.setDead();
            }
            //changed from 600
            if (this.idleTime > 1800 && this.rand.nextInt(800) == 0 && var8 > 1024.0D && this.canDespawn()) {
                this.setDead();
            } else if (var8 < 1024.0D) {
                this.idleTime = 0;
            }
        }
    }

    public float getMoveSpeed() {
        return 0.7F;
    }

    @Override
    public int nameYOffset() {
        return 0;
    }

    @Override
    public boolean renderName() {
        return MoCreatures.proxy.getDisplayPetName()
                && (getPetName() != null && !getPetName().equals("") && (!this.isBeingRidden()) && (this.getRidingEntity() == null));
    }

    /*@Override
    public boolean updateMount() {
        return false;
    }*/

    /*@Override
    public boolean forceUpdates() {
        return false;
    }*/

    @Override
    public void makeEntityDive() {
        this.divePending = true;
    }

    @Override
    public float getSizeFactor() {
        return 1.0F;
    }

    @Override
    public float getAdjustedYOffset() {
        return 0F;
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this
     * entity.
     */
    @Override
    public boolean getCanSpawnHere() {
        boolean willSpawn = this.world.checkNoEntityCollision(this.getEntityBoundingBox()) && this.posY >= world.getSeaLevel() - 12;
        boolean debug = MoCreatures.proxy.debug;
        if (debug && willSpawn)
            System.out.println("Aquatic: " + this.getName() + " at: " + this.getPosition() + " State: " + this.world.getBlockState(this.getPosition()) + " biome: " + MoCTools.biomeName(world, getPosition()));
        return willSpawn;
    }

    @Override
    public boolean attackEntityFrom(DamageSource damagesource, float i) {
        if (usesNewAI()) {
            return super.attackEntityFrom(damagesource, i);
        }

        if (isNotScared()) {
            EntityLivingBase tempEntity = this.getAttackTarget();
            setAttackTarget(tempEntity);
            return super.attackEntityFrom(damagesource, i);
        }

        return super.attackEntityFrom(damagesource, i);
    }

    protected boolean canBeTrappedInNet() {
        return (this instanceof IMoCTameable) && getIsTamed();
    }

    protected void dropMyStuff() {
    }

    /**
     * Used to heal the animal
     */
    protected boolean isMyHealFood(ItemStack itemstack) {
        return false;
    }

    @Override
    public void setArmorType(int i) {
    }

    @Override
    public float pitchRotationOffset() {
        return 0F;
    }

    @Override
    public float rollRotationOffset() {
        return 0F;
    }

    /**
     * The act of getting Hooked into a fish Hook.
     */
    private void getFished() {
        EntityPlayer entityplayer1 = this.world.getClosestPlayerToEntity(this, 18D);
        if (entityplayer1 != null) {
            EntityFishHook fishHook = entityplayer1.fishEntity;
            if (fishHook != null && fishHook.caughtEntity == null) {
                float f = fishHook.getDistance(this);
                if (f > 1) {
                    MoCTools.setPathToEntity(this, fishHook, f);
                } else {
                    fishHook.caughtEntity = this;
                    this.fishHooked = true;
                }
            }
        }
    }

    /**
     * Is this aquatic entity prone to be fished with a fish Hook?
     */
    protected boolean isFisheable() {
        return false;
    }

    @Override
    public float getAdjustedZOffset() {
        return 0F;
    }

    @Override
    public float getAdjustedXOffset() {
        return 0F;
    }

    /**
     * Finds and entity described in entitiesToInclude at d distance
     */
    protected EntityLivingBase getBoogey(double d) {
        EntityLivingBase entityliving = null;
        List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().grow(d, 4D, d));
        for (Entity entity : list) {
            if (entitiesToInclude(entity)) {
                entityliving = (EntityLivingBase) entity;
            }
        }
        return entityliving;
    }

    /**
     * Used in getBoogey to specify what kind of entity to look for
     */
    public boolean entitiesToInclude(Entity entity) {
        return ((entity.getClass() != this.getClass()) && (entity instanceof EntityLivingBase) && ((entity.width >= 0.5D) || (entity.height >= 0.5D)));
    }

    @Override
    public boolean isNotScared() {
        return false;
    }

    @Override
    public boolean canAttackTarget(EntityLivingBase entity) {
        return false;
    }

    @Override
    public boolean canBeLeashedTo(EntityPlayer player) {
        if (!this.world.isRemote && !MoCTools.isThisPlayerAnOP(player) && this.getIsTamed() && !player.getUniqueID().equals(this.getOwnerId())) {
            return false;
        }
        return super.canBeLeashedTo(player);
    }

    @Override
    public boolean getIsSitting() {
        return false;
    }

    @Override
    public boolean shouldAttackPlayers() {
        return !getIsTamed() && this.world.getDifficulty() != EnumDifficulty.PEACEFUL;
    }

    /**
     * Moves the entity based on the specified heading.  Args: strafe, forward
     */
    @Override
    public void travel(float strafe, float vertical, float forward) {
        if (this.isInWater()) {
            if (this.isBeingRidden()) {
                EntityLivingBase passenger = (EntityLivingBase) this.getControllingPassenger();
                if (passenger != null) this.moveWithRider(strafe, vertical, forward, passenger); //riding movement
                return;
            }
            this.moveRelative(strafe, vertical, forward, 0.1F);
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.8999999761581421D;
            this.motionY *= 0.8999999761581421D;
            this.motionZ *= 0.8999999761581421D;

            if (this.getAttackTarget() == null) {
                this.motionY -= 0.005D;
            }
            this.prevLimbSwingAmount = this.limbSwingAmount;
            double d2 = this.posX - this.prevPosX;
            double d3 = this.posZ - this.prevPosZ;
            float f7 = MathHelper.sqrt(d2 * d2 + d3 * d3) * 4.0F;

            if (f7 > 1.0F) {
                f7 = 1.0F;
            }

            this.limbSwingAmount += (f7 - this.limbSwingAmount) * 0.4F;
            this.limbSwing += this.limbSwingAmount;
        } else {
            super.travel(strafe, vertical, forward);
        }
    }

    /**
     * * Riding Code
     */
    public void moveWithRider(float strafe, float vertical, float forward, EntityLivingBase passenger) {
        if (passenger == null) {
            return;
        }
        //Buckles rider if out of water
        if (this.isBeingRidden() && !getIsTamed() && !isSwimming()) {
            this.removePassengers();
            return;
        }

        if (this.isBeingRidden() && !getIsTamed()) {
            this.moveWithRiderUntamed(strafe, vertical, forward, passenger);
            return;
        }

        if (this.isBeingRidden() && getIsTamed()) {
            this.prevRotationYaw = this.rotationYaw = passenger.rotationYaw;
            this.rotationPitch = passenger.rotationPitch * 0.5F;
            this.setRotation(this.rotationYaw, this.rotationPitch);
            this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
            strafe = passenger.moveStrafing * 0.35F;
            forward = passenger.moveForward * (float) (this.getCustomSpeed() / 5D);
            if (this.jumpPending) {
                if (this.isSwimming()) {
                    this.motionY += getCustomJump();
                }
                this.jumpPending = false;
            }
            //So it doesn't sink on its own
            if (this.motionY < 0D && isSwimming()) {
                this.motionY = 0D;
            }
            if (this.divePending) {
                this.divePending = false;
                this.motionY -= 0.3D;
            }
            this.setAIMoveSpeed((float) this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
            super.travel(strafe, vertical, forward);
            this.moveRelative(strafe, vertical, forward, 0.1F);
        }
    }

    public void moveWithRiderUntamed(float strafe, float vertical, float forward, EntityLivingBase passenger) {
        //Riding behaviour if untamed
        if ((this.isBeingRidden()) && !getIsTamed()) {
            if ((this.rand.nextInt(5) == 0) && !getIsJumping() && this.jumpPending) {
                this.motionY += getCustomJump();
                setIsJumping(true);
                this.jumpPending = false;
            }
            if (this.rand.nextInt(10) == 0) {
                this.motionX += this.rand.nextDouble() / 30D;
                this.motionZ += this.rand.nextDouble() / 10D;
            }
            //if (!this.world.isRemote) {
            move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            //}
            if (!this.world.isRemote && this.rand.nextInt(100) == 0) {
                passenger.motionY += 0.9D;
                passenger.motionZ -= 0.3D;
                passenger.dismountRidingEntity();
            }
            if (this.onGround) {
                setIsJumping(false);
            }
            if (!this.world.isRemote && this instanceof IMoCTameable && passenger instanceof EntityPlayer) {
                int chance = (getMaxTemper() - getTemper());
                if (chance <= 0) {
                    chance = 1;
                }
                if (this.rand.nextInt(chance * 8) == 0) {
                    MoCTools.tameWithName((EntityPlayer) passenger, (IMoCTameable) this);
                }

            }
        }
    }

    /**
     * Whether the current entity is in lava
     */
    @Override
    public boolean isNotColliding() {
        return this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this);
    }

    /**
     * Get number of ticks, at least during which the living entity will be silent.
     */
    @Override
    public int getTalkInterval() {
        return 300;
    }

    /**
     * Get the experience points the entity currently has.
     */
    @Override
    protected int getExperiencePoints(EntityPlayer player) {
        return 1 + this.world.rand.nextInt(3);
    }

    /**
     * Gets called every tick from main Entity class
     */
    @Override
    public void onEntityUpdate() {
        int i = this.getAir();
        super.onEntityUpdate();

        if (this.isEntityAlive() && !this.isInWater()) {
            --i;
            this.setAir(i);

            if (this.getAir() == -30) {
                this.setAir(0);
                this.attackEntityFrom(DamageSource.DROWN, 1.0F);
                this.motionX += this.rand.nextDouble() / 10D;
                this.motionZ += this.rand.nextDouble() / 10D;
            }
        } else {
            this.setAir(300);
        }
    }

    @Override
    public boolean isPushedByWater() {
        return false;
    }

    protected boolean usesNewAI() {
        return false;
    }

    @Override
    public PathNavigate getNavigator() {
        if (this.isInWater()) {
            return this.navigatorWater;
        }
        return this.navigator;
    }

    /**
     * The distance the entity will float under the surface. 0F = surface 1.0F = 1 block under
     */
    @Override
    public double getDivingDepth() {
        return (float) this.divingDepth;
    }

    /**
     * Sets diving depth. if setDepth given = 0.0D, will then choose a random value within proper range
     */
    protected void setNewDivingDepth(double setDepth) {
        if (setDepth != 0.0D) {
            if (setDepth > maxDivingDepth()) {
                setDepth = maxDivingDepth();
            }
            if (setDepth < minDivingDepth()) {
                setDepth = minDivingDepth();
            }
            this.divingDepth = setDepth;
        } else {
            this.divingDepth = (float) (this.rand.nextDouble() * (maxDivingDepth() - minDivingDepth()) + minDivingDepth());
        }

    }

    protected void setNewDivingDepth() {
        setNewDivingDepth(0.0D);
    }

    protected double minDivingDepth() {
        return 0.20D;
    }

    protected double maxDivingDepth() {
        return 3.0D;
    }

    @Override
    public void forceEntityJump() {
        this.jump();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public float yawRotationOffset() {
        double d4 = 0F;
        if ((this.motionX != 0D) || (this.motionZ != 0D)) {
            d4 = Math.sin(this.ticksExisted * 0.5D) * 8D;
        }
        return (float) (d4);//latOffset;
    }

    public int getMaxAge() {
        return 100;
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (!entityIn.isInWater()) {
            return false;
        }
        boolean flag =
                entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), ((int) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE)
                        .getAttributeValue()));
        if (flag) {
            this.applyEnchantments(this, entityIn);
        }
        return flag;
    }

    @Override
    public int maxFlyingHeight() {
        return 1;
    }

    @Override
    public int minFlyingHeight() {
        return 1;
    }

    /**
     * Boolean used for flying mounts
     */
    public boolean isFlyer() {
        return false;
    }

    @Override
    public boolean getIsFlying() {
        return false;
    }

    protected SoundEvent getUpsetSound() {
        return SoundEvents.ENTITY_GENERIC_HURT;
    }

    /**
     * Returns true if the entity is of the @link{EnumCreatureType} provided
     *
     * @param type          The EnumCreatureType type this entity is evaluating
     * @param forSpawnCount If this is being invoked to check spawn count caps.
     * @return If the creature is of the type provided
     */
    @Override
    public boolean isCreatureType(EnumCreatureType type, boolean forSpawnCount) {
        return type == EnumCreatureType.WATER_CREATURE;
    }

    /**
     * For vehicles, the first passenger is generally considered the controller and "drives" the vehicle. For example,
     * Pigs, Horses, and Boats are generally "steered" by the controlling passenger.
     */
    @Nullable
    public Entity getControllingPassenger() {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }

    @Override
    public String getClazzString() {
        return EntityList.getEntityString(this);
    }

    @Override
    public boolean getIsGhost() {
        return false;
    }

    @Override
    public void setLeashHolder(Entity entityIn, boolean sendAttachNotification) {
        if (this.getIsTamed() && entityIn instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer) entityIn;
            if (MoCreatures.proxy.enableOwnership && this.getOwnerId() != null
                    && !entityplayer.getUniqueID().equals(this.getOwnerId()) && !MoCTools.isThisPlayerAnOP((entityplayer))) {
                return;
            }
        }
        super.setLeashHolder(entityIn, sendAttachNotification);
    }
}
