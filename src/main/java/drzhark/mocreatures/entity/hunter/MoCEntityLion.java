/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.entity.hunter;

import javax.annotation.Nullable;

import drzhark.mocreatures.MoCLootTables;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.IMoCTameable;
import drzhark.mocreatures.init.MoCItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MoCEntityLion extends MoCEntityBigCat {

    public MoCEntityLion(World world) {
        super(world);
        // TODO: Separate hitbox for the lioness
        setSize(1.25F, 1.275F);
    }

    @Override
    public void selectType() {

        if (getType() == 0) {
            if (rand.nextInt(20) == 0) {
                setType(rand.nextInt(2) + 6);//white lions
            } else {
                setType(rand.nextInt(2) + 1);
            }
        }
        super.selectType();
    }

    @Override
    public ResourceLocation getTexture() {
        if (MoCreatures.proxy.legacyBigCatModels) {
            switch (getType()) {
                case 6:
                case 7:
                case 8:
                    return MoCreatures.proxy.getModelTexture("big_cat_white_lion_legacy.png");
                default:
                    return MoCreatures.proxy.getModelTexture("big_cat_lion_legacy.png");
            }
        }
        switch (getType()) {
            case 2:
            case 3:
                return MoCreatures.proxy.getModelTexture("big_cat_lion_male.png");
            case 6:
            case 7:
            case 8:
                return MoCreatures.proxy.getModelTexture("big_cat_white_lion.png");
            default:
                return MoCreatures.proxy.getModelTexture("big_cat_lion_female.png");
        }
    }

    @Override
    public boolean hasMane() {
        return this.getIsAdult() && (this.getType() == 2 || this.getType() == 3 || this.getType() == 7);
    }

    @Override
    public boolean isFlyer() {
        return this.getType() == 3 || this.getType() == 5 || this.getType() == 8;
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        final Boolean tameResult = this.processTameInteract(player, hand);
        if (tameResult != null) {
            return tameResult;
        }

        final ItemStack stack = player.getHeldItem(hand);
        if (!stack.isEmpty() && getIsTamed() && (getType() == 2 || getType() == 7) && (stack.getItem() == MoCItems.essencelight)) {
            if (!player.capabilities.isCreativeMode) stack.shrink(1);
            if (stack.isEmpty()) {
                player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
            } else {
                player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
            }
            setType(getType() + 1);
            return true;
        }

        if (this.getIsRideable() && this.getIsAdult() && (!this.getIsChested() || !player.isSneaking()) && !this.isBeingRidden()) {
            if (!this.world.isRemote && player.startRiding(this)) {
                player.rotationYaw = this.rotationYaw;
                player.rotationPitch = this.rotationPitch;
                setSitting(false);
            }

            return true;
        }

        return super.processInteract(player, hand);
    }

    @Nullable
    protected ResourceLocation getLootTable() {
        if (!getIsAdult()) {
            return null;
        }

        return MoCLootTables.LION;
    }

    @Override
    public String getOffspringClazz(IMoCTameable mate) {
        if (mate instanceof MoCEntityTiger && mate.getType() < 3) {
            return "Liger";//return 4; //liger"
        }
        if (getType() == 2 && mate instanceof MoCEntityLeopard && mate.getType() == 1) {
            return "Liard";//return 9; //liard
        }
        if (getType() == 2 && mate instanceof MoCEntityPanther && mate.getType() == 1) {
            return "Lither";//return 5; //lither
        }
        return "Lion";
    }

    @Override
    public int getOffspringTypeInt(IMoCTameable mate) {
        int x = 0;
        if (mate instanceof MoCEntityTiger && mate.getType() < 3) {
            return 1;//4; //liger
        }
        if (getType() == 2 && mate instanceof MoCEntityLeopard && mate.getType() == 1) {
            return 1;//9; //liard
        }
        if (getType() == 2 && mate instanceof MoCEntityPanther && mate.getType() == 1) {
            return 1;//5; //lither
        }
        if (mate instanceof MoCEntityLion) {
            int lionMateType = mate.getType();
            if (this.getType() == 1 && lionMateType == 2) {
                x = this.rand.nextInt(2) + 1;
            }
            if (this.getType() == 2 && lionMateType == 1) {
                x = this.rand.nextInt(2) + 1;
            }
            if (this.getType() == 6 && lionMateType == 7) {
                x = this.rand.nextInt(2) + 6;
            }
            if (this.getType() == 7 && lionMateType == 6) {
                x = this.rand.nextInt(2) + 6;
            }
            if (this.getType() == 7 && lionMateType == 1) {
                x = this.rand.nextInt(2) + 1;
            }
            if (this.getType() == 6 && lionMateType == 2) {
                x = this.rand.nextInt(2) + 1;
            }
            if (this.getType() == 1 && lionMateType == 7) {
                x = this.rand.nextInt(2) + 1;
            }
            if (this.getType() == 2 && lionMateType == 6) {
                x = this.rand.nextInt(2) + 1;
            }
        }
        return x;
    }

    @Override
    public boolean compatibleMate(Entity mate) {
        if (this.getType() == 2 && mate instanceof MoCEntityTiger && ((MoCEntityTiger) mate).getType() < 3) {
            return true;
        }
        if (this.getType() == 2 && mate instanceof MoCEntityLeopard && ((MoCEntityLeopard) mate).getType() == 1) {
            return true;
        }
        if (this.getType() == 2 && mate instanceof MoCEntityPanther && ((MoCEntityPanther) mate).getType() == 1) {
            return true;
        }
        if (mate instanceof MoCEntityLion) {
            return (getOffspringTypeInt((MoCEntityLion) mate) != 0);
        }
        return false;
    }

    @Override
    public boolean readytoBreed() {
        return (this.getType() < 3 || this.getType() == 6 || this.getType() == 7) && super.readytoBreed();
    }

    @Override
    public float calculateMaxHealth() {
        if (this.getType() == 2 || this.getType() == 7) {
            return 35F;
        }
        if (this.getType() == 4) {
            return 40F;
        }
        return 30F;
    }

    @Override
    public double calculateAttackDmg() {
        return 7D;
    }

    @Override
    public double getAttackRange() {
        if (this.getType() == 1 || this.getType() == 6) {
            return 12D;
        }
        return 8D;
    }

    @Override
    public int getMaxAge() {
        if (getType() == 1 || getType() == 6) {
            return 110;
        }
        if (getType() == 9) {
            return 100;
        }
        return 120;
    }

    @Override
    public boolean canAttackTarget(EntityLivingBase entity) {
        if (!this.getIsAdult() && (this.getAge() < this.getMaxAge() * 0.8)) {
            return false;
        }
        if (entity instanceof MoCEntityLion) {
            return false;
        }
        return entity.height < 2F && entity.width < 2F;
    }

    public float getEyeHeight() {
        return this.height * 0.92F;
    }
}
