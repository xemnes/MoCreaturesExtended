package drzhark.mocreatures.init;

import java.util.Random;

import drzhark.mocreatures.MoCConstants;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;

@GameRegistry.ObjectHolder(MoCConstants.MOD_ID)
@Mod.EventBusSubscriber(modid = MoCConstants.MOD_ID)
public class MoCVillagerTrades {
    public static VillagerProfession butcherProfession, farmerProfession, librarianProfession, priestProfession, smithProfession;
    public static VillagerCareer butcher, cleric, fisherman, leatherWorker, librarian, smith, smithWeapon, smithTool;

    @SubscribeEvent
    public static void registerVillagers(RegistryEvent.Register<VillagerProfession> event) {
        butcherProfession = event.getRegistry().getValue(new ResourceLocation("minecraft:butcher"));
        farmerProfession = event.getRegistry().getValue(new ResourceLocation("minecraft:farmer"));
        librarianProfession = event.getRegistry().getValue(new ResourceLocation("minecraft:librarian"));
        priestProfession = event.getRegistry().getValue(new ResourceLocation("minecraft:priest"));
        smithProfession = event.getRegistry().getValue(new ResourceLocation("minecraft:smith"));

        if (butcherProfession != null) {
            butcher = butcherProfession.getCareer(0);
            leatherWorker = butcherProfession.getCareer(1);
        }

        if (farmerProfession != null) {
            fisherman = farmerProfession.getCareer(1);
        }

        if (librarianProfession != null) {
            librarian = librarianProfession.getCareer(0);
        }

        if (priestProfession != null) {
            cleric = priestProfession.getCareer(0);
        }

        if (smithProfession != null) {
            smith = smithProfession.getCareer(0);
            smithWeapon = smithProfession.getCareer(1);
            smithTool = smithProfession.getCareer(2);
        }

        butcher.addTrade(1, new Butcherlvl1Trades());
        butcher.addTrade(2, new Butcherlvl2Trades());
        cleric.addTrade(1, new Clericlvl1Trades());
        cleric.addTrade(4, new Clericlvl4Trades());
        fisherman.addTrade(1, new Fishermanlvl1Trades());
        fisherman.addTrade(2, new Fishermanlvl2Trades());
        leatherWorker.addTrade(1, new Leatherworkerlvl1Trades());
        librarian.addTrade(1, new Librarianlvl1Trades());
        smith.addTrade(2, new SmithLvl2Trades());
        smithWeapon.addTrade(2, new WeaponsmithLvl2Trades());
        smithTool.addTrade(2, new ToolsmithLvl2Trades());
    }

    public static class Butcherlvl1Trades implements ITradeList {
        @Override
        public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
            recipeList.add(new MerchantRecipe(new ItemStack(MoCItems.duckRaw, 14 + random.nextInt(5)), new ItemStack(Items.EMERALD, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(MoCItems.ostrichraw, 10 + random.nextInt(3)), new ItemStack(Items.EMERALD, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(MoCItems.ratRaw, 14 + random.nextInt(5)), new ItemStack(Items.EMERALD, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(MoCItems.rawTurkey, 10 + random.nextInt(3)), new ItemStack(Items.EMERALD, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(MoCItems.venisonRaw, 10 + random.nextInt(3)), new ItemStack(Items.EMERALD, 1)));
        }
    }

    public static class Butcherlvl2Trades implements ITradeList {
        @Override
        public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 1), new ItemStack(MoCItems.duckCooked, 6 + random.nextInt(3))));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 1), new ItemStack(MoCItems.ostrichcooked, 5 + random.nextInt(3))));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 1), new ItemStack(MoCItems.ratCooked, 8 + random.nextInt(5))));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 1), new ItemStack(MoCItems.cookedTurkey, 5 + random.nextInt(3))));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 1), new ItemStack(MoCItems.venisonCooked, 5 + random.nextInt(3))));
        }
    }

    public static class Clericlvl1Trades implements ITradeList {
        @Override
        public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
            recipeList.add(new MerchantRecipe(new ItemStack(MoCItems.ancientSilverScrap, 4 + random.nextInt(3)), new ItemStack(Items.EMERALD, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(MoCItems.heartdarkness, 1), new ItemStack(Items.EMERALD, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(MoCItems.heartfire, 1), new ItemStack(Items.EMERALD, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(MoCItems.heartundead, 1), new ItemStack(Items.EMERALD, 1)));
        }
    }

    public static class Clericlvl4Trades implements ITradeList {
        @Override
        public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 1), new ItemStack(MoCItems.mysticPear, 2 + random.nextInt(2))));
        }
    }

    public static class Fishermanlvl1Trades implements ITradeList {
        @Override
        public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 2 + random.nextInt(3)), new ItemStack(MoCItems.fishnet, 1)));

            recipeList.add(new MerchantRecipe(new ItemStack(MoCItems.sharkteeth, 4 + random.nextInt(3)), new ItemStack(Items.EMERALD, 1)));
        }
    }

    public static class Fishermanlvl2Trades implements ITradeList {
        @Override
        public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 1), new ItemStack(MoCItems.crabcooked, 6 + random.nextInt(3))));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 1), new ItemStack(MoCItems.turtlecooked, 5 + random.nextInt(3))));
        }
    }

    public static class Leatherworkerlvl1Trades implements ITradeList {
        @Override
        public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
            recipeList.add(new MerchantRecipe(new ItemStack(MoCItems.fur, 9 + random.nextInt(4)), new ItemStack(Items.EMERALD, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(MoCItems.animalHide, 6 + random.nextInt(4)), new ItemStack(Items.EMERALD, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(MoCItems.hideCroc, 3 + random.nextInt(4)), new ItemStack(Items.EMERALD, 1)));
        }
    }

    public static class Librarianlvl1Trades implements ITradeList {
        @Override
        public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
            recipeList.add(new MerchantRecipe(new ItemStack(Items.PAPER, 1), new ItemStack(Items.FEATHER, 1), new ItemStack(MoCItems.scrollOfFreedom, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.PAPER, 1), new ItemStack(Items.FEATHER, 1), new ItemStack(MoCItems.scrollOfRenaming, 1)));
            recipeList.add(new MerchantRecipe(new ItemStack(Items.PAPER, 1), new ItemStack(Items.FEATHER, 1), new ItemStack(MoCItems.scrollOfSale, 1)));
        }
    }

    public static class SmithLvl2Trades implements ITradeList {
        @Override
        public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 4 + random.nextInt(3)), new ItemStack(MoCItems.ancientSilverIngot, 1)));

            recipeList.add(new MerchantRecipe(new ItemStack(MoCItems.ancientSilverIngot, 3 + random.nextInt(2)), new ItemStack(Items.EMERALD, 1)));
        }
    }

    public static class WeaponsmithLvl2Trades implements ITradeList {
        @Override
        public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 4 + random.nextInt(3)), new ItemStack(MoCItems.ancientSilverIngot, 1)));

            recipeList.add(new MerchantRecipe(new ItemStack(MoCItems.ancientSilverIngot, 3 + random.nextInt(2)), new ItemStack(Items.EMERALD, 1)));
        }
    }

    public static class ToolsmithLvl2Trades implements ITradeList {
        @Override
        public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
            recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 4 + random.nextInt(3)), new ItemStack(MoCItems.ancientSilverIngot, 1)));

            recipeList.add(new MerchantRecipe(new ItemStack(MoCItems.ancientSilverIngot, 3 + random.nextInt(2)), new ItemStack(Items.EMERALD, 1)));
        }
    }
}
