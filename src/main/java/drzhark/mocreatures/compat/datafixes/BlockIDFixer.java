/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.compat.datafixes;

import drzhark.mocreatures.MoCConstants;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.IFixableData;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

public class BlockIDFixer implements IFixableData {
    private static final Map<ResourceLocation, ResourceLocation> BLOCK_NAME_MAPPINGS = new HashMap<>();

    static {
        BLOCK_NAME_MAPPINGS.put(new ResourceLocation(MoCConstants.MOD_ID, "MoCStone"), new ResourceLocation(MoCConstants.MOD_ID, "wyvstone"));
        BLOCK_NAME_MAPPINGS.put(new ResourceLocation(MoCConstants.MOD_ID, "MoCGrass"), new ResourceLocation(MoCConstants.MOD_ID, "wyvgrass"));
        BLOCK_NAME_MAPPINGS.put(new ResourceLocation(MoCConstants.MOD_ID, "MoCDirt"), new ResourceLocation(MoCConstants.MOD_ID, "wyvdirt"));
        BLOCK_NAME_MAPPINGS.put(new ResourceLocation(MoCConstants.MOD_ID, "MoCLeaves"), new ResourceLocation(MoCConstants.MOD_ID, "wyvwood_leaves"));
        BLOCK_NAME_MAPPINGS.put(new ResourceLocation(MoCConstants.MOD_ID, "MoCLog"), new ResourceLocation(MoCConstants.MOD_ID, "wyvwood_log"));
        BLOCK_NAME_MAPPINGS.put(new ResourceLocation(MoCConstants.MOD_ID, "MoCTallGrass"), new ResourceLocation(MoCConstants.MOD_ID, "tall_wyvgrass"));
        BLOCK_NAME_MAPPINGS.put(new ResourceLocation(MoCConstants.MOD_ID, "MoCWoodPlank"), new ResourceLocation(MoCConstants.MOD_ID, "wyvwood_planks"));
    }

    public BlockIDFixer() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public int getFixVersion() {
        return 1;
    }

    @Override
    public NBTTagCompound fixTagCompound(NBTTagCompound compound) {
        return compound;
    }

    @SubscribeEvent
    public void missingBlockMapping(RegistryEvent.MissingMappings<Block> event) {
        for (RegistryEvent.MissingMappings.Mapping<Block> entry : event.getAllMappings()) {
            ResourceLocation oldName = entry.key;
            ResourceLocation newName = BLOCK_NAME_MAPPINGS.get(oldName);
            if (newName != null) {
                Block newBlock = ForgeRegistries.BLOCKS.getValue(newName);
                if (newBlock != null) {
                    entry.remap(newBlock);
                }
            }
        }
    }

    @SubscribeEvent
    public void missingItemBlockMapping(RegistryEvent.MissingMappings<Item> event) {
        for (RegistryEvent.MissingMappings.Mapping<Item> entry : event.getAllMappings()) {
            ResourceLocation oldName = entry.key;
            ResourceLocation newName = BLOCK_NAME_MAPPINGS.get(oldName);
            if (newName != null) {
                Item newItem = ForgeRegistries.ITEMS.getValue(newName);
                if (newItem != null) {
                    entry.remap(newItem);
                }
            }
        }
    }
}
