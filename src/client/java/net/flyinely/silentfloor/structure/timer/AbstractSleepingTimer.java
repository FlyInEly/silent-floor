package net.flyinely.silentfloor.structure.timer;

import net.flyinely.silentfloor.SilentFloorClient;
import net.flyinely.silentfloor.structure.Action;

public abstract class AbstractSleepingTimer extends AbstractTimer implements SleepingTimer {

    protected AbstractSleepingTimer(Action action, boolean impatient, long duration) {
        super(action, impatient, duration);
    }

    @Override
    public Thread start() {
        running = true;
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(duration); // Sleep for a short time to avoid busy waiting
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                SilentFloorClient.LOGGER.debug("finally");
                running = false;
                executeAction();
            }
        });
        thread.setDaemon(true);
        thread.start();
        return thread;
    }
}
