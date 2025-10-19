package model.animal;

import model.enums.AnimalType;
import model.enums.FeedType;
import model.ProductBundle;

public abstract class Animal {
    private String name;
    private int age;
    private int hungerLevel = 50;
    private AnimalType animalType;

    public Animal(String name, int age, int hungerLevel, AnimalType animalType) {
        this.name = name;
        this.age = age;
        this.hungerLevel = hungerLevel;
        this.animalType = animalType;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getHungerLevel() {
        return hungerLevel;
    }


    public AnimalType getAnimalType() {
        return animalType;
    }

    public void feed(FeedType feedType) {
        switch (feedType) {
            case HAY -> hungerLevel = Math.max(0, hungerLevel - 40);
            case GRAIN -> hungerLevel = Math.max(0, hungerLevel - 30);
        }
    }

    public abstract ProductBundle produce();
}
