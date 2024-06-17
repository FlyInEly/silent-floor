package net.flyinely.silentfloor;

import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.flyinely.silentfloor.system.Keybinds;
import net.flyinely.silentfloor.system.Messenger;
import net.flyinely.silentfloor.system.Timers;
import net.flyinely.silentfloor.structure.timers.GametickTimer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
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
	public static final Timers TIMERS = new Timers();

	@Override
	public void onInitializeClient() {

		TIMERS.register();

		// Register events
		ClientTickEvents.END_CLIENT_TICK.register(this::onEndTick);

		// Register keybinds
		KEYBINDS.register("draftChat", GLFW.GLFW_KEY_Y);
		KEYBINDS.register("draftCommand", GLFW.GLFW_KEY_Z);
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
		while(KEYBINDS.get("draftChat").wasPressed()) {
			TIMERS.register("test", new GametickTimer(30));
			MESSENGER.draftChat("[TEST] C2S chat message preview");
		}
		while(TIMERS.get("test") != null && TIMERS.get("test").hasExpired()) {
			if (MC.currentScreen instanceof ChatScreen) {
				MESSENGER.sendChat("Bye!");
				// Make mixin that injects get chatField method into ChatScreen
//				ChatScreen c = new ChatScreen("test");
//				c.
				MC.currentScreen.close();
			}
			TIMERS.clear("test");
		}

		while(KEYBINDS.get("draftCommand").wasPressed()) {
			MESSENGER.draftCommand("say [TEST] C2S command preview");
		}
	}

}