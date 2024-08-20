package za.co.sjpret.virtualpet.pet;

import za.co.sjpret.virtualpet.controller.MainController;

import javax.swing.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Pet implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String name;
    private final LocalDateTime dateCreated;
    private short age;
    private byte health;
    private byte food;
    private boolean dead;

    public Pet() {
        name = setName();
        dateCreated = LocalDateTime.now();
        age = 0;
        health = 100;
        food = 100;
        dead = false;
    }

    private String setName() {
        String petName = "";
        while (petName.isBlank() || petName.length() > 15) {
            petName = JOptionPane.showInputDialog(null,"Please enter a pet name:\n(15 characters max)", "Virtual Pet", JOptionPane.QUESTION_MESSAGE);
            if (petName == null) {
                throw new IllegalArgumentException("Pet name was blank");
            }
            if (petName.isBlank()) {
                JOptionPane.showMessageDialog(null, "A blank name can not be accepted.", "Virtual Pet", JOptionPane.ERROR_MESSAGE);
            }
            if (petName.length() > 15) {
                JOptionPane.showMessageDialog(null, "Pet name is longer than 15 characters, please try again.", "Virtual Pet", JOptionPane.ERROR_MESSAGE);
            }
        }
        return petName;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public short getAge() {
        return age;
    }

    //TODO Implement Age Functionality
    public void  incrementAgeByADay() {
        age += 1;
    }

    public byte getHealth() {
        return health;
    }

    //TODO Increment Health???
    public void incrementHealth(byte health) {
        if (this.health + health >= 100) {
            this.health = 100;
        } else {
            this.health += health;
            System.out.println("Pet " + name + "'s health increased by " + health + ". (Current: " + getHealth()  + ")");
        }
    }

    public void decrementHealth(byte health) {
        if (this.health - health <= 0) {
            this.health = 0;
            kill();
        } else {
            this.health -= health;
            System.out.println("Pet " + name + "'s health decreased by " + health + ". (Current: " + getHealth()  + ")");
        }
    }

    public byte getFood() {
        return food;
    }

    public void incrementFood(byte food) {
        if (!dead) {
            if (this.food + food >= 100) {
                this.food = 100;
                System.out.println("Pet " + name + "'s food is saturated");
            } else {
                this.food += food;
                System.out.println("Pet " + name + "'s food incremented by " + food + ". (Current: " + getFood()  + ")");
            }
        }
    }

    public void decrementFood(byte food) {
        if (this.food == 0) {
            System.out.println("Pet " + name + " is taking health damage due to being starved.");
            decrementHealth((byte) 1);
        } else if (this.food - food < 0) {
            this.food = 0;
            System.out.println("Pet " + name + "'s food is starved.");
        } else {
            this.food -= food;
            System.out.println("Pet " + name + "'s food decreased by " + food + ". (Current: " + getFood()  + ")");
        }
    }

    public boolean isDead() {
        return dead;
    }

    public void kill() {
        dead = true;
        MainController.removePet(this);
        System.out.println("Pet " + name + " is now declared dead.");
    }
}
