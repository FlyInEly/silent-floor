package net.flyinely.silentfloor.structure.timer;

import net.flyinely.silentfloor.structure.Action;
import net.flyinely.silentfloor.structure.actions.PlaceboAction;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractTimer implements Timer {

    protected boolean running;
    protected boolean impatient;
    protected final long duration;
    @NotNull protected final Action action;

    protected AbstractTimer(Action action, boolean impatient, long duration) {
        this.action = (action != null) ? action : new PlaceboAction();
        this.impatient = impatient;
        this.duration = duration;
        if (impatient) {
            this.start();
        }
    }

    protected AbstractTimer(long duration) {
        this(null, duration);
    }

    protected AbstractTimer(Action action, long duration) {
        this(action,true, duration);
    }

    public final boolean isRunning() {
        return running;
    }

    public final boolean isImpatient() {
        return impatient;
    }

    public void pause() {
        running = false;
    }

    public final int executeAction() {
        // Precondition: action is @NotNull
        return action.execute();
    }

}
