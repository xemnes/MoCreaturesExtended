/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.client.renderer.entity;

import drzhark.mocreatures.entity.ambient.MoCEntityCricket;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MoCRenderCricket extends MoCRenderMoC<MoCEntityCricket> {

    public MoCRenderCricket(ModelBase modelbase) {
        super(modelbase, 0.0F);
    }

    @Override
    protected void preRenderCallback(MoCEntityCricket entitycricket, float par2) {
        rotateCricket(entitycricket);
    }

    protected void rotateCricket(MoCEntityCricket entitycricket) {
        if (!entitycricket.onGround) {
            if (entitycricket.motionY > 0.5D) {
                GlStateManager.rotate(35F, -1F, 0.0F, 0.0F);
            } else if (entitycricket.motionY < -0.5D) {
                GlStateManager.rotate(-35F, -1F, 0.0F, 0.0F);
            } else {
                GlStateManager.rotate((float) (entitycricket.motionY * 70D), -1F, 0.0F, 0.0F);
            }
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(MoCEntityCricket par1Entity) {
        return par1Entity.getTexture();
    }
}
