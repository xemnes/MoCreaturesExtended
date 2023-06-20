package drzhark.mocreatures.compat;

import drzhark.mocreatures.MoCConstants;
import drzhark.mocreatures.compat.futuremc.FutureMCIntegration;
import drzhark.mocreatures.compat.thermalexpansion.ThermalExpansionIntegration;
import net.minecraft.item.crafting.IRecipe;
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
}
