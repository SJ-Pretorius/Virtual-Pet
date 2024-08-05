package za.co.sjpret.virtualpet.thread;

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
            while (!petController.getPet().isDead()) {
                petController.getPet().decrementFood((byte) 1);
                if (petController.getPet().getFood() == 0) {
                    petController.getMainGui().setAnimalStatus("Animal Status: Starved");
                } else if (petController.getPet().getFood() <= 30) {
                    petController.getMainGui().setAnimalStatus("Animal Status: Hungry");
                } else if (petController.getPet().getFood() > 30) {
                    petController.getMainGui().setAnimalStatus("Animal Status: Healthy");
                }
                petController.getMainGui().update();
                sleep(10);
            }
            petController.getMainGui().setAnimalStatus("Animal Status: Dead");
        } catch (InterruptedException e) {
            System.err.println("Food Thread has been interrupted.");
        }
    }
}