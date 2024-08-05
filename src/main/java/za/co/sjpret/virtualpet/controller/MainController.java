package za.co.sjpret.virtualpet.controller;

import za.co.sjpret.virtualpet.pet.Pet;
import za.co.sjpret.virtualpet.thread.InstanceManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//TODO Add interfaces for future improvements

public class MainController {
    private static final List<Pet> pets = new ArrayList<>();

    public MainController() {
        new InstanceManager();
        File file = new File("data.pet");
        if (file.exists()) {
            loadData();
        } else {
            createNewPet();
        }
    }

    private static void initialise(Pet pet) {
        new PetController(pet);
    }

    public static void createNewPet() {
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

    public static void removePet(Pet pet) {
        pets.remove(pet);
    }

    public static void loadData() {
        try (ObjectInputStream objStream = new ObjectInputStream(new FileInputStream("data.pet"))) {
            while (true) {
                try {
                    Pet pet = (Pet) objStream.readObject();
                    pets.add(pet);
                    initialise(pet);
                } catch (EOFException e) {
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error occurred while loading data: " + e.getMessage());
        }
    }

    public static void saveData() {
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
