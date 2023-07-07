/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.client.renderer.entity;

import drzhark.mocreatures.client.model.MoCModelScorpion;
import drzhark.mocreatures.entity.hostile.MoCEntityScorpion;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MoCRenderScorpion extends MoCRenderMoC<MoCEntityScorpion> {

    public MoCRenderScorpion(MoCModelScorpion modelbase, float f) {
        super(modelbase, f);
    }

    @Override
    public void doRender(MoCEntityScorpion entityscorpion, double d, double d1, double d2, float f, float f1) {
        super.doRender(entityscorpion, d, d1, d2, f, f1);
    }
    
    @Override
    protected float getDeathMaxRotation(MoCEntityScorpion entityscorpion) {
        return 180.0F;
    }

    @Override
    protected void preRenderCallback(MoCEntityScorpion entityscorpion, float f) {
        // Looks awkward when the scorpion has a passenger
        /*if (entityscorpion.isOnLadder()) {
            rotateAnimal(entityscorpion);
        }*/

        if (!entityscorpion.getIsAdult()) {
            stretch(entityscorpion);
        } else {
            adjustHeight(entityscorpion);
        }
    }

    protected void adjustHeight(MoCEntityScorpion entityscorpion) {
        GlStateManager.translate(0.0F, -0.1F, 0.0F);
    }

    protected void rotateAnimal(MoCEntityScorpion entityscorpion) {
        GlStateManager.rotate(90F, -1F, 0.0F, 0.0F);
    }

    protected void stretch(MoCEntityScorpion entityscorpion) {

        float f = 1.1F;
        if (!entityscorpion.getIsAdult()) {
            f = entityscorpion.getAge() * 0.01F;
        }
        GlStateManager.scale(f, f, f);
    }

    @Override
    protected ResourceLocation getEntityTexture(MoCEntityScorpion entityscorpion) {
        return entityscorpion.getTexture();
    }
}
