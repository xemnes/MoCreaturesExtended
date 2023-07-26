/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.item;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class MoCItemSugarLump extends MoCItemFood {

    public MoCItemSugarLump(String name) {
        super(name, 3);
        this.maxStackSize = 32;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);

        for (String s : I18n.format(stack.getItem().getTranslationKey() + ".desc").split("\\\\n")) {
            tooltip.add(TextFormatting.GRAY + s);
        }
    }
}
