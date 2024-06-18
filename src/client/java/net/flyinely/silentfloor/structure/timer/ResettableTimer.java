package net.flyinely.silentfloor.structure.timer;

import net.flyinely.silentfloor.structure.Action;

public interface ResettableTimer {

    void reset();
    boolean isAutomatic();

}
