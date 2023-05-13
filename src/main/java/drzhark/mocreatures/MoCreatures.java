/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures;

import com.mojang.authlib.GameProfile;
import drzhark.mocreatures.client.MoCClientTickHandler;
import drzhark.mocreatures.client.MoCCreativeTabs;
import drzhark.mocreatures.client.handlers.MoCKeyHandler;
import drzhark.mocreatures.command.CommandMoCPets;
import drzhark.mocreatures.command.CommandMoCSpawn;
import drzhark.mocreatures.command.CommandMoCTP;
import drzhark.mocreatures.command.CommandMoCreatures;
import drzhark.mocreatures.datafixer.EntityDataWalker;
import drzhark.mocreatures.dimension.WorldProviderWyvernEnd;
import drzhark.mocreatures.init.MoCEntities;
import drzhark.mocreatures.network.MoCMessageHandler;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

@Mod(modid = MoCConstants.MOD_ID, name = MoCConstants.MOD_NAME, version = MoCConstants.MOD_VERSION, acceptableRemoteVersions = MoCConstants.MOD_ACCEPTED_VERSIONS, dependencies = MoCConstants.MOD_DEPENDENCIES)
public class MoCreatures {

    public static final Logger LOGGER = LogManager.getLogger(MoCConstants.MOD_ID);
    public static final CreativeTabs tabMoC = new MoCCreativeTabs(CreativeTabs.CREATIVE_TAB_ARRAY.length, "MoCreaturesTab");
    public static final String MOC_LOGO = TextFormatting.WHITE + "[" + TextFormatting.AQUA + "Mo'Creatures" + TextFormatting.WHITE + "]";
    @Instance(MoCConstants.MOD_ID)
    public static MoCreatures instance;
    @SidedProxy(clientSide = "drzhark.mocreatures.client.MoCClientProxy", serverSide = "drzhark.mocreatures.MoCProxy")
    public static MoCProxy proxy;
    public static boolean isCustomSpawnerLoaded = false;
    public static GameProfile MOCFAKEPLAYER = new GameProfile(UUID.fromString("6E379B45-1111-2222-3333-2FE1A88BCD66"), "[MoCreatures]");
    public static DimensionType WYVERN_LAIR;
    public static int WyvernLairDimensionID; // -17
    public static Object2ObjectLinkedOpenHashMap<String, MoCEntityData> mocEntityMap = new Object2ObjectLinkedOpenHashMap<>();
    public static Object2ObjectOpenHashMap<Class<? extends EntityLiving>, MoCEntityData> entityMap = new Object2ObjectOpenHashMap<>();
    public static Int2ObjectOpenHashMap<Class<? extends EntityLiving>> instaSpawnerMap = new Int2ObjectOpenHashMap<>();
    public MoCPetMapData mapData;

    public static void burnPlayer(EntityPlayer player) {
        //TODO 4FIX
        //if (!mc.world.isRemote)
        //{
        //    inst.burned = true;
        //}
    }

    //how to check for client: if(FMLCommonHandler.instance().getSide().isRemote())

    public static void freezePlayer(EntityPlayer player) {
        //TODO 4FIX
        //if (!mc.world.isRemote)
        //{
        //    inst.freezed = true;
        //    inst.freezedcounter = 0;
        //}
    }

    public static void poisonPlayer(EntityPlayer player) {
        //TODO 4FIX
        //if (!mc.world.isRemote)
        //{
        //    inst.poisoned = true;
        //    inst.poisoncounter = 0;
        //}
    }

    public static void showCreaturePedia(EntityPlayer player, String s) {
        //TODO 4FIX        mc.displayGuiScreen(new MoCGUIPedia(s, 256, 256));
    }

    public static void showCreaturePedia(String s) {
        //TODO 4FIX        EntityPlayer entityplayer = mc.player;
        //showCreaturePedia(entityplayer, s);
    }

    public static void updateSettings() {
        proxy.readGlobalConfigValues();
    }

    public static boolean isServer() {
        return (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER);
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        if (isServer()) {
            FMLCommonHandler.instance().getMinecraftServerInstance().getDataFixer().registerWalker(FixTypes.ENTITY, new EntityDataWalker());
        }
        MoCMessageHandler.init();
        MinecraftForge.EVENT_BUS.register(new MoCEventHooks());
        MinecraftForge.TERRAIN_GEN_BUS.register(new MoCTerrainEventHooks());
        proxy.ConfigInit(event);
        proxy.initTextures();
        if (!isServer()) {
            MinecraftForge.EVENT_BUS.register(new MoCClientTickHandler());
            MinecraftForge.EVENT_BUS.register(new MoCKeyHandler());
        }
        MoCEntities.registerEntities();
    }

    @EventHandler
    public void load(FMLInitializationEvent event) {
        WyvernLairDimensionID = proxy.WyvernDimension;
        proxy.mocSettingsConfig.save();
        proxy.registerRenderers();
        proxy.registerRenderInformation();
        WYVERN_LAIR = DimensionType.register("Wyvern Lair", "_wyvern_lair", WyvernLairDimensionID, WorldProviderWyvernEnd.class, false);
        DimensionManager.registerDimension(WyvernLairDimensionID, WYVERN_LAIR);
        MoCTerrainEventHooks.addBiomeTypes();
        MoCEntities.registerSpawns();
        MoCTerrainEventHooks.buildWorldGenSpawnLists();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        isCustomSpawnerLoaded = Loader.isModLoaded("customspawner");
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandMoCreatures());
        event.registerServerCommand(new CommandMoCTP());
        event.registerServerCommand(new CommandMoCPets());
        if (isServer()) {
            if (FMLCommonHandler.instance().getMinecraftServerInstance().isDedicatedServer()) {
                event.registerServerCommand(new CommandMoCSpawn());
            }
        }
    }
}
