/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.event;

import drzhark.mocreatures.compat.CompatScreen;
import drzhark.mocreatures.item.MoCItemBow;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MoCEventHooksClient {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void displayCompatScreen(GuiOpenEvent event) {
        if (event.getGui() instanceof GuiMainMenu && CompatScreen.showScreen && Loader.isModLoaded("customspawner")) {
            event.setGui(new CompatScreen());
            CompatScreen.showScreen = false;
        }
    }

    // Courtesy of NeRdTheNed
    @SubscribeEvent
    public void FOV(FOVUpdateEvent event) {
        final EntityPlayer eventPlayer = event.getEntity();
        final Item eventItem = eventPlayer.getActiveItemStack().getItem();

        if (eventItem instanceof MoCItemBow) {
            float finalFov = event.getFov();
            final float itemUseCount = ((MoCItemBow) eventItem).getMaxItemUseDuration(eventPlayer.getActiveItemStack()) - eventPlayer.getItemInUseCount();
            /*
             * First, we have to reverse the standard bow zoom.
             * Minecraft helpfully applies the standard bow zoom
             * to any item that is an instance of a ItemBow.
             * However, our custom bows draw back at different speeds,
             * so the standard zoom is not at the right speed.
             * To compensate for this, we just calculate the standard bow zoom,
             * and apply it in reverse.
             */
            float realBow = itemUseCount / 20.0F;

            if (realBow > 1.0F) {
                realBow = 1.0F;
            } else {
                realBow *= realBow;
            }

            /*
             * Minecraft uses finalFov *= 1.0F - (realBow * 0.15F)
             * to calculate the standard bow zoom.
             * To reverse this, we just divide it instead.
             */
            finalFov /= 1.0F - (realBow * 0.15F);
            /*
             * We now calculate and apply our custom bow zoom.
             * The only difference between standard bow zoom and custom bow zoom
             * is that we change the hardcoded value of 20.0F to
             * whatever drawTime is.
             */
            float drawTime = 20.0F * ((MoCItemBow) eventItem).drawTimeMult;
            float customBow = itemUseCount / drawTime;

            if (customBow > 1.0F) {
                customBow = 1.0F;
            } else {
                customBow *= customBow;
            }

            finalFov *= 1.0F - (customBow * 0.15F);
            event.setNewfov(finalFov);
        }
    }

    /* TODO: Fix rider rotation
    @SubscribeEvent
    public void renderClimbingRiderPre(RenderLivingEvent.Pre<EntityLivingBase> event) {
        EntityLivingBase rider = event.getEntity();
        Entity mount = rider.getRidingEntity();
        if (mount instanceof MoCEntityScorpion || mount instanceof MoCEntityPetScorpion) {
            if (((EntityLivingBase) rider.getRidingEntity()).isOnLadder()) {
                EnumFacing facing = rider.getHorizontalFacing();
                GlStateManager.pushMatrix();
                if (facing == EnumFacing.NORTH) {
                    GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                } else if (facing == EnumFacing.WEST) {
                    GlStateManager.rotate(90.0F, 0.0F, 0.0F, -1.0F);
                } else if (facing == EnumFacing.SOUTH) {
                    GlStateManager.rotate(90.0F, -1.0F, 0.0F, 0.0F);
                } else if (facing == EnumFacing.EAST) {
                    GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                }
                GlStateManager.translate(0.0F, -1.0F, 0.0F);
            }
        }
    }

    @SubscribeEvent
    public void renderClimbingRiderPost(RenderLivingEvent.Post<EntityLivingBase> event) {
        EntityLivingBase rider = event.getEntity();
        Entity mount = rider.getRidingEntity();
        if (mount instanceof MoCEntityScorpion || mount instanceof MoCEntityPetScorpion) {
            if (((EntityLivingBase) rider.getRidingEntity()).isOnLadder()) {
                EnumFacing facing = rider.getHorizontalFacing();
                GlStateManager.translate(0.0F, 1.0F, 0.0F);
                if (facing == EnumFacing.NORTH) {
                    GlStateManager.rotate(90.0F, -1.0F, 0.0F, 0.0F);
                } else if (facing == EnumFacing.WEST) {
                    GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                } else if (facing == EnumFacing.SOUTH) {
                    GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                } else if (facing == EnumFacing.EAST) {
                    GlStateManager.rotate(90.0F, 0.0F, 0.0F, -1.0F);
                }
                GlStateManager.popMatrix();
            }
        }
    }
    */
}
