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
    public static VillagerProfession smithProfession;
    public static VillagerCareer smith, smithWeapon, smithTool;

    @SubscribeEvent
    public static void registerVillagers(RegistryEvent.Register<VillagerProfession> event) {
        smithProfession = event.getRegistry().getValue(new ResourceLocation("minecraft:smith"));

        if (smithProfession != null) {
            smith = smithProfession.getCareer(0);
            smithWeapon = smithProfession.getCareer(1);
            smithTool = smithProfession.getCareer(2);
        }

        smith.addTrade(2, new SmithLvl2Trades());
        smithWeapon.addTrade(2, new WeaponsmithLvl2Trades());
        smithTool.addTrade(2, new ToolsmithLvl2Trades());
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
