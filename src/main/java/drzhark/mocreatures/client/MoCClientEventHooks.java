/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.client;

import drzhark.mocreatures.entity.hostile.MoCEntityScorpion;
import drzhark.mocreatures.entity.hunter.MoCEntityPetScorpion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Type;

public class MoCClientEventHooks {

    boolean inMenu;
    int lastTickRun;

    private void onTickInGame() {
    }

    private void onTickInGui(GuiScreen curScreen) {
        // handle GUI tick stuff here
        this.inMenu = true;
        this.lastTickRun = 0;
    }

    @SubscribeEvent
    public void tickEnd(ClientTickEvent event) {
        if (event.type.equals(Type.CLIENT)) {
            GuiScreen curScreen = Minecraft.getMinecraft().currentScreen;
            if (curScreen != null) {
                onTickInGui(curScreen);
            } else {
                this.inMenu = false;
                onTickInGame();
            }
        }
    }

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
            GlStateManager.popMatrix();
        }
    }
}
