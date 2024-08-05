package za.co.sjpret.virtualpet.thread;

import za.co.sjpret.virtualpet.controller.PetController;

public class MonitorThread extends Thread {
    private final PetController petController;

    public MonitorThread(PetController petController) {
        this.petController = petController;
        start();
    }

    @Override
    public void run() {
        try {
            while (!petController.getPet().isDead()) {
                if (petController.getPet().getFood() == 0) {
                    petController.getMainGui().setAnimalStatus("Animal Status: Starved");
                } else if (petController.getPet().getFood() <= 30) {
                    petController.getMainGui().setAnimalStatus("Animal Status: Hungry");
                } else if (petController.getPet().getFood() > 30) {
                    petController.getMainGui().setAnimalStatus("Animal Status: Healthy");
                }
                sleep(1000);
            }
            petController.getMainGui().setAnimalStatus("Animal Status: Dead");
        } catch (InterruptedException e) {
            System.err.println("Monitor Thread has been interrupted.");
        }
    }
}
