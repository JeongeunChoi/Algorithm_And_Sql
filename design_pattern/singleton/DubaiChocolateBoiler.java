package algorithm.design_pattern.singleton;

public enum DubaiChocolateBoiler {
    DUBAI_CHOCOLATE_BOILER(true, false);

    private boolean empty, boiled;

    DubaiChocolateBoiler(boolean empty, boolean boiled) { // Enum의 생성자는 디폴트가 private 접근제한자
        this.empty = empty;
        this.boiled = boiled;
    }

    public void fill() {
        if (isEmpty()) {
            empty = false;
            boiled = false;
            System.out.println("보일러에 피스타치오, 카다이프를 넣는다.");
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
