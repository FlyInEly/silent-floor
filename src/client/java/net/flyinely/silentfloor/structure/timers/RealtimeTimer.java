package net.flyinely.silentfloor.structure.timers;

import net.flyinely.silentfloor.structure.Timer;

public class RealtimeTimer extends Timer {

    protected RealtimeTimer(long duration) {
        super(duration);
    }

    @Override
    public boolean hasExpired() {
        return false;
    }
}
