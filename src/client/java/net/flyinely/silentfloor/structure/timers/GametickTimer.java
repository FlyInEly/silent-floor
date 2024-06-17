package net.flyinely.silentfloor.structure.timers;

import net.flyinely.silentfloor.structure.Timer;

public class GametickTimer extends Timer {

    private static final int TICKS_PER_SECOND = 20;

    private final long duration;
    private long elapsed;

    public GametickTimer(long duration) {
        this.duration = duration;
        super.active = true;
    }

    public GametickTimer() {
        this(TICKS_PER_SECOND);
    }

    @Override
    public boolean hasExpired() {
        super.active = active && elapsed < duration;
        return elapsed >= duration;
    }

    @Override
    public void tick() {
        if (elapsed < duration) {
            elapsed++;
        }
    }

    @Override
    public void reset() {
        elapsed = 0;
    }

}
