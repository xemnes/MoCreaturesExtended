/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.dimension;

import drzhark.mocreatures.entity.ambient.MoCEntityDragonfly;
import drzhark.mocreatures.entity.hunter.MoCEntitySnake;
import drzhark.mocreatures.entity.neutral.MoCEntityWyvern;
import drzhark.mocreatures.entity.passive.MoCEntityBunny;
import drzhark.mocreatures.init.MoCBlocks;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BiomeGenWyvernLair extends Biome {

    private final MoCWorldGenBigTree wyvernGenBigTree;
    private final WorldGenShrub worldGenShrub;

    public BiomeGenWyvernLair(Biome.BiomeProperties biomeProperties) {
        super(biomeProperties);
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.modSpawnableLists.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(MoCEntityBunny.class, 6, 2, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(MoCEntityDragonfly.class, 6, 2, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(MoCEntitySnake.class, 6, 1, 3));
        this.spawnableCreatureList.add(new SpawnListEntry(MoCEntityWyvern.class, 12, 2, 3));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 1, 2, 2));
        this.topBlock = MoCBlocks.mocGrass.getStateFromMeta(0);
        this.fillerBlock = MoCBlocks.mocDirt.getStateFromMeta(0);
        this.wyvernGenBigTree = new MoCWorldGenBigTree(false, MoCBlocks.mocLog.getStateFromMeta(0), MoCBlocks.mocLeaf.getStateFromMeta(0), 2, 30, 10);
        this.worldGenShrub = new WorldGenShrub(MoCBlocks.mocLeaf.getStateFromMeta(0), MoCBlocks.mocLog.getStateFromMeta(0)); // Currently does nothing
        this.decorator = new BiomeWyvernDecorator();
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random par1Random) {
        if (par1Random.nextInt(10) == 0) {
            return this.wyvernGenBigTree;
        } else {
            return this.worldGenShrub;
        }
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random) {
        return new WorldGenWyvernGrass(MoCBlocks.mocTallGrass.getStateFromMeta(0));
    }

    @Override
    public boolean canRain() {
        return false;
    }

    @Override
    public void decorate(World par1World, Random par2Random, BlockPos pos) {
        super.decorate(par1World, par2Random, pos);

        // Commented out, currently has issues with floating vines around trees and on the ground
        //
        /*WorldGenVines var5 = new WorldGenVines();

        for (int var6 = 0; var6 < 50; ++var6) {
            int var7 = par2Random.nextInt(16) + 8;
            byte var8 = 64;
            int var9 = par2Random.nextInt(16) + 8;
            var5.generate(par1World, par2Random, pos.add(var7, var8, var9));
        }*/
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getModdedBiomeFoliageColor(final int original) {
        return 0x4F4569;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getModdedBiomeGrassColor(final int original) {
        return 0x4F4569;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(final float currentTemperature) {
        return 0x8C95FF;
    }
}
