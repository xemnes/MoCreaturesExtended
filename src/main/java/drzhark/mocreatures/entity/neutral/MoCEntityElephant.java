/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.entity.neutral;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
import drzhark.mocreatures.entity.ai.EntityAIFollowAdult;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.init.MoCItems;
import drzhark.mocreatures.init.MoCSoundEvents;
import drzhark.mocreatures.inventory.MoCAnimalChest;
import drzhark.mocreatures.network.MoCMessageHandler;
import drzhark.mocreatures.network.message.MoCMessageAnimation;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

import java.util.List;

public class MoCEntityElephant extends MoCEntityTameableAnimal {

    private static final DataParameter<Integer> TUSK_TYPE = EntityDataManager.createKey(MoCEntityElephant.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> STORAGE_TYPE = EntityDataManager.createKey(MoCEntityElephant.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> HARNESS_TYPE = EntityDataManager.createKey(MoCEntityElephant.class, DataSerializers.VARINT);
    public int sprintCounter;
    public int sitCounter;
    public MoCAnimalChest localelephantchest;
    public MoCAnimalChest localelephantchest2;
    public MoCAnimalChest localelephantchest3;
    public MoCAnimalChest localelephantchest4;
    public ItemStack localstack;
    public int tailCounter;
    public int trunkCounter;
    public int earCounter;
    boolean hasPlatform;
    private byte tuskUses;
    private byte temper;


    public MoCEntityElephant(World world) {
        super(world);
        setAdult(true);
        setTamed(false);
        setAge(50);
        // TODO: Different hitboxes for each elephant type
        setSize(1.1F, 3F);
        this.stepHeight = 1.0F;

        if (!this.world.isRemote) {
            setAdult(this.rand.nextInt(4) != 0);
        }
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(4, new EntityAIFollowAdult(this, 1.0D));
        this.tasks.addTask(5, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(6, new EntityAIWanderMoC2(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
    }

    @Override
    public void selectType() {
        checkSpawningBiome();
        if (getType() == 0) {
            setType(this.rand.nextInt(2) + 1);
        }
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(calculateMaxHealth());
        this.setHealth(getMaxHealth());
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(TUSK_TYPE, 0);// tusks: 0 nothing, 1 wood, 2 iron, 3 diamond
        this.dataManager.register(STORAGE_TYPE, 0);// storage: 0 nothing, 1 chest, 2 chests....
        this.dataManager.register(HARNESS_TYPE, 0);// harness: 0 nothing, 1 harness, 2 cabin
    }

    public int getTusks() {
        return this.dataManager.get(TUSK_TYPE);
    }

    public void setTusks(int i) {
        this.dataManager.set(TUSK_TYPE, i);
    }

    @Override
    public int getArmorType() {
        return this.dataManager.get(HARNESS_TYPE);
    }

    @Override
    public void setArmorType(int i) {
        this.dataManager.set(HARNESS_TYPE, i);
    }

    public int getStorage() {
        return this.dataManager.get(STORAGE_TYPE);
    }

    public void setStorage(int i) {
        this.dataManager.set(STORAGE_TYPE, i);
    }

    @Override
    public ResourceLocation getTexture() {
        switch (getType()) {
            case 2:
                return MoCreatures.proxy.getModelTexture("elephant_asian.png");
            case 3:
                return MoCreatures.proxy.getModelTexture("mammoth_woolly.png");
            case 4:
                return MoCreatures.proxy.getModelTexture("mammoth_songhua.png");
            case 5:
                return MoCreatures.proxy.getModelTexture("elephant_asian_decorated.png");
            default:
                return MoCreatures.proxy.getModelTexture("elephant_african.png");
        }
    }

    public float calculateMaxHealth() {
        switch (getType()) {
            case 1:
            case 5:
                return 40;
            case 3:
                return 50;
            case 4:
                return 60;
            default:
                return 30;
        }
    }

    @Override
    public double getCustomSpeed() {
        if (this.sitCounter != 0) {
            return 0D;
        }
        double tSpeed = 0.5D;
        if (getType() == 1) {
            tSpeed = 0.55D;
        } else if (getType() == 2) {
            tSpeed = 0.6D;
        } else if (getType() == 4) {
            tSpeed = 0.55D;
        }
        if (this.sprintCounter > 0 && this.sprintCounter < 150) {
            tSpeed *= 1.5D;
        }
        if (this.sprintCounter > 150) {
            tSpeed *= 0.5D;
        }
        return tSpeed;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.world.isRemote) {
            if ((this.sprintCounter > 0 && this.sprintCounter < 150) && (this.isBeingRidden()) && rand.nextInt(15) == 0) {
                MoCTools.buckleMobsNotPlayers(this, 3D, this.world);
            }

            if (this.sprintCounter > 0 && ++this.sprintCounter > 300) {
                this.sprintCounter = 0;
            }

            if (getIsTamed() && (!this.isBeingRidden()) && getArmorType() >= 1 && this.rand.nextInt(20) == 0) {
                EntityPlayer ep = this.world.getClosestPlayerToEntity(this, 3D);
                if (ep != null && (!MoCreatures.proxy.enableOwnership || ep.getUniqueID().equals(this.getOwnerId())) && ep.isSneaking()) {
                    sit();
                }
            }

            if (MoCreatures.proxy.elephantBulldozer && getIsTamed() && (this.isBeingRidden()) && (getTusks() > 0)) {
                int height = 2;
                if (getType() == 3) {
                    height = 3;
                }
                if (getType() == 4) {
                    height = 3;
                }
                int dmg = MoCTools.destroyBlocksInFront(this, 2D, this.getTusks(), height);
                checkTusks(dmg);

            }

        } else //client only animation counters
        {
            if (this.tailCounter > 0 && ++this.tailCounter > 8) {
                this.tailCounter = 0;
            }

            if (this.rand.nextInt(200) == 0) {
                this.tailCounter = 1;
            }

            if (this.trunkCounter > 0 && ++this.trunkCounter > 38) {
                this.trunkCounter = 0;
            }

            if (this.trunkCounter == 0 && this.rand.nextInt(200) == 0) {
                this.trunkCounter = rand.nextInt(10) + 1;
            }

            if (this.earCounter > 0 && ++this.earCounter > 30) {
                this.earCounter = 0;
            }

            if (this.rand.nextInt(200) == 0) {
                this.earCounter = rand.nextInt(20) + 1;
            }

        }

        if (this.sitCounter != 0) {
            if (++this.sitCounter > 100) {
                this.sitCounter = 0;
            }
        }
    }

    /**
     * Checks if the tusks sets need to break or not (wood = 59, stone = 131,
     * iron = 250, diamond = 1561, gold = 32)
     */
    private void checkTusks(int dmg) {
        this.tuskUses += (byte) dmg;
        if ((this.getTusks() == 1 && this.tuskUses > 59) || (this.getTusks() == 2 && this.tuskUses > 250)
                || (this.getTusks() == 3 && this.tuskUses > 1000)) {
            MoCTools.playCustomSound(this, MoCSoundEvents.ENTITY_TURTLE_HURT);
            setTusks((byte) 0);
        }
    }

    private void sit() {
        this.sitCounter = 1;
        if (!this.world.isRemote) {
            MoCMessageHandler.INSTANCE.sendToAllAround(new MoCMessageAnimation(this.getEntityId(), 0),
                    new TargetPoint(this.world.provider.getDimensionType().getId(), this.posX, this.posY, this.posZ, 64));
        }
        this.getNavigator().clearPath();
    }

    @Override
    public void performAnimation(int animationType) {
        if (animationType == 0) //sitting animation
        {
            this.sitCounter = 1;
            this.getNavigator().clearPath();
        }
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        final Boolean tameResult = this.processTameInteract(player, hand);
        if (tameResult != null) {
            return tameResult;
        }

        final ItemStack stack = player.getHeldItem(hand);
        if (!stack.isEmpty() && !getIsTamed() && !getIsAdult() && stack.getItem() == Items.CAKE) {
            if (!player.capabilities.isCreativeMode) stack.shrink(1);
            MoCTools.playCustomSound(this, MoCSoundEvents.ENTITY_GENERIC_EATING);
            this.temper += 2;
            this.setHealth(getMaxHealth());
            if (!this.world.isRemote && !getIsAdult() && !getIsTamed() && this.temper >= 10) {
                MoCTools.tameWithName(player, this);
            }
            return true;
        }

        if (!stack.isEmpty() && !getIsTamed() && !getIsAdult() && stack.getItem() == MoCItems.sugarlump) {
            if (!player.capabilities.isCreativeMode) stack.shrink(1);
            MoCTools.playCustomSound(this, MoCSoundEvents.ENTITY_GENERIC_EATING);
            this.temper += 1;
            this.setHealth(getMaxHealth());
            if (!this.world.isRemote && !getIsAdult() && !getIsTamed() && this.temper >= 10) {
                setTamed(true);
                MoCTools.tameWithName(player, this);
            }
            return true;
        }

        if (!stack.isEmpty() && getIsTamed() && getIsAdult() && getArmorType() == 0 && stack.getItem() == MoCItems.elephantHarness) {
            if (!player.capabilities.isCreativeMode) stack.shrink(1);
            MoCTools.playCustomSound(this, MoCSoundEvents.ENTITY_GENERIC_ROPING);
            setArmorType((byte) 1);
            return true;
        }

        if (!stack.isEmpty() && getIsTamed() && getIsAdult() && getArmorType() >= 1 && getStorage() == 0
                && stack.getItem() == MoCItems.elephantChest) {
            if (!player.capabilities.isCreativeMode) stack.shrink(1);
            MoCTools.playCustomSound(this, MoCSoundEvents.ENTITY_GENERIC_ROPING);
            //entityplayer.inventory.addItemStackToInventory(new ItemStack(MoCreatures.key));
            setStorage((byte) 1);
            return true;
        }
        // second storage unit
        if (!stack.isEmpty() && getIsTamed() && getIsAdult() && getArmorType() >= 1 && getStorage() == 1
                && stack.getItem() == MoCItems.elephantChest) {
            if (!player.capabilities.isCreativeMode) stack.shrink(1);
            MoCTools.playCustomSound(this, MoCSoundEvents.ENTITY_GENERIC_ROPING);
            setStorage((byte) 2);
            return true;
        }
        // third storage unit for small mammoths
        if (!stack.isEmpty() && getIsTamed() && getIsAdult() && (getType() == 3) && getArmorType() >= 1 && getStorage() == 2
                && stack.getItem() == Item.getItemFromBlock(Blocks.CHEST)) {
            if (!player.capabilities.isCreativeMode) stack.shrink(1);
            MoCTools.playCustomSound(this, MoCSoundEvents.ENTITY_GENERIC_ROPING);
            setStorage((byte) 3);
            return true;
        }
        // fourth storage unit for small mammoths
        if (!stack.isEmpty() && getIsTamed() && getIsAdult() && (getType() == 3) && getArmorType() >= 1 && getStorage() == 3
                && stack.getItem() == Item.getItemFromBlock(Blocks.CHEST)) {
            if (!player.capabilities.isCreativeMode) stack.shrink(1);
            MoCTools.playCustomSound(this, MoCSoundEvents.ENTITY_GENERIC_ROPING);
            setStorage((byte) 4);
            return true;
        }

        //giving a garment to an indian elephant with a harness will make it pretty
        if (!stack.isEmpty() && getIsTamed() && getIsAdult() && getArmorType() == 1 && getType() == 2
                && stack.getItem() == MoCItems.elephantGarment) {
            if (!player.capabilities.isCreativeMode) stack.shrink(1);
            MoCTools.playCustomSound(this, MoCSoundEvents.ENTITY_GENERIC_ROPING);
            setArmorType((byte) 2);
            setType(5);
            return true;
        }

        //giving a howdah to a pretty indian elephant with a garment will attach the howdah
        if (!stack.isEmpty() && getIsTamed() && getIsAdult() && getArmorType() == 2 && getType() == 5
                && stack.getItem() == MoCItems.elephantHowdah) {
            if (!player.capabilities.isCreativeMode) stack.shrink(1);
            MoCTools.playCustomSound(this, MoCSoundEvents.ENTITY_GENERIC_ROPING);
            setArmorType((byte) 3);
            return true;
        }

        //giving a platform to a ? mammoth with harness will attach the platform
        if (!stack.isEmpty() && getIsTamed() && getIsAdult() && getArmorType() == 1 && getType() == 4
                && stack.getItem() == MoCItems.mammothPlatform) {
            if (!player.capabilities.isCreativeMode) stack.shrink(1);
            MoCTools.playCustomSound(this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_OFF);
            setArmorType((byte) 3);
            return true;
        }

        if (!stack.isEmpty() && getIsTamed() && getIsAdult() && stack.getItem() == MoCItems.tusksWood) {
            if (!player.capabilities.isCreativeMode) stack.shrink(1);
            MoCTools.playCustomSound(this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_OFF);
            dropTusks();
            this.tuskUses = (byte) stack.getItemDamage();
            setTusks((byte) 1);
            return true;
        }

        if (!stack.isEmpty() && getIsTamed() && getIsAdult() && stack.getItem() == MoCItems.tusksIron) {
            if (!player.capabilities.isCreativeMode) stack.shrink(1);
            MoCTools.playCustomSound(this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_OFF);
            dropTusks();
            this.tuskUses = (byte) stack.getItemDamage();
            setTusks((byte) 2);
            return true;
        }

        if (!stack.isEmpty() && getIsTamed() && getIsAdult() && stack.getItem() == MoCItems.tusksDiamond) {
            if (!player.capabilities.isCreativeMode) stack.shrink(1);
            MoCTools.playCustomSound(this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_OFF);
            dropTusks();
            this.tuskUses = (byte) stack.getItemDamage();
            setTusks((byte) 3);
            return true;
        }

        if (player.isSneaking()) {
            initChest();
            if (getStorage() == 1) {
                if (!this.world.isRemote) {
                    player.displayGUIChest(this.localelephantchest);
                }
                return true;
            }
            if (getStorage() == 2) {
                InventoryLargeChest doubleChest = new InventoryLargeChest("ElephantChest", this.localelephantchest, this.localelephantchest2);
                if (!this.world.isRemote) {
                    player.displayGUIChest(doubleChest);
                }
                return true;
            }
            if (getStorage() == 3) {
                InventoryLargeChest doubleChest = new InventoryLargeChest("ElephantChest", this.localelephantchest, this.localelephantchest2);
                InventoryLargeChest tripleChest = new InventoryLargeChest("ElephantChest", doubleChest, this.localelephantchest3);
                if (!this.world.isRemote) {
                    player.displayGUIChest(tripleChest);
                }
                return true;
            }
            if (getStorage() == 4) {
                InventoryLargeChest doubleChest = new InventoryLargeChest("ElephantChest", this.localelephantchest, this.localelephantchest2);
                InventoryLargeChest doubleChestb = new InventoryLargeChest("ElephantChest", this.localelephantchest3, this.localelephantchest4);
                InventoryLargeChest fourChest = new InventoryLargeChest("ElephantChest", doubleChest, doubleChestb);
                if (!this.world.isRemote) {
                    player.displayGUIChest(fourChest);
                }
                return true;
            }

        }
        if (!stack.isEmpty() && getTusks() > 0 && stack.getItem() instanceof ItemPickaxe) {
            MoCTools.playCustomSound(this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_OFF);
            dropTusks();
            return true;
        }

        if (getIsTamed() && getIsAdult() && getArmorType() >= 1 && this.sitCounter != 0) {
            if (!this.world.isRemote && player.startRiding(this)) {
                player.rotationYaw = this.rotationYaw;
                player.rotationPitch = this.rotationPitch;
                this.sitCounter = 0;
            }

            return true;
        }

        return super.processInteract(player, hand);
    }

    private void initChest() {
        if (getStorage() > 0 && this.localelephantchest == null) {
            this.localelephantchest = new MoCAnimalChest("ElephantChest", 18);
        }

        if (getStorage() > 1 && this.localelephantchest2 == null) {
            this.localelephantchest2 = new MoCAnimalChest("ElephantChest", 18);
        }

        if (getStorage() > 2 && this.localelephantchest3 == null) {
            this.localelephantchest3 = new MoCAnimalChest("ElephantChest", 9);
        }

        if (getStorage() > 3 && this.localelephantchest4 == null) {
            this.localelephantchest4 = new MoCAnimalChest("ElephantChest", 9);
        }
    }

    /**
     * Drops tusks, makes sound
     */
    private void dropTusks() {
        if (this.world.isRemote) {
            return;
        }
        int i = getTusks();

        if (i == 1) {
            EntityItem entityitem =
                    new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(MoCItems.tusksWood, 1, this.tuskUses));
            entityitem.setDefaultPickupDelay();
            this.world.spawnEntity(entityitem);
        }
        if (i == 2) {
            EntityItem entityitem =
                    new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(MoCItems.tusksIron, 1, this.tuskUses));
            entityitem.setDefaultPickupDelay();
            this.world.spawnEntity(entityitem);
        }
        if (i == 3) {
            EntityItem entityitem =
                    new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(MoCItems.tusksDiamond, 1, this.tuskUses));
            entityitem.setDefaultPickupDelay();
            this.world.spawnEntity(entityitem);
        }
        setTusks((byte) 0);
        this.tuskUses = 0;
    }

    @Override
    public boolean rideableEntity() {
        return true;
    }

    /*@Override
    public boolean updateMount() {
        return getIsTamed();
    }
    */
    /*@Override
    public boolean forceUpdates() {
        return getIsTamed();
    }*/

    @Override
    public boolean checkSpawningBiome() {
        BlockPos pos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(getEntityBoundingBox().minY), this.posZ);
        Biome currentbiome = MoCTools.biomeKind(this.world, pos);

        // African
        if (BiomeDictionary.hasType(currentbiome, Type.SANDY) || BiomeDictionary.hasType(currentbiome, Type.SAVANNA)) {
            setType(1);
            return true;
        }
        // Indian
        if (BiomeDictionary.hasType(currentbiome, Type.JUNGLE)) {
            setType(2);
            return true;
        }
        // Mammoth
        if (BiomeDictionary.hasType(currentbiome, Type.SNOWY)) {
            setType(3 + this.rand.nextInt(2));
            return true;
        }

        return false;
    }

    @Override
    public float getSizeFactor() {
        float sizeF = 1.25F;

        switch (getType()) {
            case 4:
                sizeF *= 1.2F;
                break;
            case 2:
            case 5:
                sizeF *= 0.80F;
                break;
        }

        if (!getIsAdult()) {
            sizeF = sizeF * (getAge() * 0.01F);
        }
        return sizeF;
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        super.readEntityFromNBT(nbttagcompound);
        setTusks(nbttagcompound.getInteger("Tusks"));
        setArmorType(nbttagcompound.getInteger("Harness"));
        setStorage(nbttagcompound.getInteger("Storage"));
        this.tuskUses = nbttagcompound.getByte("TuskUses");
        if (getStorage() > 0) {
            NBTTagList nbttaglist = nbttagcompound.getTagList("Items", 10);
            this.localelephantchest = new MoCAnimalChest("ElephantChest", 18);
            for (int i = 0; i < nbttaglist.tagCount(); i++) {
                NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
                int j = nbttagcompound1.getByte("Slot") & 0xff;
                if (j < this.localelephantchest.getSizeInventory()) {
                    this.localelephantchest.setInventorySlotContents(j, new ItemStack(nbttagcompound1));
                }
            }
        }
        if (getStorage() >= 2) {
            NBTTagList nbttaglist = nbttagcompound.getTagList("Items2", 10);
            this.localelephantchest2 = new MoCAnimalChest("ElephantChest", 18);
            for (int i = 0; i < nbttaglist.tagCount(); i++) {
                NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
                int j = nbttagcompound1.getByte("Slot") & 0xff;
                if (j < this.localelephantchest2.getSizeInventory()) {
                    this.localelephantchest2.setInventorySlotContents(j, new ItemStack(nbttagcompound1));
                }
            }
        }

        if (getStorage() >= 3) {
            NBTTagList nbttaglist = nbttagcompound.getTagList("Items3", 10);
            this.localelephantchest3 = new MoCAnimalChest("ElephantChest", 9);
            for (int i = 0; i < nbttaglist.tagCount(); i++) {
                NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
                int j = nbttagcompound1.getByte("Slot") & 0xff;
                if (j < this.localelephantchest3.getSizeInventory()) {
                    this.localelephantchest3.setInventorySlotContents(j, new ItemStack(nbttagcompound1));
                }
            }
        }
        if (getStorage() >= 4) {
            NBTTagList nbttaglist = nbttagcompound.getTagList("Items4", 10);
            this.localelephantchest4 = new MoCAnimalChest("ElephantChest", 9);
            for (int i = 0; i < nbttaglist.tagCount(); i++) {
                NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
                int j = nbttagcompound1.getByte("Slot") & 0xff;
                if (j < this.localelephantchest4.getSizeInventory()) {
                    this.localelephantchest4.setInventorySlotContents(j, new ItemStack(nbttagcompound1));
                }
            }
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setInteger("Tusks", getTusks());
        nbttagcompound.setInteger("Harness", getArmorType());
        nbttagcompound.setInteger("Storage", getStorage());
        nbttagcompound.setByte("TuskUses", this.tuskUses);

        if (getStorage() > 0 && this.localelephantchest != null) {
            NBTTagList nbttaglist = new NBTTagList();
            for (int i = 0; i < this.localelephantchest.getSizeInventory(); i++) {
                this.localstack = this.localelephantchest.getStackInSlot(i);
                if (!this.localstack.isEmpty()) {
                    NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                    nbttagcompound1.setByte("Slot", (byte) i);
                    this.localstack.writeToNBT(nbttagcompound1);
                    nbttaglist.appendTag(nbttagcompound1);
                }
            }
            nbttagcompound.setTag("Items", nbttaglist);
        }

        if (getStorage() >= 2 && this.localelephantchest2 != null) {
            NBTTagList nbttaglist = new NBTTagList();
            for (int i = 0; i < this.localelephantchest2.getSizeInventory(); i++) {
                this.localstack = this.localelephantchest2.getStackInSlot(i);
                if (!this.localstack.isEmpty()) {
                    NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                    nbttagcompound1.setByte("Slot", (byte) i);
                    this.localstack.writeToNBT(nbttagcompound1);
                    nbttaglist.appendTag(nbttagcompound1);
                }
            }
            nbttagcompound.setTag("Items2", nbttaglist);
        }

        if (getStorage() >= 3 && this.localelephantchest3 != null) {
            NBTTagList nbttaglist = new NBTTagList();
            for (int i = 0; i < this.localelephantchest3.getSizeInventory(); i++) {
                this.localstack = this.localelephantchest3.getStackInSlot(i);
                if (!this.localstack.isEmpty()) {
                    NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                    nbttagcompound1.setByte("Slot", (byte) i);
                    this.localstack.writeToNBT(nbttagcompound1);
                    nbttaglist.appendTag(nbttagcompound1);
                }
            }
            nbttagcompound.setTag("Items3", nbttaglist);
        }

        if (getStorage() >= 4 && this.localelephantchest4 != null) {
            NBTTagList nbttaglist = new NBTTagList();
            for (int i = 0; i < this.localelephantchest4.getSizeInventory(); i++) {
                this.localstack = this.localelephantchest4.getStackInSlot(i);
                if (!this.localstack.isEmpty()) {
                    NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                    nbttagcompound1.setByte("Slot", (byte) i);
                    this.localstack.writeToNBT(nbttagcompound1);
                    nbttaglist.appendTag(nbttagcompound1);
                }
            }
            nbttagcompound.setTag("Items4", nbttaglist);
        }
    }

    @Override
    public boolean isMyHealFood(ItemStack stack) {
        return !stack.isEmpty()
                && (stack.getItem() == Items.BAKED_POTATO || stack.getItem() == Items.BREAD || stack.getItem() == MoCItems.haystack);
    }

    @Override
    public boolean isMovementCeased() {
        return (this.isBeingRidden()) || this.sitCounter != 0;
    }

    @Override
    public void Riding() {
        if ((this.isBeingRidden()) && (this.getRidingEntity() instanceof EntityPlayer)) {
            EntityPlayer entityplayer = (EntityPlayer) this.getRidingEntity();
            List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().grow(1.0D, 0.0D, 1.0D));
            for (Entity entity : list) {
                if (entity.isDead) {
                    continue;
                }
                entity.onCollideWithPlayer(entityplayer);
            }

            if (entityplayer.isSneaking()) {
                if (!this.world.isRemote) {
                    if (this.sitCounter == 0) {
                        sit();
                    }
                    if (this.sitCounter >= 50) {
                        entityplayer.dismountRidingEntity();
                    }

                }
            }
        }
    }

    @Override
    public boolean canBePushed() {
        return !this.isBeingRidden();
    }

    @Override
    public boolean canBeCollidedWith() {
        return !this.isBeingRidden();
    }

    @Override
    public void updatePassenger(Entity passenger) {

        double dist = (1.0D);
        switch (getType()) {
            case 1:
            case 3:
                dist = 0.8D;
                break;
            case 2:
            case 5:
                dist = 0.1D;
                break;
            case 4:
                dist = 1.2D;
                break;
        }

        double newPosX = this.posX - (dist * Math.cos((MoCTools.realAngle(this.renderYawOffset - 90F)) / 57.29578F));
        double newPosZ = this.posZ - (dist * Math.sin((MoCTools.realAngle(this.renderYawOffset - 90F)) / 57.29578F));
        passenger.setPosition(newPosX, this.posY + getMountedYOffset() + passenger.getYOffset(), newPosZ);
    }

    @Override
    public double getMountedYOffset() {
        double yOff = 0F;
        boolean sit = (this.sitCounter != 0);

        switch (getType()) {
            case 1:
            case 3:
                yOff = 0.55D;
                if (sit) {
                    yOff = -0.05D;
                }
                break;
            case 2:
            case 5:
                yOff = -0.17D;
                if (sit) {
                    yOff = -0.5D;
                }
                break;
            case 4:
                yOff = 1.2D;
                if (sit) {
                    yOff = 0.45D;
                }
                break;
        }
        return yOff + (this.height * 0.75D);
    }

    /**
     * Had to set to false to avoid damage due to the collision boxes
     */
    @Override
    public boolean isEntityInsideOpaqueBlock() {
        return false;
    }

    @Override
    public int getTalkInterval() {
        return 300;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return MoCSoundEvents.ENTITY_ELEPHANT_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return MoCSoundEvents.ENTITY_ELEPHANT_HURT;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (!getIsAdult() && getAge() < 80) {
            return MoCSoundEvents.ENTITY_ELEPHANT_AMBIENT_BABY;
        }
        return MoCSoundEvents.ENTITY_ELEPHANT_AMBIENT;
    }

    @Override
    protected Item getDropItem() {
        return MoCItems.animalHide;
    }

    @Override
    public void dropMyStuff() {
        if (!this.world.isRemote) {
            dropTusks();
            //dropSaddle(this, world);
            if (getStorage() > 0) {
                if (getStorage() > 0) {
                    MoCTools.dropCustomItem(this, this.world, new ItemStack(MoCItems.elephantChest, 1));
                    if (this.localelephantchest != null) {
                        MoCTools.dropInventory(this, this.localelephantchest);
                    }

                }
                if (getStorage() >= 2) {
                    if (this.localelephantchest2 != null) {
                        MoCTools.dropInventory(this, this.localelephantchest2);
                    }
                    MoCTools.dropCustomItem(this, this.world, new ItemStack(MoCItems.elephantChest, 1));
                }
                if (getStorage() >= 3) {
                    if (this.localelephantchest3 != null) {
                        MoCTools.dropInventory(this, this.localelephantchest3);
                    }
                    MoCTools.dropCustomItem(this, this.world, new ItemStack(Blocks.CHEST, 1));
                }
                if (getStorage() >= 4) {
                    if (this.localelephantchest4 != null) {
                        MoCTools.dropInventory(this, this.localelephantchest4);
                    }
                    MoCTools.dropCustomItem(this, this.world, new ItemStack(Blocks.CHEST, 1));
                }
                setStorage((byte) 0);
            }
            dropArmor();
        }
    }

    @Override
    public void dropArmor() {
        if (this.world.isRemote) {
            return;
        }
        if (getArmorType() >= 1) {
            MoCTools.dropCustomItem(this, this.world, new ItemStack(MoCItems.elephantHarness, 1));
        }
        if (getType() == 5 && getArmorType() >= 2) {

            MoCTools.dropCustomItem(this, this.world, new ItemStack(MoCItems.elephantGarment, 1));
            if (getArmorType() == 3) {
                MoCTools.dropCustomItem(this, this.world, new ItemStack(MoCItems.elephantHowdah, 1));
            }
            setType(2);
        }
        if (getType() == 4 && getArmorType() == 3) {
            MoCTools.dropCustomItem(this, this.world, new ItemStack(MoCItems.mammothPlatform, 1));
        }
        setArmorType((byte) 0);

    }

    @Override
    public int nameYOffset() {
        int yOff = (int) ((100 / getAge()) * (getSizeFactor() * -110));
        if (getIsAdult()) {
            yOff = (int) (getSizeFactor() * -110);
        }
        if (sitCounter != 0)
            yOff += 25;
        return yOff;
    }

    @Override
    public boolean attackEntityFrom(DamageSource damagesource, float i) {
        if (super.attackEntityFrom(damagesource, i)) {
            Entity entity = damagesource.getTrueSource();
            if ((entity != null && getIsTamed() && entity instanceof EntityPlayer) || !(entity instanceof EntityLivingBase)) {
                return false;
            }
            if (this.isRidingOrBeingRiddenBy(entity)) {
                return true;
            }
            if (entity != this && super.shouldAttackPlayers()) {
                setAttackTarget((EntityLivingBase) entity);
            }
            return true;
        }
        return false;
    }

    @Override
    public void fall(float f, float f1) {
        int i = (int) Math.ceil(f - 3F);
        if ((i > 0)) {
            i /= 2;
            if (i > 0) {
                attackEntityFrom(DamageSource.FALL, i);
            }
            if ((this.isBeingRidden()) && (i > 0)) {
                for (Entity entity : this.getRecursivePassengers()) {
                    entity.attackEntityFrom(DamageSource.FALL, i);
                }
            }
            IBlockState iblockstate = this.world.getBlockState(new BlockPos(this.posX, this.posY - 0.2D - (double) this.prevRotationYaw, this.posZ));
            Block block = iblockstate.getBlock();

            if (iblockstate.getMaterial() != Material.AIR && !this.isSilent()) {
                SoundType soundtype = block.getSoundType(iblockstate, world, new BlockPos(this.posX, this.posY - 0.2D - (double) this.prevRotationYaw, this.posZ), this);
                this.world.playSound(null, this.posX, this.posY, this.posZ, soundtype.getStepSound(), this.getSoundCategory(), soundtype.getVolume() * 0.5F, soundtype.getPitch() * 0.75F);
            }
        }
    }

    @Override
    public boolean isNotScared() {
        return getIsAdult() || getAge() > 80 || getIsTamed();
    }
}
