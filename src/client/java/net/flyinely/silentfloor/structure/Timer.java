package net.flyinely.silentfloor.structure;

public abstract class Timer {

    protected boolean active;

    public abstract void tick();

    public void reset() {

    }

    public boolean hasExpired() {
        return false;
    }

}
