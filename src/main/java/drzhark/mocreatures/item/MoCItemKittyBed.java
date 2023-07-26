/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.item;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.entity.item.MoCEntityEgg;
import drzhark.mocreatures.entity.item.MoCEntityKittyBed;
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

public class MoCItemKittyBed extends MoCItem {

    private int sheetType;

    public MoCItemKittyBed(String name) {
        super(name);
    }

    public MoCItemKittyBed(String name, int type) {
        this(name);
        this.sheetType = type;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        final ItemStack stack = player.getHeldItem(hand);
//        if (!world.isRemote) {
//            if (!player.capabilities.isCreativeMode) stack.shrink(1);
//            MoCEntityKittyBed kittyBed = new MoCEntityKittyBed(world, this.sheetType);
//            kittyBed.setPosition(player.posX, player.posY, player.posZ);
//            world.spawnEntity(kittyBed);
//            MoCTools.playCustomSound(kittyBed, SoundEvents.BLOCK_WOOD_PLACE);
//        }


        if (world.isRemote) {
            return new ActionResult(EnumActionResult.PASS, stack);
        } else {
            RayTraceResult raytraceresult = this.rayTrace(world, player, true);
            if (raytraceresult != null && raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK) {
                BlockPos blockpos = raytraceresult.getBlockPos();
                if (world.isBlockModifiable(player, blockpos) && player.canPlayerEdit(blockpos, raytraceresult.sideHit, stack)) {
                    if (!player.capabilities.isCreativeMode) stack.shrink(1);
                    MoCEntityKittyBed kittyBed = new MoCEntityKittyBed(world, this.sheetType);
                    kittyBed.setPosition((double) blockpos.getX() + 0.35, (double) blockpos.getY() + 1.0, (double) blockpos.getZ() + 0.65);

                    if (world.getBlockState(blockpos).getBlock() instanceof BlockLiquid || world.getBlockState(blockpos).getBlock() instanceof BlockBush) {
                        return new ActionResult(EnumActionResult.FAIL, stack);
                    } else world.spawnEntity(kittyBed);
                    MoCTools.playCustomSound(kittyBed, SoundEvents.BLOCK_WOOD_PLACE);

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
