/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.init;

import drzhark.mocreatures.MoCConstants;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.item.*;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.*;

public class MoCItems {

    public static final Set<Item> ITEMS = new HashSet<>();
    // Misc
    public static final MoCItemRecord recordshuffle = new MoCItemRecord("recordshuffle", MoCSoundEvents.ITEM_RECORD_SHUFFLING);
    public static final MoCItem horsesaddle = new MoCItemHorseSaddle("horsesaddle");
    public static final MoCItem sharkteeth = new MoCItem("sharkteeth");
    public static final MoCItem haystack = new MoCItemHayStack("haystack");
    public static final MoCItemSugarLump sugarlump = new MoCItemSugarLump("sugarlump");
    public static final MoCItem mocegg = new MoCItemEgg("mocegg");
    public static final MoCItem bigcatclaw = new MoCItem("bigcatclaw");
    public static final MoCItem whip = new MoCItemWhip("whip");
    public static final MoCItem medallion = new MoCItem("medallion");
    public static final MoCItemKittyBed[] kittybed = new MoCItemKittyBed[16];
    public static final MoCItem litterbox = new MoCItemLitterBox("kittylitter");
    public static final MoCItem woolball = new MoCItem("woolball");
    public static final MoCItem petfood = new MoCItem("petfood");
    public static final MoCItem hideCroc = new MoCItem("reptilehide");
    public static final MoCItem fur = new MoCItem("fur");
    public static final MoCItem essencedarkness = new MoCItem("essencedarkness");
    public static final MoCItem essencefire = new MoCItem("essencefire");
    public static final MoCItem essenceundead = new MoCItem("essenceundead");
    public static final MoCItem essencelight = new MoCItem("essencelight");
    public static final MoCItem amuletbone = new MoCItemHorseAmulet("amuletbone");
    public static final MoCItem amuletbonefull = new MoCItemHorseAmulet("amuletbonefull");
    public static final MoCItem amuletghost = new MoCItemHorseAmulet("amuletghost");
    public static final MoCItem amuletghostfull = new MoCItemHorseAmulet("amuletghostfull");
    public static final MoCItem amuletfairy = new MoCItemHorseAmulet("amuletfairy");
    public static final MoCItem amuletfairyfull = new MoCItemHorseAmulet("amuletfairyfull");
    public static final MoCItem amuletpegasus = new MoCItemHorseAmulet("amuletpegasus");
    public static final MoCItem amuletpegasusfull = new MoCItemHorseAmulet("amuletpegasusfull");
    public static final MoCItem fishnet = new MoCItemPetAmulet("fishnet");
    public static final MoCItem fishnetfull = new MoCItemPetAmulet("fishnetfull");
    public static final MoCItem petamulet = new MoCItemPetAmulet("petamulet", 1);
    public static final MoCItem petamuletfull = new MoCItemPetAmulet("petamuletfull", 1);
    public static final MoCItem heartdarkness = new MoCItem("heartdarkness");
    public static final MoCItem heartfire = new MoCItem("heartfire");
    public static final MoCItem heartundead = new MoCItem("heartundead");
    public static final MoCItem unicornhorn = new MoCItem("unicornhorn");
    public static final MoCItem horsearmorcrystal = new MoCItem("horsearmorcrystal");
    public static final MoCItem animalHide = new MoCItem("hide");
    public static final MoCItem chitinCave = new MoCItem("chitinblack");
    public static final MoCItem chitinFrost = new MoCItem("chitinfrost");
    public static final MoCItem chitinNether = new MoCItem("chitinnether");
    public static final MoCItem chitinUndead = new MoCItem("chitinundead");
    public static final MoCItem chitin = new MoCItem("chitin");
    public static final MoCItem tusksWood = new MoCItem("tuskswood");
    public static final MoCItem tusksIron = new MoCItem("tusksiron");
    public static final MoCItem tusksDiamond = new MoCItem("tusksdiamond");
    public static final MoCItem elephantHarness = new MoCItem("elephantharness");
    public static final MoCItem elephantChest = new MoCItem("elephantchest");
    public static final MoCItem elephantGarment = new MoCItem("elephantgarment");
    public static final MoCItem elephantHowdah = new MoCItem("elephanthowdah");
    public static final MoCItem mammothPlatform = new MoCItem("mammothplatform");
    public static final MoCItem scrollFreedom = new MoCItem("scrolloffreedom");
    public static final MoCItem scrollOfSale = new MoCItem("scrollofsale");
    public static final MoCItem scrollOfOwner = new MoCItem("scrollofowner");
    // Food
    public static final MoCItemFood cookedTurkey = new MoCItemFood("turkeycooked", 7, 0.8F, true);
    public static final MoCItemFood crabraw = (MoCItemFood) new MoCItemFood("crabraw", 2, 0.1F, true).setPotionEffect(new PotionEffect(MobEffects.HUNGER, 30 * 20, 0), 0.8F);
    public static final MoCItemFood crabcooked = new MoCItemFood("crabcooked", 4, 0.6F, true);
    public static final MoCItemFood duckCooked = new MoCItemFood("duckcooked", 6, 0.7F, true);
    public static final MoCItemFood duckRaw = new MoCItemFood("duckraw", 2, 0.4F, true);
    public static final MoCItemFood omelet = new MoCItemFood("omelet", 3, 0.5F, false);
    public static final MoCItemFood ostrichraw = (MoCItemFood) new MoCItemFood("ostrichraw", 3, 0.4F, true).setPotionEffect(new PotionEffect(MobEffects.HUNGER, 30 * 20, 0), 0.8F);
    public static final MoCItemFood ostrichcooked = new MoCItemFood("ostrichcooked", 7, 0.8F, true);
    public static final MoCItemFood ratBurger = new MoCItemFood("ratburger", 9, 0.5F, false);
    public static final MoCItemFood ratCooked = new MoCItemFood("ratcooked", 4, 0.5F, true);
    public static final MoCItemFood ratRaw = (MoCItemFood) new MoCItemFood("ratraw", 2, 0.1F, true).setPotionEffect(new PotionEffect(MobEffects.HUNGER, 30 * 20, 0), 0.8F);
    public static final MoCItemFood rawTurkey = (MoCItemFood) new MoCItemFood("turkeyraw", 3, 0.4F, true).setPotionEffect(new PotionEffect(MobEffects.HUNGER, 30 * 20, 0), 0.8F);
    public static final MoCItemFood turtlecooked = new MoCItemFood("turtlecooked", 6, 0.7F, true);
    public static final MoCItemFood turtleraw = new MoCItemFood("turtleraw", 2, 0.2F, true);
    public static final MoCItemFood turtlesoup = new MoCItemTurtleSoup("turtlesoup", 8, 0.8F, false);
    public static final MoCItemFood venisonCooked = new MoCItemFood("venisoncooked", 8, 0.9F, true);
    public static final MoCItemFood venisonRaw = new MoCItemFood("venisonraw", 3, 0.4F, true);
    // Weapons
    public static final MoCItemSword nunchaku = new MoCItemSword("nunchaku", Item.ToolMaterial.IRON);
    public static final MoCItemSword sai = new MoCItemSword("sai", Item.ToolMaterial.IRON);
    public static final MoCItemSword bo = new MoCItemSword("bo", Item.ToolMaterial.IRON);
    public static final MoCItemSword katana = new MoCItemSword("katana", Item.ToolMaterial.IRON);
    static ToolMaterial SHARK = EnumHelper.addToolMaterial("SHARK", 0, 161, 7.0F, 2.5F, 15).setRepairItem(new ItemStack(sharkteeth));
    public static final MoCItemSword sharksword = new MoCItemSword("sharksword", SHARK);
    static ToolMaterial SCORPC = EnumHelper.addToolMaterial("SCORPC", 0, 351, 7.5F, 2.5F, 16).setRepairItem(new ItemStack(chitinCave));
    public static final MoCItemSword scorpSwordCave = new MoCItemSword("scorpswordcave", SCORPC, 4);
    static ToolMaterial SCORPF = EnumHelper.addToolMaterial("SCORPF", 0, 351, 7.5F, 2.5F, 16).setRepairItem(new ItemStack(chitinFrost));
    public static final MoCItemSword scorpSwordFrost = new MoCItemSword("scorpswordfrost", SCORPF, 2);
    static ToolMaterial SCORPN = EnumHelper.addToolMaterial("SCORPN", 0, 351, 7.5F, 2.5F, 16).setRepairItem(new ItemStack(chitinNether));
    public static final MoCItemSword scorpSwordNether = new MoCItemSword("scorpswordnether", SCORPN, 3);
    static ToolMaterial SCORPD = EnumHelper.addToolMaterial("SCORPD", 0, 351, 7.5F, 2.5F, 16).setRepairItem(new ItemStack(chitin));
    public static final MoCItemSword scorpSwordDirt = new MoCItemSword("scorpsworddirt", SCORPD, 1);
    static ToolMaterial SCORPU = EnumHelper.addToolMaterial("SCORPU", 0, 351, 7.5F, 2.5F, 16).setRepairItem(new ItemStack(chitinUndead));
    public static final MoCItemSword scorpSwordUndead = new MoCItemSword("scorpswordundead", SCORPU, 5);
    static ToolMaterial STING = EnumHelper.addToolMaterial("STING", 0, 8, 6.0F, 0.0F, 5);
    public static final MoCItemWeapon scorpStingCave = new MoCItemWeapon("scorpstingcave", STING, 4);
    public static final MoCItemWeapon scorpStingFrost = new MoCItemWeapon("scorpstingfrost", STING, 2);
    public static final MoCItemWeapon scorpStingNether = new MoCItemWeapon("scorpstingnether", STING, 3);
    public static final MoCItemWeapon scorpStingDirt = new MoCItemWeapon("scorpstingdirt", STING, 1);
    public static final MoCItemWeapon scorpStingUndead = new MoCItemWeapon("scorpstingundead", STING, 5);
    // Armor
    static ArmorMaterial crocARMOR = EnumHelper.addArmorMaterial("crocARMOR", "crocARMOR", 10, new int[]{1, 3, 4, 1}, 17, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1.0F).setRepairItem(new ItemStack(hideCroc));
    public static final MoCItemArmor plateCroc = new MoCItemArmor("reptileplate", crocARMOR, 4, EntityEquipmentSlot.CHEST);
    public static final MoCItemArmor helmetCroc = new MoCItemArmor("reptilehelmet", crocARMOR, 4, EntityEquipmentSlot.HEAD);
    public static final MoCItemArmor legsCroc = new MoCItemArmor("reptilelegs", crocARMOR, 4, EntityEquipmentSlot.LEGS);
    public static final MoCItemArmor bootsCroc = new MoCItemArmor("reptileboots", crocARMOR, 4, EntityEquipmentSlot.FEET);
    static ArmorMaterial scorpdARMOR = EnumHelper.addArmorMaterial("scorpdARMOR", "scorpdARMOR", 18, new int[]{2, 6, 7, 2}, 16, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2.0F).setRepairItem(new ItemStack(chitin));
    public static final MoCItemArmor scorpPlateDirt = new MoCItemArmor("scorpplatedirt", scorpdARMOR, 4, EntityEquipmentSlot.CHEST);
    public static final MoCItemArmor scorpHelmetDirt = new MoCItemArmor("scorphelmetdirt", scorpdARMOR, 4, EntityEquipmentSlot.HEAD);
    public static final MoCItemArmor scorpLegsDirt = new MoCItemArmor("scorplegsdirt", scorpdARMOR, 4, EntityEquipmentSlot.LEGS);
    public static final MoCItemArmor scorpBootsDirt = new MoCItemArmor("scorpbootsdirt", scorpdARMOR, 4, EntityEquipmentSlot.FEET);
    static ArmorMaterial scorpfARMOR = EnumHelper.addArmorMaterial("scorpfARMOR", "scorpfARMOR", 18, new int[]{2, 6, 7, 2}, 16, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2.0F).setRepairItem(new ItemStack(chitinFrost));
    public static final MoCItemArmor scorpPlateFrost = new MoCItemArmor("scorpplatefrost", scorpfARMOR, 4, EntityEquipmentSlot.CHEST);
    public static final MoCItemArmor scorpHelmetFrost = new MoCItemArmor("scorphelmetfrost", scorpfARMOR, 4, EntityEquipmentSlot.HEAD);
    public static final MoCItemArmor scorpLegsFrost = new MoCItemArmor("scorplegsfrost", scorpfARMOR, 4, EntityEquipmentSlot.LEGS);
    public static final MoCItemArmor scorpBootsFrost = new MoCItemArmor("scorpbootsfrost", scorpfARMOR, 4, EntityEquipmentSlot.FEET);
    static ArmorMaterial scorpnARMOR = EnumHelper.addArmorMaterial("scorpnARMOR", "scorpnARMOR", 18, new int[]{2, 6, 7, 2}, 16, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2.0F).setRepairItem(new ItemStack(chitinNether));
    public static final MoCItemArmor scorpPlateNether = new MoCItemArmor("scorpplatenether", scorpnARMOR, 4, EntityEquipmentSlot.CHEST);
    public static final MoCItemArmor scorpHelmetNether = new MoCItemArmor("scorphelmetnether", scorpnARMOR, 4, EntityEquipmentSlot.HEAD);
    public static final MoCItemArmor scorpLegsNether = new MoCItemArmor("scorplegsnether", scorpnARMOR, 4, EntityEquipmentSlot.LEGS);
    public static final MoCItemArmor scorpBootsNether = new MoCItemArmor("scorpbootsnether", scorpnARMOR, 4, EntityEquipmentSlot.FEET);
    static ArmorMaterial scorpcARMOR = EnumHelper.addArmorMaterial("scorpcARMOR", "scorpcARMOR", 18, new int[]{2, 6, 7, 2}, 16, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2.0F).setRepairItem(new ItemStack(chitinCave));
    public static final MoCItemArmor scorpPlateCave = new MoCItemArmor("scorpplatecave", scorpcARMOR, 4, EntityEquipmentSlot.CHEST);
    public static final MoCItemArmor scorpHelmetCave = new MoCItemArmor("scorphelmetcave", scorpcARMOR, 4, EntityEquipmentSlot.HEAD);
    public static final MoCItemArmor scorpLegsCave = new MoCItemArmor("scorplegscave", scorpcARMOR, 4, EntityEquipmentSlot.LEGS);
    public static final MoCItemArmor scorpBootsCave = new MoCItemArmor("scorpbootscave", scorpcARMOR, 4, EntityEquipmentSlot.FEET);
    static ArmorMaterial scorpuARMOR = EnumHelper.addArmorMaterial("scorpuARMOR", "scorpuARMOR", 18, new int[]{2, 6, 7, 2}, 16, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2.0F).setRepairItem(new ItemStack(chitinUndead));
    public static final MoCItemArmor scorpPlateUndead = new MoCItemArmor("scorpplateundead", scorpuARMOR, 4, EntityEquipmentSlot.CHEST);
    public static final MoCItemArmor scorpHelmetUndead = new MoCItemArmor("scorphelmetundead", scorpuARMOR, 4, EntityEquipmentSlot.HEAD);
    public static final MoCItemArmor scorpLegsUndead = new MoCItemArmor("scorplegsundead", scorpuARMOR, 4, EntityEquipmentSlot.LEGS);
    public static final MoCItemArmor scorpBootsUndead = new MoCItemArmor("scorpbootsundead", scorpuARMOR, 4, EntityEquipmentSlot.FEET);
    static ArmorMaterial furARMOR = EnumHelper.addArmorMaterial("furARMOR", "furARMOR", 4, new int[]{1, 2, 2, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F).setRepairItem(new ItemStack(fur));
    public static final MoCItemArmor chestFur = new MoCItemArmor("furchest", furARMOR, 4, EntityEquipmentSlot.CHEST);
    public static final MoCItemArmor helmetFur = new MoCItemArmor("furhelmet", furARMOR, 4, EntityEquipmentSlot.HEAD);
    public static final MoCItemArmor legsFur = new MoCItemArmor("furlegs", furARMOR, 4, EntityEquipmentSlot.LEGS);
    public static final MoCItemArmor bootsFur = new MoCItemArmor("furboots", furARMOR, 4, EntityEquipmentSlot.FEET);
    static ArmorMaterial hideARMOR = EnumHelper.addArmorMaterial("hideARMOR", "hideARMOR", 8, new int[]{1, 3, 3, 1}, 18, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F).setRepairItem(new ItemStack(animalHide));
    public static final MoCItemArmor chestHide = new MoCItemArmor("hidechest", hideARMOR, 4, EntityEquipmentSlot.CHEST);
    public static final MoCItemArmor helmetHide = new MoCItemArmor("hidehelmet", hideARMOR, 4, EntityEquipmentSlot.HEAD);
    public static final MoCItemArmor legsHide = new MoCItemArmor("hidelegs", hideARMOR, 4, EntityEquipmentSlot.LEGS);
    public static final MoCItemArmor bootsHide = new MoCItemArmor("hideboots", hideARMOR, 4, EntityEquipmentSlot.FEET);

    @Mod.EventBusSubscriber(modid = MoCConstants.MOD_ID)
    public static class RegistrationHandler {
        /**
         * Register this mod's {@link Item}s.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            List<Item> items = new ArrayList<>(Arrays.asList(
                    horsesaddle,
                    sharkteeth,
                    haystack,
                    sugarlump,
                    mocegg,
                    bigcatclaw,
                    whip,
                    medallion,
                    litterbox,
                    woolball,
                    petfood,
                    hideCroc,
                    plateCroc,
                    helmetCroc,
                    legsCroc,
                    bootsCroc,
                    fur,
                    omelet,
                    turtleraw,
                    turtlecooked,
                    turtlesoup,

                    nunchaku,
                    sai,
                    bo,
                    katana,
                    sharksword,

                    essencedarkness,
                    essencefire,
                    essenceundead,
                    essencelight,

                    amuletbone,
                    amuletbonefull,
                    amuletghost,
                    amuletghostfull,
                    amuletfairy,
                    amuletfairyfull,
                    amuletpegasus,
                    amuletpegasusfull,
                    fishnet,
                    fishnetfull,
                    petamulet,
                    petamuletfull,

                    chestFur,
                    helmetFur,
                    legsFur,
                    bootsFur,

                    heartdarkness,
                    heartfire,
                    heartundead,
                    ostrichraw,
                    ostrichcooked,
                    unicornhorn,
                    horsearmorcrystal,
                    recordshuffle,

                    animalHide,
                    rawTurkey,
                    cookedTurkey,
                    duckRaw,
                    duckCooked,
                    chestHide,
                    helmetHide,
                    legsHide,
                    bootsHide,
                    ratRaw,
                    ratCooked,
                    ratBurger,
                    venisonRaw,
                    venisonCooked,

                    chitinCave,
                    chitin,
                    chitinNether,
                    chitinFrost,
                    chitinUndead,

                    scorpSwordCave,
                    scorpSwordDirt,
                    scorpSwordNether,
                    scorpSwordFrost,
                    scorpSwordUndead,

                    scorpHelmetCave,
                    scorpPlateCave,
                    scorpLegsCave,
                    scorpBootsCave,
                    scorpPlateDirt,
                    scorpHelmetDirt,
                    scorpLegsDirt,
                    scorpBootsDirt,
                    scorpPlateNether,
                    scorpHelmetNether,
                    scorpLegsNether,
                    scorpBootsNether,
                    scorpPlateFrost,
                    scorpHelmetFrost,
                    scorpLegsFrost,
                    scorpBootsFrost,
                    scorpHelmetUndead,
                    scorpPlateUndead,
                    scorpLegsUndead,
                    scorpBootsUndead,

                    scorpStingCave,
                    scorpStingDirt,
                    scorpStingNether,
                    scorpStingFrost,
                    scorpStingUndead,

                    tusksWood,
                    tusksIron,
                    tusksDiamond,
                    elephantChest,
                    elephantGarment,
                    elephantHarness,
                    elephantHowdah,
                    mammothPlatform,

                    scrollFreedom,
                    scrollOfSale,
                    scrollOfOwner,
                    crabraw,
                    crabcooked
            ));

            final IForgeRegistry<Item> registry = event.getRegistry();

            for (int i = 0; i < 16; i++) {
                String s = EnumDyeColor.byMetadata(i).getTranslationKey().toLowerCase();
                if (s.equalsIgnoreCase("lightBlue")) s = "light_blue";
                kittybed[i] = new MoCItemKittyBed("kittybed_" + s, i);
                registry.register(kittybed[i]);
                if (!MoCreatures.isServer()) {
                    ModelLoader.setCustomModelResourceLocation(kittybed[i], 0,
                            new ModelResourceLocation(MoCConstants.MOD_PREFIX + kittybed[i].getTranslationKey().replace("item.", ""), "inventory"));
                }
            }

            for (final Item item : items) {
                registry.register(item);
                ITEMS.add(item);
                if (!MoCreatures.isServer()) {
                    ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(MoCConstants.MOD_PREFIX + item.getTranslationKey().replace("item.",
                            ""), "inventory"));
                }
                if (item instanceof MoCItemEgg) {
                    for (int i = 0; i < 91; i++) {
                        if (!MoCreatures.isServer()) {
                            ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(MoCConstants.MOD_PREFIX + "mocegg", "inventory"));
                        }
                    }
                }
            }
        }
    }
}
