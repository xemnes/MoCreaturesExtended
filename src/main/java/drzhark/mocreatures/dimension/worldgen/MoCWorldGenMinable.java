package drzhark.mocreatures.dimension.worldgen;

import com.google.common.base.Predicate;
import drzhark.mocreatures.init.MoCBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class MoCWorldGenMinable extends WorldGenMinable {

    public MoCWorldGenMinable(IBlockState state, int blockCount) {
        this(state, blockCount, new WyvstonePredicate());
    }

    public MoCWorldGenMinable(IBlockState state, int blockCount, Predicate<IBlockState> predicate) {
        super(state, blockCount, predicate);
    }

    static class WyvstonePredicate implements Predicate<IBlockState> {
        private WyvstonePredicate() {
        }

        public boolean apply(IBlockState blockState) {
            return blockState != null && blockState.getBlock() == MoCBlocks.wyvstone;
        }
    }
}
