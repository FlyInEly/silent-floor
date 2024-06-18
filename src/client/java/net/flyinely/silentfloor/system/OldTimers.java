package net.flyinely.silentfloor.system;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.flyinely.silentfloor.structure.timers.OldTickingTimer;
import net.flyinely.silentfloor.structure.OldTimer;
import net.minecraft.client.MinecraftClient;

import java.util.HashMap;
import java.util.Map;

import static net.flyinely.silentfloor.SilentFloorClient.LOGGER;

public class OldTimers {

    private static final String TIMER_ALREADY_REGISTERED_ERR = "Timer ID already registered: ";

    private final HashMap<String, OldTimer> timers;
    private final HashMap<String, OldTickingTimer> tickingTimers;

    public OldTimers() {
        this.timers = new HashMap<>();
        this.tickingTimers = new HashMap<>();
    }

    public void register(String timerID, OldTimer timer) {
        if (timers.containsKey(timerID) || tickingTimers.containsKey(timerID)) {
            LOGGER.error(TIMER_ALREADY_REGISTERED_ERR + "{}", timerID);
            return;
        }
        timers.put(timerID, timer);
        if (timer.getClass() == OldTickingTimer.class) {
            tickingTimers.put(timerID, (OldTickingTimer) timer);
        }
    }

    public OldTimer get(String timerID) {
        return timers.get(timerID);
    }

    public void clear(String timerID) {
        timers.remove(timerID);
        tickingTimers.remove(timerID);
    }

    public void register() {
        ClientTickEvents.END_CLIENT_TICK.register(this::tick);
    }

    private void tick(MinecraftClient client) {
        for (Map.Entry<String, OldTimer> entry : timers.entrySet()) {
            if (entry.getValue() instanceof OldTickingTimer) {
                ((OldTickingTimer) entry.getValue()).tick();
            }
            if (entry.getValue().needsClearing()) {
                clear(entry.getKey());
            }
        }

    }

}
