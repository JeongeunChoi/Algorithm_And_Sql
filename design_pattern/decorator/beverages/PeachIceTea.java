package algorithm.design_pattern.decorator.beverages;

import algorithm.design_pattern.decorator.Beverage;

public class PeachIceTea extends Beverage {

    public PeachIceTea(Size size) {
        this.description = "복숭아아이스티" + size;
        this.size = size;
    }

    @Override
    public int cost() {
        if (size == Size.REGULAR) {
            return 2900;
        } else if (size == Size.EXTRA) {
            return 2900 + 1000;
        }
        return 2900;
    }
}
