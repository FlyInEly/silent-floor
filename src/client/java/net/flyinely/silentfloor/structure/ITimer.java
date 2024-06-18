package net.flyinely.silentfloor.structure;

public interface ITimer {

    void start();
    void pause();
    boolean isRunning();
    boolean isImpatient();
    int executeAction();
    boolean hasExpired();

}
