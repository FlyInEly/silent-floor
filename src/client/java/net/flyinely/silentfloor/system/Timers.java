package net.flyinely.silentfloor.system;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.flyinely.silentfloor.structure.OldTimer;
import net.flyinely.silentfloor.structure.timer.TickingTimer;
import net.flyinely.silentfloor.structure.timer.Timer;
import net.flyinely.silentfloor.structure.timers.OldTickingTimer;
import net.minecraft.client.MinecraftClient;

import java.util.HashMap;
import java.util.Map;

import static net.flyinely.silentfloor.SilentFloorClient.LOGGER;

public class Timers {

    private static final String TIMER_ALREADY_REGISTERED_ERR = "Timer ID already registered: ";

    private final HashMap<String, Timer> timers;

    public Timers() {
        this.timers = new HashMap<>();
    }

    public void register(String timerID, Timer timer) {
        if (timers.containsKey(timerID)) {
            LOGGER.error(TIMER_ALREADY_REGISTERED_ERR + "{}", timerID);
            return;
        }
        timers.put(timerID, timer);
    }

    public Timer get(String timerID) {
        return timers.get(timerID);
    }

    public void clear(String timerID) {
        timers.remove(timerID);
    }

    public void register() {
        ClientTickEvents.END_CLIENT_TICK.register(this::tick);
    }

    private void tick(MinecraftClient client) {
        for (Map.Entry<String, Timer> entry : timers.entrySet()) {
            if (entry.getValue() instanceof TickingTimer) {
                ((TickingTimer) entry.getValue()).tick();
            }
        }

    }

}
