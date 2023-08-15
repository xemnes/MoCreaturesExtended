/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.item;

import drzhark.mocreatures.MoCConstants;
import drzhark.mocreatures.MoCreatures;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class MoCItemAxe extends ItemAxe {

    private int specialWeaponType = 0;

    public MoCItemAxe(String name, Item.ToolMaterial material, float damage, float speed) {
        this(name, 0, material, damage, speed);
    }

    public MoCItemAxe(String name, int meta, Item.ToolMaterial material, float damage, float speed) {
        super(material, damage - 1.0F, speed - 4.0F);
        this.setCreativeTab(MoCreatures.tabMoC);
        this.setRegistryName(MoCConstants.MOD_ID, name);
        this.setTranslationKey(name);
    }

    public MoCItemAxe(String name, Item.ToolMaterial material, float damage, float speed, int damageType) {
        this(name, material, damage, speed);
        this.specialWeaponType = damageType;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        int timer = 15; // In seconds
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

        stack.damageItem(2, attacker);
        return true;
    }
}
