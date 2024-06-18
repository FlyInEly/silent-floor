package net.flyinely.silentfloor.structure.timer;

import net.flyinely.silentfloor.structure.Action;

public class SimpleSleepingTimer extends AbstractSleepingTimer implements SleepingTimer {

    public SimpleSleepingTimer(Action action, boolean impatient, long duration) {
        super(action, impatient, duration);
    }

}
