package drzhark.mocreatures.compat.tinkers.traits.armor;

import c4.conarm.lib.traits.AbstractArmorTrait;
import drzhark.mocreatures.init.MoCSoundEvents;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import slimeknights.tconstruct.shared.client.ParticleEffect;
import slimeknights.tconstruct.tools.TinkerTools;

public class TraitShellEffectPlayerArmor extends AbstractArmorTrait {
    protected final float chance;
    protected final Potion playerEffect;
    protected final Potion targetEffect;
    protected final Potion playerTargetEffect;

    public TraitShellEffectPlayerArmor(String identifier, int color, float chance, Potion playerEffect, Potion targetEffect, Potion playerTargetEffect) {
        super(identifier, color);

        this.chance = chance;
        this.playerEffect = playerEffect;
        this.targetEffect = targetEffect;
        this.playerTargetEffect = playerTargetEffect;
    }

    @Override
    public float onDamaged(ItemStack armor, EntityPlayer player, DamageSource source, float damage, float newDamage, LivingDamageEvent event) {
        if (random.nextFloat() <= chance) {
            // Completely cancel out the damage
            event.setCanceled(true);
            player.world.playSound(null, player.getPosition(), MoCSoundEvents.ENTITY_GENERIC_CLANG, SoundCategory.PLAYERS, 1.0F, 0.5F / (player.world.rand.nextFloat() * 0.4F + 1.2F));

            // Inflict negative effect on the target (15 seconds) and inflict positive effect on the wielder (30 seconds)
            if (!player.world.isRemote) {
                // Don't set the normal effect on player targets
                if (!(source.getTrueSource() instanceof EntityPlayer)) {
                    ((EntityLivingBase) source.getTrueSource()).addPotionEffect(new PotionEffect(targetEffect, 15 * 20, 0));
                }

                // Set our alternative effect on player targets
                if (source.getTrueSource() instanceof EntityPlayer) {
                    ((EntityLivingBase) source.getTrueSource()).addPotionEffect(new PotionEffect(playerTargetEffect, 15 * 20, 0));
                }

                if (player instanceof EntityPlayer) {
                    player.addPotionEffect(new PotionEffect(playerEffect, 30 * 20, 0));
                    TinkerTools.proxy.spawnEffectParticle(ParticleEffect.Type.HEART_ARMOR, player, (int) damage);
                }
            }
        }

        return newDamage;
    }
}
