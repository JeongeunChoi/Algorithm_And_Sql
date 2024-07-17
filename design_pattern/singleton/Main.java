package algorithm.design_pattern.singleton;

public class Main {

    public static void main(String[] args) {
        // 클래스를 사용한 싱글톤 구현
        ChocolateBoiler chocolateBoiler = ChocolateBoiler.getInstance();
        chocolateBoiler.fill();
        chocolateBoiler.boil();
        chocolateBoiler.drain();

        // 이넘을 사용한 싱글톤 구현
        DubaiChocolateBoiler dubaiChocolateBoiler = DubaiChocolateBoiler.DUBAI_CHOCOLATE_BOILER;
        dubaiChocolateBoiler.fill();
        dubaiChocolateBoiler.boil();
        dubaiChocolateBoiler.drain();
    }

}
