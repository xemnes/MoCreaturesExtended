/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.dimension;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BiomeProviderWyvernLair extends BiomeProvider {

    private final float hellTemperature;
    /**
     * The rainfall in the world
     */
    private final float rainfall;
    /**
     * The biome generator object.
     */
    private final Biome biomeGenerator;

    public BiomeProviderWyvernLair(Biome par1Biome, float par2, float par3) {
        this.biomeGenerator = par1Biome;
        this.hellTemperature = par2;
        this.rainfall = par3;
    }

    /**
     * Returns the Biome related to the x, z position on the world.
     */
    public Biome getBiomeGenAt(int par1, int par2) {
        return this.biomeGenerator;
    }

    /**
     * Returns an array of biomes for the location input.
     */
    @Override
    public Biome[] getBiomesForGeneration(Biome[] biomes, int xStart, int zStart, int xSize, int zSize) {
        return getBiomes(biomes, xStart, zStart, xSize, zSize, true);
    }

    /**
     * Returns a list of temperatures to use for the specified blocks. Args:
     * listToReuse, x, y, width, length
     */
    public float[] getTemperatures(float[] par1ArrayOfFloat, int xStart, int zStart, int xSize, int zSize) {
        if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < xSize * zSize) {
            par1ArrayOfFloat = new float[xSize * zSize];
        }

        Arrays.fill(par1ArrayOfFloat, 0, xSize * zSize, this.hellTemperature);
        return par1ArrayOfFloat;
    }

    /**
     * Returns biomes to use for the blocks and loads the other data like
     * temperature and humidity onto the WorldChunkManager Args: oldBiomeList,
     * x, z, width, depth
     */
    @Override
    public Biome[] getBiomes(@Nullable Biome[] biomes, int xStart, int zStart, int xSize, int zSize) {
        return getBiomes(biomes, xStart, zStart, xSize, zSize, true);
    }

    /**
     * Return a list of biomes for the specified blocks. Args: listToReuse, x,
     * y, width, length, cacheFlag (if false, don't check biomeCache to avoid
     * infinite loop in BiomeCacheBlock)
     */
    @Override
    public Biome[] getBiomes(@Nullable Biome[] biomes, int xStart, int zStart, int xSize, int zSize, boolean par6) {
        if (biomes == null || biomes.length < xSize * zSize) {
            biomes = new Biome[xSize * zSize];
        }

        Arrays.fill(biomes, 0, xSize * zSize, this.biomeGenerator);
        return biomes;
    }

    /**
     * Finds a valid position within a range, that is in one of the listed
     * biomes. Searches {par1,par2} +-par3 blocks. Strongly favors positive y
     * positions.
     */
    @Override
    public BlockPos findBiomePosition(int x, int z, int range, List<Biome> biomes, Random random) {
        return biomes.contains(this.biomeGenerator) ? new BlockPos(x - range + random.nextInt(range * 2 + 1), 0, z - range
                + random.nextInt(range * 2 + 1)) : null;
    }

    /**
     * checks given Chunk's Biomes against List of allowed ones
     */
    @Override
    public boolean areBiomesViable(int par1, int par2, int par3, List<Biome> biomes) {
        return biomes.contains(this.biomeGenerator);
    }
}
