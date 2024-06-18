package net.flyinely.silentfloor.structure;

public interface IAction {

    // Abstract methods
    int execute();



    //ReversibleAction public abstract boolean undo();
    // To be placed in a timer, and the timer .runs the action on completion
    // ALlow passing to a Keybind object which needs making and gets put in Keybinds

}
