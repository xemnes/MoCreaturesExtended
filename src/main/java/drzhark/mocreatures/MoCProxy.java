/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures;

import drzhark.mocreatures.configuration.MoCConfigCategory;
import drzhark.mocreatures.configuration.MoCConfiguration;
import drzhark.mocreatures.configuration.MoCProperty;
import drzhark.mocreatures.entity.IMoCEntity;
import drzhark.mocreatures.entity.hostile.MoCEntityGolem;
import drzhark.mocreatures.entity.passive.MoCEntityHorse;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoCProxy implements IGuiHandler {

    protected static final String CATEGORY_MOC_GENERAL_SETTINGS = "global-settings";
    protected static final String CATEGORY_MOC_CREATURE_GENERAL_SETTINGS = "creature-general-settings";
    protected static final String CATEGORY_MOC_MONSTER_GENERAL_SETTINGS = "monster-general-settings";
    protected static final String CATEGORY_MOC_WATER_CREATURE_GENERAL_SETTINGS = "water-mob-general-settings";
    protected static final String CATEGORY_MOC_AMBIENT_GENERAL_SETTINGS = "ambient-general-settings";
    protected static final String CATEGORY_MOC_ID_SETTINGS = "custom-id-settings";
    private static final String CATEGORY_OWNERSHIP_SETTINGS = "ownership-settings";
    public static String ARMOR_TEXTURE = "textures/armor/";
    public static String BLOCK_TEXTURE = "textures/blocks/";
    public static String ITEM_TEXTURE = "textures/items/";
    public static String MODEL_TEXTURE = "textures/models/";
    public static String GUI_TEXTURE = "textures/gui/";
    public static String MISC_TEXTURE = "textures/misc/";

    // CONFIG VARIABLES
    public boolean displayPetHealth;
    public boolean displayPetName;
    public boolean displayPetIcons;
    public boolean animateTextures;
    public boolean attackWolves;
    public boolean attackHorses;
    public boolean staticBed;
    public boolean staticLitter;
    public boolean easyHorseBreeding;
    public boolean destroyDrops;
    public boolean enableOwnership;
    public boolean enableResetOwnership;
    public boolean elephantBulldozer;
    public boolean golemDestroyBlocks;
    public int wyvernDimension;
    public int maxTamed;
    public int maxOPTamed;
    public int zebraChance;
    public int ostrichEggDropChance;
    public int rareItemDropChance;
    public int wyvernEggDropChance;
    public int motherWyvernEggDropChance;
    public int particleFX;
    public float ogreStrength;
    public float ogreCaveStrength;
    public float ogreFireStrength;
    public short ogreAttackRange;
    public boolean enableHunters;
    public boolean debug = false;
    public boolean allowInstaSpawn;
    public boolean worldInitDone = false;
    public double spawnMultiplier;
    public int kittyVillageChance;
    public boolean humanWerewolfSounds;
    public boolean legacyRatDeathSound;
    public boolean classicWraithSounds;
    public boolean classicWyvernLairSky;
    public boolean darkerWyvernLair;
    public boolean foggyWyvernLair;
    public boolean simpleBunnyTextures;
    public boolean verboseEntityNames;
    public boolean legacyBigCatModels;
    public boolean alphaWraithEyes;
    public MoCConfiguration mocSettingsConfig;
    public MoCConfiguration mocEntityConfig;
    protected File configFile;

    public void resetAllData() {
        this.readGlobalConfigValues();
    }

    //----------------CONFIG INITIALIZATION
    public void configInit(FMLPreInitializationEvent event) {
        this.mocSettingsConfig = new MoCConfiguration(new File(event.getSuggestedConfigurationFile().getParent(), "MoCreatures" + File.separator + "MoCSettings.cfg"));
        this.mocEntityConfig = new MoCConfiguration(new File(event.getSuggestedConfigurationFile().getParent(), "MoCreatures" + File.separator + "MoCreatures.cfg"));
        this.configFile = event.getSuggestedConfigurationFile();
        this.mocSettingsConfig.load();
        this.mocEntityConfig.load();
        this.readGlobalConfigValues();
        if (this.debug) {
            MoCreatures.LOGGER.info("Initializing MoCreatures Config File at " + event.getSuggestedConfigurationFile().getParent() + "MoCSettings.cfg");
        }
    }

    //-----------------THE FOLLOWING ARE CLIENT SIDE ONLY, NOT TO BE USED IN SERVER AS THEY AFFECT ONLY DISPLAY / SOUNDS

    public void UndeadFX(Entity entity) {
    }

    public void StarFX(MoCEntityHorse moCEntityHorse) {
    }

    public void LavaFX(Entity entity) {
    }

    public void VanishFX(MoCEntityHorse entity) {
    }

    public void MaterializeFX(MoCEntityHorse entity) {
    }

    public void VacuumFX(MoCEntityGolem entity) {
    }

    public void hammerFX(EntityPlayer entityplayer) {
    }

    public void teleportFX(EntityPlayer entity) {
    }

    public boolean getAnimateTextures() {
        return false;
    }

    public boolean getDisplayPetName() {
        return this.displayPetName;
    }

    public boolean getDisplayPetIcons() {
        return this.displayPetIcons;
    }

    public boolean getDisplayPetHealth() {
        return this.displayPetHealth;
    }

    public int getParticleFX() {
        return 0;
    }

    public ResourceLocation getArmorTexture(String texture) {
        return null;
    }

    public ResourceLocation getBlockTexture(String texture) {
        return null;
    }

    public ResourceLocation getItemTexture(String texture) {
        return null;
    }

    public ResourceLocation getModelTexture(String texture) {
        return null;
    }

    public ResourceLocation getGuiTexture(String texture) {
        return null;
    }

    public ResourceLocation getMiscTexture(String texture) {
        return null;
    }

    public EntityPlayer getPlayer() {
        return null;
    }

    // Client side only
    public void printMessageToPlayer(String msg) {
    }

    public List<BiomeDictionary.Type> parseBiomeTypes(String[] biomeNames) {
        List<BiomeDictionary.Type> biomeTypes = new ArrayList<>();
        for (String biomeName : biomeNames) {
            BiomeDictionary.Type biomeType = BiomeDictionary.Type.getType(biomeName);
            biomeTypes.add(biomeType);
        }
        return biomeTypes;
    }

    public void readMocConfigValues() {
        if (MoCreatures.mocEntityMap != null && !MoCreatures.mocEntityMap.isEmpty()) {
            for (MoCEntityData entityData : MoCreatures.mocEntityMap.values()) {
                MoCConfigCategory cat = this.mocEntityConfig.getCategory(entityData.getEntityName().toLowerCase());
                if (!cat.containsKey("biomeTypes")) {
                    cat.put("biomeTypes", new MoCProperty("biomeTypes", Arrays.toString(entityData.getBiomeTypes().toArray()), MoCProperty.Type.STRING));
                } else {
                    entityData.setBiomeTypes(parseBiomeTypes(cat.get("biomeTypes").value.replaceAll(" ", "").replaceAll("\\[", "").replaceAll("]", "").split(",")));
                }
                if (!cat.containsKey("blockedBiomeTypes")) {
                    cat.put("blockedBiomeTypes", new MoCProperty("blockedBiomeTypes", Arrays.toString(entityData.getBlockedBiomeTypes().toArray()), MoCProperty.Type.STRING));
                } else {
                    entityData.setBlockedBiomeTypes(parseBiomeTypes(cat.get("blockedBiomeTypes").value.replaceAll(" ", "").replaceAll("\\[", "").replaceAll("]", "").split(",")));
                }
                if (!cat.containsKey("canSpawn")) {
                    cat.put("canSpawn", new MoCProperty("canSpawn", Boolean.toString(entityData.getCanSpawn()), MoCProperty.Type.BOOLEAN));
                } else {
                    entityData.setCanSpawn(Boolean.parseBoolean(cat.get("canSpawn").value));
                }
                if (!cat.containsKey("dimensions")) {
                    cat.put("dimensions", new MoCProperty("dimensions", Arrays.toString(entityData.getDimensions()), MoCProperty.Type.STRING));
                } else {
                    entityData.setDimensions(Arrays.stream(cat.get("dimensions").value.replaceAll(" ", "").replaceAll("\\[", "").replaceAll("]", "").split(",")).mapToInt(Integer::parseInt).toArray());
                }
                if (!cat.containsKey("frequency")) {
                    cat.put("frequency", new MoCProperty("frequency", Integer.toString(entityData.getFrequency()), MoCProperty.Type.INTEGER));
                } else {
                    entityData.setFrequency(Integer.parseInt(cat.get("frequency").value));
                }
                //if (!cat.containsKey("maxChunk")) {
                //    cat.put("maxChunk", new MoCProperty("maxChunk", Integer.toString(entityData.getMaxInChunk()), MoCProperty.Type.INTEGER));
                //} else {
                //    entityData.setMaxInChunk(Integer.parseInt(cat.get("maxChunk").value));
                //}
                if (!cat.containsKey("maxSpawn")) {
                    cat.put("maxSpawn", new MoCProperty("maxSpawn", Integer.toString(entityData.getMaxSpawn()), MoCProperty.Type.INTEGER));
                } else {
                    entityData.setMaxSpawn(Integer.parseInt(cat.get("maxSpawn").value));
                }
                if (!cat.containsKey("minSpawn")) {
                    cat.put("minSpawn", new MoCProperty("minSpawn", Integer.toString(entityData.getMinSpawn()), MoCProperty.Type.INTEGER));
                } else {
                    entityData.setMinSpawn(Integer.parseInt(cat.get("minSpawn").value));
                }
            }
        }
        this.mocEntityConfig.save();
    }

    /**
     * Reads values from file
     */
    public void readGlobalConfigValues() {
        // Client side only
        this.displayPetHealth = this.mocSettingsConfig.get(CATEGORY_MOC_GENERAL_SETTINGS, "DisplayPetHealth", true, "Shows the health of pets.").getBoolean(true);
        this.displayPetName = this.mocSettingsConfig.get(CATEGORY_MOC_GENERAL_SETTINGS, "DisplayPetName", true, "Shows the name of pets.").getBoolean(true);
        this.displayPetIcons = this.mocSettingsConfig.get(CATEGORY_MOC_GENERAL_SETTINGS, "DisplayPetIcons", true, "Shows the emotes of pets.").getBoolean(true);
        this.animateTextures = this.mocSettingsConfig.get(CATEGORY_MOC_GENERAL_SETTINGS, "AnimateTextures", true, "Enables animated textures.").getBoolean(true);

        // General
        //this.allowInstaSpawn = this.mocSettingsConfig.get(CATEGORY_MOC_GENERAL_SETTINGS, "AllowInstaSpawn", false, "Allows you to instantly spawn MoCreatures from GUI.").getBoolean(false);
        this.debug = this.mocSettingsConfig.get(CATEGORY_MOC_GENERAL_SETTINGS, "Debug", false, "Turns on verbose logging.").getBoolean(false);
        this.maxTamed = this.mocSettingsConfig.get(CATEGORY_OWNERSHIP_SETTINGS, "MaxTamedPerPlayer", 10, "Maximum tamed creatures a player can have. Requires EnableOwnership to be enabled.").getInt();
        this.maxOPTamed = this.mocSettingsConfig.get(CATEGORY_OWNERSHIP_SETTINGS, "MaxTamedPerOP", 20, "Maximum tamed creatures an OP can have. Requires EnableOwnership to be enabled.").getInt();
        this.enableOwnership = this.mocSettingsConfig.get(CATEGORY_OWNERSHIP_SETTINGS, "EnableOwnership", false, "Assigns the player as the owner for each creature they tame. Only the owner can interact with the tamed creature.").getBoolean(false);
        this.enableResetOwnership = this.mocSettingsConfig.get(CATEGORY_OWNERSHIP_SETTINGS, "EnableResetOwnerScroll", false, "Allows players to remove a tamed creature's owner, essentially untaming it.").getBoolean(false);
        this.easyHorseBreeding = this.mocSettingsConfig.get(CATEGORY_MOC_CREATURE_GENERAL_SETTINGS, "EasyHorseBreeding", false, "Guarantees a new horse mix on every breed.").getBoolean(false);
        this.elephantBulldozer = this.mocSettingsConfig.get(CATEGORY_MOC_CREATURE_GENERAL_SETTINGS, "ElephantBulldozer", true, "Makes elephants destroy blocks in front of them when riding.").getBoolean(true);
        this.zebraChance = this.mocSettingsConfig.get(CATEGORY_MOC_CREATURE_GENERAL_SETTINGS, "ZebraChance", 10, "The percentage for spawning a zebra.").getInt();
        this.ostrichEggDropChance = this.mocSettingsConfig.get(CATEGORY_MOC_CREATURE_GENERAL_SETTINGS, "OstrichEggDropChance", 3, "The percentage for ostriches to drop an egg.").getInt();
        this.staticBed = this.mocSettingsConfig.get(CATEGORY_MOC_CREATURE_GENERAL_SETTINGS, "StaticBed", true, "Makes the kitty bed not pushable.").getBoolean(true);
        this.staticLitter = this.mocSettingsConfig.get(CATEGORY_MOC_CREATURE_GENERAL_SETTINGS, "StaticLitter", true, "Makes the kitty litter box not pushable.").getBoolean(true);
        this.particleFX = this.mocSettingsConfig.get(CATEGORY_MOC_GENERAL_SETTINGS, "ParticleFX", 3, "The density of MoCreatures particle effects.").getInt();
        this.attackHorses = this.mocSettingsConfig.get(CATEGORY_MOC_CREATURE_GENERAL_SETTINGS, "AttackHorses", false, "Allows creatures to attack horses.").getBoolean(false);
        this.attackWolves = this.mocSettingsConfig.get(CATEGORY_MOC_CREATURE_GENERAL_SETTINGS, "AttackWolves", false, "Allows creatures to attack wolves.").getBoolean(false);
        this.enableHunters = this.mocSettingsConfig.get(CATEGORY_MOC_CREATURE_GENERAL_SETTINGS, "EnableHunters", true, "Allows creatures to attack other creatures. Not recommended if despawning is off.").getBoolean(true);
        this.destroyDrops = this.mocSettingsConfig.get(CATEGORY_MOC_CREATURE_GENERAL_SETTINGS, "DestroyDrops", false, "Destroys animal drops when not killed by a player.").getBoolean(false);
        this.rareItemDropChance = this.mocSettingsConfig.get(CATEGORY_MOC_CREATURE_GENERAL_SETTINGS, "RareItemDropChance", 25, "The percentage for horses, ostriches, scorpions etc. to drop a rare item such as a heart of darkness, unicorn, bone when killed.").getInt();
        this.wyvernEggDropChance = this.mocSettingsConfig.get(CATEGORY_MOC_CREATURE_GENERAL_SETTINGS, "WyvernEggDropChance", 33, "The percentage for wyverns to drop an egg.").getInt();
        this.motherWyvernEggDropChance = this.mocSettingsConfig.get(CATEGORY_MOC_CREATURE_GENERAL_SETTINGS, "MotherWyvernEggDropChance", 66, "The percentage for mother wyverns to drop an egg.").getInt();
        this.ogreStrength = Float.parseFloat(this.mocSettingsConfig.get(CATEGORY_MOC_MONSTER_GENERAL_SETTINGS, "OgreStrength", 2.0F, "The block destruction radius of green ogres.").getString());
        this.ogreCaveStrength = Float.parseFloat(this.mocSettingsConfig.get(CATEGORY_MOC_MONSTER_GENERAL_SETTINGS, "CaveOgreStrength", 2.5F, "The block destruction radius of cave ogres.").getString());
        this.ogreFireStrength = Float.parseFloat(this.mocSettingsConfig.get(CATEGORY_MOC_MONSTER_GENERAL_SETTINGS, "FireOgreStrength", 2.0F, "The block destruction radius of fire ogres.").getString());
        this.ogreAttackRange = (short) this.mocSettingsConfig.get(CATEGORY_MOC_MONSTER_GENERAL_SETTINGS, "OgreAttackRange", 12, "The block radius where ogres 'smell' players.").getInt();
        this.golemDestroyBlocks = this.mocSettingsConfig.get(CATEGORY_MOC_MONSTER_GENERAL_SETTINGS, "GolemDestroyBlocks", true, "Allows big golems to break blocks.").getBoolean(true);
        this.wyvernDimension = this.mocSettingsConfig.get(CATEGORY_MOC_ID_SETTINGS, "WyvernLairDimensionID", -17, "The dimension ID of the wyvern lair.").getInt();
        this.spawnMultiplier = this.mocSettingsConfig.get(CATEGORY_MOC_CREATURE_GENERAL_SETTINGS, "SpawnMultiplier", 2.0D, "Multiplier for entity spawns during world generation.").getDouble(2.0D);
        this.kittyVillageChance = this.mocSettingsConfig.get(CATEGORY_MOC_CREATURE_GENERAL_SETTINGS, "KittyVillageChance", 15, "The percentage for kitties spawning in village chunks.").getInt();
        this.humanWerewolfSounds = this.mocSettingsConfig.get(CATEGORY_MOC_CREATURE_GENERAL_SETTINGS, "HumanWerewolfSounds", false, "Enables legacy human werewolf sounds.").getBoolean(false);
        this.classicWraithSounds = this.mocSettingsConfig.get(CATEGORY_MOC_CREATURE_GENERAL_SETTINGS, "ClassicWraithSounds", false, "Enables legacy wraith sounds.").getBoolean(false);
        this.classicWyvernLairSky = this.mocSettingsConfig.get(CATEGORY_MOC_GENERAL_SETTINGS, "ClassicWyvernLairSky", false, "Enables legacy wyvern lair sky.").getBoolean(false);
        this.darkerWyvernLair = this.mocSettingsConfig.get(CATEGORY_MOC_GENERAL_SETTINGS, "DarkerWyvernLair", false, "Disables sky light at the wyvern lair like in legacy versions. It is highly recommended to also have the classic wyvern lair sky enabled.").getBoolean(false);
        this.foggyWyvernLair = this.mocSettingsConfig.get(CATEGORY_MOC_GENERAL_SETTINGS, "FoggyWyvernLair", false, "Enables extra fog at the wyvern lair like in legacy versions.").getBoolean(false);
        this.simpleBunnyTextures = this.mocSettingsConfig.get(CATEGORY_MOC_CREATURE_GENERAL_SETTINGS, "SimpleBunnyTextures", false, "Enables simple bunny textures like in legacy versions.").getBoolean(false);
        this.verboseEntityNames = this.mocSettingsConfig.get(CATEGORY_MOC_CREATURE_GENERAL_SETTINGS, "VerboseEntityNames", false, "Enables detailed names for creatures, describing their species.").getBoolean(false);
        this.legacyBigCatModels = this.mocSettingsConfig.get(CATEGORY_MOC_CREATURE_GENERAL_SETTINGS, "LegacyBigCatModels", false, "Enables simple big cat models and textures like in legacy versions.").getBoolean(false);
        this.alphaWraithEyes = this.mocSettingsConfig.get(CATEGORY_MOC_CREATURE_GENERAL_SETTINGS, "AlphaWraithEyes", false, "Enables different eye colors for wraiths and flame wraiths like in alpha versions.").getBoolean(false);
        this.legacyRatDeathSound = this.mocSettingsConfig.get(CATEGORY_MOC_CREATURE_GENERAL_SETTINGS, "LegacyRatDeathSound", false, "Enables legacy rat death sound.").getBoolean(false);

        // Save
        this.mocSettingsConfig.save();
    }

    // Client side only
    public void registerRenderers() {
    }

    // Client side only
    public void registerRenderInformation() {
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        // TODO Auto-generated method stub
        return null;
    }

    /***
     * Dummy to know if is dedicated server or not
     */
    public int getProxyMode() {
        return 1;
    }

    /**
     * Sets the name on client side. Name is synchronized with data watchers
     */
    public void setName(EntityPlayer player, IMoCEntity mocanimal) {
    }
}
