/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.block;

import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

// NOTE: Wooden pressure plates are not flammable in vanilla
public class MoCBlockPressurePlateWood extends BlockPressurePlate {
    private final MapColor mapColor;

    public MoCBlockPressurePlateWood(MapColor mapColor) {
        super(Material.WOOD, Sensitivity.EVERYTHING);
        this.setSoundType(SoundType.WOOD);
        this.setHardness(0.5F);
        this.setHarvestLevel("axe", 0);
        this.mapColor = mapColor;
    }

    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess world, BlockPos pos) {
        return mapColor;
    }
}
