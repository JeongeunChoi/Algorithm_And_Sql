package algorithm.design_pattern.command.receiver;

public class Light {

    private final String name;
    private boolean isOn;

    public Light(String name) {
        this.name = name;
        this.isOn = false;
    }

    public void on() {
        this.isOn = true;
        System.out.println(this.name + " ON!");
    }

    public void off() {
        this.isOn = false;
        System.out.println(this.name + " OFF!");
    }

}
