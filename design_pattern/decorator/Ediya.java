package algorithm.design_pattern.decorator;

import algorithm.design_pattern.decorator.Beverage.Size;
import algorithm.design_pattern.decorator.beverages.PeachIceTea;
import algorithm.design_pattern.decorator.beverages.ToffeeNutLatte;
import algorithm.design_pattern.decorator.options.EspressoShot;
import algorithm.design_pattern.decorator.options.TapiocaPearl;
import algorithm.design_pattern.decorator.options.Whipping;

public class Ediya {

    public static void main(String[] args) {

        // 복숭아아이스티REGULAR
        Beverage beverage = new PeachIceTea(Size.REGULAR);
        System.out.println(beverage.getDescription() + " " + beverage.cost() + "원");

        // 복숭아아이스티EXTRA + 에스프레소샷 + 에스프레소샷 + 타피오카펄
        Beverage beverage2 = new PeachIceTea(Size.EXTRA);
        beverage2 = new EspressoShot(beverage2);
        beverage2 = new EspressoShot(beverage2);
        beverage2 = new TapiocaPearl(beverage2);
        System.out.println(beverage2.getDescription() + " " + beverage2.cost() + "원");

        // 토피넛라떼REGULAR
        Beverage beverage3 = new ToffeeNutLatte(Size.REGULAR);
        System.out.println(beverage3.getDescription() + " " + beverage3.cost() + "원");

        // 토피넛라떼EXTRA + 에스프레소샷 + 휘핑크림
        Beverage beverage4 = new ToffeeNutLatte(Size.EXTRA);
        beverage4 = new EspressoShot(beverage4);
        beverage4 = new Whipping(beverage4);
        System.out.println(beverage4.getDescription() + " " + beverage4.cost() + "원");
    }
}
