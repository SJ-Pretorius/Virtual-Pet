package za.co.sjpret.virtualpet.controller;

import za.co.sjpret.virtualpet.notification.WindowsNotification;
import za.co.sjpret.virtualpet.gui.MainGui;
import za.co.sjpret.virtualpet.pet.Pet;
import za.co.sjpret.virtualpet.thread.FoodThread;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//TODO Make Controller more usable
public class Controller {
    private final List<Pet> pets = new ArrayList<>();

    public Controller() {
        File file = new File("data.pet");
        if (file.exists()) {
            loadData();
        } else {
            createNewPet();
        }
    }

    private void initialise(Pet pet) {
        MainGui mainGui = new MainGui(pet, this);
        new FoodThread(pet, mainGui);
        WindowsNotification windowsNotification = new WindowsNotification(mainGui, pet.getName());
        windowsNotification.showNotification("Use the tray icon to access your pet at any time.");
    }

    public void createNewPet() {
        Pet pet;
        try {
            pet = new Pet();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            if (pets.isEmpty()) {
                System.exit(0);
            }
            return;
        }
        pets.add(pet);
        initialise(pet);
    }

    public void removePet(Pet pet) {
        pets.remove(pet);
        pet.decrementHealth((byte)100);
        pet.decrementFood((byte)100);
    }

    public void loadData() {
        try (ObjectInputStream objStream = new ObjectInputStream(new FileInputStream("data.pet"))) {
            while (true) {
                try {
                    Pet pet = (Pet) objStream.readObject();
                    pets.add(pet);
                    initialise(pet);
                } catch (EOFException e) {
                    break; // End of file reached
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error occurred while loading data: " + e.getMessage());
        }
    }

    public void saveData() {
        if (!pets.isEmpty()) {
            try (ObjectOutputStream objStream = new ObjectOutputStream(new FileOutputStream("data.pet"))) {
                for (Pet pet : pets) {
                    objStream.writeObject(pet);
                }
            } catch (IOException e) {
                System.out.println("Error occurred while saving data: " + e);
            }
        } else {
            File file = new File("data.pet");
            file.delete();
        }
    }
}
