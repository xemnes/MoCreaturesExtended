/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.dimension.chunk;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.chunk.ChunkPrimer;

// Courtesy of lhns
public class MoCOffsetChunkPrimer extends ChunkPrimer {
    private final ChunkPrimer primer;
    private final int offset;

    public MoCOffsetChunkPrimer(ChunkPrimer primer, int offset) {
        this.primer = primer;
        this.offset = offset;
    }

    @Override
    public void setBlockState(int x, int y, int z, IBlockState state) {
        primer.setBlockState(x, y + offset, z, state);
    }

    @Override
    public IBlockState getBlockState(int x, int y, int z) {
        return primer.getBlockState(x, y + offset, z);
    }

    @Override
    public int findGroundBlockIdx(int x, int z) {
        return primer.findGroundBlockIdx(x, z) + offset;
    }
}
