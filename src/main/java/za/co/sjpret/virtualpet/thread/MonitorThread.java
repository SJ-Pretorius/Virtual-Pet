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
            while (true) {
                if (petController.getPet().isDead()) {
                    petController.getMainGui().setAnimalStatus("Animal Status: Dead");
                } else if (petController.getPet().getFood() == 0) {
                    petController.getMainGui().setAnimalStatus("Animal Status: Starved");
                } else if (petController.getPet().getFood() <= 30) {
                    petController.getMainGui().setAnimalStatus("Animal Status: Hungry");
                } else if (petController.getPet().getFood() > 30) {
                    petController.getMainGui().setAnimalStatus("Animal Status: Healthy");
                }
                if (petController.getPet().isDead()) {
                    interrupt();
                }
                sleep(1000);
            }
        } catch (InterruptedException e) {
            System.err.println("Monitor Thread has been interrupted.");
        }
    }
}
