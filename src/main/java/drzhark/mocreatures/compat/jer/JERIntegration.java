/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.compat.jer;

import drzhark.mocreatures.MoCLootTables;
import drzhark.mocreatures.entity.ambient.*;
import drzhark.mocreatures.entity.aquatic.*;
import drzhark.mocreatures.entity.hostile.*;
import drzhark.mocreatures.entity.hunter.*;
import drzhark.mocreatures.entity.neutral.*;
import drzhark.mocreatures.entity.passive.*;
import jeresources.api.IJERAPI;
import jeresources.api.IMobRegistry;
import jeresources.api.conditionals.LightLevel;
import jeresources.compatibility.JERAPI;
import net.minecraft.world.World;

public class JERIntegration {

    public static void init() {
        IJERAPI jerApi = JERAPI.getInstance();
        IMobRegistry jerMobRegistry = jerApi.getMobRegistry();
        World jerWorld = jerApi.getWorld();

        jerMobRegistry.register(new MoCEntityAnchovy(jerWorld), LightLevel.any, MoCLootTables.ANCHOVY);
        jerMobRegistry.register(new MoCEntityAngelFish(jerWorld), LightLevel.any, MoCLootTables.ANGELFISH);
        jerMobRegistry.register(new MoCEntityAngler(jerWorld), LightLevel.any, MoCLootTables.ANGLERFISH);
        jerMobRegistry.register(new MoCEntityAnt(jerWorld), LightLevel.any, MoCLootTables.ANT);
        jerMobRegistry.register(new MoCEntityBass(jerWorld), LightLevel.any, MoCLootTables.BASS);
        jerMobRegistry.register(new MoCEntityBee(jerWorld), LightLevel.any, MoCLootTables.BEE);
        jerMobRegistry.register(new MoCEntityBird(jerWorld), LightLevel.any, MoCLootTables.BIRD);
        jerMobRegistry.register(new MoCEntityBlackBear(jerWorld), LightLevel.any, MoCLootTables.BLACK_BEAR);
        jerMobRegistry.register(new MoCEntityBoar(jerWorld), LightLevel.any, MoCLootTables.BOAR);
        jerMobRegistry.register(new MoCEntityBunny(jerWorld), LightLevel.any, MoCLootTables.BUNNY);
        jerMobRegistry.register(new MoCEntityButterfly(jerWorld), LightLevel.any, MoCLootTables.BUTTERFLY);
        jerMobRegistry.register(new MoCEntityCaveOgre(jerWorld), LightLevel.hostile, MoCLootTables.CAVE_OGRE);
        jerMobRegistry.register(new MoCEntityCaveScorpion(jerWorld), LightLevel.hostile, MoCLootTables.CAVE_SCORPION);
        jerMobRegistry.register(new MoCEntityClownFish(jerWorld), LightLevel.any, MoCLootTables.CLOWNFISH);
        jerMobRegistry.register(new MoCEntityCod(jerWorld), LightLevel.any, MoCLootTables.COD);
        jerMobRegistry.register(new MoCEntityCrab(jerWorld), LightLevel.any, MoCLootTables.CRAB);
        jerMobRegistry.register(new MoCEntityCricket(jerWorld), LightLevel.any, MoCLootTables.CRICKET);
        jerMobRegistry.register(new MoCEntityCrocodile(jerWorld), LightLevel.any, MoCLootTables.CROCODILE);
        jerMobRegistry.register(new MoCEntityDarkManticore(jerWorld), LightLevel.hostile, MoCLootTables.DARK_MANTICORE);
        jerMobRegistry.register(new MoCEntityDeer(jerWorld), LightLevel.any, MoCLootTables.DEER);
        jerMobRegistry.register(new MoCEntityDirtScorpion(jerWorld), LightLevel.hostile, MoCLootTables.DIRT_SCORPION);
        jerMobRegistry.register(new MoCEntityDolphin(jerWorld), LightLevel.any, MoCLootTables.DOLPHIN);
        jerMobRegistry.register(new MoCEntityDragonfly(jerWorld), LightLevel.any, MoCLootTables.DRAGONFLY);
        jerMobRegistry.register(new MoCEntityDuck(jerWorld), LightLevel.any, MoCLootTables.DUCK);
        jerMobRegistry.register(new MoCEntityFilchLizard(jerWorld), LightLevel.any, MoCLootTables.FILCH_LIZARD_SPAWN);
        jerMobRegistry.register(new MoCEntityFireManticore(jerWorld), LightLevel.hostile, MoCLootTables.FIRE_MANTICORE);
        jerMobRegistry.register(new MoCEntityFireOgre(jerWorld), LightLevel.hostile, MoCLootTables.FIRE_OGRE);
        jerMobRegistry.register(new MoCEntityFireScorpion(jerWorld), LightLevel.hostile, MoCLootTables.FIRE_SCORPION);
        jerMobRegistry.register(new MoCEntityFirefly(jerWorld), LightLevel.any, MoCLootTables.FIREFLY);
        jerMobRegistry.register(new MoCEntityFishy(jerWorld), LightLevel.any, MoCLootTables.FISHY);
        jerMobRegistry.register(new MoCEntityFlameWraith(jerWorld), LightLevel.hostile, MoCLootTables.FLAME_WRAITH);
        jerMobRegistry.register(new MoCEntityFly(jerWorld), LightLevel.any, MoCLootTables.FLY);
        jerMobRegistry.register(new MoCEntityFox(jerWorld), LightLevel.any, MoCLootTables.FOX);
        jerMobRegistry.register(new MoCEntityFrostManticore(jerWorld), LightLevel.hostile, MoCLootTables.FROST_MANTICORE);
        jerMobRegistry.register(new MoCEntityFrostScorpion(jerWorld), LightLevel.hostile, MoCLootTables.FROST_SCORPION);
        jerMobRegistry.register(new MoCEntityGoat(jerWorld), LightLevel.any, MoCLootTables.GOAT);
        jerMobRegistry.register(new MoCEntityGoldFish(jerWorld), LightLevel.any, MoCLootTables.GOLDFISH);
        jerMobRegistry.register(new MoCEntityGolem(jerWorld), LightLevel.hostile, MoCLootTables.BIG_GOLEM);
        jerMobRegistry.register(new MoCEntityGrasshopper(jerWorld), LightLevel.any, MoCLootTables.GRASSHOPPER);
        jerMobRegistry.register(new MoCEntityGreenOgre(jerWorld), LightLevel.hostile, MoCLootTables.GREEN_OGRE);
        jerMobRegistry.register(new MoCEntityGrizzlyBear(jerWorld), LightLevel.any, MoCLootTables.GRIZZLY_BEAR);
        jerMobRegistry.register(new MoCEntityHellRat(jerWorld), LightLevel.hostile, MoCLootTables.HELL_RAT);
        jerMobRegistry.register(new MoCEntityHippoTang(jerWorld), LightLevel.any, MoCLootTables.HIPPO_TANG);
        jerMobRegistry.register(new MoCEntityJellyFish(jerWorld), LightLevel.any, MoCLootTables.JELLYFISH);
        jerMobRegistry.register(new MoCEntityKitty(jerWorld), LightLevel.any, MoCLootTables.KITTY);
        jerMobRegistry.register(new MoCEntityKomodo(jerWorld), LightLevel.any, MoCLootTables.KOMODO_DRAGON);
        jerMobRegistry.register(new MoCEntityLeoger(jerWorld), LightLevel.any, MoCLootTables.LEOGER);
        jerMobRegistry.register(new MoCEntityLeopard(jerWorld), LightLevel.any, MoCLootTables.LEOPARD);
        jerMobRegistry.register(new MoCEntityLiard(jerWorld), LightLevel.any, MoCLootTables.LIARD);
        jerMobRegistry.register(new MoCEntityLiger(jerWorld), LightLevel.any, MoCLootTables.LIGER);
        jerMobRegistry.register(new MoCEntityLion(jerWorld), LightLevel.any, MoCLootTables.LION);
        jerMobRegistry.register(new MoCEntityLither(jerWorld), LightLevel.any, MoCLootTables.LITHER);
        jerMobRegistry.register(new MoCEntityMaggot(jerWorld), LightLevel.any, MoCLootTables.MAGGOT);
        jerMobRegistry.register(new MoCEntityManderin(jerWorld), LightLevel.any, MoCLootTables.MANDARINFISH);
        jerMobRegistry.register(new MoCEntityMantaRay(jerWorld), LightLevel.any, MoCLootTables.MANTA_RAY);
        jerMobRegistry.register(new MoCEntityMiniGolem(jerWorld), LightLevel.hostile, MoCLootTables.MINI_GOLEM);
        jerMobRegistry.register(new MoCEntityMole(jerWorld), LightLevel.any, MoCLootTables.MOLE);
        jerMobRegistry.register(new MoCEntityMouse(jerWorld), LightLevel.any, MoCLootTables.MOUSE);
        jerMobRegistry.register(new MoCEntityOstrich(jerWorld), LightLevel.any, MoCLootTables.OSTRICH);
        jerMobRegistry.register(new MoCEntityPandaBear(jerWorld), LightLevel.any, MoCLootTables.PANDA_BEAR);
        jerMobRegistry.register(new MoCEntityPanthard(jerWorld), LightLevel.any, MoCLootTables.PANTHARD);
        jerMobRegistry.register(new MoCEntityPanther(jerWorld), LightLevel.any, MoCLootTables.PANTHER);
        jerMobRegistry.register(new MoCEntityPanthger(jerWorld), LightLevel.any, MoCLootTables.PANTHGER);
        jerMobRegistry.register(new MoCEntityPiranha(jerWorld), LightLevel.any, MoCLootTables.PIRANHA);
        jerMobRegistry.register(new MoCEntityPlainManticore(jerWorld), LightLevel.hostile, MoCLootTables.PLAIN_MANTICORE);
        jerMobRegistry.register(new MoCEntityPolarBear(jerWorld), LightLevel.any, MoCLootTables.POLAR_BEAR);
        jerMobRegistry.register(new MoCEntityRaccoon(jerWorld), LightLevel.any, MoCLootTables.RACCOON);
        jerMobRegistry.register(new MoCEntityRat(jerWorld), LightLevel.hostile, MoCLootTables.RAT);
        jerMobRegistry.register(new MoCEntityRoach(jerWorld), LightLevel.any, MoCLootTables.ROACH);
        jerMobRegistry.register(new MoCEntitySalmon(jerWorld), LightLevel.any, MoCLootTables.SALMON);
        jerMobRegistry.register(new MoCEntityShark(jerWorld), LightLevel.any, MoCLootTables.SHARK);
        jerMobRegistry.register(new MoCEntitySilverSkeleton(jerWorld), LightLevel.hostile, MoCLootTables.SILVER_SKELETON);
        jerMobRegistry.register(new MoCEntitySnail(jerWorld), LightLevel.any, MoCLootTables.SNAIL);
        jerMobRegistry.register(new MoCEntityStingRay(jerWorld), LightLevel.any, MoCLootTables.STINGRAY);
        jerMobRegistry.register(new MoCEntityTiger(jerWorld), LightLevel.any, MoCLootTables.TIGER);
        jerMobRegistry.register(new MoCEntityToxicManticore(jerWorld), LightLevel.hostile, MoCLootTables.TOXIC_MANTICORE);
        jerMobRegistry.register(new MoCEntityTurkey(jerWorld), LightLevel.any, MoCLootTables.TURKEY);
        jerMobRegistry.register(new MoCEntityTurtle(jerWorld), LightLevel.any, MoCLootTables.TURTLE);
        jerMobRegistry.register(new MoCEntityUndeadScorpion(jerWorld), LightLevel.hostile, MoCLootTables.UNDEAD_SCORPION);
        jerMobRegistry.register(new MoCEntityWerewolf(jerWorld), LightLevel.hostile, MoCLootTables.WEREWOLF);
        jerMobRegistry.register(new MoCEntityWWolf(jerWorld), LightLevel.hostile, MoCLootTables.WILD_WOLF);
        jerMobRegistry.register(new MoCEntityWraith(jerWorld), LightLevel.hostile, MoCLootTables.WRAITH);
        jerMobRegistry.register(new MoCEntityWyvern(jerWorld), LightLevel.any, MoCLootTables.WYVERN);
    }
}
