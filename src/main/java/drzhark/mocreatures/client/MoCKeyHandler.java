/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.client;

import drzhark.mocreatures.entity.IMoCEntity;
import drzhark.mocreatures.network.MoCMessageHandler;
import drzhark.mocreatures.network.message.MoCMessageEntityDive;
import drzhark.mocreatures.network.message.MoCMessageEntityJump;
import drzhark.mocreatures.proxy.MoCProxyClient;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class MoCKeyHandler {

    static KeyBinding diveBinding = new KeyBinding("Flying Mount Descent (Mo' Creatures)", Keyboard.KEY_Z, "key.categories.movement");

    public MoCKeyHandler() {
        ClientRegistry.registerKeyBinding(diveBinding);
    }

    @SubscribeEvent
    public void onInput(InputEvent event) {
        int keyPressed = (Mouse.getEventButton() - 100);
        if (keyPressed == -101) {
            keyPressed = Keyboard.getEventKey();
        }

        EntityPlayer ep = MoCProxyClient.mc.player;
        if (ep == null) {
            return;
        }
        if (FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().getChatOpen()) {
            return; // if chatting return
        }
        if (Keyboard.getEventKeyState() && ep.getRidingEntity() != null) {
            Keyboard.enableRepeatEvents(true); // allow holding down key. Fixes flying
        }

        boolean kbJump = MoCProxyClient.mc.gameSettings.keyBindJump.getKeyCode() >= 0 ? GameSettings.isKeyDown(MoCProxyClient.mc.gameSettings.keyBindJump) : keyPressed == MoCProxyClient.mc.gameSettings.keyBindJump.getKeyCode();
        boolean kbDive = diveBinding.getKeyCode() >= 0 ? GameSettings.isKeyDown(diveBinding) : keyPressed == diveBinding.getKeyCode();

        /*
         * this avoids double jumping
         */
        if (kbJump && ep.getRidingEntity() != null && ep.getRidingEntity() instanceof IMoCEntity) {
            // jump code needs to be executed client/server simultaneously to take
            ((IMoCEntity) ep.getRidingEntity()).makeEntityJump();
            MoCMessageHandler.INSTANCE.sendToServer(new MoCMessageEntityJump());
        }

        if (kbDive && ep.getRidingEntity() != null && ep.getRidingEntity() instanceof IMoCEntity) {
            // jump code needs to be executed client/server simultaneously to take
            ((IMoCEntity) ep.getRidingEntity()).makeEntityDive();
            MoCMessageHandler.INSTANCE.sendToServer(new MoCMessageEntityDive());
        }
    }
}
