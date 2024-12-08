/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class MoCBlockMetal extends Block {

    public MoCBlockMetal(MapColor mapColor) {
        super(Material.IRON, mapColor);
        this.setSoundType(SoundType.METAL);
    }

    public MoCBlockMetal(Material material, MapColor mapColor) {
        super(material, mapColor);
    }
}
