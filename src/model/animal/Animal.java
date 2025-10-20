package model.animal;

import model.enums.AnimalType;
import model.enums.FeedType;
import model.ProductBundle;

public abstract class Animal {
    protected String name;
    protected int age;
    protected int hungerLevel; // Уровень голода: 0 = сыт, 10 = голоден
    protected final FeedType compatibleFeedType;
    protected final AnimalType animalType;

    public Animal(String name, int age, FeedType compatibleFeedType, AnimalType animalType) {
        this.name = name;
        this.age = age;
        this.hungerLevel = 5;
        this.compatibleFeedType = compatibleFeedType;
        this.animalType = animalType;
    }

    // Метод кормления
    public boolean feed(FeedType feed) {
        if (feed == compatibleFeedType) {
            this.hungerLevel = Math.max(0, this.hungerLevel - 5);
            System.out.println(this.animalType.getDisplayName() + " " + this.name + " поел(а) " + feed.getDisplayName() + ". Уровень голода: " + this.hungerLevel);
            return true;
        } else {
            System.out.println(this.animalType.getDisplayName() + " " + this.name + " не ест " + feed.getDisplayName() + ".");
            return false;
        }
    }

    public abstract ProductBundle produce();

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getHungerLevel() {
        return hungerLevel;
    }

    public FeedType getCompatibleFeedType() {
        return compatibleFeedType;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public boolean isHungry() {
        return hungerLevel > 6;
    }

    @Override
    public String toString() {
        return "'" + name + "' (Возраст: " + age + ", Голод: " + hungerLevel + "/10)";
    }
}