package za.co.sjpret.virtualpet.thread;

import za.co.sjpret.virtualpet.gui.Gui;
import za.co.sjpret.virtualpet.pet.Pet;

public class Life extends Thread {
    private final Pet pet;
    private final Gui gui;

    public Life(Pet pet, Gui gui) {
        this.pet = pet;
        this.gui = gui;
        start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                pet.decrementFood((byte) 1);
                sleep(500);
                gui.update();
            }
        } catch (Exception e) {}

    }
}