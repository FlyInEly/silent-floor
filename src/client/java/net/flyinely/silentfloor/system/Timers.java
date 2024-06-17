package net.flyinely.silentfloor.system;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.flyinely.silentfloor.structure.timers.GametickTimer;
import net.flyinely.silentfloor.structure.Timer;
import net.minecraft.client.MinecraftClient;

import java.util.HashMap;
import java.util.Map;

import static net.flyinely.silentfloor.SilentFloorClient.LOGGER;

public class Timers {

    private static final String TIMER_ALREADY_REGISTERED_ERR = "Timer ID already registered: ";

    private final HashMap<String, Timer> timers;
    private final HashMap<String, Timer> tickingTimers;

    public Timers() {
        this.timers = new HashMap<>();
        this.tickingTimers = new HashMap<>();
    }

    public void register(String timerID, Timer timer) {
        if (timers.containsKey(timerID) || tickingTimers.containsKey(timerID)) {
            LOGGER.error(TIMER_ALREADY_REGISTERED_ERR + "{}", timerID);
            return;
        }
        timers.put(timerID, timer);
        if (timer.getClass() == GametickTimer.class) {
            tickingTimers.put(timerID, timer);
        }
    }

    public Timer get(String timerID) {
        return timers.get(timerID);
    }

    public void clear(String timerID) {
        timers.remove(timerID);
        tickingTimers.remove(timerID);
    }

    public void register() {
        ClientTickEvents.END_CLIENT_TICK.register(this::endTick);
    }

    private void endTick(MinecraftClient client) {
        for (Map.Entry<String, Timer> entry : tickingTimers.entrySet()) {
            entry.getValue().tick();
        }

    }

}
