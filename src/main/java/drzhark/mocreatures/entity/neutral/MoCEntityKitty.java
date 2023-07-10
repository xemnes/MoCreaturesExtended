/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.entity.neutral;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
import drzhark.mocreatures.entity.ai.EntityAIFollowAdult;
import drzhark.mocreatures.entity.ai.EntityAIPanicMoC;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.entity.item.MoCEntityKittyBed;
import drzhark.mocreatures.entity.item.MoCEntityLitterBox;
import drzhark.mocreatures.init.MoCItems;
import drzhark.mocreatures.init.MoCSoundEvents;
import drzhark.mocreatures.network.MoCMessageHandler;
import drzhark.mocreatures.network.message.MoCMessageAnimation;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

import java.util.List;

public class MoCEntityKitty extends MoCEntityTameableAnimal {

    private static final DataParameter<Boolean> SITTING = EntityDataManager.createKey(MoCEntityKitty.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> HUNGRY = EntityDataManager.createKey(MoCEntityKitty.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> EMO = EntityDataManager.createKey(MoCEntityKitty.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> KITTY_STATE = EntityDataManager.createKey(MoCEntityKitty.class, DataSerializers.VARINT);
    private final int[] treeCoord = {-1, -1, -1};
    private int kittytimer;
    private int madtimer;
    private boolean foundTree;
    private boolean isSwinging;
    private boolean onTree;
    private EntityItem itemAttackTarget;

    public MoCEntityKitty(World world) {
        super(world);
        setSize(0.7F, 0.5F);
        setAdult(true);
        setAge(40);
        setKittyState(1);
        this.kittytimer = 0;
        this.madtimer = this.rand.nextInt(5);
        this.foundTree = false;
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIPanicMoC(this, 1.0D));
        this.tasks.addTask(4, new EntityAIFollowAdult(this, 1.0D));
        this.tasks.addTask(5, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(6, new EntityAIWanderMoC2(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }

    @Override
    public void selectType() {
        if (getType() == 0) {
            setType(this.rand.nextInt(8) + 1);
        }
    }

    @Override
    public ResourceLocation getTexture() {

        switch (getType()) {
            case 2:
                return MoCreatures.proxy.getTexture("pussycatb.png");
            case 3:
                return MoCreatures.proxy.getTexture("pussycatc.png");
            case 4:
                return MoCreatures.proxy.getTexture("pussycatd.png");
            case 5:
                return MoCreatures.proxy.getTexture("pussycate.png");
            case 6:
                return MoCreatures.proxy.getTexture("pussycatf.png");
            case 7:
                return MoCreatures.proxy.getTexture("pussycatg.png");
            case 8:
                return MoCreatures.proxy.getTexture("pussycath.png");
            default:
                return MoCreatures.proxy.getTexture("pussycata.png");
        }
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(SITTING, Boolean.FALSE);
        this.dataManager.register(HUNGRY, Boolean.FALSE);
        this.dataManager.register(EMO, Boolean.FALSE);
        this.dataManager.register(KITTY_STATE, 0);
    }

    public int getKittyState() {
        return this.dataManager.get(KITTY_STATE);
    }

    public void setKittyState(int i) {
        this.dataManager.set(KITTY_STATE, i);
    }

    @Override
    public boolean getIsSitting() {
        return this.dataManager.get(SITTING);
    }

    public boolean getIsHungry() {
        return this.dataManager.get(HUNGRY);
    }

    public boolean getIsEmo() {
        return this.dataManager.get(EMO);
    }

    public void setIsEmo(boolean flag) {
        this.dataManager.set(EMO, flag);
    }

    public boolean getIsSwinging() {
        return this.isSwinging;
    }

    public boolean getOnTree() {
        return this.onTree;
    }

    public void setOnTree(boolean var1) {
        this.onTree = var1;
    }

    public void setSitting(boolean flag) {
        this.dataManager.set(SITTING, flag);
    }

    public void setHungry(boolean flag) {
        this.dataManager.set(HUNGRY, flag);
    }

    public void setSwinging(boolean var1) {
        this.isSwinging = var1;
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (getKittyState() != 18 && getKittyState() != 10) {
            swingArm();
        }
        if ((getKittyState() == 13 && entityIn instanceof EntityPlayer) || (getKittyState() == 8 && entityIn instanceof EntityItem) || (getKittyState() == 18 && entityIn instanceof MoCEntityKitty) || getKittyState() == 10) {
            return false;
        }
        return super.attackEntityAsMob(entityIn);
    }

    @Override
    public boolean attackEntityFrom(DamageSource damagesource, float i) {
        if (super.attackEntityFrom(damagesource, i)) {
            Entity entity = damagesource.getTrueSource();
            if (entity != this && entity instanceof EntityLivingBase) {
                EntityLivingBase entityliving = (EntityLivingBase) entity;
                if (getKittyState() == 10) {
                    List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().grow(16D, 6D, 16D));
                    for (Entity entity1 : list) {
                        if ((entity1 instanceof MoCEntityKitty) && ((MoCEntityKitty) entity1).getKittyState() == 21) {
                            ((MoCEntityKitty) entity1).setAttackTarget(entityliving);
                            return true;
                        }
                    }
                    return true;
                }
                if (entityliving instanceof EntityPlayer && super.shouldAttackPlayers()) {
                    if (getKittyState() < 2) {
                        setAttackTarget(entityliving);
                        setKittyState(-1);
                    }
                    if (getKittyState() == 19 || getKittyState() == 20 || getKittyState() == 21) {
                        setAttackTarget(entityliving);
                        setSitting(false);
                        return true;
                    }
                    if (getKittyState() > 1 && getKittyState() != 10 && getKittyState() != 19 && getKittyState() != 20 && getKittyState() != 21) {
                        setKittyState(13);
                        setSitting(false);
                    }
                    return true;
                }
                setAttackTarget(entityliving);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected boolean canDespawn() {
        if (MoCreatures.proxy.forceDespawns) {
            return getKittyState() < 3;
        } else {
            return false;
        }
    }

    private void changeKittyState(int i) {
        setKittyState(i);
        setSitting(false);
        this.kittytimer = 0;
        setOnTree(false);
        this.foundTree = false;
        setAttackTarget(null);
        this.itemAttackTarget = null;
    }

    public boolean climbingTree() {
        return getKittyState() == 16 && isOnLadder();
    }

    @Override
    public void fall(float f, float f1) {
        // No fall damage
    }

    @Override
    protected Entity findPlayerToAttack() {
        if (this.world.getDifficulty().getId() > 0 && getKittyState() != 8 && getKittyState() != 10 && getKittyState() != 15 && getKittyState() != 18 && getKittyState() != 19 && !isMovementCeased() && getIsHungry()) {
            return getClosestTarget(this, 10D);
        } else {
            return null;
        }
    }

    @Override
    protected Item getDropItem() {
        return null;
    }

    public ResourceLocation getEmoteIcon() {
        switch (getKittyState()) {
            case -1:
                return MoCreatures.proxy.getTexture("emoticon.png"); // Blank
            case 3:
                return MoCreatures.proxy.getTexture("emoticon3.png"); // Food
            case 4:
                return MoCreatures.proxy.getTexture("emoticon4.png"); // Happy
            case 5:
                return MoCreatures.proxy.getTexture("emoticon5.png"); // Litter Box
            case 7:
                return MoCreatures.proxy.getTexture("emoticon7.png"); // Very Happy
            case 8:
                return MoCreatures.proxy.getTexture("emoticon8.png"); // Very, Very Happy
            case 9:
            case 18:
                return MoCreatures.proxy.getTexture("emoticon9.png"); // In Love
            case 10:
            case 21:
                return MoCreatures.proxy.getTexture("emoticon10.png"); // Pleased
            case 11:
                return MoCreatures.proxy.getTexture("emoticon11.png"); // Wondering
            case 12:
                return MoCreatures.proxy.getTexture("emoticon12.png"); // Sleeping
            case 13:
                return MoCreatures.proxy.getTexture("emoticon13.png"); // Angry
            case 16:
                return MoCreatures.proxy.getTexture("emoticon16.png"); // Tree
            case 17:
                return MoCreatures.proxy.getTexture("emoticon17.png"); // Scared
            case 19:
            case 20:
                return MoCreatures.proxy.getTexture("emoticon19.png"); // In Labor
            case 0:
            case 1:
            case 2:
            case 6:
            case 14:
            case 15:
            default:
                return MoCreatures.proxy.getTexture("emoticon1.png"); // Neutral
        }
    }

    @Override
    protected SoundEvent getDeathSound() {
        if (getKittyState() == 10) {
            return MoCSoundEvents.ENTITY_KITTY_DEATH_BABY;
        } else {
            return MoCSoundEvents.ENTITY_KITTY_DEATH;
        }
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        if (getKittyState() == 10) {
            return MoCSoundEvents.ENTITY_KITTY_HURT_BABY;
        } else {
            return MoCSoundEvents.ENTITY_KITTY_HURT;
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (getKittyState() == 4) {
            if (this.getRidingEntity() != null) {
                MoCEntityKittyBed entitykittybed = (MoCEntityKittyBed) this.getRidingEntity();
                if ((entitykittybed != null) && !entitykittybed.getHasMilk()) {
                    return MoCSoundEvents.ENTITY_KITTY_DRINKING;
                }
                if ((entitykittybed != null) && !entitykittybed.getHasFood()) {
                    return MoCSoundEvents.ENTITY_KITTY_EATING;
                }
            }
            return null;
        }
        if (getKittyState() == 6) {
            return MoCSoundEvents.ENTITY_KITTY_LITTER;
        }
        if (getKittyState() == 3) {
            return MoCSoundEvents.ENTITY_KITTY_HUNGRY;
        }
        if (getKittyState() == 10) {
            return MoCSoundEvents.ENTITY_KITTY_AMBIENT_BABY;
        }
        if (getKittyState() == 13) {
            return MoCSoundEvents.ENTITY_KITTY_ANGRY;
        }
        if (getKittyState() == 17) {
            return MoCSoundEvents.ENTITY_KITTY_TRAPPED;
        }
        if (getKittyState() == 18 || getKittyState() == 12) {
            return MoCSoundEvents.ENTITY_KITTY_PURR;
        } else {
            return MoCSoundEvents.ENTITY_KITTY_AMBIENT;
        }
    }

    public EntityLiving getKittyStuff(Entity entity, double d, boolean flag) {
        double d1 = -1D;
        EntityLiving obj = null;
        List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(entity, getEntityBoundingBox().grow(d));
        for (Entity entity1 : list) {
            if (flag) {
                if (!(entity1 instanceof MoCEntityLitterBox)) {
                    continue;
                }
                MoCEntityLitterBox entitylitterbox = (MoCEntityLitterBox) entity1;
                if (entitylitterbox.getUsedLitter()) {
                    continue;
                }
                double d2 = entity1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
                if (((d < 0.0D) || (d2 < (d * d))) && ((d1 == -1.0D) || (d2 < d1)) && entitylitterbox.canEntityBeSeen(entity)) {
                    d1 = d2;
                    obj = entitylitterbox;
                }
                continue;
            }
            if (!(entity1 instanceof MoCEntityKittyBed)) {
                continue;
            }
            MoCEntityKittyBed entitykittybed = (MoCEntityKittyBed) entity1;
            double d3 = entity1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
            if (((d < 0.0D) || (d3 < (d * d))) && ((d1 == -1.0D) || (d3 < d1)) && entitykittybed.canEntityBeSeen(entity)) {
                d1 = d3;
                obj = entitykittybed;
            }
        }
        return obj;
    }

    // Mojang changed offsets in 1.12 so this needs to be reviewed
    @Override
    public double getYOffset() {
        if (this.getRidingEntity() instanceof EntityPlayer && this.world.isRemote) {
            if (getKittyState() == 10) {
                return (super.getYOffset() + 0.4F);
            }
            if (upsideDown()) {
                return (super.getYOffset() - 0.1F);
            }
            if (onMaBack()) {
                return (super.getYOffset() + 0.1F);
            }
        }
        return super.getYOffset();
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        // Only process one hand to prevent double interactions
        if (hand != EnumHand.MAIN_HAND) {
            return false;
        }

        final Boolean tameResult = this.processTameInteract(player, hand);
        if (tameResult != null) {
            return tameResult;
        }

        final ItemStack stack = player.getHeldItem(hand);
        if (getKittyState() == 2 && !stack.isEmpty() && stack.getItem() == MoCItems.medallion) {
            if (!this.world.isRemote) {
                MoCTools.tameWithName(player, this);
            }
            if (getIsTamed()) {
                stack.shrink(1);
                if (stack.isEmpty()) {
                    player.setHeldItem(hand, ItemStack.EMPTY);
                }
                changeKittyState(3);
                this.setHealth(getMaxHealth());
                return true;
            }
            return false;
        }
        if (getKittyState() == 7 && !stack.isEmpty() && stack.getItem() == Items.CAKE || stack.getItem() == Items.FISH || stack.getItem() == Items.COOKED_FISH) {
            stack.shrink(1);
            if (stack.isEmpty()) {
                player.setHeldItem(hand, ItemStack.EMPTY);
            }
            MoCTools.playCustomSound(this, MoCSoundEvents.ENTITY_KITTY_EATING);
            this.setHealth(getMaxHealth());
            changeKittyState(9);
            return true;
        }
        if (getKittyState() == 11 && !stack.isEmpty() && stack.getItem() == MoCItems.woolball) {
            stack.shrink(1);
            if (stack.isEmpty()) {
                player.setHeldItem(hand, ItemStack.EMPTY);
            }
            setKittyState(8);
            if (!this.world.isRemote) {
                EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY + 1.0D, this.posZ, new ItemStack(MoCItems.woolball, 1));
                entityitem.setPickupDelay(30);
                entityitem.setNoDespawn();
                this.world.spawnEntity(entityitem);
                entityitem.motionY += this.world.rand.nextFloat() * 0.05F;
                entityitem.motionX += (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.3F;
                entityitem.motionZ += (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.3F;
                this.itemAttackTarget = entityitem;
            }
            return true;
        }
        if (getKittyState() == 13 && !stack.isEmpty() && stack.getItem() == Items.FISH || stack.getItem() == Items.COOKED_FISH) {
            stack.shrink(1);
            if (stack.isEmpty()) {
                player.setHeldItem(hand, ItemStack.EMPTY);
            }
            MoCTools.playCustomSound(this, MoCSoundEvents.ENTITY_KITTY_EATING);
            this.setHealth(getMaxHealth());
            changeKittyState(7);
            return true;
        }
        if (!stack.isEmpty() && getKittyState() > 2 && stack.getItem() == MoCItems.medallion || stack.getItem() == Items.BOOK) {
            if (!this.world.isRemote) {
                MoCTools.tameWithName(player, this);
            }

            return true;
        }
        if (!stack.isEmpty() && getKittyState() > 2 && pickable() && stack.getItem() == Items.LEAD) {
            if (this.startRiding(player)) {
                changeKittyState(14);
            }
            return true;
        }
        if (!stack.isEmpty() && getKittyState() > 2 && whipable() && stack.getItem() == MoCItems.whip) {
            setSitting(!getIsSitting());
            return true;
        }
        if (stack.isEmpty() && getKittyState() > 2 && pickable()) {
            if (this.startRiding(player)) {
                changeKittyState(15);
            }
            return true;
        }
        if (stack.isEmpty() && getKittyState() == 15) {
            changeKittyState(7);
            return true;
        }
        if (getKittyState() == 14 && this.getRidingEntity() != null) {
            changeKittyState(7);
            return true;
        }

        return super.processInteract(player, hand);
    }

    @Override
    public boolean isMovementCeased() {
        return getIsSitting() || (getKittyState() == 6) || ((getKittyState() == 16) && getOnTree()) || (getKittyState() == 12) || (getKittyState() == 17) || (getKittyState() == 14) || (getKittyState() == 20) || (getKittyState() == 23);
    }

    @Override
    public boolean isOnLadder() {
        if (getKittyState() == 16) {
            return this.collidedHorizontally && getOnTree();
        } else {
            return super.isOnLadder();
        }
    }

    @Override
    public void onLivingUpdate() {
        if (!this.world.isRemote) {
            if (!getIsAdult() && getKittyState() != 10) {
                setKittyState(10);
            }
            if (getKittyState() != 12) {
                super.onLivingUpdate();
            }
            if (this.rand.nextInt(200) == 0) {
                setIsEmo(!getIsEmo());
            }
            if (!getIsAdult() && this.rand.nextInt(200) == 0) {
                setAge(getAge() + 1);
                if (getAge() >= 100) {
                    setAdult(true);
                }
            }
            if (!getIsHungry() && !getIsSitting() && this.rand.nextInt(100) == 0) {
                setHungry(true);
            }

            label0:
            switch (getKittyState()) {
                case -1:
                case 23:
                    break;
                case 1: // Casual living
                    if (this.rand.nextInt(10) == 0) {
                        EntityLivingBase entityliving = getBoogey(6D);
                        if (entityliving != null) {
                            MoCTools.runLikeHell(this, entityliving);
                        }
                        break;
                    }
                    if (!getIsHungry() || (this.rand.nextInt(10) != 0)) {
                        break;
                    }
                    EntityItem entityitem = getClosestItem(this, 10D, Items.COOKED_FISH, Items.COOKED_FISH);
                    if (entityitem == null) {
                        break;
                    }
                    float f = entityitem.getDistance(this);
                    if (f > 2.0F) {
                        getMyOwnPath(entityitem, f);
                    }
                    if (f < 2.0F && this.deathTime == 0) {
                        entityitem.setDead();
                        MoCTools.playCustomSound(this, MoCSoundEvents.ENTITY_KITTY_EATING);
                        setHungry(false);
                        setKittyState(2);
                    }
                    break;
                case 2: // Scared
                    EntityLivingBase entityliving1 = getBoogey(6D);
                    if (entityliving1 != null) {
                        MoCTools.runLikeHell(this, entityliving1);
                    }
                    break;
                case 3: // Looking for kitty bed
                    this.kittytimer++;
                    if (this.kittytimer > 500) {
                        if (this.rand.nextInt(200) == 0) {
                            changeKittyState(13);
                            break;
                        }
                        if (this.rand.nextInt(500) == 0) {
                            changeKittyState(7);
                            break;
                        }
                    }
                    if (this.rand.nextInt(20) != 0) {
                        break;
                    }
                    MoCEntityKittyBed entitykittybed = (MoCEntityKittyBed) getKittyStuff(this, 18D, false);
                    if ((entitykittybed == null) || (entitykittybed.isBeingRidden()) || (!entitykittybed.getHasMilk() && !entitykittybed.getHasFood())) {
                        break;
                    }
                    float f5 = entitykittybed.getDistance(this);
                    if (f5 > 2.0F) {
                        getMyOwnPath(entitykittybed, f5);
                    }
                    if (f5 < 2.0F) {
                        if (this.startRiding(entitykittybed)) {
                            changeKittyState(4);
                            setSitting(true);
                        }
                    }
                    break;
                case 4: // Sitting in kitty bed
                    if (this.getRidingEntity() != null) {
                        MoCEntityKittyBed entitykittybed1 = (MoCEntityKittyBed) this.getRidingEntity();
                        if ((entitykittybed1 != null) && !entitykittybed1.getHasMilk() && !entitykittybed1.getHasFood()) {
                            this.setHealth(getMaxHealth());
                            changeKittyState(5);
                        }
                    } else {
                        this.setHealth(getMaxHealth());
                        changeKittyState(5);
                    }
                    if (this.rand.nextInt(2500) == 0) {
                        this.setHealth(getMaxHealth());
                        changeKittyState(7);
                    }
                    break;
                case 5: // Looking for litter box
                    this.kittytimer++;
                    if ((this.kittytimer > 2000) && (this.rand.nextInt(1000) == 0)) {
                        changeKittyState(13);
                        break;
                    }
                    if (this.rand.nextInt(20) != 0) {
                        break;
                    }
                    MoCEntityLitterBox entitylitterbox = (MoCEntityLitterBox) getKittyStuff(this, 18D, true);
                    if ((entitylitterbox == null) || (entitylitterbox.isBeingRidden()) || entitylitterbox.getUsedLitter()) {
                        break;
                    }
                    float f6 = entitylitterbox.getDistance(this);
                    if (f6 > 2.0F) {
                        getMyOwnPath(entitylitterbox, f6);
                    }
                    if (f6 < 2.0F) {
                        if (this.startRiding(entitylitterbox)) {
                            changeKittyState(6);
                        }
                    }
                    break;
                case 6: // Doing business in litter box
                    this.kittytimer++;
                    if (this.kittytimer <= 300) {
                        break;
                    }
                    MoCTools.playCustomSound(this, MoCSoundEvents.ENTITY_KITTY_LITTER);
                    MoCEntityLitterBox entitylitterbox1 = (MoCEntityLitterBox) this.getRidingEntity();
                    if (entitylitterbox1 != null) {
                        entitylitterbox1.setUsedLitter(true);
                        entitylitterbox1.littertime = 0;
                    }
                    changeKittyState(7);
                    break;
                case 7: // ???
                    if (getIsSitting()) {
                        break;
                    }
                    if (this.rand.nextInt(20) == 0) {
                        EntityPlayer entityplayer = this.world.getClosestPlayerToEntity(this, 12D);
                        if (entityplayer != null) {
                            ItemStack stack = entityplayer.inventory.getCurrentItem();
                            if (!stack.isEmpty() && (stack.getItem() == MoCItems.woolball)) {
                                changeKittyState(11);
                                break;
                            }
                        }
                    }
                    if (this.inWater && (this.rand.nextInt(500) == 0)) {
                        changeKittyState(13);
                        break;
                    }
                    if (this.rand.nextInt(500) == 0 && !this.world.isDaytime()) {
                        changeKittyState(12);
                        break;
                    }
                    if (this.rand.nextInt(2000) == 0) {
                        changeKittyState(3);
                        break;
                    }
                    if (this.rand.nextInt(4000) == 0) {
                        changeKittyState(16);
                    }
                    break;
                case 8: // ???
                    if (this.inWater && this.rand.nextInt(200) == 0) {
                        changeKittyState(13);
                        break;
                    }
                    if (this.itemAttackTarget != null && getAttackTarget() != null) {
                        float f1 = getDistance(getAttackTarget());
                        if (f1 < 1.5F) {
                            swingArm();
                            if (this.rand.nextInt(10) == 0) {
                                float force = 0.3F;
                                if (getType() == 10) force = 0.2F;
                                MoCTools.bigsmack(this, this.itemAttackTarget, force);
                            }
                        }
                    }
                    if (getAttackTarget() == null || this.rand.nextInt(1000) == 0) {
                        changeKittyState(7);
                    }
                    break;
                case 9: // Looking for mate
                    this.kittytimer++;
                    if (this.rand.nextInt(50) == 0) {
                        List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().grow(16D, 6D, 16D));
                        int j = 0;
                        do {
                            if (j >= list.size()) {
                                break;
                            }
                            Entity entity = list.get(j);
                            if (entity instanceof MoCEntityKitty && ((MoCEntityKitty) entity).getKittyState() == 9) {
                                changeKittyState(18);
                                setAttackTarget((EntityLivingBase) entity);
                                ((MoCEntityKitty) entity).changeKittyState(18);
                                ((MoCEntityKitty) entity).setAttackTarget(this);
                                break;
                            }
                            j++;
                        } while (true);
                    }
                    if (this.kittytimer > 2000) {
                        changeKittyState(7);
                    }
                    break;
                case 10: // ???
                    if (getIsAdult()) {
                        changeKittyState(7);
                        break;
                    }
                    if (this.rand.nextInt(50) == 0) {
                        List<Entity> list1 = this.world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().grow(16D, 6D, 16D));
                        for (Entity entity1 : list1) {
                            if (!(entity1 instanceof MoCEntityKitty) || ((MoCEntityKitty) entity1).getKittyState() != 21) {
                                continue;
                            }
                            float f9 = getDistance(entity1);
                            if (f9 > 12F) {
                                setAttackTarget((EntityLivingBase) entity1);
                            }
                        }
                    }
                    if ((this.itemAttackTarget == null || getAttackTarget() == null) && this.rand.nextInt(100) == 0) {
                        int i = this.rand.nextInt(10);
                        if (i < 7) {
                            this.itemAttackTarget = getClosestItem(this, 10D, null, null);
                        } else {
                            this.setAttackTarget(this.world.getClosestPlayerToEntity(this, 18D));
                        }
                    }
                    if ((this.getAttackTarget() != null || this.itemAttackTarget != null) && this.rand.nextInt(400) == 0) {
                        setAttackTarget(null);
                        this.itemAttackTarget = null;
                    }
                    if (this.itemAttackTarget != null) {
                        float f2 = getDistance(this.itemAttackTarget);
                        if (f2 < 1.5F) {
                            swingArm();
                            if (this.rand.nextInt(10) == 0) {
                                MoCTools.bigsmack(this, this.itemAttackTarget, 0.2F);
                            }
                        }
                    }
                    if (getAttackTarget() instanceof MoCEntityKitty && this.rand.nextInt(20) == 0) {
                        float f3 = getDistance(getAttackTarget());
                        if (f3 < 2.0F) {
                            swingArm();
                            this.getNavigator().clearPath();
                        }
                    }
                    if (!(getAttackTarget() instanceof EntityPlayer)) {
                        break;
                    }
                    float f4 = getDistance(getAttackTarget());
                    if ((f4 < 2.0F) && (this.rand.nextInt(20) == 0)) {
                        swingArm();
                    }
                    break;
                case 11: // '\013'
                    EntityPlayer entityplayer1 = this.world.getClosestPlayerToEntity(this, 18D);
                    if ((entityplayer1 == null) || (this.rand.nextInt(10) != 0)) {
                        break;
                    }
                    ItemStack itemstack1 = entityplayer1.inventory.getCurrentItem();
                    if (itemstack1.getItem() != MoCItems.woolball) {
                        changeKittyState(7);
                        break;
                    }
                    float f8 = entityplayer1.getDistance(this);
                    if (f8 > 5F) {
                        getPathOrWalkableBlock(entityplayer1, f8);
                    }
                    break;
                case 12: // '\f'
                    this.kittytimer++;
                    if (this.world.isDaytime() || ((this.kittytimer > 500) && (this.rand.nextInt(500) == 0))) {
                        changeKittyState(7);
                        break;
                    }
                    setSitting(true);
                    if ((this.rand.nextInt(80) == 0) || !this.onGround) {
                        super.onLivingUpdate();
                    }
                    break;
                case 13: // '\r'
                    setHungry(false);
                    setAttackTarget(this.world.getClosestPlayerToEntity(this, 18D));
                    if (getAttackTarget() != null) {
                        float f7 = getDistance(getAttackTarget());
                        if (f7 < 1.5F) {
                            swingArm();
                            if (this.rand.nextInt(20) == 0) {
                                this.madtimer--;
                                getAttackTarget().attackEntityFrom(DamageSource.causeMobDamage(this), 1);
                                if (this.madtimer < 1) {
                                    changeKittyState(7);
                                    this.madtimer = this.rand.nextInt(5);
                                }
                            }
                        }
                        if (this.rand.nextInt(500) == 0) {
                            changeKittyState(7);
                        }
                    } else {
                        changeKittyState(7);
                    }
                    break;
                case 14: // Held by rope
                    if (this.onGround) {
                        changeKittyState(13);
                        break;
                    }
                    if (this.rand.nextInt(50) == 0) {
                        swingArm();
                    }
                    if (this.getRidingEntity() == null) {
                        break;
                    }
                    this.rotationYaw = this.getRidingEntity().rotationYaw + 90F;
                    EntityPlayer entityplayer2 = (EntityPlayer) this.getRidingEntity();
                    if (entityplayer2 == null) {
                        changeKittyState(13);
                        break;
                    }
                    ItemStack itemstack2 = entityplayer2.inventory.getCurrentItem();
                    if (itemstack2.getItem() != Items.LEAD) {
                        changeKittyState(13);
                    }
                    break;
                case 15: // '\017'
                    if (this.onGround) {
                        changeKittyState(7);
                    }
                    if (this.getRidingEntity() != null) {
                        this.rotationYaw = this.getRidingEntity().rotationYaw + 90F;
                    }
                    break;
                case 16: // '\020'
                    this.kittytimer++;
                    if (this.kittytimer > 500 && !getOnTree()) {
                        changeKittyState(7);
                    }
                    if (!getOnTree()) {
                        if (!this.foundTree && (this.rand.nextInt(50) == 0)) {
                            int[] ai = MoCTools.ReturnNearestMaterialCoord(this, Material.WOOD, 18D, 4D);
                            if (ai[0] != -1) {
                                int i1 = 0;
                                do {
                                    if (i1 >= 20) {
                                        break;
                                    }
                                    IBlockState blockstate = this.world.getBlockState(new BlockPos(ai[0], ai[1] + i1, ai[2]));
                                    if ((blockstate.getMaterial() == Material.LEAVES)) {
                                        this.foundTree = true;
                                        this.treeCoord[0] = ai[0];
                                        this.treeCoord[1] = ai[1];
                                        this.treeCoord[2] = ai[2];
                                        break;
                                    }
                                    i1++;
                                } while (true);
                            }
                        }
                        if (!this.foundTree || this.rand.nextInt(10) != 0) {
                            break;
                        }
                        Path pathentity = this.navigator.getPathToXYZ(this.treeCoord[0], this.treeCoord[1], this.treeCoord[2]);

                        if (pathentity != null) {
                            this.navigator.setPath(pathentity, 24F);
                        }
                        double double1 = getDistanceSq(this.treeCoord[0], this.treeCoord[1], this.treeCoord[2]);
                        if (double1 < 7D) {
                            setOnTree(true);
                        }
                        break;
                    }
                    int l = this.treeCoord[0];
                    int j1 = this.treeCoord[1];
                    int l1 = this.treeCoord[2];
                    faceItem(l, j1, l1, 30F);
                    if ((j1 - MathHelper.floor(this.posY)) > 2) {
                        this.motionY += 0.029999999999999999D;
                    }

                    if (this.posX < l) {
                        this.motionX += 0.01D;
                    } else {
                        this.motionX -= 0.01D;
                    }
                    if (this.posZ < l1) {
                        this.motionZ += 0.01D;
                    } else {
                        this.motionZ -= 0.01D;
                    }
                    if (this.onGround || !this.collidedHorizontally || !this.collidedVertically) {
                        break;
                    }
                    int i4 = 0;
                    do {
                        BlockPos pos = new BlockPos(this.treeCoord[0], this.treeCoord[1] + i4, this.treeCoord[2]);
                        Block block = this.world.getBlockState(pos).getBlock();
                        if (block == Blocks.AIR) {
                            setLocationAndAngles(this.treeCoord[0], this.treeCoord[1] + i4, this.treeCoord[2], this.rotationYaw, this.rotationPitch);
                            changeKittyState(17);
                            this.treeCoord[0] = -1;
                            this.treeCoord[1] = -1;
                            this.treeCoord[2] = -1;
                            break label0;
                        }
                        i4++;
                    } while (i4 < 30);
                    break;
                case 17: // '\021'
                    EntityPlayer entityplayer3 = this.world.getClosestPlayerToEntity(this, 2D);
                    if (entityplayer3 != null) {
                        changeKittyState(7);
                    }
                    break;
                case 18: // '\022'
                    if (!(getAttackTarget() instanceof MoCEntityKitty)) {
                        changeKittyState(9);
                        break;
                    }
                    MoCEntityKitty entitykitty = (MoCEntityKitty) getAttackTarget();
                    if (entitykitty != null && entitykitty.getKittyState() == 18) {
                        if (this.rand.nextInt(50) == 0) {
                            swingArm();
                        }
                        float f10 = getDistance(entitykitty);
                        if (f10 < 5F) {
                            this.kittytimer++;
                        }
                        if (this.kittytimer > 500 && this.rand.nextInt(50) == 0) {
                            ((MoCEntityKitty) getAttackTarget()).changeKittyState(7);
                            changeKittyState(19);
                        }
                    } else {
                        changeKittyState(9);
                    }
                    break;
                case 19: // '\023'
                    if (this.rand.nextInt(20) != 0) {
                        break;
                    }
                    MoCEntityKittyBed entitykittybed2 = (MoCEntityKittyBed) getKittyStuff(this, 18D, false);
                    if (entitykittybed2 == null || entitykittybed2.isBeingRidden()) {
                        break;
                    }
                    float f11 = entitykittybed2.getDistance(this);
                    if (f11 > 2.0F) {
                        getMyOwnPath(entitykittybed2, f11);
                    }
                    if (f11 < 2.0F) {
                        if (this.startRiding(entitykittybed2)) {
                            changeKittyState(20);
                        }
                    }
                    break;
                case 20: // '\024'
                    if (this.getRidingEntity() == null) {
                        changeKittyState(19);
                        break;
                    }
                    this.rotationYaw = 180F;
                    this.kittytimer++;
                    if (this.kittytimer <= 1000) {
                        break;
                    }
                    int i2 = this.rand.nextInt(3) + 1;
                    for (int l2 = 0; l2 < i2; l2++) {
                        MoCEntityKitty entitykitty1 = new MoCEntityKitty(this.world);
                        int babytype = this.getType();
                        if (this.rand.nextInt(2) == 0) {
                            babytype = (this.rand.nextInt(8) + 1);
                        }
                        entitykitty1.setType(babytype);
                        entitykitty1.setPosition(this.posX, this.posY, this.posZ);
                        this.world.spawnEntity(entitykitty1);
                        MoCTools.playCustomSound(this, SoundEvents.ENTITY_CHICKEN_EGG);
                        entitykitty1.setAdult(false);
                        entitykitty1.changeKittyState(10);
                        // attackEntityFrom(DamageSource.generic, 1); blood - workaround to fix infinite births
                    }
                    changeKittyState(21);
                    break;
                case 21: // '\025'
                    this.kittytimer++;
                    if (this.kittytimer > 2000) {
                        List<Entity> list2 = this.world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().grow(24D, 8D, 24D));
                        int i3 = 0;
                        for (Entity entity2 : list2) {
                            if (entity2 instanceof MoCEntityKitty && ((MoCEntityKitty) entity2).getKittyState() == 10) {
                                i3++;
                            }
                        }
                        if (i3 < 1) {
                            changeKittyState(7);
                            break;
                        }
                        this.kittytimer = 1000;
                    }
                    if (getAttackTarget() instanceof EntityPlayer && this.rand.nextInt(300) == 0) {
                        setAttackTarget(null);
                    }
                    break;
                case 0:
                    changeKittyState(1);
                    break;
                // case 22: // '\026'
                default:
                    changeKittyState(7);
                    break;
            }
        } else {
            super.onLivingUpdate();
        }
        // Dismount player on both sides to prevent desyncs
        if (this.isRiding()) MoCTools.dismountSneakingPlayer(this);
    }

    public boolean onMaBack() {
        return getKittyState() == 15;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (getIsSwinging()) {
            this.swingProgress += 0.2F;
            if (this.swingProgress > 2.0F) {
                setSwinging(false);
                this.swingProgress = 0.0F;
            }
        }
    }

    private boolean pickable() {
        return getKittyState() != 13 && getKittyState() != 14 && getKittyState() != 15 && getKittyState() != 19 && getKittyState() != 20 && getKittyState() != 21;
    }

    @Override
    public boolean renderName() {
        return getKittyState() != 14 && getKittyState() != 15 && getKittyState() > 1 && super.renderName();
    }

    @Override
    public void setDead() {
        if (this.world.isRemote || getKittyState() <= 2 || getHealth() <= 0) {
            super.setDead();
        }
    }

    public void swingArm() {
        //to synchronize, uses the packet handler to invoke the same method in the clients
        if (!this.world.isRemote) {
            MoCMessageHandler.INSTANCE.sendToAllAround(new MoCMessageAnimation(this.getEntityId(), 0), new TargetPoint(this.world.provider.getDimensionType().getId(), this.posX, this.posY, this.posZ, 64));
        }

        if (!getIsSwinging()) {
            setSwinging(true);
            this.swingProgress = 0.0F;
        }
    }

    @Override
    public void performAnimation(int i) {
        swingArm();
    }

    public boolean upsideDown() {
        return getKittyState() == 14;
    }

    public boolean whipable() {
        return getKittyState() != 13;
    }

    @Override
    public boolean getCanSpawnHere() {
        if (MoCreatures.proxy.kittyVillageChance <= 0) return super.getCanSpawnHere();
        BlockPos villagePos = world.findNearestStructure("Village", getPosition(), true);
        if (villagePos != null) {
            double villageDist = villagePos.getDistance(getPosition().getX(), getPosition().getY(), getPosition().getZ());
            if (villageDist < 128) return super.getCanSpawnHere();
        }
        return false;
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        super.readEntityFromNBT(nbttagcompound);
        setSitting(nbttagcompound.getBoolean("Sitting"));
        setKittyState(nbttagcompound.getInteger("KittyState"));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setBoolean("Sitting", getIsSitting());
        nbttagcompound.setInteger("KittyState", getKittyState());
    }

    //drops medallion on death
    @Override
    public void onDeath(DamageSource damagesource) {
        if (!this.world.isRemote && getIsTamed()) {
            MoCTools.dropCustomItem(this, this.world, new ItemStack(MoCItems.medallion, 1));
        }
        super.onDeath(damagesource);
    }

    @Override
    public boolean swimmerEntity() {
        return true;
    }

    @Override
    public int nameYOffset() {
        if (this.getIsSitting()) return -30;
        return -40;
    }
}
