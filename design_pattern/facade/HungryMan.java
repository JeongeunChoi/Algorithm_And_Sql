package algorithm.design_pattern.facade;

public class HungryMan {

    public static void main(String[] args) {
        Boiler boiler = new Boiler();
        Plate plate = new Plate();
        Ramyeon ramyeon = new Ramyeon("진라면");

        TaewhagangRamyeonMachineFacade taewhagangRamyeonMachineFacade = new TaewhagangRamyeonMachineFacade(boiler, plate, ramyeon);
        taewhagangRamyeonMachineFacade.cookRamyeon();
    }

}
