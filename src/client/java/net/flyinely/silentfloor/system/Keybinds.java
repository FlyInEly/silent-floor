package net.flyinely.silentfloor.system;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import java.util.HashMap;

import static net.flyinely.silentfloor.SilentFloorClient.*;

public class Keybinds {

    private static final String KEYBIND_ALREADY_REGISTERED_ERR = "Keybind ID already registered: ";


    private final HashMap<String, KeyBinding> keybinds = new HashMap<>();

    public void register(String keybindID, int keycode) {
        if (keybinds.containsKey(keybindID)) {
            LOGGER.error(KEYBIND_ALREADY_REGISTERED_ERR + keybindID);
            return;
        }
        keybinds.put(keybindID, KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key." + MOD_ID + "." + keybindID,
                InputUtil.Type.KEYSYM,
                keycode,
                "category." + MOD_ID + "." + keybindID)));
    }

    public KeyBinding get(String keybindID) {
        return keybinds.get(keybindID);
    }
}
