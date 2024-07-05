package algorithm.design_pattern.decorator.options;

import algorithm.design_pattern.decorator.Beverage;
import algorithm.design_pattern.decorator.OptionDecorator;

public class Whipping extends OptionDecorator {

    public Whipping(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public int cost() {
        return beverage.cost() + 500;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " + 휘핑크림";
    }

}
