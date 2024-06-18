package net.flyinely.silentfloor.structure.timers.impl;

import net.flyinely.silentfloor.structure.IAction;
import net.flyinely.silentfloor.structure.timers.AbstractTickingTimer;
import net.flyinely.silentfloor.structure.timers.IResettingTimer;


public class TickingTimer extends AbstractTickingTimer implements IResettingTimer {

    private final boolean resettable;
    private final boolean resetOnExpire;
    private int resetCount;
    private int expiryCount;

    public TickingTimer(long duration, IAction action, boolean resettable, boolean resetOnExpire, boolean impatient) {
        super(action, impatient, duration);
        this.resettable = resettable;
        this.resetOnExpire = resetOnExpire;
        this.resetCount = 0;
        this.expiryCount = 0;
    }

    private TickingTimer(Builder builder) {
        this(builder.duration, builder.action, builder.resettable, builder.resetOnExpire, builder.impatient);
    }

    public static class Builder {
        // Required args
        private final long duration;
        private final IAction action;

        // Optional args
        private boolean resettable;
        private boolean resetOnExpire;
        private boolean impatient;

        public Builder(long duration, IAction action) {
            this.duration = duration;
            this.action = action;
            this.resettable = false;
            this.resetOnExpire = false;
            this.impatient = true;
        }

        public Builder autoReset() {
            return this.setResettable(true).setResetOnExpire(true);
        }

        public Builder manualReset() {
            return this.setResettable(true).setResetOnExpire(false);
        }

        public Builder singleUse() {
            return this.setResettable(false).setResetOnExpire(false);
        }

        public Builder setResetOnExpire(boolean resetOnExpire) {
            this.resetOnExpire = resetOnExpire;
            return this;
        }

        public Builder setResettable(boolean resettable) {
            this.resettable = resettable;
            return this;
        }

        public Builder impatient() {
            return setImpatient(true);
        }

        public Builder patient() {
            return setImpatient(false);
        }

        public Builder setImpatient(boolean impatient) {
            this.impatient = impatient;
            return this;
        }

        public TickingTimer build() {
            return new TickingTimer(this);
        }

    }

    @Override
    public int tick() {
        int n = super.tick();
        if (n == 1) {
            expiryCount++;
            if (resetOnExpire) {
                reset();
            }
        }
        return n;
    }

    @Override
    public int reset() {
        if (!resettable) return -1;
        resetCount++;
        super.elapsed = 0;
        if (super.isImpatient()) {
            super.start();
            return 1;
        }
        return 0;
    }

    @Override
    public final int getResetCount() {
        return resetCount;
    }

    @Override
    public final int getExpiryCount() {
        return expiryCount;
    }

}
