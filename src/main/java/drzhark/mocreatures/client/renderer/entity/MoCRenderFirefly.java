/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.client.renderer.entity;

import drzhark.mocreatures.entity.ambient.MoCEntityFirefly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MoCRenderFirefly extends MoCRenderInsect<MoCEntityFirefly> {

    public MoCRenderFirefly(ModelBase modelbase) {
        super(modelbase);
        //this.addLayer(new LayerMoCFirefly(this));
    }

    @Override
    protected void preRenderCallback(MoCEntityFirefly entityfirefly, float par2) {
        if (entityfirefly.getIsFlying()) {
            rotateFirefly(entityfirefly);
        } else if (entityfirefly.climbing()) {
            rotateAnimal(entityfirefly);
        }

    }

    protected void rotateFirefly(MoCEntityFirefly entityfirefly) {
        GlStateManager.rotate(40F, -1F, 0.0F, 0.0F);

    }

    @Override
    protected ResourceLocation getEntityTexture(MoCEntityFirefly entityfirefly) {
        return entityfirefly.getTexture();
    }
}
