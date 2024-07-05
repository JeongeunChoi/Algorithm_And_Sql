package algorithm.design_pattern.decorator.options;

import algorithm.design_pattern.decorator.Beverage;
import algorithm.design_pattern.decorator.OptionDecorator;

public class EspressoShot extends OptionDecorator {

    public EspressoShot(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public int cost() {
        return beverage.cost() + 500;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " + 에스프레소샷";
    }
}
