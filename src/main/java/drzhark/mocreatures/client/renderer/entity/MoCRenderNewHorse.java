/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.client.renderer.entity;

import drzhark.mocreatures.client.model.MoCModelNewHorse;
import drzhark.mocreatures.entity.passive.MoCEntityHorse;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MoCRenderNewHorse extends MoCRenderMoC<MoCEntityHorse> {

    public MoCRenderNewHorse(MoCModelNewHorse modelbase) {
        super(modelbase, 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(MoCEntityHorse entityhorse) {
        return entityhorse.getTexture();
    }

    protected void adjustHeight(MoCEntityHorse entityhorse, float FHeight) {
        GlStateManager.translate(0.0F, FHeight, 0.0F);
    }

    @Override
    protected void preRenderCallback(MoCEntityHorse entityhorse, float f) {
        if (!entityhorse.getIsAdult() || entityhorse.getType() > 64) {
            stretch(entityhorse);
        }
        if (entityhorse.getIsGhost()) {
            adjustHeight(entityhorse, -0.3F + (entityhorse.tFloat() / 5F));
        }
        super.preRenderCallback(entityhorse, f);
    }

    protected void stretch(MoCEntityHorse entityhorse) {
        float sizeFactor = entityhorse.getEdad() * 0.01F;
        if (entityhorse.getIsAdult()) {
            sizeFactor = 1.0F;
        }
        if (entityhorse.getType() > 64) //donkey
        {
            sizeFactor *= 0.9F;
        }
        GlStateManager.scale(sizeFactor, sizeFactor, sizeFactor);
    }
}
