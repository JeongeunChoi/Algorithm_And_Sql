package algorithm.design_pattern.command.commander;

import algorithm.design_pattern.command.receiver.Window;

public class WindowOpenCommand implements Command {

    Window window;

    public WindowOpenCommand(Window window) {
        this.window = window;
    }

    @Override
    public void execute() {
        window.open();
    }
}
