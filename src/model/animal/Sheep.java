package model.animal;

import model.enums.AnimalType;
import model.ProductBundle;

public class Sheep extends Animal {

    public Sheep(String name, int age, int hungerLevel, AnimalType animalType) {
        super(name, age, hungerLevel, animalType);
    }

    @Override
    public ProductBundle produce() {
        return null;
    }
}
