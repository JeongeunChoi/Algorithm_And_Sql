package algorithm.design_pattern.command.receiver;

public class Stereo {

    private final String name;
    private final String cd;
    private boolean isOn;
    private int volume;

    public Stereo(String name, String cd) {
        this.name = name;
        this.cd = cd;
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

    public void setCd() {
        System.out.println("PUT CD " + this.cd);
    }

    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println("SET VOLUME " + volume);
    }
}
