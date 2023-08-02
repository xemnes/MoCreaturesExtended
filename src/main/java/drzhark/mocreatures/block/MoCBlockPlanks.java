/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class MoCBlockPlanks extends Block {

    public MoCBlockPlanks() {
        super(Material.WOOD);
        this.setSoundType(SoundType.WOOD);
    }
}
