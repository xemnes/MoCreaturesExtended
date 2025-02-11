/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.block;

import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.init.MoCBlocks;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MoCBlockTallGrass extends MoCBlockBush implements IShearable {

    protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.8D, 0.9D);

    public MoCBlockTallGrass(String name) {
        super(name, Material.VINE);
        this.setCreativeTab(MoCreatures.tabMoC);
        this.setSoundType(SoundType.PLANT);
    }

    public MoCBlockTallGrass(String name, boolean lighted) {
        this(name);
        if (lighted) {
            this.setLightLevel(0.8F);
        }
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return AABB;
    }

    /**
     * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i'
     * (inclusive).
     */
    @Override
    public int quantityDroppedWithBonus(int par1, Random par2Random) {
        return 1 + par2Random.nextInt(par1 * 2 + 1);
    }

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
        return true;
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        return new ArrayList<>(Collections.singletonList(new ItemStack(MoCBlocks.mocTallGrass)));
    }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
        Block soil = worldIn.getBlockState(pos.down()).getBlock();
        return soil == MoCBlocks.mocGrass || soil == MoCBlocks.mocDirt || soil instanceof BlockGrass || soil instanceof BlockDirt || soil instanceof BlockFarmland;
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        IBlockState soil = worldIn.getBlockState(pos.down());
        Block tempblock = soil.getBlock();
        if (tempblock instanceof MoCBlockDirt || tempblock instanceof MoCBlockGrass) {
            return true;
        }
        return super.canPlaceBlockAt(worldIn, pos) && soil.getBlock().canSustainPlant(soil, worldIn, pos.down(), EnumFacing.UP, this);
    }
}
