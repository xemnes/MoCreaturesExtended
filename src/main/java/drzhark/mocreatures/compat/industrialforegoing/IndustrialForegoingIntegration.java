/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.compat.industrialforegoing;

import com.buuz135.industrial.api.extractor.ExtractorEntry;
import com.buuz135.industrial.proxy.FluidsRegistry;
import drzhark.mocreatures.init.MoCBlocks;
import drzhark.mocreatures.init.MoCItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

// Courtesy of SokyranTheDragon
public class IndustrialForegoingIntegration {

    public static ItemStack[] getBasicProteinGeneratorEntries() {
        return new ItemStack[]{
                new ItemStack(MoCItems.rawTurkey),
                new ItemStack(MoCItems.cookedTurkey),
                new ItemStack(MoCItems.ratRaw),
                new ItemStack(MoCItems.ratCooked),
                new ItemStack(MoCItems.crabraw),
                new ItemStack(MoCItems.crabcooked),
                new ItemStack(MoCItems.ostrichraw),
                new ItemStack(MoCItems.ostrichcooked),
                new ItemStack(MoCItems.turtleraw),
                new ItemStack(MoCItems.turtlecooked),
                new ItemStack(MoCItems.heartdarkness),
                new ItemStack(MoCItems.heartfire),
                new ItemStack(MoCItems.heartundead)
        };
    }

    public static ExtractorEntry[] getLatexEntries() {
        return new ExtractorEntry[]{
                new ExtractorEntry(new ItemStack(MoCBlocks.wyvernLog, 1, 0), new FluidStack(FluidsRegistry.LATEX, 1)),
                new ExtractorEntry(new ItemStack(MoCBlocks.wyvernLog, 1, 1), new FluidStack(FluidsRegistry.LATEX, 1))
        };
    }
}
