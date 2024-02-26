/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.compat.crossbow.item;

import drzhark.mocreatures.MoCConstants;
import drzhark.mocreatures.MoCreatures;
import git.jbredwards.crossbow.api.ICrossbow;
import git.jbredwards.crossbow.mod.common.Crossbow;
import git.jbredwards.crossbow.mod.common.capability.ICrossbowProjectiles;
import git.jbredwards.crossbow.mod.common.item.ItemCrossbow;
import net.minecraft.item.ItemFirework;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

// TODO
public class MoCItemCrossbow extends ItemCrossbow implements ICrossbow {
    public Ingredient repairMaterial;

    public MoCItemCrossbow(String name, int durability, Ingredient repairMaterial) {
        this.setCreativeTab(MoCreatures.tabMoC);
        this.setRegistryName(MoCConstants.MOD_ID, name);
        this.setTranslationKey(name);
        this.maxStackSize = 1;
        this.setMaxDamage(durability);
        this.repairMaterial = repairMaterial;
        addPropertyOverride(new ResourceLocation(Crossbow.MODID, "pull"), (stack, world, entity) -> {
            if (entity == null) return 0;
            final ICrossbowProjectiles cap = ICrossbowProjectiles.get(stack);
            return cap == null || !cap.isEmpty() ? 0 : (float) (stack.getMaxItemUseDuration() - entity.getItemInUseCount()) / getPullTime(stack);
        });

        addPropertyOverride(new ResourceLocation(Crossbow.MODID, "pulling"), (stack, world, entity) ->
                entity != null && entity.isHandActive() && entity.getActiveItemStack() == stack ? 1 : 0);

        addPropertyOverride(new ResourceLocation(Crossbow.MODID, "charged"), (stack, world, entity) -> {
            final ICrossbowProjectiles cap = ICrossbowProjectiles.get(stack);
            return cap != null && !cap.isEmpty() ? 1 : 0;
        });

        addPropertyOverride(new ResourceLocation(Crossbow.MODID, "firework"), (stack, world, entity) -> {
            final ICrossbowProjectiles cap = ICrossbowProjectiles.get(stack);
            return cap != null && cap.stream().anyMatch(projectile -> projectile.getItem() instanceof ItemFirework) ? 1 : 0;
        });
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repairMaterial.test(repair) || super.getIsRepairable(toRepair, repair);
    }
}
