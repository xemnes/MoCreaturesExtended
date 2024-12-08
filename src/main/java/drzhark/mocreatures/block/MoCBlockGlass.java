/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.block;

import net.minecraft.block.BlockBreakable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class MoCBlockGlass extends BlockBreakable {
    private final boolean isTranslucent;

    public MoCBlockGlass(boolean isTranslucent) {
        super(Material.GLASS, false);
        this.setSoundType(SoundType.GLASS);
        this.setHarvestLevel("pickaxe", 0);
        this.isTranslucent = isTranslucent;
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return this.isTranslucent ? BlockRenderLayer.TRANSLUCENT : BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    protected boolean canSilkHarvest() {
        return true;
    }
}
