/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.item;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import com.google.common.collect.Multimap;

public class MoCItemLuckyArmor extends MoCItemArmor {
    public float luck;

    public MoCItemLuckyArmor(String name, float luck, ItemArmor.ArmorMaterial materialIn, int renderIndex, EntityEquipmentSlot equipmentSlotIn) {
        super(name, materialIn, renderIndex, equipmentSlotIn);
        this.luck = luck;
    }

    @Override
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == this.armorType) {
            multimap.put(SharedMonsterAttributes.ARMOR.getName(), new AttributeModifier(ARMOR_MODIFIERS[equipmentSlot.getIndex()], "Armor modifier", (double) this.damageReduceAmount, 0));
            multimap.put(SharedMonsterAttributes.ARMOR_TOUGHNESS.getName(), new AttributeModifier(ARMOR_MODIFIERS[equipmentSlot.getIndex()], "Armor toughness", (double) this.toughness, 0));
            multimap.put(SharedMonsterAttributes.LUCK.getName(), new AttributeModifier(ARMOR_MODIFIERS[equipmentSlot.getIndex()], "Armor luck", (double) this.luck, 0));
        }

        return multimap;
    }
}
