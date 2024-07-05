package algorithm.design_pattern.decorator;

public abstract class Beverage {

    protected String description = "음료 이름";
    protected Size size = Size.REGULAR;

    public String getDescription() {
        return description;
    }

    public Size getSize() {
        return this.size;
    }

    public abstract int cost();

    public enum Size {
        REGULAR, EXTRA
    }
}
