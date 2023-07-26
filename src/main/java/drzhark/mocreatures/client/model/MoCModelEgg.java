/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MoCModelEgg extends ModelBase {

    public ModelRenderer Egg;
    ModelRenderer Egg1;
    ModelRenderer Egg2;
    ModelRenderer Egg3;
    ModelRenderer Egg4;
    ModelRenderer Egg5;

    public MoCModelEgg() {

        Egg1 = new ModelRenderer(this);
        Egg1.setRotationPoint(0.0F, 20.0F, 0.0F);
        Egg1.cubeList.add(new ModelBox(Egg1, 0, 4, 0.0F, 0.0F, 0.0F, 4, 3, 4, 0.0F, false));

        Egg2 = new ModelRenderer(this);
        Egg2.setRotationPoint(0.5F, 19.5F, 0.5F);
        Egg2.cubeList.add(new ModelBox(Egg2, 2, 3, 0.0F, -1.5F, 0.0F, 3, 6, 3, 0.0F, false));

        Egg3 = new ModelRenderer(this);
        Egg3.setRotationPoint(0.5F, 22.5F, 0.5F);
        Egg3.cubeList.add(new ModelBox(Egg3, 4, 2, 0.5F, -5.5F, 0.5F, 2, 1, 2, 0.0F, false));

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5);
        this.Egg1.render(f5);
        this.Egg2.render(f5);
        this.Egg3.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
        //super.setRotationAngles(f, f1, f2, f3, f4, f5);
    }
}
