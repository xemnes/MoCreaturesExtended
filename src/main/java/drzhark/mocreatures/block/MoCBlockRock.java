/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class MoCBlockRock extends Block {

    public MoCBlockRock(MapColor mapColor) {
        super(Material.ROCK, mapColor);
        setTickRandomly(true);
        this.setSoundType(SoundType.STONE);
    }

    public MoCBlockRock(Material material, MapColor mapColor) {
        super(material, mapColor);
    }
}
