package net.flyinely.silentfloor.structure.timer;

public interface Timer<T> {

    T start();
    boolean isRunning();
    boolean isImpatient();
    int executeAction();

}
