package net.flyinely.silentfloor.structure.timer;

import net.flyinely.silentfloor.structure.Action;

public class ResettableSleepingTimer extends AbstractSleepingTimer implements SleepingTimer, ResettableTimer {

    private boolean automatic;

    public ResettableSleepingTimer(Action action, boolean impatient, long duration) {
        super(action, impatient, duration);
        this.automatic = true;
    }

    @Override
    public void reset() {
        if (!running) {
            start();
        }
    }

    @Override
    public boolean isAutomatic() {
        return automatic;
    }

    @Override
    public Thread start() {
        Thread t = super.start();
        while(t.getState().equals(Thread.State.TIMED_WAITING)) {
        }
        running = false;
        super.start();
        return t;
    }
}
