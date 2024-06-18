package net.flyinely.silentfloor.structure.timers;

import net.flyinely.silentfloor.structure.IAction;

public abstract class AbstractTickingTimer extends AbstractTimer implements ITickingTimer {

    protected long elapsed;

    protected AbstractTickingTimer(IAction action, boolean impatient, long duration) {
        super(action, impatient, duration);
    }

    public void start() {
        super.running = true;
    }

    public void pause() {
        super.running = false;
    }

    public int tick() {
        if (!super.isRunning()) {
            return -1;
        }
        elapsed++;
        if (hasExpired()) {
            super.running = false;
            super.executeAction();
            return 1;
        }
        return 0;
    }

    @Override
    public boolean hasExpired() {
        return elapsed >= duration;
    }
}
