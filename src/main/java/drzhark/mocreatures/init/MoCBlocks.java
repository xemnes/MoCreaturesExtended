/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.init;

import com.google.common.base.Preconditions;
import drzhark.mocreatures.MoCConstants;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.block.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;

@SuppressWarnings("deprecation")
@Mod.EventBusSubscriber(modid = MoCConstants.MOD_ID)
@GameRegistry.ObjectHolder(MoCConstants.MOD_ID)
public class MoCBlocks {

    @GameRegistry.ObjectHolder("wyvstone")
    public static Block wyvstone;
    @GameRegistry.ObjectHolder("wyvgrass")
    public static Block wyvgrass;
    @GameRegistry.ObjectHolder("wyvdirt")
    public static Block wyvdirt;
    @GameRegistry.ObjectHolder("wyvwood_leaves")
    public static Block wyvwoodLeaves;
    @GameRegistry.ObjectHolder("wyvwood_log")
    public static Block wyvwoodLog;
    @GameRegistry.ObjectHolder("tall_wyvgrass")
    public static Block tallWyvgrass;
    @GameRegistry.ObjectHolder("wyvwood_planks")
    public static Block wyvwoodPlanks;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
                setup(new MoCBlockRock(MapColor.STONE), "wyvstone").setHardness(1.5F).setResistance(10.0F),
                setup(new MoCBlockGrass(MapColor.BLUE_STAINED_HARDENED_CLAY), "wyvgrass").setHardness(0.5F),
                setup(new MoCBlockDirt(MapColor.DIRT), "wyvdirt").setHardness(0.6F),
                setup(new MoCBlockLeaf(MapColor.DIAMOND, true, 20), "wyvwood_leaves").setHardness(0.2F).setLightOpacity(1),
                setup(new MoCBlockLog(MapColor.CYAN_STAINED_HARDENED_CLAY, true), "wyvwood_log").setHardness(2.0F),
                setup(new MoCBlockTallGrass(MapColor.LIGHT_BLUE_STAINED_HARDENED_CLAY, false), "tall_wyvgrass").setHardness(0.0F),
                setup(new MoCBlockPlanks(MapColor.DIAMOND, false), "wyvwood_planks").setHardness(2.0F).setResistance(5.0F)
        );
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();
        ForgeRegistries.BLOCKS.getValues().stream()
                .filter(block -> block.getRegistryName().getNamespace().equals(MoCConstants.MOD_ID))
                .forEach(block -> registry.register(setup(new ItemBlock(block), block.getRegistryName())));

    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        for (Item item : ForgeRegistries.ITEMS.getValues()) {
            if (item.getRegistryName().getNamespace().equals(MoCConstants.MOD_ID)) {
                ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "normal"));
            }
        }
    }

    @Nonnull
    public static <T extends IForgeRegistryEntry> T setup(T entry, String name) {
        return setup(entry, new ResourceLocation(MoCConstants.MOD_ID, name));
    }

    @Nonnull
    public static <T extends IForgeRegistryEntry> T setup(T entry, ResourceLocation registryName) {
        Preconditions.checkNotNull(entry, "Entry to setup must not be null!");
        Preconditions.checkNotNull(registryName, "Registry name to assign must not be null!");
        entry.setRegistryName(registryName);
        if (entry instanceof Block) {
            ((Block) entry).setTranslationKey(registryName.getNamespace() + "." + registryName.getPath()).setCreativeTab(MoCreatures.tabMoC);
        }
        if (entry instanceof Item) {
            ((Item) entry).setTranslationKey(registryName.getNamespace() + "." + registryName.getPath()).setCreativeTab(MoCreatures.tabMoC);
        }
        return entry;
    }
}
