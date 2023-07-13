/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.dimension;

import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.init.MoCBiomes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//this one is a copy of the end world provider

public class WorldProviderWyvernEnd extends WorldProviderSurface {

    @Override
    protected void init() {
        this.biomeProvider = new BiomeProviderWyvernLair(MoCBiomes.WyvernLairBiome, 0.5F, 0.0F);
        this.hasSkyLight = !MoCreatures.proxy.darkerWyvernLair;
        setDimension(MoCreatures.wyvernLairDimensionID);
        setCustomSky();
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new ChunkGeneratorWyvernLair(this.world, false, this.world.getSeed());
    }

    private void setCustomSky() {
        if (!this.world.isRemote) {
            return;
        }

        // It'll do for now until the custom sky renderer is ever expanded upon
        if (MoCreatures.proxy.classicWyvernLairSky) setSkyRenderer(new MoCSkyRenderer());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks) {
        return MoCreatures.proxy.classicWyvernLairSky ? null : super.calcSunriseSunsetColors(celestialAngle, partialTicks);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Vec3d getFogColor(float par1, float par2) {
        float var4 = MathHelper.cos(par1 * (float) Math.PI * 2.0F) * 2.0F + 0.5F;

        // Classic Sky
        if (MoCreatures.proxy.classicWyvernLairSky) {
            if (var4 < 0.0F) {
                var4 = 0.0F;
            }

            if (var4 > 1.0F) {
                var4 = 1.0F;
            }

            float var5 = 0 / 255.0F;
            float var6 = 98 / 255.0F;
            float var7 = 73 / 255.0F;

            var5 *= var4 * 0.0F + 0.15F;
            var6 *= var4 * 0.0F + 0.15F;
            var7 *= var4 * 0.0F + 0.15F;

            return new Vec3d(var5, var6, var7);
        }

        // New Sky
        else {
            if (var4 < 0.0F) {
                var4 = 0.0F;
            }

            if (var4 > 1.0F) {
                var4 = 1.0F;
            }

            float var5 = 200 / 255.0F;
            float var6 = 220 / 255.0F;
            float var7 = 190 / 255.0F;

            var5 *= var4 * (var4 * 0.94F + 0.06F);
            var6 *= var4 * (var4 * 0.94F + 0.06F);
            var7 *= var4 * (var4 * 0.91F + 0.09F);

            return new Vec3d(var5, var6, var7);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isSkyColored() {
        return true;
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getCloudHeight() {
        return -5.0F;
    }

    @Override
    public double getHorizon() {
        return 0.0;
    }

    @Override
    public boolean canCoordinateBeSpawn(int par1, int par2) {
        BlockPos pos = this.world.getTopSolidOrLiquidBlock(new BlockPos(par1, 0, par2));
        return this.world.getBlockState(pos).getMaterial().blocksMovement();
    }

    @Override
    public BlockPos getSpawnCoordinate() {
        return new BlockPos(0, 70, 0);
    }

    // No bed explosions allowed
    @Override
    public WorldSleepResult canSleepAt(EntityPlayer player, BlockPos pos) {
        return WorldSleepResult.DENY;
    }

    @Override
    public boolean canDoLightning(Chunk chunk) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int par1, int par2) {
        return MoCreatures.proxy.foggyWyvernLair;
    }

    @Override
    public DimensionType getDimensionType() {
        return MoCreatures.WYVERN_LAIR;
    }

    // No custom sun and moon yet but the textures are both here for now
    public String getSunTexture() {
        return MoCreatures.proxy.getMiscTexture("twinsuns.png").toString();
    }

    public String getMoonTexture() {
        return MoCreatures.proxy.getMiscTexture("moon_phases.png").toString();
    }

    /*@Override
    public String getSaveFolder() {
        return "MoCWyvernLair";
    }*/

    @Override
    public double getMovementFactor() {
        return 1.0;
    }
}
