/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.dimension;

import net.minecraft.world.biome.BiomeDecorator;

public class BiomeWyvernDecorator extends BiomeDecorator {

    public BiomeWyvernDecorator() {
        this.generateFalls = true;
        this.grassPerChunk = 2;
        this.flowersPerChunk = -999;
        this.mushroomsPerChunk = 20;
        this.treesPerChunk = 4;
    }
}
