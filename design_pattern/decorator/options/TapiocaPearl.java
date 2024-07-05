package algorithm.design_pattern.decorator.options;

import algorithm.design_pattern.decorator.Beverage;
import algorithm.design_pattern.decorator.OptionDecorator;

public class TapiocaPearl extends OptionDecorator {

    public TapiocaPearl(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public int cost() {
        return beverage.cost() + 1000;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " + 타피오카펄";
    }

}
