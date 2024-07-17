package za.co.sjpret.virtualpet.thread;

import za.co.sjpret.virtualpet.gui.MainGui;
import za.co.sjpret.virtualpet.pet.Pet;

public class FoodThread extends Thread {
    private final Pet pet;
    private final MainGui mainGui;

    public FoodThread(Pet pet, MainGui mainGui) {
        this.pet = pet;
        this.mainGui = mainGui;
        start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                pet.decrementFood((byte) 1);
                sleep(500);
                mainGui.update();
            }
        } catch (Exception e) {}

    }
}