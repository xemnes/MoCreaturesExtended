/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.block;

import drzhark.mocreatures.init.MoCBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class MoCBlockLeaf extends Block {

    public MoCBlockLeaf() {
        super(Material.LEAVES);
        setTickRandomly(true);
        this.setSoundType(SoundType.PLANT);
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
        return true;
    }

    @Override
    public int quantityDropped(Random random) {
        return random.nextInt(20) != 0 ? 0 : 1;
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, @Nullable ItemStack stack) {
        if (!worldIn.isRemote && !stack.isEmpty() && stack.getItem() == Items.SHEARS) {
            player.addStat(StatList.getBlockStats(this), 1);
            spawnAsEntity(worldIn, pos, new ItemStack(MoCBlocks.wyvernLeaves, 1, 0));
        } else {
            super.harvestBlock(worldIn, player, pos, state, te, stack);
        }
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random) {
        return 0;
    }

}
