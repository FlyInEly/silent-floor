package net.flyinely.silentfloor.structure.timers;

import net.flyinely.silentfloor.structure.Action;
import net.flyinely.silentfloor.structure.Timer;

public class TickingTimer extends Timer {

    private static final int TICKS_PER_SECOND = 20;

    public TickingTimer(long duration, Action action) {
        super(duration, action);
    }

    public TickingTimer(long duration) {
        this(duration, null);
    }

    public TickingTimer() {
        this(TICKS_PER_SECOND);
    }

    @Override
    public boolean hasExpired() {
        boolean hasExpired = elapsed >= duration;
        if (hasExpired && super.isActive() && impatient) {
            executeAction();
            super.setActive(false);
        }
        return hasExpired;
    }

    public boolean tick() {
        if (elapsed < duration) {
            elapsed++;
        }
        return hasExpired();
    }

}
