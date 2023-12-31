/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class MoCBlockNest extends Block {

    public MoCBlockNest() {
        super(Material.GRASS, MapColor.WOOD);
        this.setSoundType(SoundType.PLANT);
    }
    
    @Override
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.fall(fallDistance, 0.2F);
    }
    
    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
    	return Blocks.HAY_BLOCK.getFlammability(world, pos, face);
    }

    public MoCBlockNest(Material material, MapColor mapColor) {
        super(material, mapColor);
    }
}
