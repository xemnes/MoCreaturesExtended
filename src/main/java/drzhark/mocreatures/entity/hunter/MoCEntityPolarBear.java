/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.entity.hunter;

import javax.annotation.Nullable;

import drzhark.mocreatures.MoCLootTables;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.IMoCTameable;
import drzhark.mocreatures.init.MoCItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MoCEntityPolarBear extends MoCEntityBear {

    public MoCEntityPolarBear(World world) {
        super(world);
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
        return MoCreatures.proxy.getModelTexture("bear_polar.png");
    }

    @Override
    public float getBearSize() {
        return 1.4F;
    }

    @Override
    public int getMaxAge() {
        return 140;
    }

    @Override
    public float calculateMaxHealth() {
        return 50;
    }

    public double getAttackRange() {
        int factor = 1;
        if (this.world.getDifficulty().getId() > 1) {
            factor = 2;
        }
        return 8D * factor;
    }

    @Override
    public int getAttackStrength() {
        int factor = (this.world.getDifficulty().getId());
        return 4 * factor;
    }

    @Override
    public boolean shouldAttackPlayers() {
        return super.shouldAttackPlayers();
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

            if (!getIsTamed() && !this.world.isRemote) {
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
            if (!this.world.isRemote && player.startRiding(this)) {
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

        return MoCLootTables.POLAR_BEAR;
    }

    @Override
    public String getOffspringClazz(IMoCTameable mate) {
        return "WildPolarBear";
    }

    @Override
    public int getOffspringTypeInt(IMoCTameable mate) {
        return 1;
    }

    @Override
    public boolean compatibleMate(Entity mate) {
        return mate instanceof MoCEntityPolarBear;
    }
}
