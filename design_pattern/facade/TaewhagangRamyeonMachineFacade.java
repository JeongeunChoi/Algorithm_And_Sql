package algorithm.design_pattern.facade;

public class TaewhagangRamyeonMachineFacade {

    Boiler boiler;
    Plate plate;
    Ramyeon ramyeon;

    public TaewhagangRamyeonMachineFacade(Boiler boiler, Plate plate, Ramyeon ramyeon) {
        this.boiler = boiler;
        this.plate = plate;
        this.ramyeon = ramyeon;
    }

    public void cookRamyeon() {
        System.out.println("점심으로 " + ramyeon.getName() + " 끓이기 시작!");
        plate.putOnPlate();
        plate.pourWater(550);
        boiler.startBoil();
        ramyeon.openBag();
        ramyeon.takeOutNoodles();
        ramyeon.takeOutPowder();
        plate.putRamyeon();
        boiler.endBoil();
        plate.putDownPlate();
        System.out.println(ramyeon.getName() + " 완성🍜");
    }

}
