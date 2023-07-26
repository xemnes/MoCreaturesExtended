package drzhark.mocreatures.compat.thermalexpansion;

import cofh.core.util.helpers.ItemHelper;
import cofh.thermalexpansion.util.managers.device.FactorizerManager;
import cofh.thermalexpansion.util.managers.device.TapperManager;
import cofh.thermalexpansion.util.managers.machine.PulverizerManager;
import cofh.thermalexpansion.util.managers.machine.SawmillManager;
import cofh.thermalexpansion.util.managers.machine.SmelterManager;
import cofh.thermalfoundation.init.TFFluids;
import cofh.thermalfoundation.item.ItemMaterial;
import drzhark.mocreatures.init.MoCItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

// Courtesy of SokyranTheDragon
public class ThermalExpansionIntegration {

    public static void addRecipes() {

        // Induction Smelter
        int energy = 6_000;
        SmelterManager.addRecycleRecipe(energy, new ItemStack(MoCItems.tusksIron), new ItemStack(Items.IRON_INGOT), 2);

        // Pulverizer
        energy = 3_000;
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.sharkteeth), new ItemStack(Items.DYE, 2, 15), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.sharksword), new ItemStack(MoCItems.sharkteeth), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.woolball), new ItemStack(Items.STRING), 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.sugarlump), new ItemStack(Items.SUGAR), 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.tusksWood), ItemMaterial.dustWood, 10);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.tusksDiamond), new ItemStack(Items.DIAMOND), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.horsearmorcrystal), new ItemStack(Items.DIAMOND), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.sugarlump), new ItemStack(Items.SUGAR), 4);
        PulverizerManager.addRecipe(energy, new ItemStack(MoCItems.medallion), new ItemStack(Items.LEATHER), new ItemStack(Items.GOLD_INGOT), 25);
        // Chitin Armor
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpHelmetDirt), new ItemStack(MoCItems.chitin), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpPlateDirt), new ItemStack(MoCItems.chitin), 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpLegsDirt), new ItemStack(MoCItems.chitin), 3);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpBootsDirt), new ItemStack(MoCItems.chitin), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpHelmetCave), new ItemStack(MoCItems.chitinCave), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpPlateCave), new ItemStack(MoCItems.chitinCave), 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpLegsCave), new ItemStack(MoCItems.chitinCave), 3);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpBootsCave), new ItemStack(MoCItems.chitinCave), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpHelmetFrost), new ItemStack(MoCItems.chitinFrost), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpPlateFrost), new ItemStack(MoCItems.chitinFrost), 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpLegsFrost), new ItemStack(MoCItems.chitinFrost), 3);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpBootsFrost), new ItemStack(MoCItems.chitinFrost), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpHelmetNether), new ItemStack(MoCItems.chitinNether), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpPlateNether), new ItemStack(MoCItems.chitinNether), 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpLegsNether), new ItemStack(MoCItems.chitinNether), 3);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpBootsNether), new ItemStack(MoCItems.chitinNether), 2);
        // Sting Blades
        PulverizerManager.addRecipe(energy, new ItemStack(MoCItems.scorpSwordCave), new ItemStack(Items.DIAMOND), new ItemStack(MoCItems.scorpStingCave));
        PulverizerManager.addRecipe(energy, new ItemStack(MoCItems.scorpSwordDirt), new ItemStack(Items.DIAMOND), new ItemStack(MoCItems.scorpStingDirt));
        PulverizerManager.addRecipe(energy, new ItemStack(MoCItems.scorpSwordFrost), new ItemStack(Items.DIAMOND), new ItemStack(MoCItems.scorpStingFrost));
        PulverizerManager.addRecipe(energy, new ItemStack(MoCItems.scorpSwordNether), new ItemStack(Items.DIAMOND), new ItemStack(MoCItems.scorpStingNether));

        // Sawmill
        energy = 1_500;
        SawmillManager.addRecycleRecipe(energy, new ItemStack(MoCItems.horsesaddle), new ItemStack(Items.LEATHER), 3);
        SawmillManager.addRecycleRecipe(energy, new ItemStack(MoCItems.whip), new ItemStack(Items.LEATHER), 3);
        SawmillManager.addRecipe(energy, new ItemStack(MoCItems.elephantChest), new ItemStack(Blocks.CHEST), new ItemStack(MoCItems.animalHide), 50);
        SawmillManager.addRecipe(energy, new ItemStack(MoCItems.elephantGarment), new ItemStack(MoCItems.medallion), new ItemStack(Items.STRING, 12), 100);
        SawmillManager.addRecipe(energy, new ItemStack(MoCItems.elephantHarness), new ItemStack(MoCItems.animalHide, 2), new ItemStack(Items.STRING, 8), 100);
        SawmillManager.addRecipe(energy, new ItemStack(MoCItems.elephantHowdah), new ItemStack(Items.STRING, 12));
        SawmillManager.addRecipe(energy, new ItemStack(MoCItems.mammothPlatform), ItemHelper.cloneStack(ItemMaterial.dustWood, 16), new ItemStack(Items.LEAD, 1), 150);
        SawmillManager.addRecipe(energy, new ItemStack(MoCItems.litterbox), new ItemStack(Blocks.PLANKS, 2), new ItemStack(Blocks.SAND), 25);
        for (int i = 0; i < MoCItems.kittybed.length; i++)
            SawmillManager.addRecipe(energy, new ItemStack(MoCItems.kittybed[i]), new ItemStack(Blocks.PLANKS, 1), new ItemStack(Blocks.WOOL, 1, i));
        // Reptile Hide
        SawmillManager.addRecycleRecipe(energy, new ItemStack(MoCItems.helmetCroc), new ItemStack(MoCItems.hideCroc), 2);
        SawmillManager.addRecycleRecipe(energy, new ItemStack(MoCItems.plateCroc), new ItemStack(MoCItems.hideCroc), 4);
        SawmillManager.addRecycleRecipe(energy, new ItemStack(MoCItems.legsCroc), new ItemStack(MoCItems.hideCroc), 3);
        SawmillManager.addRecycleRecipe(energy, new ItemStack(MoCItems.bootsCroc), new ItemStack(MoCItems.hideCroc), 2);
        // Fur
        SawmillManager.addRecycleRecipe(energy, new ItemStack(MoCItems.helmetFur), new ItemStack(MoCItems.fur), 2);
        SawmillManager.addRecycleRecipe(energy, new ItemStack(MoCItems.chestFur), new ItemStack(MoCItems.fur), 4);
        SawmillManager.addRecycleRecipe(energy, new ItemStack(MoCItems.legsFur), new ItemStack(MoCItems.fur), 3);
        SawmillManager.addRecycleRecipe(energy, new ItemStack(MoCItems.bootsFur), new ItemStack(MoCItems.fur), 2);
        // Hide
        SawmillManager.addRecycleRecipe(energy, new ItemStack(MoCItems.helmetHide), new ItemStack(MoCItems.animalHide), 2);
        SawmillManager.addRecycleRecipe(energy, new ItemStack(MoCItems.chestHide), new ItemStack(MoCItems.animalHide), 4);
        SawmillManager.addRecycleRecipe(energy, new ItemStack(MoCItems.legsHide), new ItemStack(MoCItems.animalHide), 3);
        SawmillManager.addRecycleRecipe(energy, new ItemStack(MoCItems.bootsHide), new ItemStack(MoCItems.animalHide), 2);
    }
}
