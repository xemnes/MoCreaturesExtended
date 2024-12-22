package drzhark.mocreatures.compat.tinkers;

import drzhark.mocreatures.compat.tinkers.traits.TraitStingEffect;
import drzhark.mocreatures.compat.tinkers.traits.TraitStingEffectPlayer;
import drzhark.mocreatures.compat.tinkers.traits.TraitStingFire;
import drzhark.mocreatures.init.MoCItems;
import net.minecraft.init.MobEffects;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.BowMaterialStats;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.materials.MaterialTypes;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.HarvestLevels;
import slimeknights.tconstruct.tools.TinkerTraits;

public class TinkersConstructIntegration {
    public static final Material DARK_CHITIN = new Material("dark_chitin", 0x535A6B);
    public static final Material EARTH_CHITIN = new Material("earth_chitin", 0xF37A07);
    public static final Material FIRE_CHITIN = new Material("fire_chitin", 0xC62B13);
    public static final Material FROST_CHITIN = new Material("frost_chitin", 0x1B7A87);
    public static final Material UNDEAD_CHITIN = new Material("undead_chitin", 0x92B859);

    public static final AbstractTrait DARK_STING = new TraitStingEffectPlayer("dark_sting", 0x535A6B, 1.5F, MobEffects.WEAKNESS, MobEffects.NAUSEA);
    public static final AbstractTrait EARTH_STING = new TraitStingEffect("earth_sting", 0xF37A07, 1.5F, MobEffects.POISON);
    public static final AbstractTrait FIRE_STING = new TraitStingFire(1.5F);
    public static final AbstractTrait FROST_STING = new TraitStingEffect("frost_sting", 0x1B7A87, 1.5F, MobEffects.SLOWNESS);
    public static final AbstractTrait UNDEAD_STING = new TraitStingEffectPlayer("undead_sting", 0x92B859, 1.5F, MobEffects.WITHER, MobEffects.BLINDNESS);

    public static void preInit() {
        TinkerRegistry.addMaterialStats(DARK_CHITIN,
                new HeadMaterialStats(300, 6.0F, 3.5F, HarvestLevels.DIAMOND),
                new HandleMaterialStats(0.95F, 60),
                new ExtraMaterialStats(60),
                new BowMaterialStats(0.9F, 0.7F, 0.0F));
        DARK_CHITIN.addTrait(DARK_STING, MaterialTypes.HEAD);
        DARK_CHITIN.addTrait(TinkerTraits.fractured);
        TinkerRegistry.integrate(DARK_CHITIN).preInit();

        TinkerRegistry.addMaterialStats(EARTH_CHITIN,
                new HeadMaterialStats(300, 6.0F, 3.5F, HarvestLevels.DIAMOND),
                new HandleMaterialStats(0.95F, 60),
                new ExtraMaterialStats(60),
                new BowMaterialStats(0.9F, 0.7F, 0.0F));
        EARTH_CHITIN.addTrait(EARTH_STING, MaterialTypes.HEAD);
        EARTH_CHITIN.addTrait(TinkerTraits.fractured);
        TinkerRegistry.integrate(EARTH_CHITIN).preInit();

        TinkerRegistry.addMaterialStats(FIRE_CHITIN,
                new HeadMaterialStats(300, 6.0F, 3.5F, HarvestLevels.DIAMOND),
                new HandleMaterialStats(0.95F, 60),
                new ExtraMaterialStats(60),
                new BowMaterialStats(0.9F, 0.7F, 0.0F));
        FIRE_CHITIN.addTrait(FIRE_STING, MaterialTypes.HEAD);
        FIRE_CHITIN.addTrait(TinkerTraits.fractured);
        TinkerRegistry.integrate(FIRE_CHITIN).preInit();

        TinkerRegistry.addMaterialStats(FROST_CHITIN,
                new HeadMaterialStats(300, 6.0F, 3.5F, HarvestLevels.DIAMOND),
                new HandleMaterialStats(0.95F, 60),
                new ExtraMaterialStats(60),
                new BowMaterialStats(0.9F, 0.7F, 0.0F));
        FROST_CHITIN.addTrait(FROST_STING, MaterialTypes.HEAD);
        FROST_CHITIN.addTrait(TinkerTraits.fractured);
        TinkerRegistry.integrate(FROST_CHITIN).preInit();

        TinkerRegistry.addMaterialStats(UNDEAD_CHITIN,
                new HeadMaterialStats(300, 6.0F, 3.5F, HarvestLevels.DIAMOND),
                new HandleMaterialStats(0.95F, 60),
                new ExtraMaterialStats(60),
                new BowMaterialStats(0.9F, 0.7F, 0.0F));
        UNDEAD_CHITIN.addTrait(UNDEAD_STING, MaterialTypes.HEAD);
        UNDEAD_CHITIN.addTrait(TinkerTraits.fractured);
        TinkerRegistry.integrate(UNDEAD_CHITIN).preInit();
    }

    public static void init() {
        DARK_CHITIN.addItem(MoCItems.chitinCave, 1, Material.VALUE_Ingot);
        DARK_CHITIN.setRepresentativeItem(MoCItems.chitinCave);
        DARK_CHITIN.setCraftable(true).setCastable(false);

        EARTH_CHITIN.addItem(MoCItems.chitin, 1, Material.VALUE_Ingot);
        EARTH_CHITIN.setRepresentativeItem(MoCItems.chitin);
        EARTH_CHITIN.setCraftable(true).setCastable(false);

        FIRE_CHITIN.addItem(MoCItems.chitinNether, 1, Material.VALUE_Ingot);
        FIRE_CHITIN.setRepresentativeItem(MoCItems.chitinNether);
        FIRE_CHITIN.setCraftable(true).setCastable(false);

        FROST_CHITIN.addItem(MoCItems.chitinFrost, 1, Material.VALUE_Ingot);
        FROST_CHITIN.setRepresentativeItem(MoCItems.chitinFrost);
        FROST_CHITIN.setCraftable(true).setCastable(false);

        UNDEAD_CHITIN.addItem(MoCItems.chitinUndead, 1, Material.VALUE_Ingot);
        UNDEAD_CHITIN.setRepresentativeItem(MoCItems.chitinUndead);
        UNDEAD_CHITIN.setCraftable(true).setCastable(false);
    }
}
