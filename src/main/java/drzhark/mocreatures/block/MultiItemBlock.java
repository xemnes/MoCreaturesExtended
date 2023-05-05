/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;

public class MultiItemBlock extends ItemMultiTexture {

    public MultiItemBlock(Block block) {
        super(block, block, stack -> MoCBlock.EnumType.byMetadata(stack.getMetadata()).getTranlationKey());
        setHasSubtypes(true);
    }
}
