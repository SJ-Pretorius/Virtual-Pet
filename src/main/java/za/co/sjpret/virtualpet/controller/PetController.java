package za.co.sjpret.virtualpet.controller;

import za.co.sjpret.virtualpet.gui.MainGui;
import za.co.sjpret.virtualpet.notification.WindowsNotification;
import za.co.sjpret.virtualpet.pet.Pet;
import za.co.sjpret.virtualpet.thread.FoodThread;

public class PetController {
    private final Pet pet;
    private final MainGui mainGui;
    private final WindowsNotification windowsNotification;
    private final FoodThread foodThread;

    public PetController(Pet pet) {
        this.pet = pet;
        this.mainGui = new MainGui(this);
        this.windowsNotification = new WindowsNotification(this);
        this.foodThread = new FoodThread(this);
    }

    public Pet getPet() {
        return pet;
    }

    public MainGui getMainGui() {
        return mainGui;
    }

    public WindowsNotification getWindowsNotification() {
        return windowsNotification;
    }
}