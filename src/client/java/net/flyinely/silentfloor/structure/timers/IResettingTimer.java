package net.flyinely.silentfloor.structure.timers;

import net.flyinely.silentfloor.structure.ITimer;

public interface IResettingTimer extends ITimer {

    int reset();
    int getResetCount();
    int getExpiryCount();

}
