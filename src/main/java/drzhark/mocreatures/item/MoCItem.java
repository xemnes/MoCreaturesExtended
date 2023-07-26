/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.item;

import drzhark.mocreatures.MoCConstants;
import drzhark.mocreatures.MoCreatures;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class MoCItem extends Item {

    public MoCItem() {
    }

    public MoCItem(String name) {
        this(name, 0);
    }

    public MoCItem(String name, int meta) {
        this.setCreativeTab(MoCreatures.tabMoC);
        this.setRegistryName(MoCConstants.MOD_ID, name);
        this.setTranslationKey(name);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);

        for (String s : I18n.format(stack.getItem().getTranslationKey() + ".desc").split("\\\\n")) {
            if (I18n.hasKey(stack.getItem().getTranslationKey() + ".desc")) {
                tooltip.add(TextFormatting.GRAY + s);
            }
        }
    }
}
