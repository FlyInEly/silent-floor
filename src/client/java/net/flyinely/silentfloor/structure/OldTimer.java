package net.flyinely.silentfloor.structure;

public abstract class OldTimer {

    // Final fields
    protected final long duration;
    protected final Action action;
    protected final boolean singleUse;

    // Non-final fields
    private boolean active;
    protected boolean impatient; // impatient timers start when initialized and run executeAction() when they expire
    protected long elapsed;

    // Abstract methods
    public abstract boolean hasExpired(); // must call executeAction() and setActive(false) when expired if impatient

    // Protected constructors, methods
    protected OldTimer(long duration, Action action, boolean impatient) {
        this.duration = duration;
        this.action = action;
        this.impatient = impatient;
        reset();
        singleUse = true; // CHANGE
    }

    protected OldTimer(long duration, Action action) {
        this(duration, action, true);
    }

    protected OldTimer(long duration) {
        this(duration, null);
    }

    protected final int executeAction() {
        if (action != null) {
            return action.execute();
        }
        return -1;
    }

    // Public getters
    public final boolean isActive() {
        return active;
    }

    public final boolean isSingleUse() {
        return singleUse;
    }

    public final boolean needsClearing() {
        return hasExpired() && singleUse;
    }

    // Public setters
    public void reset() {
        elapsed = 0;
        active = active || impatient;
    }

    public final void setActive() {
        setActive(true);
    }

    public final void setActive(boolean active) {
        this.active = active;
    }

    public final void setImpatient() {
        setImpatient(true);
    }

    public final void setImpatient(boolean impatient) {
        this.impatient = impatient;
    }

}
