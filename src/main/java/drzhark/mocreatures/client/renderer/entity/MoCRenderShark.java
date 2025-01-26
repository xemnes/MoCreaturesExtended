/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.client.renderer.entity;

import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.aquatic.MoCEntityShark;
import drzhark.mocreatures.proxy.MoCProxyClient;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MoCRenderShark extends RenderLiving<MoCEntityShark> {

    public MoCRenderShark(ModelBase modelbase, float f) {
        super(MoCProxyClient.mc.getRenderManager(), modelbase, f);
    }

    @Override
    public void doRender(MoCEntityShark entityshark, double d, double d1, double d2, float f, float f1) {
        super.doRender(entityshark, d, d1, d2, f, f1);
        boolean flag = MoCreatures.proxy.getDisplayPetName() && !(entityshark.getPetName().isEmpty());
        boolean flag1 = MoCreatures.proxy.getDisplayPetHealth();
        if (entityshark.shouldRenderNameAndHealth()) {
            float f2 = 1.6F;
            float f3 = 0.01666667F * f2;
            float f4 = entityshark.getDistance(this.renderManager.renderViewEntity);
            if (f4 < 16F) {
                String s = "";
                s = s + entityshark.getPetName();
                float f5 = 0.1F;
                FontRenderer fontrenderer = getFontRendererFromRenderManager();
                GlStateManager.pushMatrix();
                GlStateManager.translate((float) d + 0.0F, (float) d1 + f5, (float) d2);
                GlStateManager.glNormal3f(0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
                GlStateManager.scale(-f3, -f3, f3);
                GlStateManager.disableLighting();
                Tessellator tessellator = Tessellator.getInstance();
                byte byte0 = -50;
                if (flag1) {
                    GlStateManager.disableTexture2D();
                    if (!flag) {
                        byte0 += 8;
                    }
                    tessellator.getBuffer().begin(7, DefaultVertexFormats.POSITION_COLOR);
                    // might break SSP
                    float f6 = entityshark.getHealth();
                    float f7 = entityshark.getMaxHealth();
                    float f8 = f6 / f7;
                    float f9 = 40F * f8;
                    tessellator.getBuffer().pos(-20F + f9, -10 + byte0, 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
                    tessellator.getBuffer().pos(-20F + f9, -6 + byte0, 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
                    tessellator.getBuffer().pos(20D, -6 + byte0, 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
                    tessellator.getBuffer().pos(20D, -10 + byte0, 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
                    tessellator.getBuffer().pos(-20D, -10 + byte0, 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
                    tessellator.getBuffer().pos(-20D, -6 + byte0, 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
                    tessellator.getBuffer().pos(f9 - 20F, -6 + byte0, 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
                    tessellator.getBuffer().pos(f9 - 20F, -10 + byte0, 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
                    tessellator.draw();
                    GlStateManager.enableTexture2D();
                }
                if (flag) {
                    GlStateManager.depthMask(false);
                    GlStateManager.disableDepth();
                    GlStateManager.enableBlend();
                    GlStateManager.blendFunc(770, 771);
                    GlStateManager.disableTexture2D();
                    tessellator.getBuffer().begin(7, DefaultVertexFormats.POSITION_COLOR);
                    int i = fontrenderer.getStringWidth(s) / 2;
                    tessellator.getBuffer().pos(-i - 1, -1 + byte0, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
                    tessellator.getBuffer().pos(-i - 1, 8 + byte0, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
                    tessellator.getBuffer().pos(i + 1, 8 + byte0, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
                    tessellator.getBuffer().pos(i + 1, -1 + byte0, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
                    tessellator.draw();
                    GlStateManager.enableTexture2D();
                    fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, byte0, 0x20ffffff);
                    GlStateManager.enableDepth();
                    GlStateManager.depthMask(true);
                    fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, byte0, -1);
                    GlStateManager.disableBlend();
                    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                }
                GlStateManager.enableLighting();
                GlStateManager.popMatrix();
            }
        }
    }

    @Override
    protected float handleRotationFloat(MoCEntityShark entityshark, float f) {
        stretch(entityshark);
        return entityshark.ticksExisted + f;
    }

    protected void stretch(MoCEntityShark entityshark) {
        GlStateManager.scale((entityshark.getAge() * 0.01F) / 2, (entityshark.getAge() * 0.01F) / 2, (entityshark.getAge() * 0.01F) / 2);
    }

    @Override
    protected ResourceLocation getEntityTexture(MoCEntityShark entityshark) {
        return entityshark.getTexture();
    }
}
