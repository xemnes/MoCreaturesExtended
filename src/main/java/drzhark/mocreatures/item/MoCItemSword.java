/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.item;

import drzhark.mocreatures.MoCConstants;
import drzhark.mocreatures.MoCreatures;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class MoCItemSword extends ItemSword {

    private int specialWeaponType = 0;

    public MoCItemSword(String name, Item.ToolMaterial material) {
        this(name, 0, material);
    }

    public MoCItemSword(String name, int meta, Item.ToolMaterial material) {
        super(material);
        this.setCreativeTab(MoCreatures.tabMoC);
        this.setRegistryName(MoCConstants.MOD_ID, name);
        this.setTranslationKey(name);
    }

    public MoCItemSword(String name, Item.ToolMaterial material, int damageType) {
        this(name, material);
        this.specialWeaponType = damageType;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        int timer = 10; // In seconds
        switch (this.specialWeaponType) {
            case 1: // Poison 2
                target.addPotionEffect(new PotionEffect(MobEffects.POISON, timer * 20, 1));
                break;
            case 2: // Slowness
                target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, timer * 20, 0));
                break;
            case 3: // Fire
                target.setFire(timer);
                break;
            case 4: // Weakness (Nausea for players)
                target.addPotionEffect(new PotionEffect(target instanceof EntityPlayer ? MobEffects.NAUSEA : MobEffects.WEAKNESS, timer * 20, 0));
                break;
            case 5: // Wither (Blindness for players)
                target.addPotionEffect(new PotionEffect(target instanceof EntityPlayer ? MobEffects.BLINDNESS : MobEffects.WITHER, timer * 20, 0));
                break;
            default:
                break;
        }

        stack.damageItem(1, attacker);
        return true;
    }

    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);

        for (String s : I18n.format(stack.getItem().getTranslationKey() + ".desc").split("\\\\n")) {
            if (I18n.hasKey(stack.getItem().getTranslationKey() + ".desc")) {
                tooltip.add(TextFormatting.GRAY + s);
            }
        }
    }
}
