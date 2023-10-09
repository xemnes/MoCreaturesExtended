/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.dimension.worldgen;

import drzhark.mocreatures.init.MoCBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class MoCWorldGenWyvernNest extends WorldGenerator {

    @Override
    public boolean generate(World world, Random rand, BlockPos position) {
        // Generate the log pillar
        int pillarHeight = 8;
        IBlockState logState = MoCBlocks.wyvwoodLog.getDefaultState();

        for (int i = 0; i < pillarHeight; i++) {
            BlockPos currentPos = position.up(i);
            world.setBlockState(currentPos, logState, 2);
        }

        // Generate the log circle on top of the pillar
        int circleRadius = 3;
        IBlockState hayState = Blocks.HAY_BLOCK.getDefaultState();

        for (int y = 0; y <= 1; y++) {
            for (int x = -circleRadius - y; x <= circleRadius + y; x++) {
                for (int z = -circleRadius - y; z <= circleRadius + y; z++) {
                    if (x * x + z * z <= (circleRadius + y) * (circleRadius + y)) {
                        BlockPos currentPos = position.add(x, pillarHeight + y, z);
                        world.setBlockState(currentPos, logState, 2);

                        // Place hay blocks on top
                        world.setBlockState(currentPos.up(), hayState, 2);
                    }
                }
            }
        }

        return true;
    }
}
