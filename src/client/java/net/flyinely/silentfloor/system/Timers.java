package net.flyinely.silentfloor.system;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.flyinely.silentfloor.structure.timers.AbstractTickingTimer;
import net.flyinely.silentfloor.structure.timers.IResettingTimer;
import net.flyinely.silentfloor.structure.ITimer;
import net.minecraft.client.MinecraftClient;

import java.util.HashMap;
import java.util.Map;

import static net.flyinely.silentfloor.SilentFloorClient.LOGGER;

public class Timers<T extends ITimer> {

    private static final String TIMER_ALREADY_REGISTERED_ERR = "Timer ID already registered: ";
    private boolean registered;

    private final HashMap<String, T> timers;

    public Timers() {
        this.timers = new HashMap<>();
    }

    public int add(String timerID, T timer) {
        if (timers.containsKey(timerID)) {
            LOGGER.error(TIMER_ALREADY_REGISTERED_ERR + "{}", timerID);
            return -1;
        }
        timers.put(timerID, timer);
        return 0;
    }

    public int set(String timerID, T timer) {
        if (timers.containsKey(timerID)) {
            return this.reset(timerID);
        } else {
            return this.add(timerID, timer);
        }
    }


    public T get(String timerID) {
        return timers.get(timerID);
    }

    public void clear(String timerID) {
        timers.remove(timerID);
    }

    public int reset(String timerID) {
        try {
            ((IResettingTimer) timers.get(timerID)).reset();
        } catch (Exception ignored) {
            return -1;
        }
        return 0;
    }

    public final void register() {
        if (registered) return;
        ClientTickEvents.END_CLIENT_TICK.register(this::tick);
        this.registered = true;
    }

    private void tick(MinecraftClient client) {
        for (Map.Entry<String, T> entry : timers.entrySet()) {
            if (entry.getValue() instanceof AbstractTickingTimer) {
                ((AbstractTickingTimer) entry.getValue()).tick();
            }
        }

    }

}
