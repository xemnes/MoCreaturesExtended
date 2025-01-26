package drzhark.mocreatures.compat.tinkers.traits;

import java.util.List;

import com.google.common.collect.ImmutableList;

import drzhark.mocreatures.MoCConstants;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitBigGameHunter extends AbstractTrait {
    protected final float bonus;

    public TraitBigGameHunter(float bonus) {
        super(MoCConstants.MOD_ID + "." + "big_game_hunter", 0xBBA56C);

        this.bonus = bonus;
    }

    @Override
    public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
        // Bonus damage to animals
        if (target instanceof EntityAnimal) {
            newDamage += damage * bonus;
        }

        return newDamage;
    }

    @Override
    public List<String> getExtraInfo(ItemStack tool, NBTTagCompound modifierTag) {
        String loc = String.format(LOC_Extra, getModifierIdentifier());

        return ImmutableList.of(Util.translateFormatted(loc, Util.dfPercent.format(bonus)));
    }
}
