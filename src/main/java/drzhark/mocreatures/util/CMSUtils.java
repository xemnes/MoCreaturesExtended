/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.util;

import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

public class CMSUtils {
    /**
     * Gets the amount of light on a block without taking into account sunlight
     */
    public static int getLightFromNeighbors(Chunk chunk, int x, int y, int z) {
        ExtendedBlockStorage extendedblockstorage = chunk.getBlockStorageArray()[y >> 4];

        if (extendedblockstorage == null) {
            return 0;
        } else {
            return extendedblockstorage.getBlockLight(x, y & 15, z);
        }
    }
}
