package za.co.sjpret.pet;

public class Pet {
    private final String name;
    private short age;
    private byte health;
    private byte food;

    public Pet(String name) {
        this.name = name;
        age = 0;
        health = 100;
        food = 100;
    }

    public String getName() {
        return name;
    }

    public short getAge() {
        return age;
    }

    public void incrementAgeByADay() {
        age += 1;
    }

    public byte getHealth() {
        return health;
    }

    public void incrementHealth(byte health) {
        if (this.health + health > 100) {
            this.health = 100;
        } else {
            this.health += health;
        }
    }

    public void decrementHealth(byte health) {
        if (this.health - health < 0) {
            this.health = 0;
        } else {
            this.health -= health;
        }
    }

    public byte getFood() {
        return food;
    }

    public void incrementFood(byte food) {
        if (this.food + food > 100) {
            this.food = 100;
        } else {
            this.food += food;
        }
    }

    public void decrementFood(byte food) {
        if (this.food == 0) {
            decrementHealth((byte) 1);
        } else if (this.food - food < 0) {
            this.food = 0;
        } else {
            this.food -= food;
        }
    }
}
