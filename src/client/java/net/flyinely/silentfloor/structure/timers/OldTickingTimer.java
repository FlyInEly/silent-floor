package net.flyinely.silentfloor.structure.timers;

import net.flyinely.silentfloor.structure.Action;
import net.flyinely.silentfloor.structure.OldTimer;

public class OldTickingTimer extends OldTimer {

    private static final int TICKS_PER_SECOND = 20;

    public OldTickingTimer(long duration, Action action) {
        super(duration, action);
    }

    public OldTickingTimer(long duration) {
        this(duration, null);
    }

    public OldTickingTimer() {
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
