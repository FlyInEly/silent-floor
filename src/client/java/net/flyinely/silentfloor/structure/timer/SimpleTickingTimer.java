package net.flyinely.silentfloor.structure.timer;

import net.flyinely.silentfloor.structure.Action;

public class SimpleTickingTimer extends SimpleTimer implements TickingTimer {

    protected SimpleTickingTimer(Action action, boolean impatient, long duration) {
        super(action, impatient, duration);
    }

    @Override
    public Object start() {
        return null;
    }

    @Override
    public void tick() {

    }

}
