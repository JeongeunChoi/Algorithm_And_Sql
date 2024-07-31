package algorithm.design_pattern.facade;

public class Plate {

    public void pourWater(int waterAmount) {
        System.out.println("물 " + waterAmount + "ml 붓기");
    }

    public void putRamyeon() {
        System.out.println("라면 재료 넣기");
    }

    public void putOnPlate() {
        System.out.println("접시 올리기");
    }

    public void putDownPlate() {
        System.out.println("접시 내리기");
    }

}
