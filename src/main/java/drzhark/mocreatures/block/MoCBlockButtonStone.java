/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.block;

import net.minecraft.block.BlockButtonStone;
import net.minecraft.block.SoundType;

public class MoCBlockButtonStone extends BlockButtonStone {
    public MoCBlockButtonStone() {
    	super();
        this.setSoundType(SoundType.STONE);
        this.setHardness(0.5F);
        this.setHarvestLevel("pickaxe", 0);
    }
}
