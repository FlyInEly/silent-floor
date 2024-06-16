package net.flyinely.silentfloor.system;

import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.text.Text;

import static net.flyinely.silentfloor.SilentFloorClient.MC;

public class Messenger {

    public void sendChat(String message) {
        sendChat(Text.of(message));
    }
    public void sendChat(Text message) {
        if (MC.player == null) {
            // ?Add logger error
            return;
        }
        MC.player.networkHandler.sendChatMessage(message.getString());
    }

    public void sendCommand(Text command) {
        sendCommand(command.getString());
    }
    public void sendCommand(String command) {
        if (MC.player == null) {
            // ?Add logger error
            return;
        }
        MC.player.networkHandler.sendChatCommand(command);
    }


    public void display(String message) {
        display(Text.of(message), false);
    }
    public void display(Text message) {
        display(message, false);
    }
    public void display(String message, boolean useActionBar) {
        display(Text.of(message), useActionBar);
    }
    public void display(Text message, boolean useActionBar) {
        if (MC.player == null) {
            // ?Add logger error
            return;
        }
        MC.player.sendMessage(message, useActionBar);
    }

    public void prepareChat(Text message) {
        prepareChat(message.getString());
    }
    public void prepareChat(String message) {
        MC.setScreen(new ChatScreen(message));
    }

}
