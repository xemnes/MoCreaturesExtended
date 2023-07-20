/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MoCModelRat extends ModelBase {

    public ModelRenderer Head;

    public ModelRenderer EarR;

    public ModelRenderer EarL;

    public ModelRenderer WhiskerR;

    public ModelRenderer WhiskerL;

    public ModelRenderer Body;

    public ModelRenderer Tail;

    public ModelRenderer FrontL;

    public ModelRenderer FrontR;

    public ModelRenderer RearL;

    public ModelRenderer RearR;

    public ModelRenderer BodyF;

    public MoCModelRat() {
        Head = new ModelRenderer(this);
        Head.setRotationPoint(0.0F, 18.0F, -9.0F);
        Head.cubeList.add(new ModelBox(Head, 0, 0, -1.5F, 0.0F, -6.0F, 3, 4, 6, 0.0F, false));

        EarR = new ModelRenderer(this);
        EarR.setRotationPoint(0.0F, 18.0F, -9.0F);
        EarR.cubeList.add(new ModelBox(EarR, 16, 26, -3.5F, -2.0F, -2.0F, 3, 3, 1, 0.0F, false));

        EarL = new ModelRenderer(this);
        EarL.setRotationPoint(0.0F, 18.0F, -9.0F);
        EarL.cubeList.add(new ModelBox(EarL, 24, 26, 0.5F, -2.0F, -2.0F, 3, 3, 1, 0.0F, false));

        WhiskerR = new ModelRenderer(this);
        WhiskerR.setRotationPoint(0.0F, 18.0F, -9.0F);
        WhiskerR.cubeList.add(new ModelBox(WhiskerR, 24, 16, -4.5F, 0.0F, -6.0F, 3, 3, 1, 0.0F, false));

        WhiskerL = new ModelRenderer(this);
        WhiskerL.setRotationPoint(0.0F, 18.0F, -9.0F);
        WhiskerL.cubeList.add(new ModelBox(WhiskerL, 24, 20, 1.5F, 0.0F, -6.0F, 3, 3, 1, 0.0F, false));

        Body = new ModelRenderer(this);
        Body.setRotationPoint(0.0F, 19.0F, 0.0F);
        setRotationAngle(Body, 1.5708F, 0.0F, 0.0F);
        Body.cubeList.add(new ModelBox(Body, 24, 0, -4.0F, -3.0F, -4.0F, 8, 8, 8, 0.0F, false));

        Tail = new ModelRenderer(this);
        Tail.setRotationPoint(0.0F, 19.0F, 5.0F);
        setRotationAngle(Tail, 1.5708F, 0.0F, 0.0F);
        Tail.cubeList.add(new ModelBox(Tail, 56, 0, -1.0F, 0.0F, -2.0F, 2, 18, 2, 0.0F, false));

        FrontL = new ModelRenderer(this);
        FrontL.setRotationPoint(3.0F, 22.0F, -7.0F);
        FrontL.cubeList.add(new ModelBox(FrontL, 0, 18, -2.0F, 1.0F, -3.0F, 2, 1, 4, 0.0F, false));

        FrontR = new ModelRenderer(this);
        FrontR.setRotationPoint(-3.0F, 22.0F, -7.0F);
        FrontR.cubeList.add(new ModelBox(FrontR, 0, 18, 0.0F, 1.0F, -3.0F, 2, 1, 4, 0.0F, false));

        RearL = new ModelRenderer(this);
        RearL.setRotationPoint(4.0F, 22.0F, 2.0F);
        RearL.cubeList.add(new ModelBox(RearL, 0, 24, -2.0F, 1.0F, -4.0F, 2, 1, 5, 0.0F, false));

        RearR = new ModelRenderer(this);
        RearR.setRotationPoint(-4.0F, 22.0F, 2.0F);
        RearR.cubeList.add(new ModelBox(RearR, 0, 24, 0.0F, 1.0F, -4.0F, 2, 1, 5, 0.0F, false));

        BodyF = new ModelRenderer(this);
        BodyF.setRotationPoint(0.0F, 19.0F, -2.0F);
        BodyF.cubeList.add(new ModelBox(BodyF, 32, 16, -3.0F, -2.0F, -7.0F, 6, 6, 6, 0.0F, false));
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5);
        this.Head.render(f5);
        this.EarR.render(f5);
        this.EarL.render(f5);
        this.WhiskerR.render(f5);
        this.WhiskerL.render(f5);
        this.Body.render(f5);
        this.Tail.render(f5);
        this.FrontL.render(f5);
        this.FrontR.render(f5);
        this.RearL.render(f5);
        this.RearR.render(f5);
        this.BodyF.render(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
        this.Head.rotateAngleX = -(f4 / 57.29578F);
        this.Head.rotateAngleY = f3 / 57.29578F;
        this.EarR.rotateAngleX = this.Head.rotateAngleX;
        this.EarR.rotateAngleY = this.Head.rotateAngleY;
        this.EarL.rotateAngleX = this.Head.rotateAngleX;
        this.EarL.rotateAngleY = this.Head.rotateAngleY;
        this.WhiskerR.rotateAngleX = this.Head.rotateAngleX;
        this.WhiskerR.rotateAngleY = this.Head.rotateAngleY;
        this.WhiskerL.rotateAngleX = this.Head.rotateAngleX;
        this.WhiskerL.rotateAngleY = this.Head.rotateAngleY;
        this.FrontL.rotateAngleX = MathHelper.cos(f * 0.6662F) * 0.6F * f1;
        this.RearL.rotateAngleX = MathHelper.cos((f * 0.6662F) + 3.141593F) * 0.8F * f1;
        this.RearR.rotateAngleX = MathHelper.cos(f * 0.6662F) * 0.6F * f1;
        this.FrontR.rotateAngleX = MathHelper.cos((f * 0.6662F) + 3.141593F) * 0.8F * f1;
        this.Tail.rotateAngleY = this.FrontL.rotateAngleX * 0.625F;
    }
}
