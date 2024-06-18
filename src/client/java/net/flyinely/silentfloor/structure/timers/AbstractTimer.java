package net.flyinely.silentfloor.structure.timers;

import net.flyinely.silentfloor.structure.IAction;
import net.flyinely.silentfloor.structure.ITimer;
import net.flyinely.silentfloor.structure.actions.impl.PlaceboAction;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractTimer implements ITimer {

    protected boolean running;
    private final boolean impatient;
    protected final long duration;
    @NotNull protected final IAction action;

    protected AbstractTimer(IAction action, boolean impatient, long duration) {
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

    protected AbstractTimer(IAction action, long duration) {
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
