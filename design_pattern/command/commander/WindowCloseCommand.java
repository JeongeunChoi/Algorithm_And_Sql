package algorithm.design_pattern.command.commander;

import algorithm.design_pattern.command.receiver.Window;

public class WindowCloseCommand implements Command {

    Window window;

    public WindowCloseCommand(Window window) {
        this.window = window;
    }

    @Override
    public void execute() {
        window.close();
    }
}
