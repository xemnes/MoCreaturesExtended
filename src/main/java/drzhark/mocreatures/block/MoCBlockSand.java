/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.block;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MoCBlockSand extends BlockFalling {

    private final MapColor mapColor;

    public MoCBlockSand(MapColor mapColor) {
        super(Material.GROUND);
        this.mapColor = mapColor;
        this.setSoundType(SoundType.SAND);
        this.setHarvestLevel("shovel", 0);
    }

    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess world, BlockPos pos) {
        return mapColor;
    }

    @SideOnly(Side.CLIENT)
    public int getDustColor(IBlockState state) {
        return 12107978;
    }
}
