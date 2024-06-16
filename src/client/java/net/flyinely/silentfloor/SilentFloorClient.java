package net.flyinely.silentfloor;

import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.flyinely.silentfloor.system.Keybinds;
import net.flyinely.silentfloor.system.Messenger;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SilentFloorClient implements ClientModInitializer {

	public static final String MOD_ID = "silent-floor";
	public static final String LOGGER_ID = "silent-floor";
	public static final String MOD_NAME = "Silent Floor";

	public static final MinecraftClient MC = MinecraftClient.getInstance();
	public static final Logger LOGGER = LoggerFactory.getLogger(LOGGER_ID);

	public static final Messenger MESSENGER = new Messenger();
	public static final Keybinds KEYBINDS = new Keybinds();

	@Override
	public void onInitializeClient() {

		// Register events
		ClientTickEvents.END_CLIENT_TICK.register(this::onEndTick);

		// Register keybinds
		KEYBINDS.register("prepareChat", GLFW.GLFW_KEY_Y);
		KEYBINDS.register("sendChat", GLFW.GLFW_KEY_U);
		KEYBINDS.register("sendCommand", GLFW.GLFW_KEY_I);
		KEYBINDS.register("displayChat", GLFW.GLFW_KEY_O);
		KEYBINDS.register("displayOverlay", GLFW.GLFW_KEY_P);

	}

	private void onEndTick(MinecraftClient client) {
		while(KEYBINDS.get("displayChat").wasPressed()) {
			MESSENGER.display("[TEST] Local chat message");
		}
		while(KEYBINDS.get("displayOverlay").wasPressed()) {
			MESSENGER.display("[TEST] Local action bar message", true);
		}
		while(KEYBINDS.get("sendChat").wasPressed()) {
			MESSENGER.sendChat("[TEST] C2S chat message");
		}
		while(KEYBINDS.get("sendCommand").wasPressed()) {
			MESSENGER.sendCommand("say [TEST] C2S command");
		}
		while(KEYBINDS.get("prepareChat").wasPressed()) {
			MESSENGER.prepareChat("[TEST] C2S chat message preview");
		}
	}

}