/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.client.renderer.entity;

import drzhark.mocreatures.client.MoCClientProxy;
import drzhark.mocreatures.client.model.MoCModelNewHorseMob;
import drzhark.mocreatures.entity.hostile.MoCEntityHorseMob;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MoCRenderHorseMob extends RenderLiving<MoCEntityHorseMob> {

    public MoCRenderHorseMob(MoCModelNewHorseMob modelbase) {
        super(MoCClientProxy.mc.getRenderManager(), modelbase, 0.5F);

    }

    protected void adjustHeight(MoCEntityHorseMob entityhorsemob, float FHeight) {
        GlStateManager.translate(0.0F, FHeight, 0.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(MoCEntityHorseMob entityhorsemob) {
        return entityhorsemob.getTexture();
    }
}
