/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.item;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

import java.util.UUID;

import com.google.common.collect.Multimap;

public class MoCItemLuckyArmor extends MoCItemArmor {
    protected static final UUID LUCK_MODIFIER = UUID.fromString("F34BB326-D435-4B63-8254-0B6CB57A8E6F");
    public float luck;

    public MoCItemLuckyArmor(String name, float luck, ItemArmor.ArmorMaterial materialIn, int renderIndex, EntityEquipmentSlot equipmentSlotIn) {
        super(name, materialIn, renderIndex, equipmentSlotIn);
        this.luck = luck;
    }

    @Override
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
        super.getItemAttributeModifiers(equipmentSlot);

        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == this.armorType) {
            multimap.put(SharedMonsterAttributes.LUCK.getName(), new AttributeModifier(LUCK_MODIFIER, "Armor luck", (double) this.luck, 0));
        }

        return multimap;
    }
}
