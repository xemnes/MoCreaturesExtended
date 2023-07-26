/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.item;

import drzhark.mocreatures.entity.aquatic.MoCEntityFishy;
import drzhark.mocreatures.entity.aquatic.MoCEntityMediumFish;
import drzhark.mocreatures.entity.aquatic.MoCEntitySmallFish;
import drzhark.mocreatures.entity.item.MoCEntityEgg;
import net.minecraft.block.BlockLiquid;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MoCItemEgg extends MoCItem {

    public MoCItemEgg(String name) {
        super(name);
        this.maxStackSize = 16;
        setHasSubtypes(true);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        final ItemStack stack = player.getHeldItem(hand);

        if (world.isRemote) {
            return new ActionResult(EnumActionResult.PASS, stack);
        } else {
            RayTraceResult raytraceresult = this.rayTrace(world, player, true);
            if (raytraceresult != null && raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK) {
                BlockPos blockpos = raytraceresult.getBlockPos();
                if (world.isBlockModifiable(player, blockpos) && player.canPlayerEdit(blockpos, raytraceresult.sideHit, stack)) {
                    if (!player.capabilities.isCreativeMode) stack.shrink(1);
                    int i = stack.getItemDamage();
                    if (i == 30) {
                        i = 31; //for ostrich eggs. placed eggs become stolen eggs.
                    }
                    MoCEntityEgg entityegg = new MoCEntityEgg(world, i);
                    if (world.getBlockState(blockpos).getBlock() instanceof BlockLiquid) {
                        entityegg.setPosition((double) blockpos.getX() + 0.35, (double) blockpos.getY(), (double) blockpos.getZ() + 0.65);
                    } else entityegg.setPosition((double) blockpos.getX() + 0.35, (double) blockpos.getY() + 1.0, (double) blockpos.getZ() + 0.65);

                    player.world.spawnEntity(entityegg);
                    entityegg.motionY += world.rand.nextFloat() * 0.05F;
                    entityegg.motionX += (world.rand.nextFloat() - world.rand.nextFloat()) * 0.3F;
                    entityegg.motionZ += (world.rand.nextFloat() - world.rand.nextFloat()) * 0.3F;

                } else {
                    return new ActionResult(EnumActionResult.FAIL, stack);
                }
            } else {
                return new ActionResult(EnumActionResult.PASS, stack);
            }

        }
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (!this.isInCreativeTab(tab)) {
            return;
        }

        // Fish eggs
        int length = MoCEntityFishy.fishNames.length;
        for (int i = 0; i < length; i++) { // fishy
            items.add(new ItemStack(this, 1, i));
        }
        length = 80 + MoCEntitySmallFish.fishNames.length;
        for (int i = 80; i < length; i++) { // small fish
            items.add(new ItemStack(this, 1, i));
        }
        length = 70 + MoCEntityMediumFish.fishNames.length;
        for (int i = 70; i < length; i++) { // medium fish
            items.add(new ItemStack(this, 1, i));
        }
        items.add(new ItemStack(this, 1, 90)); // piranha
        items.add(new ItemStack(this, 1, 11)); // shark
        for (int i = 21; i < 28; i++) { // snakes
            items.add(new ItemStack(this, 1, i));
        }
        items.add(new ItemStack(this, 1, 30)); // ostrich
        items.add(new ItemStack(this, 1, 31)); // stolen ostrich egg
        items.add(new ItemStack(this, 1, 33)); // komodo
        for (int i = 41; i < 46; i++) { // scorpions
            items.add(new ItemStack(this, 1, i));
        }
        for (int i = 50; i < 67; i++) { // wyverns, manticores
            items.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public String getTranslationKey(ItemStack itemstack) {
        return getTranslationKey() + "." + itemstack.getItemDamage();
    }
}
