package drzhark.mocreatures.compat.tinkers;

import c4.conarm.common.armor.traits.ArmorTraits;
import c4.conarm.lib.materials.*;
import slimeknights.tconstruct.library.TinkerRegistry;

public class ConstructsArmoryIntegration
{
    // These traits are for armor and not tools
    //
	
    // Materials are already registered in the tools class, we are just registering support for armor sets here
    public static void preInit()
    {
        TinkerRegistry.addMaterialStats(TinkersConstructIntegration.SHARK_TOOTH,
            new CoreMaterialStats(15.0F, 17.0F),
            new PlatesMaterialStats(1.0F, 8.0F, 2.0F),
            new TrimMaterialStats(10.0F));
        ArmorMaterials.addArmorTrait(TinkersConstructIntegration.SHARK_TOOTH, ArmorTraits.aquaspeed, ArmorMaterialType.CORE);
        ArmorMaterials.addArmorTrait(TinkersConstructIntegration.SHARK_TOOTH, ArmorTraits.aquaspeed, ArmorMaterialType.PLATES);
        ArmorMaterials.addArmorTrait(TinkersConstructIntegration.SHARK_TOOTH, ArmorTraits.aquaspeed, ArmorMaterialType.TRIM);

        TinkerRegistry.addMaterialStats(TinkersConstructIntegration.ANCIENT_SILVER,
            new CoreMaterialStats(15.0F, 17.0F),
            new PlatesMaterialStats(1.0F, 8.0F, 2.0F),
            new TrimMaterialStats(10.0F));
        ArmorMaterials.addArmorTrait(TinkersConstructIntegration.ANCIENT_SILVER, ArmorTraits.heavy, ArmorMaterialType.CORE);
        ArmorMaterials.addArmorTrait(TinkersConstructIntegration.ANCIENT_SILVER, ArmorTraits.heavy, ArmorMaterialType.PLATES);
        ArmorMaterials.addArmorTrait(TinkersConstructIntegration.ANCIENT_SILVER, ArmorTraits.heavy, ArmorMaterialType.TRIM);
    }
}
