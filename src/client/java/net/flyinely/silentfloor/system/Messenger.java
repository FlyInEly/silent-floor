package net.flyinely.silentfloor.system;

import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.text.Text;

import static net.flyinely.silentfloor.SilentFloorClient.MC;

public class Messenger {

    /**
     * Sends a chat message to the server.
     * @param message The message content.
     * @since 1.1.1
     */
    public void sendChat(Text message) {
        if (MC.player == null) {
            // ?Add logger error
            return;
        }
        MC.player.networkHandler.sendChatMessage(message.getString());
    }

    /**
     * {@link #sendChat(Text)}
     * @custom.passes {@code message} using {@code Text.of(String)}
     * @since 1.1.1
     */
    public void sendChat(String message) {
        sendChat(Text.of(message));
    }

    /**
     * Sends a command to the server.
     * @param command The command, without a leading slash
     * @since 1.1.1
     */
    public void sendCommand(String command) {
        if (MC.player == null) {
            // ?Add logger error
            return;
        }
        MC.player.networkHandler.sendChatCommand(command);
    }

    /**
     * {@link #sendCommand(String)}
     * @custom.passes {@code command} using {@code Text.getString()}
     * @since 1.1.1
     */
    public void sendCommand(Text command) {
        sendCommand(command.getString());
    }

    /**
     * Displays a message in the chat or action bar.
     * @param message The message content.
     * @param useActionBar Whether to use the action bar instead of chat.
     * @since 1.1.1
     */
    public void display(Text message, boolean useActionBar) {
        if (MC.player == null) {
            // ?Add logger error
            return;
        }
        MC.player.sendMessage(message, useActionBar);
    }

    /**
     * {@link #display(Text, boolean)}
     * @custom.passes {@code message} using {@code Text.of(String)}
     * @custom.passes {@code useActionBar} defaults to {@code false}
     * @since 1.1.1
     */
    public void display(String message) {
        display(Text.of(message), false);
    }

    /**
     * {@link #display(Text, boolean)}
     * @custom.passes {@code message} using {@code Text.of(String)}
     * @custom.passes {@code useActionBar} defaults to {@code false}
     * @since 1.1.1
     */
    public void display(Text message) {
        display(message, false);
    }

    /**
     * {@link #display(Text, boolean)}
     * @custom.passes {@code message} using {@code Text.of(String)}
     * @custom.passes {@code useActionBar} defaults to {@code false}
     * @since 1.1.1
     */
    public void display(String message, boolean useActionBar) {
        display(Text.of(message), useActionBar);
    }

    /**
     * Drafts a message in the chat without sending.
     * @param message The message content.
     * @since 1.1.1
     */
    public void draftChat(String message) {
        MC.setScreen(new ChatScreen(message));
    }

    /**
     * {@link #draftChat(String)}
     * @custom.passes {@code message} using {@code Text.getString()}
     * @since 1.1.1
     */
    public void draftChat(Text message) {
        draftChat(message.getString());
    }

    /**
     * Drafts a command in the chat without sending.
     * @param command The command, without a leading slash.
     * @since 1.2.0
     */
    public void draftCommand(String command) {
        draftChat("/" + command);
    }

    /**
     * {@link #draftCommand(String)}
     * @passes {@code command} defaults to {@code ""}
     * @since 1.2.0
     */
    public void draftCommand() {
        draftChat("/");
    }

    /**
     * {@link #draftCommand(String)}
     * @custom.passes {@code command} using {@code Text.getString()}
     * @since 1.2.0
     */
    public void draftCommand(Text command) {
        draftCommand(command.getString());
    }

}
