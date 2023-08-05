/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.block;

import java.util.Random;

import drzhark.mocreatures.init.MoCBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class MoCBlockRock extends Block {

    public MoCBlockRock(MapColor mapColor) {
        super(Material.ROCK, mapColor);
        this.setSoundType(SoundType.STONE);
    }

    public MoCBlockRock(Material material, MapColor mapColor) {
        super(material, mapColor);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        if (this == MoCBlocks.deep_wyvstone) {
            return MoCBlocks.cobbled_deep_wyvstone.getItemDropped(MoCBlocks.cobbled_deep_wyvstone.getDefaultState(), rand, fortune);
        }

        if (this == MoCBlocks.wyvstone) {
            return MoCBlocks.cobbled_wyvstone.getItemDropped(MoCBlocks.cobbled_wyvstone.getDefaultState(), rand, fortune);
        } else {
            return Item.getItemFromBlock(this);
        }
    }
}
