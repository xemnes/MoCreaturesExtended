/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.dimension.biome;

import drzhark.mocreatures.dimension.world.MoCWorldGenBigTree;
import drzhark.mocreatures.dimension.world.MoCWorldGenWyvernGrass;
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

public class MoCBiomeGenWyvernLair extends Biome {

    private final MoCWorldGenBigTree wyvernGenBigTree;
    private final WorldGenShrub worldGenShrub;

    @SuppressWarnings("deprecation")
    public MoCBiomeGenWyvernLair(Biome.BiomeProperties biomeProperties) {
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
        this.spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 1, 1, 1));
        this.topBlock = MoCBlocks.wyvgrass.getDefaultState();
        this.fillerBlock = MoCBlocks.wyvdirt.getDefaultState();
        this.wyvernGenBigTree = new MoCWorldGenBigTree(false, MoCBlocks.wyvwoodLog.getDefaultState(), MoCBlocks.wyvwoodLeaves.getStateFromMeta(0), 2, 30, 10);
        this.worldGenShrub = new WorldGenShrub(MoCBlocks.wyvwoodLeaves.getDefaultState(), MoCBlocks.wyvwoodLog.getDefaultState()); // Currently does nothing
        this.decorator = new MoCBiomeWyvernLairDecorator();
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
        return new MoCWorldGenWyvernGrass(MoCBlocks.tallWyvgrass.getDefaultState());
    }

    @Override
    public boolean canRain() {
        return false;
    }

    @Override
    public void decorate(World par1World, Random par2Random, BlockPos pos) {
        super.decorate(par1World, par2Random, pos);
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
