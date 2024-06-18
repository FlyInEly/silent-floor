package net.flyinely.silentfloor.structure.timer;

import net.flyinely.silentfloor.structure.Action;

public abstract class SimpleTimer extends AbstractTimer {

    protected SimpleTimer(Action action, boolean impatient, long duration) {
        super(action, impatient, duration);
    }
}
