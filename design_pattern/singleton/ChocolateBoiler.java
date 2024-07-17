package algorithm.design_pattern.singleton;

public class ChocolateBoiler {

    private static ChocolateBoiler chocolateBoiler;
    private boolean empty;
    private boolean boiled;

    private ChocolateBoiler() {
        empty = true;
        boiled = false;
    }

    public static ChocolateBoiler getInstance() {
        if (chocolateBoiler == null) {
            chocolateBoiler = new ChocolateBoiler();
        }
        return chocolateBoiler;
    }

    public void fill() {
        if (isEmpty()) {
            empty = false;
            boiled = false;
            System.out.println("보일러에 우유와 초콜릿 넣는다.");
        }
    }

    public void drain() {
        if (!isEmpty() && isBoiled()) {
            empty = true;
            System.out.println("끓인 재료를 다음 단계로 넘긴다.");
        }
    }

    public void boil() {
        if (!isEmpty() && !isBoiled()) {
            boiled = true;
            System.out.println("재료를 끓인다");
        }
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isBoiled() {
        return boiled;
    }

}
