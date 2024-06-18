//package net.flyinely.silentfloor.impl;
//
//import net.flyinely.silentfloor.SilentFloorClient;
//import net.flyinely.silentfloor.structure.Action;
//import net.flyinely.silentfloor.structure.timer.AbstractTimer;
//
//public class ThreadTimer extends AbstractTimer implements Timer {
//
//    protected ThreadTimer(Action action, boolean impatient, long duration) {
//        super(action, impatient, duration);
//    }
//
//    @Override
//    public void start() {
//        running = true;
//        Thread thread = new Thread(() -> {
//            try {
//                Thread.sleep(duration); // Sleep for a short time to avoid busy waiting
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                SilentFloorClient.LOGGER.debug("finally");
//                running = false;
//                executeAction();
//            }
//        });
//        thread.setDaemon(true);
//        thread.start();
//        return;
//    }
//}
