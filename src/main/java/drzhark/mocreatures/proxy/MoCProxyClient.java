/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.proxy;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.client.gui.MoCGUIEntityNamer;
import drzhark.mocreatures.client.model.*;
import drzhark.mocreatures.client.model.legacy.MoCLegacyModelBigCat1;
import drzhark.mocreatures.client.model.legacy.MoCLegacyModelBigCat2;
import drzhark.mocreatures.client.model.legacy.MoCLegacyModelShark;
import drzhark.mocreatures.client.renderer.entity.*;
import drzhark.mocreatures.client.renderer.entity.legacy.MoCLegacyRenderBigCat;
import drzhark.mocreatures.client.renderer.entity.legacy.MoCLegacyRenderShark;
import drzhark.mocreatures.client.renderer.fx.MoCEntityFXStar;
import drzhark.mocreatures.client.renderer.fx.MoCEntityFXUndead;
import drzhark.mocreatures.client.renderer.fx.MoCEntityFXVacuum;
import drzhark.mocreatures.client.renderer.fx.MoCEntityFXVanish;
import drzhark.mocreatures.client.renderer.texture.MoCTextures;
import drzhark.mocreatures.entity.IMoCEntity;
import drzhark.mocreatures.entity.ambient.*;
import drzhark.mocreatures.entity.aquatic.*;
import drzhark.mocreatures.entity.hostile.*;
import drzhark.mocreatures.entity.hunter.MoCEntitySnake;
import drzhark.mocreatures.entity.hunter.*;
import drzhark.mocreatures.entity.item.MoCEntityEgg;
import drzhark.mocreatures.entity.item.MoCEntityKittyBed;
import drzhark.mocreatures.entity.item.MoCEntityLitterBox;
import drzhark.mocreatures.entity.item.MoCEntityThrowableRock;
import drzhark.mocreatures.entity.neutral.MoCEntityBoar;
import drzhark.mocreatures.entity.neutral.*;
import drzhark.mocreatures.entity.passive.*;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class MoCProxyClient extends MoCProxy {

    public static Minecraft mc = Minecraft.getMinecraft();
    public static MoCProxyClient instance;
    public static MoCTextures mocTextures = new MoCTextures();

    public MoCProxyClient() {
        instance = this;
    }

    @Override
    public void registerRenderers() {
        super.registerRenderers();
    }

    @Override
    public ResourceLocation getArmorTexture(String texture) {
        return mocTextures.getArmorTexture(texture);
    }

    @Override
    public ResourceLocation getBlockTexture(String texture) {
        return mocTextures.getBlockTexture(texture);
    }

    @Override
    public ResourceLocation getItemTexture(String texture) {
        return mocTextures.getItemTexture(texture);
    }

    @Override
    public ResourceLocation getModelTexture(String texture) {
        return mocTextures.getModelTexture(texture);
    }

    @Override
    public ResourceLocation getGuiTexture(String texture) {
        return mocTextures.getGuiTexture(texture);
    }

    @Override
    public ResourceLocation getMiscTexture(String texture) {
        return mocTextures.getMiscTexture(texture);
    }

    @SuppressWarnings({"deprecation", "unchecked", "rawtypes"})
    @Override
    public void registerRenderInformation() {
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityBunny.class, new MoCRenderBunny(new MoCModelBunny(), 0.3F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityBird.class, new MoCRenderBird(new MoCModelBird(), 0.3F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityTurtle.class, new MoCRenderTurtle(new MoCModelTurtle(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityMouse.class, new MoCRenderMouse(new MoCModelMouse(), 0.1F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntitySnake.class, new MoCRenderSnake(new MoCModelSnake(), 0.0F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityTurkey.class, new MoCRenderMoC(new MoCModelTurkey(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityButterfly.class, new MoCRenderButterfly(new MoCModelButterfly()));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityHorse.class, new MoCRenderHorse(new MoCModelHorse()));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityHorseMob.class, new MoCRenderHorseMob(new MoCModelHorseMob()));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityBoar.class, new MoCRenderMoC(new MoCModelBoar(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityBlackBear.class, new MoCRenderMoC(new MoCModelBear(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityGrizzlyBear.class, new MoCRenderMoC(new MoCModelBear(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityPandaBear.class, new MoCRenderMoC(new MoCModelBear(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityPolarBear.class, new MoCRenderMoC(new MoCModelBear(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityDuck.class, new MoCRenderMoC(new MoCModelDuck(), 0.3F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityDeer.class, new MoCRenderMoC(new MoCModelDeer(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityWWolf.class, new MoCRenderWWolf(new MoCModelWolf(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityWraith.class, new MoCRenderWraith(new MoCModelWraith(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityFlameWraith.class, new MoCRenderWraith(new MoCModelWraith(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityWerewolf.class, new MoCRenderWerewolf(new MoCModelWerehuman(), new MoCModelWerewolf(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityFilchLizard.class, new MoCRenderFilchLizard(new MoCModelFilchLizard(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityFox.class, new MoCRenderMoC(new MoCModelFox(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityDolphin.class, new MoCRenderDolphin(new MoCModelDolphin(), 0.6F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityFishy.class, new MoCRenderMoC(new MoCModelFishy(), 0.1F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityEgg.class, new MoCRenderEgg(new MoCModelEgg(), 0.0F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityKitty.class, new MoCRenderKitty(new MoCModelKitty(0.0F, 15F), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityKittyBed.class, new MoCRenderKittyBed(new MoCModelKittyBed(), new MoCModelKittyBed2(), 0.3F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityLitterBox.class, new MoCRenderLitterBox(new MoCModelLitterBox(), 0.3F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityRat.class, new MoCRenderRat(new MoCModelRat(), 0.2F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityHellRat.class, new MoCRenderHellRat(new MoCModelRat(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityCaveScorpion.class, new MoCRenderScorpion(new MoCModelScorpion(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityDirtScorpion.class, new MoCRenderScorpion(new MoCModelScorpion(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityFireScorpion.class, new MoCRenderScorpion(new MoCModelScorpion(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityFrostScorpion.class, new MoCRenderScorpion(new MoCModelScorpion(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityUndeadScorpion.class, new MoCRenderScorpion(new MoCModelScorpion(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityCrocodile.class, new MoCRenderCrocodile(new MoCModelCrocodile(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityMantaRay.class, new MoCRenderMoC(new MoCModelRay(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityStingRay.class, new MoCRenderMoC(new MoCModelRay(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityJellyFish.class, new MoCRenderMoC(new MoCModelJellyFish(), 0.1F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityGoat.class, new MoCRenderGoat(new MoCModelGoat(), 0.3F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityOstrich.class, new MoCRenderOstrich(new MoCModelOstrich(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityBee.class, new MoCRenderInsect(new MoCModelBee()));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityFly.class, new MoCRenderInsect(new MoCModelFly()));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityDragonfly.class, new MoCRenderInsect(new MoCModelDragonfly()));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityFirefly.class, new MoCRenderFirefly(new MoCModelFirefly()));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityCricket.class, new MoCRenderCricket(new MoCModelCricket()));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityGrasshopper.class, new MoCRenderGrasshopper(new MoCModelGrasshopper()));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntitySnail.class, new MoCRenderMoC(new MoCModelSnail(), 0.0F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityGolem.class, new MoCRenderGolem(new MoCModelGolem(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityThrowableRock.class, new MoCRenderTRock());
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityPetScorpion.class, new MoCRenderPetScorpion(new MoCModelPetScorpion(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityElephant.class, new MoCRenderMoC(new MoCModelElephant(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityKomodo.class, new MoCRenderMoC(new MoCModelKomodo(), 0.3F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityWyvern.class, new MoCRenderMoC(new MoCModelWyvern(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityGreenOgre.class, new MoCRenderMoC(new MoCModelOgre(), 0.6F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityCaveOgre.class, new MoCRenderMoC(new MoCModelOgre(), 0.6F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityFireOgre.class, new MoCRenderMoC(new MoCModelOgre(), 0.6F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityRoach.class, new MoCRenderInsect(new MoCModelRoach()));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityMaggot.class, new MoCRenderMoC(new MoCModelMaggot(), 0F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityCrab.class, new MoCRenderMoC(new MoCModelCrab(), 0.2F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityRaccoon.class, new MoCRenderMoC(new MoCModelRaccoon(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityMiniGolem.class, new MoCRenderMoC(new MoCModelMiniGolem(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntitySilverSkeleton.class, new MoCRenderMoC(new MoCModelSilverSkeleton(), 0.6F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityAnt.class, new MoCRenderMoC(new MoCModelAnt(), 0F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityCod.class, new MoCRenderMoC(new MoCModelMediumFish(), 0.2F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntitySalmon.class, new MoCRenderMoC(new MoCModelMediumFish(), 0.2F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityBass.class, new MoCRenderMoC(new MoCModelMediumFish(), 0.2F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityAnchovy.class, new MoCRenderMoC(new MoCModelSmallFish(), 0.1F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityAngelFish.class, new MoCRenderMoC(new MoCModelSmallFish(), 0.1F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityAngler.class, new MoCRenderMoC(new MoCModelSmallFish(), 0.1F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityClownFish.class, new MoCRenderMoC(new MoCModelSmallFish(), 0.1F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityGoldFish.class, new MoCRenderMoC(new MoCModelSmallFish(), 0.1F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityHippoTang.class, new MoCRenderMoC(new MoCModelSmallFish(), 0.1F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityManderin.class, new MoCRenderMoC(new MoCModelSmallFish(), 0.1F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityPiranha.class, new MoCRenderMoC(new MoCModelSmallFish(), 0.1F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityEnt.class, new MoCRenderMoC(new MoCModelEnt(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityMole.class, new MoCRenderMoC(new MoCModelMole(), 0F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityDarkManticore.class, new MoCRenderMoC(new MoCModelManticore(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityFireManticore.class, new MoCRenderMoC(new MoCModelManticore(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityFrostManticore.class, new MoCRenderMoC(new MoCModelManticore(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityPlainManticore.class, new MoCRenderMoC(new MoCModelManticore(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityToxicManticore.class, new MoCRenderMoC(new MoCModelManticore(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(MoCEntityManticorePet.class, new MoCRenderMoC(new MoCModelManticorePet(), 0.7F));

        if (MoCreatures.proxy.legacyBigCatModels) {
            RenderingRegistry.registerEntityRenderingHandler(MoCEntityLeoger.class, new MoCLegacyRenderBigCat(new MoCLegacyModelBigCat2(), new MoCLegacyModelBigCat1(), 0.5F));
            RenderingRegistry.registerEntityRenderingHandler(MoCEntityLeopard.class, new MoCLegacyRenderBigCat(new MoCLegacyModelBigCat2(), new MoCLegacyModelBigCat1(), 0.5F));
            RenderingRegistry.registerEntityRenderingHandler(MoCEntityLiard.class, new MoCLegacyRenderBigCat(new MoCLegacyModelBigCat2(), new MoCLegacyModelBigCat1(), 0.5F));
            RenderingRegistry.registerEntityRenderingHandler(MoCEntityLiger.class, new MoCLegacyRenderBigCat(new MoCLegacyModelBigCat2(), new MoCLegacyModelBigCat1(), 0.5F));
            RenderingRegistry.registerEntityRenderingHandler(MoCEntityLion.class, new MoCLegacyRenderBigCat(new MoCLegacyModelBigCat2(), new MoCLegacyModelBigCat1(), 0.5F));
            RenderingRegistry.registerEntityRenderingHandler(MoCEntityLither.class, new MoCLegacyRenderBigCat(new MoCLegacyModelBigCat2(), new MoCLegacyModelBigCat1(), 0.5F));
            RenderingRegistry.registerEntityRenderingHandler(MoCEntityPanthard.class, new MoCLegacyRenderBigCat(new MoCLegacyModelBigCat2(), new MoCLegacyModelBigCat1(), 0.5F));
            RenderingRegistry.registerEntityRenderingHandler(MoCEntityPanther.class, new MoCLegacyRenderBigCat(new MoCLegacyModelBigCat2(), new MoCLegacyModelBigCat1(), 0.5F));
            RenderingRegistry.registerEntityRenderingHandler(MoCEntityPanthger.class, new MoCLegacyRenderBigCat(new MoCLegacyModelBigCat2(), new MoCLegacyModelBigCat1(), 0.5F));
            RenderingRegistry.registerEntityRenderingHandler(MoCEntityTiger.class, new MoCLegacyRenderBigCat(new MoCLegacyModelBigCat2(), new MoCLegacyModelBigCat1(), 0.5F));
        } else {
            RenderingRegistry.registerEntityRenderingHandler(MoCEntityLeoger.class, new MoCRenderMoC(new MoCModelBigCat(), 0.5F));
            RenderingRegistry.registerEntityRenderingHandler(MoCEntityLeopard.class, new MoCRenderMoC(new MoCModelBigCat(), 0.5F));
            RenderingRegistry.registerEntityRenderingHandler(MoCEntityLiard.class, new MoCRenderMoC(new MoCModelBigCat(), 0.5F));
            RenderingRegistry.registerEntityRenderingHandler(MoCEntityLiger.class, new MoCRenderMoC(new MoCModelBigCat(), 0.5F));
            RenderingRegistry.registerEntityRenderingHandler(MoCEntityLion.class, new MoCRenderMoC(new MoCModelBigCat(), 0.5F));
            RenderingRegistry.registerEntityRenderingHandler(MoCEntityLither.class, new MoCRenderMoC(new MoCModelBigCat(), 0.5F));
            RenderingRegistry.registerEntityRenderingHandler(MoCEntityPanthard.class, new MoCRenderMoC(new MoCModelBigCat(), 0.5F));
            RenderingRegistry.registerEntityRenderingHandler(MoCEntityPanther.class, new MoCRenderMoC(new MoCModelBigCat(), 0.5F));
            RenderingRegistry.registerEntityRenderingHandler(MoCEntityPanthger.class, new MoCRenderMoC(new MoCModelBigCat(), 0.5F));
            RenderingRegistry.registerEntityRenderingHandler(MoCEntityTiger.class, new MoCRenderMoC(new MoCModelBigCat(), 0.5F));
        }
        if (MoCreatures.proxy.legacySharkModel) {
            RenderingRegistry.registerEntityRenderingHandler(MoCEntityShark.class, new MoCLegacyRenderShark(new MoCLegacyModelShark(), 0.6F));
        } else {
            RenderingRegistry.registerEntityRenderingHandler(MoCEntityShark.class, new MoCRenderShark(new MoCModelShark(), 0.6F));
        }
    }

    @Override
    public EntityPlayer getPlayer() {
        return MoCProxyClient.mc.player;
    }

    /**
     * Sets the name client side. Name is synchronized with data watchers
     */
    @Override
    public void setName(EntityPlayer player, IMoCEntity mocanimal) {
        mc.displayGuiScreen(new MoCGUIEntityNamer(mocanimal, mocanimal.getPetName()));
    }

    @Override
    public void UndeadFX(Entity entity) {
        //if (!((Boolean) MoCreatures.particleFX.get()).booleanValue()) return;
        int densityInt = (MoCreatures.proxy.getParticleFX());
        if (densityInt == 0) {
            return;
        }

        int i = (densityInt / 2) * (entity.world.rand.nextInt(2) + 1);
        if (i == 0) {
            i = 1;
        }
        if (i > 10) {
            i = 10;
        }
        for (int x = 0; x < i; x++) {
            MoCEntityFXUndead FXUndead = new MoCEntityFXUndead(entity.world, entity.posX, entity.posY + entity.world.rand.nextFloat() * entity.height, entity.posZ);
            mc.effectRenderer.addEffect(FXUndead);

        }
    }

    @Override
    public void StarFX(MoCEntityHorse entity) {
        int densityInt = MoCreatures.proxy.getParticleFX();
        if (densityInt == 0) {
            return;
        }

        if ((entity.getType() >= 50 && entity.getType() < 60) || entity.getType() == 36) {

            float fRed = entity.colorFX(1, entity.getType());
            float fGreen = entity.colorFX(2, entity.getType());
            float fBlue = entity.colorFX(3, entity.getType());

            int i = densityInt * entity.world.rand.nextInt(2);// + 2;
            for (int x = 0; x < i; x++) {
                MoCEntityFXStar FXStar = new MoCEntityFXStar(mc.world, entity.posX, entity.posY + entity.world.rand.nextFloat() * entity.height, entity.posZ, fRed, fGreen, fBlue);
                mc.effectRenderer.addEffect(FXStar);

            }

        }
    }

    @Override
    public void LavaFX(Entity entity) {
        int densityInt = (MoCreatures.proxy.getParticleFX());
        if (densityInt == 0) {
            return;
        }
        double var2 = entity.world.rand.nextGaussian() * 0.02D;
        double var4 = entity.world.rand.nextGaussian() * 0.02D;
        double var6 = entity.world.rand.nextGaussian() * 0.02D;
        mc.world.spawnParticle(EnumParticleTypes.LAVA, entity.posX + entity.world.rand.nextFloat() * entity.width - entity.width, entity.posY + 0.5D + entity.world.rand.nextFloat() * entity.height, entity.posZ + entity.world.rand.nextFloat() * entity.width - entity.width, var2, var4, var6);

    }

    @Override
    public void VanishFX(MoCEntityHorse entity) {
        int densityInt = (MoCreatures.proxy.getParticleFX());
        if (densityInt == 0) {
            return;
        }

        for (int var6 = 0; var6 < densityInt * 8; ++var6) {
            double newPosX = ((float) entity.posX + entity.world.rand.nextFloat());
            double newPosY = 0.7D + ((float) entity.posY + entity.world.rand.nextFloat());
            double newPosZ = ((float) entity.posZ + entity.world.rand.nextFloat());
            int var19 = entity.world.rand.nextInt(2) * 2 - 1;
            double speedY = (entity.world.rand.nextFloat() - 0.5D) * 0.5D;
            double speedX = entity.world.rand.nextFloat() * 2.0F * var19;
            double speedZ = entity.world.rand.nextFloat() * 2.0F * var19;

            MoCEntityFXVanish FXVanish = new MoCEntityFXVanish(entity.world, newPosX, newPosY, newPosZ, speedX, speedY, speedZ, entity.colorFX(1, entity.getType()), entity.colorFX(2, entity.getType()), entity.colorFX(3, entity.getType()), false);
            mc.effectRenderer.addEffect(FXVanish);
        }
    }

    @Override
    public void MaterializeFX(MoCEntityHorse entity) {
        int densityInt = (MoCreatures.proxy.getParticleFX());
        if (densityInt == 0) {
            return;
        }

        for (int var6 = 0; var6 < (densityInt * 50); ++var6) {
            double newPosX = ((float) entity.posX + entity.world.rand.nextFloat());
            double newPosY = 0.7D + ((float) entity.posY + entity.world.rand.nextFloat());
            double newPosZ = ((float) entity.posZ + entity.world.rand.nextFloat());
            int var19 = entity.world.rand.nextInt(2) * 2 - 1;
            double speedY = (entity.world.rand.nextFloat() - 0.5D) * 0.5D;
            double speedX = entity.world.rand.nextFloat() * 2.0F * var19;
            double speedZ = entity.world.rand.nextFloat() * 2.0F * var19;

            MoCEntityFXVanish FXVanish = new MoCEntityFXVanish(mc.world, newPosX, newPosY, newPosZ, speedX, speedY, speedZ, entity.colorFX(1, entity.getType()), entity.colorFX(2, entity.getType()), entity.colorFX(3, entity.getType()), true);
            mc.effectRenderer.addEffect(FXVanish);
        }

    }

    @Override
    public void VacuumFX(MoCEntityGolem entity) {
        int densityInt = (MoCreatures.proxy.getParticleFX());
        if (densityInt == 0) {
            return;
        }

        for (int var1 = 0; var1 < 2; ++var1) {
            double newPosX = entity.posX - (1.5 * Math.cos((MoCTools.realAngle(entity.rotationYaw - 90F)) / 57.29578F));
            double newPosZ = entity.posZ - (1.5 * Math.sin((MoCTools.realAngle(entity.rotationYaw - 90F)) / 57.29578F));
            double newPosY = entity.posY + (entity.height - 0.8D - entity.getAdjustedYOffset() * 1.8);// + (entity.world.rand.nextDouble() * ((double) entity.height - (double) entity.getAdjustedYOffset() * 2));
            //adjustedYOffset from 0 (tallest) to 1.45 (on the ground)
            //height = 4F

            double speedX = (entity.world.rand.nextDouble() - 0.5D) * 4.0D;
            double speedY = -entity.world.rand.nextDouble();
            double speedZ = (entity.world.rand.nextDouble() - 0.5D) * 4.0D;
            MoCEntityFXVacuum FXVacuum = new MoCEntityFXVacuum(mc.world, newPosX, newPosY, newPosZ, speedX, speedY, speedZ, entity.colorFX(1), entity.colorFX(2), entity.colorFX(3), 146);
            mc.effectRenderer.addEffect(FXVacuum);
        }
    }

    @SuppressWarnings("unused")
    @Override
    public void hammerFX(EntityPlayer entity) {
        int densityInt = (MoCreatures.proxy.getParticleFX());
        if (densityInt == 0) {
            return;
        }

        for (int var6 = 0; var6 < (densityInt * 10); ++var6) {
            double newPosX = ((float) entity.posX + entity.world.rand.nextFloat());
            double newPosY = 0.3D + ((float) entity.posY + entity.world.rand.nextFloat());
            double newPosZ = ((float) entity.posZ + entity.world.rand.nextFloat());
            int var19 = entity.world.rand.nextInt(2) * 2 - 1;
            double speedY = (entity.world.rand.nextFloat() - 0.5D) * 0.5D;
            double speedX = entity.world.rand.nextFloat() * 2.0F * var19;
            double speedZ = entity.world.rand.nextFloat() * 2.0F * var19;

            // TODO - fix particle fx
            /*EntitySpellParticleFX hammerFX = new EntitySpellParticleFX(mc.world, newPosX, newPosY, newPosZ, speedX, speedY, speedZ);
            hammerFX.setBaseSpellTextureIndex(144);
            ((EntityFX) hammerFX).setRBGColorF(74F / 256F, 145F / 256F, 71F / 256F);
            mc.effectRenderer.addEffect(hammerFX);*/
        }

    }

    @Override
    public void teleportFX(EntityPlayer entity) {
        int densityInt = (MoCreatures.proxy.getParticleFX());
        if (densityInt == 0) {
            return;
        }

        for (int var6 = 0; var6 < (densityInt * 50); ++var6) {
            double newPosX = ((float) entity.posX + entity.world.rand.nextFloat());
            double newPosY = 0.7D + ((float) entity.posY + entity.world.rand.nextFloat());
            double newPosZ = ((float) entity.posZ + entity.world.rand.nextFloat());
            int var19 = entity.world.rand.nextInt(2) * 2 - 1;
            double speedY = (entity.world.rand.nextFloat() - 0.5D) * 0.5D;
            double speedX = entity.world.rand.nextFloat() * 2.0F * var19;
            double speedZ = entity.world.rand.nextFloat() * 2.0F * var19;

            MoCEntityFXVanish hammerFX = new MoCEntityFXVanish(mc.world, newPosX, newPosY, newPosZ, speedX, speedY, speedZ, 189F / 256F, 110F / 256F, 229F / 256F, true);
            mc.effectRenderer.addEffect(hammerFX);
        }

    }

    @Override
    public int getProxyMode() {
        return 2;
    }

    @Override
    public void configInit(FMLPreInitializationEvent event) {
        super.configInit(event);
    }

    @Override
    public void resetAllData() {
        super.resetAllData();
    }

    @Override
    public int getParticleFX() {
        return this.particleFX;
    }

    @Override
    public boolean getDisplayPetName() {
        return this.displayPetName;
    }

    @Override
    public boolean getDisplayPetIcons() {
        return this.displayPetIcons;
    }

    @Override
    public boolean getDisplayPetHealth() {
        return this.displayPetHealth;
    }

    @Override
    public boolean getAnimateTextures() {
        return this.animateTextures;
    }

    @Override
    public void printMessageToPlayer(String msg) {
        Minecraft.getMinecraft().player.sendMessage(new TextComponentTranslation(msg));
    }
}
