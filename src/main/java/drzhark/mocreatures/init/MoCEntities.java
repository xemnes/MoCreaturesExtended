/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.init;

import drzhark.mocreatures.MoCConstants;
import drzhark.mocreatures.MoCEntityData;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.ambient.*;
import drzhark.mocreatures.entity.aquatic.*;
import drzhark.mocreatures.entity.hostile.*;
import drzhark.mocreatures.entity.hunter.*;
import drzhark.mocreatures.entity.item.MoCEntityEgg;
import drzhark.mocreatures.entity.item.MoCEntityKittyBed;
import drzhark.mocreatures.entity.item.MoCEntityLitterBox;
import drzhark.mocreatures.entity.item.MoCEntityThrowableRock;
import drzhark.mocreatures.entity.neutral.*;
import drzhark.mocreatures.entity.passive.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
public class MoCEntities {

    public static BiomeDictionary.Type STEEP = BiomeDictionary.Type.getType("STEEP");
    public static BiomeDictionary.Type WYVERN_LAIR = BiomeDictionary.Type.getType("WYVERN_LAIR");
    public static List<EntityEntry> ENTITIES = new ArrayList<>();
    public static List<EntityEntry> SPAWN_ENTITIES = new ArrayList<>();
    /**
     * Animal
     */
    public static EntityEntry BIRD = createEntityEntry(MoCEntityBird.class, "Bird", 14020607, 14020607, EntityLiving.SpawnPlacementType.IN_AIR);// 0x03600, 0x003500);
    public static EntityEntry BEAR = createEntityEntry(MoCEntityBlackBear.class, "BlackBear", 10, 1, EntityLiving.SpawnPlacementType.ON_GROUND);//, 0x2600, 0x052500);
    public static EntityEntry BOAR = createEntityEntry(MoCEntityBoar.class, "Boar", 14772545, 9141102, EntityLiving.SpawnPlacementType.ON_GROUND);//, 0x2600, 0x052500);
    public static EntityEntry BUNNY = createEntityEntry(MoCEntityBunny.class, "Bunny", 12623485, 9141102, EntityLiving.SpawnPlacementType.ON_GROUND);//, 0x05600, 0x006500);
    public static EntityEntry CROCODILE = createEntityEntry(MoCEntityCrocodile.class, "Crocodile", 16711680, 65407, EntityLiving.SpawnPlacementType.ON_GROUND);//, 0x2600, 0x052500);
    public static EntityEntry DUCK = createEntityEntry(MoCEntityDuck.class, "Duck", 14021607, 15656192, EntityLiving.SpawnPlacementType.ON_GROUND);//, 0x2600, 0x052500);
    public static EntityEntry DEER = createEntityEntry(MoCEntityDeer.class, "Deer", 14021607, 33023, EntityLiving.SpawnPlacementType.ON_GROUND);//, 0x2600, 0x052500);
    public static EntityEntry ELEPHANT = createEntityEntry(MoCEntityElephant.class, "Elephant", 14772545, 23423, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry ENT = createEntityEntry(MoCEntityEnt.class, "Ent", 12623485, 16711680, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry FOX = createEntityEntry(MoCEntityFox.class, "Fox", 14772545, 5253242, EntityLiving.SpawnPlacementType.ON_GROUND);//, 0x2600, 0x052500);
    public static EntityEntry GOAT = createEntityEntry(MoCEntityGoat.class, "Goat", 7434694, 6053069, EntityLiving.SpawnPlacementType.ON_GROUND);//, 0x2600, 0x052500);
    public static EntityEntry GRIZZLY_BEAR = createEntityEntry(MoCEntityGrizzlyBear.class, "GrizzlyBear", 14772545, 1, EntityLiving.SpawnPlacementType.ON_GROUND);//, 0x2600, 0x052500);
    public static EntityEntry KITTY = createEntityEntry(MoCEntityKitty.class, "Kitty", 12623485, 5253242, EntityLiving.SpawnPlacementType.ON_GROUND);//, 0x2600, 0x052500);
    public static EntityEntry KOMODO_DRAGON = createEntityEntry(MoCEntityKomodo.class, "KomodoDragon", 16711680, 23423, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry LEOGER = createEntityEntry(MoCEntityLeoger.class, "Leoger", 14772545, 14772545, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry LEOPARD = createEntityEntry(MoCEntityLeopard.class, "Leopard", 13749760, 10, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry LIARD = createEntityEntry(MoCEntityLiard.class, "Liard", 15313474, 13749760, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry LION = createEntityEntry(MoCEntityLion.class, "Lion", 15313474, 13749760, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry LIGER = createEntityEntry(MoCEntityLiger.class, "Liger", 15313474, 12623485, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry LITHER = createEntityEntry(MoCEntityLither.class, "Lither", 15313474, 14772545, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry MANTICORE_PET = createEntityEntry(MoCEntityManticorePet.class, "ManticorePet");
    public static EntityEntry MOLE = createEntityEntry(MoCEntityMole.class, "Mole", 14020607, 16711680, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry MOUSE = createEntityEntry(MoCEntityMouse.class, "Mouse", 14772545, 0, EntityLiving.SpawnPlacementType.ON_GROUND);//, 0x02600, 0x002500);
    public static EntityEntry OSTRICH = createEntityEntry(MoCEntityOstrich.class, "Ostrich", 14020607, 9639167, EntityLiving.SpawnPlacementType.ON_GROUND);//, 0x2600, 0x052500);
    public static EntityEntry PANDA_BEAR = createEntityEntry(MoCEntityPandaBear.class, "PandaBear", 10, 9141102, EntityLiving.SpawnPlacementType.ON_GROUND);//, 0x2600, 0x052500);
    public static EntityEntry PANTHARD = createEntityEntry(MoCEntityPanthard.class, "Panthard", 10, 13749760, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry PANTHER = createEntityEntry(MoCEntityPanther.class, "Panther", 10, 205, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry PANTHGER = createEntityEntry(MoCEntityPanthger.class, "Panthger", 10, 14772545, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry PET_SCORPION = createEntityEntry(MoCEntityPetScorpion.class, "PetScorpion");
    public static EntityEntry POLAR_BEAR = createEntityEntry(MoCEntityPolarBear.class, "WildPolarBear", 14020607, 9141102, EntityLiving.SpawnPlacementType.ON_GROUND);//, 0x2600, 0x052500);
    public static EntityEntry RACCOON = createEntityEntry(MoCEntityRaccoon.class, "Raccoon", 14772545, 13749760, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry SNAKE = createEntityEntry(MoCEntitySnake.class, "Snake", 14020607, 13749760, EntityLiving.SpawnPlacementType.ON_GROUND);//, 0x05800, 0x006800);
    public static EntityEntry TIGER = createEntityEntry(MoCEntityTiger.class, "Tiger", 14772545, 0, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry TURTLE = createEntityEntry(MoCEntityTurtle.class, "Turtle", 14772545, 9320590, EntityLiving.SpawnPlacementType.ON_GROUND);//, 0x04800, 0x004500);
    public static EntityEntry TURKEY = createEntityEntry(MoCEntityTurkey.class, "Turkey", 14020607, 16711680, EntityLiving.SpawnPlacementType.ON_GROUND);//, 0x2600, 0x052500);
    public static EntityEntry WILDHORSE = createEntityEntry(MoCEntityHorse.class, "WildHorse", 12623485, 15656192, EntityLiving.SpawnPlacementType.ON_GROUND);//, 0x2600, 0x052500);
    public static EntityEntry WYVERN = createEntityEntry(MoCEntityWyvern.class, "Wyvern", 14772545, 65407, EntityLiving.SpawnPlacementType.ON_GROUND);
    /**
     * Monster
     */
    public static EntityEntry CAVE_OGRE = createEntityEntry(MoCEntityCaveOgre.class, "CaveOgre", 16711680, 33023, EntityLiving.SpawnPlacementType.ON_GROUND);//, 0x2600, 0x052500);
    public static EntityEntry FLAME_WRAITH = createEntityEntry(MoCEntityFlameWraith.class, "FlameWraith", 16711680, 12623485, EntityLiving.SpawnPlacementType.ON_GROUND);//, 0x2600, 0x052500);
    public static EntityEntry FIRE_OGRE = createEntityEntry(MoCEntityFireOgre.class, "FireOgre", 16711680, 9320595, EntityLiving.SpawnPlacementType.ON_GROUND);//, 0x2600, 0x052500);
    public static EntityEntry GREEN_OGRE = createEntityEntry(MoCEntityGreenOgre.class, "GreenOgre", 16711680, 65407, EntityLiving.SpawnPlacementType.ON_GROUND);//, 0x2600, 0x052500);
    public static EntityEntry BIG_GOLEM = createEntityEntry(MoCEntityGolem.class, "BigGolem", 16711680, 16622, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry HORSEMOB = createEntityEntry(MoCEntityHorseMob.class, "HorseMob", 16711680, 9320590, EntityLiving.SpawnPlacementType.ON_GROUND);//, 0x2600, 0x052500);
    public static EntityEntry HELLRAT = createEntityEntry(MoCEntityHellRat.class, "HellRat", 16711680, 14772545, EntityLiving.SpawnPlacementType.ON_GROUND);//, 0x2600, 0x052500);
    public static EntityEntry DARK_MANTICORE = createEntityEntry(MoCEntityDarkManticore.class, "DarkManticore", 3289650, 657930, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry FIRE_MANTICORE = createEntityEntry(MoCEntityFireManticore.class, "FireManticore", 7148552, 2819585, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry FROST_MANTICORE = createEntityEntry(MoCEntityFrostManticore.class, "FrostManticore", 3559006, 2041389, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry PLAIN_MANTICORE = createEntityEntry(MoCEntityPlainManticore.class, "PlainManticore", 7623465, 5510656, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry TOXIC_MANTICORE = createEntityEntry(MoCEntityToxicManticore.class, "ToxicManticore", 6252034, 3365689, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry MINI_GOLEM = createEntityEntry(MoCEntityMiniGolem.class, "MiniGolem", 16711680, 13749760, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry RAT = createEntityEntry(MoCEntityRat.class, "Rat", 16711680, 9141102, EntityLiving.SpawnPlacementType.ON_GROUND);//, 0x2600, 0x052500);
    public static EntityEntry SILVER_SKELETON = createEntityEntry(MoCEntitySilverSkeleton.class, "SilverSkeleton", 16711680, 33023, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry CAVE_SCORPION = createEntityEntry(MoCEntityCaveScorpion.class, "CaveScorpion", 789516, 3223866, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry DIRT_SCORPION = createEntityEntry(MoCEntityDirtScorpion.class, "DirtScorpion", 4134919, 13139755, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry FROST_SCORPION = createEntityEntry(MoCEntityFrostScorpion.class, "FrostScorpion", 333608, 5218691, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry FIRE_SCORPION = createEntityEntry(MoCEntityFireScorpion.class, "FireScorpion", 2163457, 9515286, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry UNDEAD_SCORPION = createEntityEntry(MoCEntityUndeadScorpion.class, "UndeadScorpion", 1118208, 7899732, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry WEREWOLF = createEntityEntry(MoCEntityWerewolf.class, "Werewolf", 16711680, 7434694, EntityLiving.SpawnPlacementType.ON_GROUND);//, 0x2600, 0x052500);
    public static EntityEntry WRAITH = createEntityEntry(MoCEntityWraith.class, "Wraith", 16711680, 0, EntityLiving.SpawnPlacementType.ON_GROUND);//, 0x2600, 0x052500);
    public static EntityEntry WWOLF = createEntityEntry(MoCEntityWWolf.class, "WWolf", 16711680, 13749760, EntityLiving.SpawnPlacementType.ON_GROUND);//, 0x2600, 0x052500);
    /**
     * Aquatic
     */
    public static EntityEntry ANCHOVY = createEntityEntry(MoCEntityAnchovy.class, "Anchovy", 5665535, 205, EntityLiving.SpawnPlacementType.IN_WATER);
    public static EntityEntry ANGELFISH = createEntityEntry(MoCEntityAngelFish.class, "AngelFish", 5665535, 7434694, EntityLiving.SpawnPlacementType.IN_WATER);
    public static EntityEntry ANGLER = createEntityEntry(MoCEntityAngler.class, "Angler", 5665535, 10, EntityLiving.SpawnPlacementType.IN_WATER);
    public static EntityEntry BASS = createEntityEntry(MoCEntityBass.class, "Bass", 33023, 2372490, EntityLiving.SpawnPlacementType.IN_WATER);
    public static EntityEntry CLOWNFISH = createEntityEntry(MoCEntityClownFish.class, "ClownFish", 5665535, 14772545, EntityLiving.SpawnPlacementType.IN_WATER);
    public static EntityEntry COD = createEntityEntry(MoCEntityCod.class, "Cod", 33023, 16622, EntityLiving.SpawnPlacementType.IN_WATER);
    public static EntityEntry DOLPHIN = createEntityEntry(MoCEntityDolphin.class, "Dolphin", 33023, 15631086, EntityLiving.SpawnPlacementType.IN_WATER);//, 0x2600, 0x052500);
    public static EntityEntry FISHY = createEntityEntry(MoCEntityFishy.class, "Fishy", 5665535, 65407, EntityLiving.SpawnPlacementType.IN_WATER);//, 0x2600, 0x052500);
    public static EntityEntry GOLDFISH = createEntityEntry(MoCEntityGoldFish.class, "GoldFish", 5665535, 15656192, EntityLiving.SpawnPlacementType.IN_WATER);
    public static EntityEntry HIPPOTANG = createEntityEntry(MoCEntityHippoTang.class, "HippoTang", 5665535, 2037680, EntityLiving.SpawnPlacementType.IN_WATER);
    public static EntityEntry JELLYFISH = createEntityEntry(MoCEntityJellyFish.class, "JellyFish", 33023, 14772545, EntityLiving.SpawnPlacementType.IN_WATER);//, 0x2600, 0x052500);
    public static EntityEntry MANDERIN = createEntityEntry(MoCEntityManderin.class, "Manderin", 5665535, 12623485, EntityLiving.SpawnPlacementType.IN_WATER);
    public static EntityEntry PIRANHA = createEntityEntry(MoCEntityPiranha.class, "Piranha", 33023, 16711680, EntityLiving.SpawnPlacementType.IN_WATER);
    public static EntityEntry SALMON = createEntityEntry(MoCEntitySalmon.class, "Salmon", 33023, 12623485, EntityLiving.SpawnPlacementType.IN_WATER);
    public static EntityEntry MANTARAY = createEntityEntry(MoCEntityMantaRay.class, "MantaRay", 33023, 9141102, EntityLiving.SpawnPlacementType.IN_WATER);//14772545, 9141102);
    public static EntityEntry SHARK = createEntityEntry(MoCEntityShark.class, "Shark", 33023, 9013643, EntityLiving.SpawnPlacementType.IN_WATER);//, 0x2600, 0x052500);
    public static EntityEntry STINGRAY = createEntityEntry(MoCEntityStingRay.class, "StingRay", 33023, 6053069, EntityLiving.SpawnPlacementType.IN_WATER);//14772545, 9141102);
    /**
     * Ambient
     */
    public static EntityEntry ANT = createEntityEntry(MoCEntityAnt.class, "Ant", 5915945, 2693905, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry BEE = createEntityEntry(MoCEntityBee.class, "Bee", 15912747, 526604, EntityLiving.SpawnPlacementType.IN_AIR);
    public static EntityEntry BUTTERFLY = createEntityEntry(MoCEntityButterfly.class, "ButterFly", 12615169, 2956801, EntityLiving.SpawnPlacementType.IN_AIR);
    public static EntityEntry CRAB = createEntityEntry(MoCEntityCrab.class, "Crab", 11880978, 15514213, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry CRICKET = createEntityEntry(MoCEntityCricket.class, "Cricket", 7830593, 3747075, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry DRAGONFLY = createEntityEntry(MoCEntityDragonfly.class, "DragonFly", 665770, 2207231, EntityLiving.SpawnPlacementType.IN_AIR);
    public static EntityEntry FIREFLY = createEntityEntry(MoCEntityFirefly.class, "Firefly", 2102294, 8501028, EntityLiving.SpawnPlacementType.IN_AIR);
    public static EntityEntry FLY = createEntityEntry(MoCEntityFly.class, "Fly", 1184284, 11077640, EntityLiving.SpawnPlacementType.IN_AIR);
    public static EntityEntry MAGGOT = createEntityEntry(MoCEntityMaggot.class, "Maggot", 14076037, 6839592, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry SNAIL = createEntityEntry(MoCEntitySnail.class, "Snail", 10850932, 7225384, EntityLiving.SpawnPlacementType.ON_GROUND);
    public static EntityEntry ROACH = createEntityEntry(MoCEntityRoach.class, "Roach", 5185289, 10245148, EntityLiving.SpawnPlacementType.ON_GROUND);
    /**
     * Other
     */
    public static EntityEntry EGG = createEntityEntry(MoCEntityEgg.class, "Egg");
    public static EntityEntry KITTY_BED = createEntityEntry(MoCEntityKittyBed.class, "KittyBed");
    public static EntityEntry LITTERBOX = createEntityEntry(MoCEntityLitterBox.class, "LitterBox");
    public static EntityEntry TROCK = createEntityEntry(MoCEntityThrowableRock.class, "TRock");
    static int MoCEntityID = 0;

    private static EntityEntry createEntityEntry(Class<? extends Entity> cls, String name) {
        EntityEntry entityEntry = new EntityEntry(cls, name);
        entityEntry.setRegistryName(new ResourceLocation(MoCConstants.MOD_PREFIX + name.toLowerCase()));
        ENTITIES.add(entityEntry);
        return entityEntry;
    }

    private static EntityEntry createEntityEntry(Class<? extends Entity> cls, String name, int primaryColorIn, int secondaryColorIn, EntityLiving.SpawnPlacementType type) {
        EntityEntry entityEntry = new EntityEntry(cls, name);
        entityEntry.setRegistryName(new ResourceLocation(MoCConstants.MOD_PREFIX + name.toLowerCase()));
        entityEntry.setEgg(new EntityEggInfo(new ResourceLocation(MoCConstants.MOD_PREFIX + name.toLowerCase()), primaryColorIn, secondaryColorIn));
        EntitySpawnPlacementRegistry.setPlacementType(cls, type);
        SPAWN_ENTITIES.add(entityEntry);
        return entityEntry;
    }

    private static void registerEntity(Class<? extends Entity> entityClass, String entityName) {
        final ResourceLocation resourceLocation = new ResourceLocation(MoCConstants.MOD_PREFIX + entityName.toLowerCase());
        EntityRegistry.registerModEntity(resourceLocation, entityClass, resourceLocation.toString(), MoCEntityID++, MoCreatures.instance, 64, 1, true);
    }

    private static void registerEntity(Class<? extends Entity> entityClass, String entityName, int eggColor, int eggDotsColor) {
        final ResourceLocation resourceLocation = new ResourceLocation(MoCConstants.MOD_PREFIX + entityName.toLowerCase());
        EntityRegistry.registerModEntity(resourceLocation, entityClass, resourceLocation.toString(), MoCEntityID++, MoCreatures.instance, 64, 1, true, eggColor, eggDotsColor);
    }

    @SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
    public static void registerEntities() {
        MoCreatures.LOGGER.info("Registering entities...");

        for (EntityEntry entry : ENTITIES) {
            registerEntity(entry.getEntityClass(), entry.getName());
        }

        for (EntityEntry entry : SPAWN_ENTITIES) {
            registerEntity(entry.getEntityClass(), entry.getName(), entry.getEgg().primaryColor, entry.getEgg().secondaryColor);
        }

        int[] overworld = new int[]{0};
        int[] nether = new int[]{-1};
        int[] overworldNether = new int[]{0, -1};
        int[] wyvernLair = new int[]{MoCreatures.proxy.wyvernDimension};
        int[] overworldWyvernLair = new int[]{0, MoCreatures.proxy.wyvernDimension};

        /*
         * Animal
         */
        MoCreatures.mocEntityMap.put("BlackBear", new MoCEntityData("BlackBear", 4, overworld, EnumCreatureType.CREATURE, new SpawnListEntry(MoCEntityBlackBear.class, 8, 1, 3), new ArrayList<>(Arrays.asList(Type.FOREST, Type.CONIFEROUS))));
        MoCreatures.mocEntityMap.put("GrizzlyBear", new MoCEntityData("GrizzlyBear", 4, overworld, EnumCreatureType.CREATURE, new SpawnListEntry(MoCEntityGrizzlyBear.class, 7, 1, 2), new ArrayList<>(Arrays.asList(Type.FOREST))));
        MoCreatures.mocEntityMap.put("WildPolarBear", new MoCEntityData("WildPolarBear", 4, overworld, EnumCreatureType.CREATURE, new SpawnListEntry(MoCEntityPolarBear.class, 8, 1, 2), new ArrayList<>(Arrays.asList(Type.SNOWY))));
        MoCreatures.mocEntityMap.put("PandaBear", new MoCEntityData("PandaBear", 4, overworld, EnumCreatureType.CREATURE, new SpawnListEntry(MoCEntityPandaBear.class, 7, 1, 3), new ArrayList<>(Arrays.asList(Type.JUNGLE))));
        MoCreatures.mocEntityMap.put("Bird", new MoCEntityData("Bird", 4, overworld, EnumCreatureType.CREATURE, new SpawnListEntry(MoCEntityBird.class, 16, 2, 3), new ArrayList<>(Arrays.asList(Type.FOREST, Type.JUNGLE, Type.PLAINS, Type.MESA, Type.LUSH, STEEP))));
        MoCreatures.mocEntityMap.put("Boar", new MoCEntityData("Boar", 3, overworld, EnumCreatureType.CREATURE, new SpawnListEntry(MoCEntityBoar.class, 12, 2, 2), new ArrayList<>(Arrays.asList(Type.FOREST, Type.PLAINS))));
        MoCreatures.mocEntityMap.put("Bunny", new MoCEntityData("Bunny", 4, overworldWyvernLair, EnumCreatureType.CREATURE, new SpawnListEntry(MoCEntityBunny.class, 12, 2, 3), new ArrayList<>(Arrays.asList(Type.FOREST, Type.PLAINS, Type.SNOWY, Type.CONIFEROUS, WYVERN_LAIR, STEEP))));
        MoCreatures.mocEntityMap.put("Crocodile", new MoCEntityData("Crocodile", 2, overworld, EnumCreatureType.CREATURE, new SpawnListEntry(MoCEntityCrocodile.class, 10, 1, 2), new ArrayList<>(Arrays.asList(Type.SWAMP))));
        MoCreatures.mocEntityMap.put("Deer", new MoCEntityData("Deer", 2, overworld, EnumCreatureType.CREATURE, new SpawnListEntry(MoCEntityDeer.class, 10, 1, 2), new ArrayList<>(Arrays.asList(Type.FOREST, Type.PLAINS, Type.CONIFEROUS))));
        MoCreatures.mocEntityMap.put("Duck", new MoCEntityData("Duck", 3, overworld, EnumCreatureType.CREATURE, new SpawnListEntry(MoCEntityDuck.class, 12, 2, 4), new ArrayList<>(Arrays.asList(Type.RIVER, Type.LUSH))));
        MoCreatures.mocEntityMap.put("Elephant", new MoCEntityData("Elephant", 3, overworld, EnumCreatureType.CREATURE, new SpawnListEntry(MoCEntityElephant.class, 6, 1, 2), new ArrayList<>(Arrays.asList(Type.SANDY, Type.JUNGLE, Type.SAVANNA, Type.SNOWY)), new ArrayList<>(Arrays.asList(Type.MESA))));
        MoCreatures.mocEntityMap.put("Ent", new MoCEntityData("Ent", 3, overworld, EnumCreatureType.CREATURE, new SpawnListEntry(MoCEntityEnt.class, 5, 1, 2), new ArrayList<>(Arrays.asList(Type.FOREST))));
        MoCreatures.mocEntityMap.put("Fox", new MoCEntityData("Fox", 2, overworld, EnumCreatureType.CREATURE, new SpawnListEntry(MoCEntityFox.class, 10, 1, 1), new ArrayList<>(Arrays.asList(Type.FOREST, Type.SNOWY, Type.CONIFEROUS))));
        MoCreatures.mocEntityMap.put("Goat", new MoCEntityData("Goat", 2, overworld, EnumCreatureType.CREATURE, new SpawnListEntry(MoCEntityGoat.class, 12, 1, 3), new ArrayList<>(Arrays.asList(Type.PLAINS, STEEP))));
        MoCreatures.mocEntityMap.put("Kitty", new MoCEntityData("Kitty", 3, overworld, EnumCreatureType.CREATURE, new SpawnListEntry(MoCEntityKitty.class, 8, 1, 2), new ArrayList<>(Arrays.asList(Type.PLAINS, Type.FOREST)))); // spawns in villages
        MoCreatures.mocEntityMap.put("KomodoDragon", new MoCEntityData("KomodoDragon", 2, overworld, EnumCreatureType.CREATURE, new SpawnListEntry(MoCEntityKomodo.class, 12, 1, 2), new ArrayList<>(Arrays.asList(Type.SWAMP, Type.SAVANNA))));
        MoCreatures.mocEntityMap.put("Leopard", new MoCEntityData("Leopard", 4, overworld, EnumCreatureType.CREATURE, new SpawnListEntry(MoCEntityLeopard.class, 7, 1, 2), new ArrayList<>(Arrays.asList(Type.JUNGLE, Type.SNOWY, Type.SAVANNA))));
        MoCreatures.mocEntityMap.put("Lion", new MoCEntityData("Lion", 4, overworld, EnumCreatureType.CREATURE, new SpawnListEntry(MoCEntityLion.class, 8, 1, 3), new ArrayList<>(Arrays.asList(Type.SAVANNA, Type.SANDY, Type.MESA))));
        MoCreatures.mocEntityMap.put("Mole", new MoCEntityData("Mole", 3, overworld, EnumCreatureType.CREATURE, new SpawnListEntry(MoCEntityMole.class, 10, 1, 2), new ArrayList<>(Arrays.asList(Type.FOREST, Type.LUSH, Type.PLAINS))));
        MoCreatures.mocEntityMap.put("Mouse", new MoCEntityData("Mouse", 2, overworld, EnumCreatureType.CREATURE, new SpawnListEntry(MoCEntityMouse.class, 10, 1, 2), new ArrayList<>(Arrays.asList(Type.FOREST, Type.PLAINS, Type.MESA, STEEP))));
        MoCreatures.mocEntityMap.put("Ostrich", new MoCEntityData("Ostrich", 3, overworld, EnumCreatureType.CREATURE, new SpawnListEntry(MoCEntityOstrich.class, 7, 1, 1), new ArrayList<>(Arrays.asList(Type.SAVANNA, Type.SANDY)), new ArrayList<>(Arrays.asList(Type.MESA))));
        MoCreatures.mocEntityMap.put("Panther", new MoCEntityData("Panther", 4, overworld, EnumCreatureType.CREATURE, new SpawnListEntry(MoCEntityPanther.class, 6, 1, 2), new ArrayList<>(Arrays.asList(Type.JUNGLE))));
        MoCreatures.mocEntityMap.put("Raccoon", new MoCEntityData("Raccoon", 2, overworld, EnumCreatureType.CREATURE, new SpawnListEntry(MoCEntityRaccoon.class, 12, 1, 2), new ArrayList<>(Arrays.asList(Type.FOREST))));
        MoCreatures.mocEntityMap.put("Snake", new MoCEntityData("Snake", 3, overworldWyvernLair, EnumCreatureType.CREATURE, new SpawnListEntry(MoCEntitySnake.class, 12, 1, 1), new ArrayList<>(Arrays.asList(Type.SANDY, Type.MESA, Type.PLAINS, Type.FOREST, Type.SWAMP, Type.LUSH, Type.JUNGLE, WYVERN_LAIR, STEEP))));
        MoCreatures.mocEntityMap.put("Tiger", new MoCEntityData("Tiger", 4, overworld, EnumCreatureType.CREATURE, new SpawnListEntry(MoCEntityTiger.class, 7, 1, 2), new ArrayList<>(Arrays.asList(Type.JUNGLE))));
        MoCreatures.mocEntityMap.put("Turkey", new MoCEntityData("Turkey", 2, overworld, EnumCreatureType.CREATURE, new SpawnListEntry(MoCEntityTurkey.class, 12, 1, 2), new ArrayList<>(Arrays.asList(Type.FOREST, Type.PLAINS))));
        MoCreatures.mocEntityMap.put("Turtle", new MoCEntityData("Turtle", 3, overworld, EnumCreatureType.CREATURE, new SpawnListEntry(MoCEntityTurtle.class, 12, 1, 3), new ArrayList<>(Arrays.asList(Type.SWAMP, Type.RIVER))));
        MoCreatures.mocEntityMap.put("WildHorse", new MoCEntityData("WildHorse", 4, overworld, EnumCreatureType.CREATURE, new SpawnListEntry(MoCEntityHorse.class, 12, 1, 4), new ArrayList<>(Arrays.asList(Type.PLAINS, Type.SAVANNA))));
        MoCreatures.mocEntityMap.put("Wyvern", new MoCEntityData("Wyvern", 3, wyvernLair, EnumCreatureType.CREATURE, new SpawnListEntry(MoCEntityWyvern.class, 12, 1, 3), new ArrayList<>(Arrays.asList(WYVERN_LAIR))));
        /*
         * Monster
         */
        MoCreatures.mocEntityMap.put("BigGolem", new MoCEntityData("BigGolem", 1, overworld, EnumCreatureType.MONSTER, new SpawnListEntry(MoCEntityGolem.class, 3, 1, 1), new ArrayList<>(Arrays.asList(Type.SANDY, Type.HILLS, Type.MESA, Type.MOUNTAIN, Type.PLAINS, Type.WASTELAND))));
        MoCreatures.mocEntityMap.put("MiniGolem", new MoCEntityData("MiniGolem", 2, overworld, EnumCreatureType.MONSTER, new SpawnListEntry(MoCEntityMiniGolem.class, 6, 1, 3), new ArrayList<>(Arrays.asList(Type.SANDY, Type.MESA, Type.MOUNTAIN, Type.PLAINS, Type.WASTELAND))));
        MoCreatures.mocEntityMap.put("HorseMob", new MoCEntityData("HorseMob", 3, overworldNether, EnumCreatureType.MONSTER, new SpawnListEntry(MoCEntityHorseMob.class, 8, 1, 3), new ArrayList<>(Arrays.asList(Type.NETHER, Type.PLAINS, Type.SAVANNA, Type.WASTELAND, Type.DEAD, Type.SPOOKY))));
        MoCreatures.mocEntityMap.put("CaveScorpion", new MoCEntityData("CaveScorpion", 3, overworld, EnumCreatureType.MONSTER, new SpawnListEntry(MoCEntityCaveScorpion.class, 4, 1, 3), new ArrayList<>(Arrays.asList(Type.SANDY, Type.SNOWY, Type.MESA, Type.DRY, Type.HOT, Type.WASTELAND, Type.DEAD, Type.SPOOKY))));
        MoCreatures.mocEntityMap.put("DirtScorpion", new MoCEntityData("DirtScorpion", 3, overworld, EnumCreatureType.MONSTER, new SpawnListEntry(MoCEntityDirtScorpion.class, 6, 1, 3), new ArrayList<>(Arrays.asList(Type.SANDY, Type.MESA, Type.DRY, Type.HOT))));
        MoCreatures.mocEntityMap.put("FireScorpion", new MoCEntityData("FireScorpion", 3, nether, EnumCreatureType.MONSTER, new SpawnListEntry(MoCEntityFireScorpion.class, 6, 1, 3), new ArrayList<>(Arrays.asList(Type.NETHER))));
        MoCreatures.mocEntityMap.put("FrostScorpion", new MoCEntityData("FrostScorpion", 3, overworld, EnumCreatureType.MONSTER, new SpawnListEntry(MoCEntityFrostScorpion.class, 6, 1, 3), new ArrayList<>(Arrays.asList(Type.SNOWY))));
        MoCreatures.mocEntityMap.put("UndeadScorpion", new MoCEntityData("UndeadScorpion", 3, overworld, EnumCreatureType.MONSTER, new SpawnListEntry(MoCEntityUndeadScorpion.class, 6, 1, 3), new ArrayList<>(Arrays.asList(Type.WASTELAND, Type.DEAD, Type.SPOOKY))));
        MoCreatures.mocEntityMap.put("SilverSkeleton", new MoCEntityData("SilverSkeleton", 4, overworld, EnumCreatureType.MONSTER, new SpawnListEntry(MoCEntitySilverSkeleton.class, 6, 1, 4), new ArrayList<>(Arrays.asList(Type.SANDY, Type.SNOWY, Type.MESA, Type.PLAINS, Type.WASTELAND, Type.DEAD, Type.SPOOKY))));
        MoCreatures.mocEntityMap.put("Werewolf", new MoCEntityData("Werewolf", 3, overworld, EnumCreatureType.MONSTER, new SpawnListEntry(MoCEntityWerewolf.class, 8, 1, 4), new ArrayList<>(Arrays.asList(Type.CONIFEROUS, Type.FOREST))));
        MoCreatures.mocEntityMap.put("WWolf", new MoCEntityData("WWolf", 3, overworld, EnumCreatureType.MONSTER, new SpawnListEntry(MoCEntityWWolf.class, 8, 1, 3), new ArrayList<>(Arrays.asList(Type.FOREST, Type.SNOWY, Type.WASTELAND))));
        MoCreatures.mocEntityMap.put("DarkManticore", new MoCEntityData("DarkManticore", 3, overworld, EnumCreatureType.MONSTER, new SpawnListEntry(MoCEntityDarkManticore.class, 5, 1, 3), new ArrayList<>(Arrays.asList(Type.SANDY, Type.MOUNTAIN, Type.PLAINS, Type.SNOWY, Type.WASTELAND, Type.DEAD, Type.SPOOKY))));
        MoCreatures.mocEntityMap.put("FireManticore", new MoCEntityData("FireManticore", 3, nether, EnumCreatureType.MONSTER, new SpawnListEntry(MoCEntityFireManticore.class, 8, 1, 3), new ArrayList<>(Arrays.asList(Type.NETHER))));
        MoCreatures.mocEntityMap.put("FrostManticore", new MoCEntityData("FrostManticore", 3, overworld, EnumCreatureType.MONSTER, new SpawnListEntry(MoCEntityFrostManticore.class, 8, 1, 3), new ArrayList<>(Arrays.asList(Type.SNOWY))));
        MoCreatures.mocEntityMap.put("PlainManticore", new MoCEntityData("PlainManticore", 3, overworld, EnumCreatureType.MONSTER, new SpawnListEntry(MoCEntityPlainManticore.class, 8, 1, 3), new ArrayList<>(Arrays.asList(Type.SANDY, Type.MOUNTAIN, Type.PLAINS))));
        MoCreatures.mocEntityMap.put("ToxicManticore", new MoCEntityData("ToxicManticore", 3, overworld, EnumCreatureType.MONSTER, new SpawnListEntry(MoCEntityToxicManticore.class, 8, 1, 3), new ArrayList<>(Arrays.asList(Type.WASTELAND, Type.DEAD, Type.SPOOKY))));
        MoCreatures.mocEntityMap.put("GreenOgre", new MoCEntityData("GreenOgre", 3, overworld, EnumCreatureType.MONSTER, new SpawnListEntry(MoCEntityGreenOgre.class, 8, 1, 2), new ArrayList<>(Arrays.asList(Type.PLAINS, Type.SWAMP, Type.LUSH, Type.WASTELAND, Type.DEAD, Type.SPOOKY))));
        MoCreatures.mocEntityMap.put("CaveOgre", new MoCEntityData("CaveOgre", 3, overworld, EnumCreatureType.MONSTER, new SpawnListEntry(MoCEntityCaveOgre.class, 5, 1, 2), new ArrayList<>(Arrays.asList(Type.SANDY, Type.FOREST, Type.SNOWY, Type.JUNGLE, Type.HILLS, Type.MESA, Type.MOUNTAIN, Type.PLAINS, Type.SWAMP, Type.WASTELAND, Type.DEAD, Type.SPOOKY))));
        MoCreatures.mocEntityMap.put("FireOgre", new MoCEntityData("FireOgre", 3, nether, EnumCreatureType.MONSTER, new SpawnListEntry(MoCEntityFireOgre.class, 6, 1, 2), new ArrayList<>(Arrays.asList(Type.NETHER))));
        MoCreatures.mocEntityMap.put("Wraith", new MoCEntityData("Wraith", 3, overworld, EnumCreatureType.MONSTER, new SpawnListEntry(MoCEntityWraith.class, 6, 1, 4), new ArrayList<>(Arrays.asList(Type.FOREST, Type.CONIFEROUS, Type.DEAD, Type.DENSE, Type.SPOOKY))));
        MoCreatures.mocEntityMap.put("FlameWraith", new MoCEntityData("FlameWraith", 3, nether, EnumCreatureType.MONSTER, new SpawnListEntry(MoCEntityFlameWraith.class, 5, 1, 2), new ArrayList<>(Arrays.asList(Type.NETHER))));
        MoCreatures.mocEntityMap.put("Rat", new MoCEntityData("Rat", 2, overworld, EnumCreatureType.MONSTER, new SpawnListEntry(MoCEntityRat.class, 7, 1, 2), new ArrayList<>(Arrays.asList(Type.FOREST, Type.PLAINS, Type.MESA, STEEP))));
        MoCreatures.mocEntityMap.put("HellRat", new MoCEntityData("HellRat", 4, nether, EnumCreatureType.MONSTER, new SpawnListEntry(MoCEntityHellRat.class, 6, 1, 4), new ArrayList<>(Arrays.asList(Type.NETHER))));
        /*
         * Aquatic
         */
        MoCreatures.mocEntityMap.put("Bass", new MoCEntityData("Bass", 4, overworld, EnumCreatureType.WATER_CREATURE, new SpawnListEntry(MoCEntityBass.class, 10, 1, 4), new ArrayList<>(Arrays.asList(Type.SWAMP, Type.RIVER, Type.FOREST, Type.PLAINS))));
        MoCreatures.mocEntityMap.put("Cod", new MoCEntityData("Cod", 4, overworld, EnumCreatureType.WATER_CREATURE, new SpawnListEntry(MoCEntityCod.class, 10, 1, 4), new ArrayList<>(Arrays.asList(Type.BEACH, Type.OCEAN))));
        MoCreatures.mocEntityMap.put("Dolphin", new MoCEntityData("Dolphin", 3, overworld, EnumCreatureType.WATER_CREATURE, new SpawnListEntry(MoCEntityDolphin.class, 6, 2, 4), new ArrayList<>(Arrays.asList(Type.OCEAN))));
        MoCreatures.mocEntityMap.put("Fishy", new MoCEntityData("Fishy", 6, overworld, EnumCreatureType.WATER_CREATURE, new SpawnListEntry(MoCEntityFishy.class, 12, 1, 6), new ArrayList<>(Arrays.asList(Type.BEACH, Type.WATER, Type.OCEAN, Type.RIVER, Type.FOREST, Type.PLAINS))));
        MoCreatures.mocEntityMap.put("JellyFish", new MoCEntityData("JellyFish", 4, overworld, EnumCreatureType.WATER_CREATURE, new SpawnListEntry(MoCEntityJellyFish.class, 8, 1, 4), new ArrayList<>(Arrays.asList(Type.OCEAN))));
        MoCreatures.mocEntityMap.put("Salmon", new MoCEntityData("Salmon", 4, overworld, EnumCreatureType.WATER_CREATURE, new SpawnListEntry(MoCEntitySalmon.class, 10, 1, 4), new ArrayList<>(Arrays.asList(Type.BEACH, Type.WATER, Type.OCEAN, Type.RIVER, Type.FOREST, Type.PLAINS))));
        MoCreatures.mocEntityMap.put("Piranha", new MoCEntityData("Piranha", 4, overworld, EnumCreatureType.WATER_CREATURE, new SpawnListEntry(MoCEntityPiranha.class, 4, 1, 3), new ArrayList<>(Arrays.asList(Type.SWAMP, Type.JUNGLE, Type.LUSH))));
        MoCreatures.mocEntityMap.put("MantaRay", new MoCEntityData("MantaRay", 3, overworld, EnumCreatureType.WATER_CREATURE, new SpawnListEntry(MoCEntityMantaRay.class, 10, 1, 2), new ArrayList<>(Arrays.asList(Type.OCEAN))));
        MoCreatures.mocEntityMap.put("StingRay", new MoCEntityData("StingRay", 3, overworld, EnumCreatureType.WATER_CREATURE, new SpawnListEntry(MoCEntityStingRay.class, 10, 1, 2), new ArrayList<>(Arrays.asList(Type.SWAMP, Type.RIVER))));
        MoCreatures.mocEntityMap.put("Shark", new MoCEntityData("Shark", 3, overworld, EnumCreatureType.WATER_CREATURE, new SpawnListEntry(MoCEntityShark.class, 6, 1, 2), new ArrayList<>(Arrays.asList(Type.OCEAN))));
        MoCreatures.mocEntityMap.put("Anchovy", new MoCEntityData("Anchovy", 6, overworld, EnumCreatureType.WATER_CREATURE, new SpawnListEntry(MoCEntityAnchovy.class, 12, 1, 6), new ArrayList<>(Arrays.asList(Type.BEACH, Type.OCEAN, Type.RIVER))));
        MoCreatures.mocEntityMap.put("AngelFish", new MoCEntityData("AngelFish", 6, overworld, EnumCreatureType.WATER_CREATURE, new SpawnListEntry(MoCEntityAngelFish.class, 12, 1, 6), new ArrayList<>(Arrays.asList(Type.SWAMP, Type.RIVER, Type.JUNGLE))));
        MoCreatures.mocEntityMap.put("Angler", new MoCEntityData("Angler", 6, overworld, EnumCreatureType.WATER_CREATURE, new SpawnListEntry(MoCEntityAngler.class, 12, 1, 6), new ArrayList<>(Arrays.asList(Type.BEACH, Type.OCEAN))));
        MoCreatures.mocEntityMap.put("ClownFish", new MoCEntityData("ClownFish", 6, overworld, EnumCreatureType.WATER_CREATURE, new SpawnListEntry(MoCEntityClownFish.class, 12, 1, 6), new ArrayList<>(Arrays.asList(Type.BEACH, Type.OCEAN))));
        MoCreatures.mocEntityMap.put("GoldFish", new MoCEntityData("GoldFish", 6, overworld, EnumCreatureType.WATER_CREATURE, new SpawnListEntry(MoCEntityGoldFish.class, 12, 1, 6), new ArrayList<>(Arrays.asList(Type.RIVER))));
        MoCreatures.mocEntityMap.put("HippoTang", new MoCEntityData("HippoTang", 6, overworld, EnumCreatureType.WATER_CREATURE, new SpawnListEntry(MoCEntityHippoTang.class, 12, 1, 6), new ArrayList<>(Arrays.asList(Type.BEACH, Type.OCEAN))));
        MoCreatures.mocEntityMap.put("Manderin", new MoCEntityData("Manderin", 6, overworld, EnumCreatureType.WATER_CREATURE, new SpawnListEntry(MoCEntityManderin.class, 12, 1, 6), new ArrayList<>(Arrays.asList(Type.BEACH, Type.OCEAN))));
        /*
         * Ambient
         */
        MoCreatures.mocEntityMap.put("Ant", new MoCEntityData("Ant", 4, overworld, EnumCreatureType.AMBIENT, new SpawnListEntry(MoCEntityAnt.class, 12, 1, 4), new ArrayList<>(Arrays.asList(Type.FOREST, Type.JUNGLE, Type.MESA, Type.PLAINS, Type.SWAMP, Type.HOT, Type.DRY, Type.LUSH, Type.SPARSE, STEEP))));
        MoCreatures.mocEntityMap.put("Bee", new MoCEntityData("Bee", 3, overworld, EnumCreatureType.AMBIENT, new SpawnListEntry(MoCEntityBee.class, 10, 1, 2), new ArrayList<>(Arrays.asList(Type.FOREST, Type.PLAINS))));
        MoCreatures.mocEntityMap.put("ButterFly", new MoCEntityData("ButterFly", 3, overworld, EnumCreatureType.AMBIENT, new SpawnListEntry(MoCEntityButterfly.class, 12, 1, 4), new ArrayList<>(Arrays.asList(Type.FOREST, Type.PLAINS))));
        MoCreatures.mocEntityMap.put("Crab", new MoCEntityData("Crab", 2, overworld, EnumCreatureType.AMBIENT, new SpawnListEntry(MoCEntityCrab.class, 11, 1, 2), new ArrayList<>(Arrays.asList(Type.BEACH, Type.WATER))));
        MoCreatures.mocEntityMap.put("Cricket", new MoCEntityData("Cricket", 2, overworld, EnumCreatureType.AMBIENT, new SpawnListEntry(MoCEntityCricket.class, 10, 1, 2), new ArrayList<>(Arrays.asList(Type.FOREST, Type.JUNGLE, Type.PLAINS, Type.SWAMP))));
        MoCreatures.mocEntityMap.put("DragonFly", new MoCEntityData("DragonFly", 2, overworldWyvernLair, EnumCreatureType.AMBIENT, new SpawnListEntry(MoCEntityDragonfly.class, 9, 1, 2), new ArrayList<>(Arrays.asList(Type.FOREST, Type.JUNGLE, Type.PLAINS, Type.SWAMP, Type.BEACH, Type.WET, WYVERN_LAIR))));
        MoCreatures.mocEntityMap.put("Firefly", new MoCEntityData("Firefly", 3, overworld, EnumCreatureType.AMBIENT, new SpawnListEntry(MoCEntityFirefly.class, 9, 1, 2), new ArrayList<>(Arrays.asList(Type.FOREST, Type.JUNGLE, Type.SWAMP, Type.LUSH, Type.DENSE, WYVERN_LAIR))));
        MoCreatures.mocEntityMap.put("Fly", new MoCEntityData("Fly", 2, overworld, EnumCreatureType.AMBIENT, new SpawnListEntry(MoCEntityFly.class, 12, 1, 2), new ArrayList<>(Arrays.asList(Type.JUNGLE, Type.MESA, Type.WET, Type.SWAMP, Type.HOT))));
        MoCreatures.mocEntityMap.put("Maggot", new MoCEntityData("Maggot", 2, overworld, EnumCreatureType.AMBIENT, new SpawnListEntry(MoCEntityMaggot.class, 8, 1, 2), new ArrayList<>(Arrays.asList(Type.JUNGLE, Type.MESA, Type.WET, Type.SWAMP, Type.HOT))));
        MoCreatures.mocEntityMap.put("Snail", new MoCEntityData("Snail", 2, overworld, EnumCreatureType.AMBIENT, new SpawnListEntry(MoCEntitySnail.class, 10, 1, 2), new ArrayList<>(Arrays.asList(Type.FOREST, Type.JUNGLE, Type.DENSE, Type.LUSH))));
        MoCreatures.mocEntityMap.put("Roach", new MoCEntityData("Roach", 2, overworld, EnumCreatureType.AMBIENT, new SpawnListEntry(MoCEntityRoach.class, 10, 1, 2), new ArrayList<>(Arrays.asList(Type.HOT, Type.WASTELAND))));
    }

    public static void registerSpawns() {
        // Read spawn properties from config
        MoCreatures.proxy.readMocConfigValues();

        // Iterate over all entities
        for (MoCEntityData entityData : MoCreatures.mocEntityMap.values()) {
            // Skip entities early that are disabled
            if (!entityData.getCanSpawn() || entityData.getFrequency() <= 0) {
                continue;
            }

            // Initialize list for valid spawn biomes
            List<Biome> spawnBiomes = new ArrayList<>();

            // Iterate over all biomes
            for (Biome biome : ForgeRegistries.BIOMES.getValuesCollection()) {
                boolean isBlocked = false;

                // Iterate over all blocked biome types
                for (BiomeDictionary.Type typeBlocked : entityData.getBlockedBiomeTypes()) {
                    // Check if biome has blocked biome type
                    // Set flag and skip when first blocked type is found
                    if (BiomeDictionary.hasType(biome, typeBlocked)) {
                        isBlocked = true;
                        break;
                    }
                }

                // Check if biome is not blocked and matches any of the allowed biome types
                // Add biome to list when valid
                if (!isBlocked && entityData.getBiomeTypes().stream().anyMatch(type -> BiomeDictionary.hasType(biome, type))) {
                    spawnBiomes.add(biome);

                    if (MoCreatures.proxy.debug) {
                        MoCreatures.LOGGER.debug("Entity {} is valid for biome {}", entityData.getEntityName(), biome.biomeName);
                    }
                }
            }

            // Register entity spawn with spawn properties from config and previously computed spawn biomes
            EntityRegistry.addSpawn(entityData.getEntityClass(), entityData.getFrequency(), entityData.getMinSpawn(), entityData.getMaxSpawn(), entityData.getType(), spawnBiomes.toArray(new Biome[0]));
        }

        MoCreatures.LOGGER.info("Entity spawn registration complete.");
    }
}
