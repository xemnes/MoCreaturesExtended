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

public class MoCEntityLiger extends MoCEntityBigCat {

    public MoCEntityLiger(World world) {
        super(world);
        setSize(1.35F, 1.43525F);
    }

    @Override
    public void selectType() {
        if (getType() == 0) {
            setType(1);
        }
        super.selectType();
    }

    @Override
    public ResourceLocation getTexture() {
        if (MoCreatures.proxy.legacyBigCatModels) return MoCreatures.proxy.getModelTexture("big_cat_liger_legacy.png");
        return MoCreatures.proxy.getModelTexture("big_cat_liger.png");
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        final Boolean tameResult = this.processTameInteract(player, hand);
        if (tameResult != null) {
            return tameResult;
        }

        final ItemStack stack = player.getHeldItem(hand);
        if (!stack.isEmpty() && getIsTamed() && (getType() == 1) && (stack.getItem() == MoCItems.essencelight)) {
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

        return MoCLootTables.LIGER;
    }

    @Override
    public String getOffspringClazz(IMoCTameable mate) {
        return "Liger";
    }

    @Override
    public int getOffspringTypeInt(IMoCTameable mate) {
        return 1;
    }

    @Override
    public boolean compatibleMate(Entity mate) {
        return false;
    }

    @Override
    public float calculateMaxHealth() {
        return 35F;
    }

    @Override
    public double calculateAttackDmg() {
        return 8D;
    }

    @Override
    public double getAttackRange() {
        return 10D;
    }

    @Override
    public int getMaxAge() {
        return 135;
    }

    @Override
    public boolean canAttackTarget(EntityLivingBase entity) {
        if (!this.getIsAdult() && (this.getAge() < this.getMaxAge() * 0.8)) {
            return false;
        }
        if (entity instanceof MoCEntityLiger) {
            return false;
        }
        return entity.height < 2F && entity.width < 2F;
    }

    @Override
    public boolean isFlyer() {
        return this.getType() == 2;
    }

    public float getEyeHeight() {
        return this.height * 0.92F;
    }
}
