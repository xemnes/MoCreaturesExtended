/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.init;

import com.google.common.base.Preconditions;
import drzhark.mocreatures.MoCConstants;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.block.*;
import net.minecraft.block.Block;
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

@Mod.EventBusSubscriber(modid = MoCConstants.MOD_ID)
@GameRegistry.ObjectHolder(MoCConstants.MOD_ID)
public class MoCBlocks {

    @GameRegistry.ObjectHolder("wyvern_stone")
    public static Block wyvernStone;
    @GameRegistry.ObjectHolder("wyvern_grass")
    public static Block wyvernGrass;
    @GameRegistry.ObjectHolder("wyvern_dirt")
    public static Block wyvernDirt;
    @GameRegistry.ObjectHolder("wyvern_leaves")
    public static Block wyvernLeaves;
    @GameRegistry.ObjectHolder("wyvern_log")
    public static Block wyvernLog;
    @GameRegistry.ObjectHolder("wyvern_tallgrass")
    public static Block wyvernTallGrass;
    @GameRegistry.ObjectHolder("wyvern_planks")
    public static Block wyvernPlanks;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
                setup(new MoCBlockRock(), "wyvern_stone").setHardness(1.5F).setResistance(10.0F),
                setup(new MoCBlockGrass(), "wyvern_grass").setHardness(0.5F),
                setup(new MoCBlockDirt(), "wyvern_dirt").setHardness(0.6F),
                setup(new MoCBlockLeaf(), "wyvern_leaves").setHardness(0.2F).setLightOpacity(1),
                setup(new MoCBlockLog(), "wyvern_log").setHardness(2.0F),
                setup(new MoCBlockTallGrass(), "wyvern_tallgrass").setHardness(0.0F),
                setup(new MoCBlockPlanks(), "wyvern_planks").setHardness(2.0F).setResistance(5.0F)
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
