package drzhark.mocreatures.block;

import java.util.Random;

import drzhark.mocreatures.init.MoCBlocks;
import drzhark.mocreatures.init.MoCItems;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

// NOTE: Wooden doors are not flammable in vanilla
public class MoCBlockDoorWood extends BlockDoor {
    private final MapColor mapColor;

    public MoCBlockDoorWood(MapColor mapColor) {
        super(Material.WOOD);
        this.setSoundType(SoundType.WOOD);
        this.setHardness(3.0F);
        this.setHarvestLevel("axe", 0);
        this.mapColor = mapColor;
        //this.enableStats = false;
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(getItemBlock());
    }

    protected Item getItemBlock() {
        if (this == MoCBlocks.wyvwoodDoor) {
            return MoCItems.wyvwoodDoor;
        }

        // If door is not listed here give an oak one instead to prevent issues
        else {
            return Items.OAK_DOOR;
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? Items.AIR : getItemBlock();
    }

    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess world, BlockPos pos) {
        return mapColor;
    }
}
