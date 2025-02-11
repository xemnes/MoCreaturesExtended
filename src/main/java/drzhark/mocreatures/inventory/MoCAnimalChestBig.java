/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.inventory;

import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.ILockableContainer;

public class MoCAnimalChestBig extends InventoryLargeChest {

    private final int mySize; //either 27 or 56

    public MoCAnimalChestBig(String name, ILockableContainer p_i45905_2_, ILockableContainer p_i45905_3_, int size) {
        super(name, p_i45905_2_, p_i45905_3_);
        this.mySize = size;
    }

    @Override
    public int getSizeInventory() {
        return this.mySize;
    }

    public void loadInventoryFromNBT(NBTTagList par1NBTTagList) {
        int var2;

        for (var2 = 0; var2 < this.getSizeInventory(); ++var2) {
            this.setInventorySlotContents(var2, null);
        }

        for (var2 = 0; var2 < par1NBTTagList.tagCount(); ++var2) {
            NBTTagCompound var3 = par1NBTTagList.getCompoundTagAt(var2);
            int var4 = var3.getByte("Slot") & 255;

            if (var4 >= 0 && var4 < this.getSizeInventory()) {
                this.setInventorySlotContents(var4, new ItemStack(var3));
            }
        }
    }

    public NBTTagList saveInventoryToNBT() {
        NBTTagList var1 = new NBTTagList();

        for (int var2 = 0; var2 < this.getSizeInventory(); ++var2) {
            ItemStack var3 = this.getStackInSlot(var2);

            if (var3 != null) {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte) var2);
                var3.writeToNBT(var4);
                var1.appendTag(var4);
            }
        }

        return var1;
    }
}
