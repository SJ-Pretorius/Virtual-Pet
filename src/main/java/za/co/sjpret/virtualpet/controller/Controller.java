package za.co.sjpret.virtualpet.controller;

import za.co.sjpret.virtualpet.gui.Gui;
import za.co.sjpret.virtualpet.pet.Pet;
import za.co.sjpret.virtualpet.thread.FoodThread;

public class Controller {

    public Controller() {
        createNewPet();
    }

    private void createNewPet() {
        Pet pet = new Pet();
        Gui gui = new Gui(pet);
        new FoodThread(pet, gui);
    }

    //TODO Save and Load functionality from text file
}