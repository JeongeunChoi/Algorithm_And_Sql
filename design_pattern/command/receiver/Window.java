package algorithm.design_pattern.command.receiver;

public class Window {

    private final String name;
    private boolean isOpen;

    public Window(String name) {
        this.name = name;
        this.isOpen = false;
    }

    public void open() {
        this.isOpen = true;
        System.out.println(this.name + " OPEN!");
    }

    public void close() {
        this.isOpen = false;
        System.out.println(this.name + " CLOSE!");
    }

}
