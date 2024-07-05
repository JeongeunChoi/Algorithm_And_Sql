package algorithm.design_pattern.decorator.beverages;

import algorithm.design_pattern.decorator.Beverage;

public class ToffeeNutLatte extends Beverage {

    public ToffeeNutLatte(Size size) {
        this.description = "토피넛라떼" + size;
        this.size = size;
    }

    @Override
    public int cost() {
        if (size == Size.REGULAR) {
            return 4200;
        } else if (size == Size.EXTRA) {
            return 4200 + 1200;
        }
        return 4200;
    }
}
