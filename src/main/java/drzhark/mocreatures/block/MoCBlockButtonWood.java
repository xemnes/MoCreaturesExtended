/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.block;

import net.minecraft.block.BlockButtonWood;
import net.minecraft.block.SoundType;

// NOTE: Wooden buttons are not flammable in vanilla
public class MoCBlockButtonWood extends BlockButtonWood {
    public MoCBlockButtonWood() {
    	super();
        this.setSoundType(SoundType.WOOD);
        this.setHardness(0.5F);
        this.setHarvestLevel("axe", 0);
    }
}
