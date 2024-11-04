package za.co.sjpret.virtualpet.thread;

import za.co.sjpret.virtualpet.controller.PetController;

//TODO: Implement a way to stop the thread and everything when the pet dies
//TODO: Move Animal Status to the Gui Class

public class FoodThread extends Thread implements Runnable{
    private static final int TIMER = 1000;
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
                petController.accessMainGui().update();
                sleep(TIMER);
            }
        } catch (InterruptedException e) {
            System.err.println("Food Thread has been interrupted.");
        }
    }
}