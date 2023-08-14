/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.entity.ai;

import net.minecraft.block.*;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.Iterator;

public class EntityAIWanderAvoidWaterFlyingMoC extends EntityAIWanderAvoidWater {
    public EntityAIWanderAvoidWaterFlyingMoC(EntityCreature creatureIn, double speedIn) {
        super(creatureIn, speedIn);
    }

    @Override
    protected Vec3d getPosition() {
        Vec3d vec3d = null;

        if (this.entity.isInWater() || this.entity.isOverWater()) {
            vec3d = RandomPositionGenerator.getLandPos(this.entity, 24, 24);
        }

        if (this.entity.getRNG().nextFloat() >= (this.probability * 4)) {
            vec3d = this.getLandingPos();
        }

        return vec3d == null ? super.getPosition() : vec3d;
    }

    private Vec3d getLandingPos() {
        BlockPos blockPos = new BlockPos(this.entity);
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos mutableBlockPos1 = new BlockPos.MutableBlockPos();
        Iterable<BlockPos.MutableBlockPos> iterable = BlockPos.MutableBlockPos.getAllInBoxMutable(MathHelper.floor(this.entity.posX - 6.0D), MathHelper.floor(this.entity.posY - 1.0D), MathHelper.floor(this.entity.posZ - 6.0D), MathHelper.floor(this.entity.posX + 6.0D), MathHelper.floor(this.entity.posY + 1.0D), MathHelper.floor(this.entity.posZ + 6.0D));
        Iterator<BlockPos.MutableBlockPos> iterator = iterable.iterator();
        BlockPos blockPos1;

        while (true) {
            if (!iterator.hasNext()) {
                return null;
            }

            blockPos1 = iterator.next();

            if (!blockPos.equals(blockPos1)) {
                Block block = this.entity.world.getBlockState(mutableBlockPos1.setPos(blockPos1).move(EnumFacing.DOWN)).getBlock();
                boolean flag = block instanceof BlockLeaves || block instanceof BlockLog || block instanceof BlockBush || block instanceof BlockGrass;

                if (flag && this.entity.world.isAirBlock(blockPos1) && this.entity.world.isAirBlock(mutableBlockPos.setPos(blockPos1).move(EnumFacing.UP))) {
                    break;
                }
            }
        }

        return new Vec3d(blockPos1.getX(), blockPos1.getY(), blockPos1.getZ());
    }
}