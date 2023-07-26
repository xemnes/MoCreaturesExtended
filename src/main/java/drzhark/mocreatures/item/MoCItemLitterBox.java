/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.item;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.entity.item.MoCEntityKittyBed;
import drzhark.mocreatures.entity.item.MoCEntityLitterBox;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class MoCItemLitterBox extends MoCItem {

    public MoCItemLitterBox(String name) {
        super(name);
        this.maxStackSize = 16;
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
                    MoCEntityLitterBox litterBox = new MoCEntityLitterBox(world);
                    litterBox.setPosition((double) blockpos.getX() + 0.35, (double) blockpos.getY() + 1.0, (double) blockpos.getZ() + 0.65);

                    if (world.getBlockState(blockpos).getBlock() instanceof BlockLiquid || world.getBlockState(blockpos).getBlock() instanceof BlockBush) {
                        return new ActionResult(EnumActionResult.FAIL, stack);
                    } else world.spawnEntity(litterBox);
                    MoCTools.playCustomSound(litterBox, SoundEvents.BLOCK_WOOD_PLACE);

                } else {
                    return new ActionResult(EnumActionResult.FAIL, stack);
                }
            } else {
                return new ActionResult(EnumActionResult.PASS, stack);
            }
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }
}
