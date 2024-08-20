package za.co.sjpret.virtualpet.thread;

import za.co.sjpret.virtualpet.controller.PetController;

//TODO: Implement a way to stop the thread and everything when the pet dies
public class FoodThread extends Thread implements Runnable{
    private static final int TIMER = 10;
    private final PetController petController;

    public FoodThread(PetController petController) {
        this.petController = petController;
        start();
    }

    @Override
    public void run() {
        try {
            byte food;
            while (!petController.accessPet().isDead()) {
                petController.accessPet().decrementFood((byte) 1);
                food = petController.accessPet().getFood();
                if (food == 0) {
                    petController.accessMainGui().setAnimalStatus("Animal Status: Starved");
                } else if (food <= 30) {
                    petController.accessMainGui().setAnimalStatus("Animal Status: Hungry");
                } else {
                    petController.accessMainGui().setAnimalStatus("Animal Status: Healthy");
                }
                petController.accessMainGui().update();
                sleep(TIMER);
            }
            petController.accessMainGui().setAnimalStatus("Animal Status: Dead");
        } catch (InterruptedException e) {
            System.err.println("Food Thread has been interrupted.");
        }
    }
}