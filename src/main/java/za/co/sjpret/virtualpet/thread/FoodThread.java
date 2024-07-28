package za.co.sjpret.virtualpet.thread;

import za.co.sjpret.virtualpet.controller.MainController;
import za.co.sjpret.virtualpet.controller.PetController;

public class FoodThread extends Thread {
    private final PetController petController;

    public FoodThread(PetController petController) {
        this.petController = petController;
        start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                petController.getPet().decrementFood((byte) 1);
                petController.getMainGui().update();
                if (petController.getPet().isDead()) {
                    MainController.removePet(petController.getPet());
                    interrupt();
                }
                sleep(1000);
            }
        } catch (InterruptedException e) {
            System.err.println("Food Thread has been interrupted.");
        }

    }
}