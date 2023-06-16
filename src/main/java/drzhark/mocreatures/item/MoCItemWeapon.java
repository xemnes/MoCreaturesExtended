/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.item;

import com.google.common.collect.Multimap;
import net.minecraft.block.BlockWeb;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class MoCItemWeapon extends MoCItem {

    private final Item.ToolMaterial material;
    private final float attackDamage;
    private int specialWeaponType = 0;
    private boolean breakable = false;

    public MoCItemWeapon(String name, Item.ToolMaterial par2ToolMaterial) {
        super(name);
        this.material = par2ToolMaterial;
        this.maxStackSize = 1;
        this.setMaxDamage(par2ToolMaterial.getMaxUses());
        this.attackDamage = 3F + par2ToolMaterial.getAttackDamage();
    }

    public MoCItemWeapon(String name, ToolMaterial par2ToolMaterial, int damageType, boolean fragile) {
        this(name, par2ToolMaterial);
        this.specialWeaponType = damageType;
        this.breakable = fragile;
    }

    public float getAttackDamage() {
        return this.material.getAttackDamage();
    }

    public float getStrVsBlock(ItemStack stack, IBlockState state) {
        if (state.getBlock() instanceof BlockWeb) {
            return 15.0F;
        } else {
            Material material = state.getMaterial();
            return material != Material.PLANTS && material != Material.VINE && material != Material.CORAL && material != Material.LEAVES && material != Material.GOURD ? 1.0F : 1.5F;
        }
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        int i = 1;
        if (this.breakable) {
            i = 5;
        }
        int timer = 10; // in seconds
        switch (this.specialWeaponType) {
            case 1: // poison
                target.addPotionEffect(new PotionEffect(MobEffects.POISON, timer * 20, 1));
                break;
            case 2: // slowness
                target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, timer * 20, 0));
                break;
            case 3: // fire
                target.setFire(timer);
                break;
            case 4: // weakness, nausea for players
                target.addPotionEffect(new PotionEffect(target instanceof EntityPlayer ? MobEffects.NAUSEA : MobEffects.WEAKNESS, timer * 20, 0));
                break;
            case 5: // blindness (unused)
                target.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, timer * 20, 0));
                break;
            default:
                break;
        }

        stack.damageItem(i, attacker);
        return true;
    }

    public boolean onBlockDestroyed(ItemStack par1ItemStack, int par2, int par3, int par4, int par5, EntityLiving par6EntityLiving) {
        par1ItemStack.damageItem(2, par6EntityLiving);
        return true;
    }

    /**
     * Returns True is the item is renderer in full 3D when held.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public boolean isFull3D() {
        return true;
    }

    /**
     * returns the action that specifies what animation to play when the items
     * are being used
     */
    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.BLOCK;
    }

    /**
     * How long it takes to use or consume an item
     */
    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 72000;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is
     * pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {
        final ItemStack stack = player.getHeldItem(hand);
        player.setActiveHand(hand);
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }

    /**
     * Returns if the item (tool) can harvest results from the block type.
     */
    @Override
    public boolean canHarvestBlock(IBlockState state) {
        return state.getBlock() instanceof BlockWeb;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based
     * on material.
     */
    @Override
    public int getItemEnchantability() {
        return this.material.getEnchantability();
    }

    /**
     * Called when a Block is destroyed using this Item. Return true to trigger the "Use Item" statistic.
     */
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase playerIn) {
        if ((double) state.getBlockHardness(worldIn, pos) != 0.0D) {
            stack.damageItem(2, playerIn);
        }

        return true;
    }

    /**
     * Return the name for this tool's material.
     */
    public String getToolMaterialName() {
        return this.material.toString();
    }

    /**
     * Return whether this item is repairable in an anvil.
     *
     * @param toRepair The ItemStack to be repaired
     * @param repair   The ItemStack that should repair this Item (leather for leather armor, etc.)
     */
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        ItemStack mat = this.material.getRepairItemStack();
        if (OreDictionary.itemMatches(mat, repair, false)) return true;
        return super.getIsRepairable(toRepair, repair);
    }

    /**
     * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
     */
    @SuppressWarnings("deprecation")
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);
        if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.attackDamage, 0));
        }
        return multimap;
    }
}
