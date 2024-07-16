package za.co.sjpret;

import za.co.sjpret.pet.Pet;
import za.co.sjpret.thread.Life;

import javax.swing.*;

public class Main {
    public static Pet pet;

    public static void main(String[] args) {
        String petName = "";
        while (petName.isEmpty()) {
            petName = JOptionPane.showInputDialog(null,"Please enter a pet name: ", "Virtual Pet", JOptionPane.QUESTION_MESSAGE);
        }
        pet = new Pet(petName);
        Life life = new Life();
        life.start();
    }
}