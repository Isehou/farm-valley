package model.animal;

import model.enums.AnimalType;
import model.ProductBundle;

public class Cow extends Animal {


    public Cow(String name, int age, int hungerLevel, AnimalType animalType) {
        super(name, age, hungerLevel, animalType);
    }

    @Override
    public ProductBundle produce() {
        return null;
    }
}
