/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.init;

import drzhark.mocreatures.MoCConstants;
import drzhark.mocreatures.dimension.biome.MoCBiomeWyvernIsles;
import drzhark.mocreatures.dimension.biome.MoCBiomeWyvernIslesDesert;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

@ObjectHolder(MoCConstants.MOD_ID)
public class MoCBiomes {

    public static Biome wyvernIsles = new MoCBiomeWyvernIsles(new BiomeProperties("Wyvern Isles")
            .setBaseHeight(0.3F)
            .setHeightVariation(1.5F)
            .setTemperature(0.25F)
            .setWaterColor(0x775757));

    public static Biome wyvernDesertIsles = new MoCBiomeWyvernIslesDesert(new BiomeProperties("Wyvern Desert Isles")
            .setBaseHeight(0.3F)
            .setHeightVariation(1.5F)
            .setTemperature(0.25F)
            .setWaterColor(0x775757));

    @Mod.EventBusSubscriber(modid = MoCConstants.MOD_ID)
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerBiomes(final RegistryEvent.Register<Biome> event) {
            final IForgeRegistry<Biome> registry = event.getRegistry();
            registerBiome(registry, wyvernIsles, "wyvern_isles", MoCEntities.WYVERN_LAIR);
            registerBiome(registry, wyvernDesertIsles, "wyvern_desert_isles", MoCEntities.WYVERN_LAIR);
        }

        private static <T extends Biome> void registerBiome(final IForgeRegistry<Biome> registry, final T biome, final String biomeName, final BiomeDictionary.Type... types) {
            registry.register(biome.setRegistryName(MoCConstants.MOD_ID, biomeName));
            BiomeDictionary.addTypes(biome, types);
        }
    }
}
