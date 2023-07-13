/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures;

import drzhark.mocreatures.entity.IMoCTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;

@Mod.EventBusSubscriber(modid = MoCConstants.MOD_ID)
public class MoCPlayerTracker {

    @SubscribeEvent
    public static void onPlayerLogout(PlayerLoggedOutEvent event) {
        EntityPlayer player = event.player;
        if (player.getRidingEntity() instanceof IMoCTameable) {
            IMoCTameable mocEntity = (IMoCTameable) player.getRidingEntity();
            mocEntity.setRiderDisconnecting(true);
        }
    }
}
