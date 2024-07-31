package algorithm.design_pattern.facade;

public class Ramyeon {

    private final String name;

    public Ramyeon(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void openBag() {
        System.out.println("라면 봉지 뜯기");
    }

    public void takeOutNoodles() {
        System.out.println("면 꺼내기");
    }

    public void takeOutPowder() {
        System.out.println("스프 꺼내기");
    }
}
