package drzhark.mocreatures.compat;

import com.buuz135.industrial.api.IndustrialForegoingHelper;
import com.buuz135.industrial.api.extractor.ExtractorEntry;
import com.buuz135.industrial.api.recipe.ProteinReactorEntry;
import drzhark.mocreatures.MoCConstants;
import drzhark.mocreatures.compat.futuremc.FutureMCIntegration;
import drzhark.mocreatures.compat.industrialforegoing.IndustrialForegoingIntegration;
import drzhark.mocreatures.compat.morph.MorphIntegration;
import drzhark.mocreatures.compat.thaumcraft.ThaumcraftIntegration;
import drzhark.mocreatures.compat.thermalexpansion.ThermalExpansionIntegration;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = MoCConstants.MOD_ID)
public class CompatHandler {

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        if (Loader.isModLoaded("futuremc")) FutureMCIntegration.addRecipes();
        if (Loader.isModLoaded("thermalexpansion")) ThermalExpansionIntegration.addRecipes();
    }

    public static void preInit() {
    }

    public static void init() {
        if (Loader.isModLoaded("industrialforegoing")) {
            for (ItemStack proteinGeneratorEntry : IndustrialForegoingIntegration.getBasicProteinGeneratorEntries())
                IndustrialForegoingHelper.addProteinReactorEntry(new ProteinReactorEntry(proteinGeneratorEntry));
        }

        if (Loader.isModLoaded("thaumcraft")) MinecraftForge.EVENT_BUS.register(ThaumcraftIntegration.class);
    }

    public static void postInit() {
        if (Loader.isModLoaded("morph")) MorphIntegration.mapAbilities();
    }
}
