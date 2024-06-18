package net.flyinely.silentfloor.structure.actions.impl;

import net.flyinely.silentfloor.SilentFloorClient;
import net.flyinely.silentfloor.structure.IAction;
import net.minecraft.text.Text;

public class DisplayAction implements IAction {

    public DisplayAction(String message) {
        this(Text.of(message));
    }

    public enum DisplayArea {
        CHAT,
        ACTION_BAR
    }

    private final DisplayArea displayArea;
    private final Text message;

    public DisplayAction(Text message) {
        this(message, DisplayArea.CHAT);
    }

    public DisplayAction(Text message, DisplayArea displayArea) {
        super();
        this.displayArea = displayArea;
        this.message = message;
    }

    @Override
    public int execute() {
        return switch (displayArea) {
            case CHAT -> {
                SilentFloorClient.MESSENGER.display(message, false);
                yield 1;
            }
            case ACTION_BAR -> {
                SilentFloorClient.MESSENGER.display(message, true);
                yield 1;
            }
            default -> 0;
        };
    }

}
