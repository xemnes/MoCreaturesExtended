/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.entity.hunter;

import javax.annotation.Nullable;

import drzhark.mocreatures.MoCLootTables;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.IMoCTameable;
import drzhark.mocreatures.entity.neutral.MoCEntityPandaBear;
import drzhark.mocreatures.init.MoCItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MoCEntityBlackBear extends MoCEntityBear {

    public MoCEntityBlackBear(World world) {
        super(world);
        setSize(0.85F, 1.175F);
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
        return MoCreatures.proxy.getModelTexture("bear_black.png");
    }

    @Override
    public float getBearSize() {
        return 0.9F;
    }

    @Override
    public int getMaxAge() {
        return 90;
    }

    public double getAttackRange() {
        int factor = 1;
        if (this.world.getDifficulty().getId() > 1) {
            factor = 2;
        }
        return 6D * factor;
    }

    @Override
    public int getAttackStrength() {
        int factor = (this.world.getDifficulty().getId());
        return 2 * factor;
    }

    @Override
    public boolean shouldAttackPlayers() {
        return false;
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        final Boolean tameResult = this.processTameInteract(player, hand);
        if (tameResult != null) {
            return tameResult;
        }

        final ItemStack stack = player.getHeldItem(hand);
        if (!stack.isEmpty() && this.getAge() < 80 && MoCTools.isItemEdibleforCarnivores(stack.getItem())) {
            if (!player.capabilities.isCreativeMode) stack.shrink(1);

            if (!this.world.isRemote && !getIsTamed()) {
                MoCTools.tameWithName(player, this);
            }

            this.setHealth(getMaxHealth());
            eatingAnimal();
            if (!this.world.isRemote && !getIsAdult() && (getAge() < 100)) {
                setAge(getAge() + 1);
            }

            return true;
        }
        if (!stack.isEmpty() && getIsTamed() && (stack.getItem() == MoCItems.whip)) {
            if (getBearState() == 0) {
                setBearState(2);
            } else {
                setBearState(0);
            }
            return true;
        }
        if (this.getIsRideable() && this.getIsAdult() && (!this.getIsChested() || !player.isSneaking()) && !this.isBeingRidden()) {
            if (player.startRiding(this)) {
                player.rotationYaw = this.rotationYaw;
                player.rotationPitch = this.rotationPitch;
                setBearState(0);
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

        return MoCLootTables.BLACK_BEAR;
    }

    @Override
    public String getOffspringClazz(IMoCTameable mate) {
        return "BlackBear";
    }

    @Override
    public int getOffspringTypeInt(IMoCTameable mate) {
        return 1;
    }

    @Override
    public boolean compatibleMate(Entity mate) {
        return mate instanceof MoCEntityPandaBear;
    }

    public float getEyeHeight() {
        return this.height * 0.76F;
    }
}
