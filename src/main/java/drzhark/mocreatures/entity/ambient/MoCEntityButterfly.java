/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.entity.ambient;

import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.MoCEntityInsect;
import net.minecraft.entity.ai.EntityAIFollow;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class MoCEntityButterfly extends MoCEntityInsect {

    private int fCounter;

    public MoCEntityButterfly(World world) {
        super(world);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(3, new EntityAIFollow(this, 1.0D, 14.0F, 28.0F));
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
    }

    @Override
    public void selectType() {
        if (getType() == 0) {
            setType(this.rand.nextInt(10) + 1);
        }
    }

    @Override
    public ResourceLocation getTexture() {
        switch (getType()) {
            case 1:
                return MoCreatures.proxy.getTexture("butterfly_agalais_urticae.png");
            case 2:
                return MoCreatures.proxy.getTexture("butterfly_argyreus_hyperbius.png");
            case 3:
                return MoCreatures.proxy.getTexture("butterfly_athyma_nefte.png");
            case 4:
                return MoCreatures.proxy.getTexture("butterfly_catopsilia_pomona.png");
            case 5:
                return MoCreatures.proxy.getTexture("butterfly_morpho_peleides.png");
            case 6:
                return MoCreatures.proxy.getTexture("butterfly_vanessa_atalanta.png");
            case 8:
                return MoCreatures.proxy.getTexture("moth_camptogramma_bilineata.png");
            case 9:
                return MoCreatures.proxy.getTexture("moth_idia_aemula.png");
            case 10:
                return MoCreatures.proxy.getTexture("moth_thyatira_batis.png");
            default:
                return MoCreatures.proxy.getTexture("butterfly_pieris_rapae.png");
        }
    }

    public float tFloat() {
        if (!getIsFlying()) {
            return 0F;
        }
        if (++this.fCounter > 1000) {
            this.fCounter = 0;
        }

        return MathHelper.cos((this.fCounter * 0.1F)) * 0.2F;
    }

    @Override
    public float getSizeFactor() {
        if (getType() < 8) {
            return 0.7F;
        }
        return 1.0F;
    }

    @Override
    public boolean isMyFavoriteFood(ItemStack stack) {
        return !stack.isEmpty() && (stack.getItem() == Item.getItemFromBlock(Blocks.RED_FLOWER) || stack.getItem() == Item.getItemFromBlock(Blocks.YELLOW_FLOWER));
    }

    @Override
    public boolean isAttractedToLight() {
        return getType() > 7;
    }

    @Override
    public boolean isFlyer() {
        return true;
    }

    @Override
    public float getAIMoveSpeed() {
        if (getIsFlying()) {
            return 0.13F;
        }
        return 0.10F;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return null;
    }

    @Override
    public float getEyeHeight() {
        return 0.1F;
    }
}
