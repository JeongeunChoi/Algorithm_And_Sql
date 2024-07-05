package algorithm.design_pattern.decorator;

public abstract class OptionDecorator extends Beverage {

    protected Beverage beverage;

    public abstract String getDescription();

    public Size getSize() {
        return beverage.getSize();
    }
}
