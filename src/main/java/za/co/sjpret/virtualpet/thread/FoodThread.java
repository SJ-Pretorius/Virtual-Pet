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
            while (!petController.accessPet().isDead()) {
                petController.accessPet().decrementFood((byte) 1);
                byte food = petController.accessPet().getFood();
                if (food == 0) {
                    petController.accessMainGui().setAnimalStatus("Animal Status: Starved");
                } else if (food <= 30) {
                    petController.accessMainGui().setAnimalStatus("Animal Status: Hungry");
                } else {
                    petController.accessMainGui().setAnimalStatus("Animal Status: Healthy");
                }
                petController.accessMainGui().update();
                sleep(10);
            }
            petController.accessMainGui().setAnimalStatus("Animal Status: Dead");
        } catch (InterruptedException e) {
            System.err.println("Food Thread has been interrupted.");
        }
    }
}