package model.animal;

import model.enums.AnimalType;
import model.ProductBundle;
import model.enums.FeedType;
import model.enums.ProductType;

public class Chicken extends Animal {

    public Chicken(String name, int age) {
        super(name, age, FeedType.GRAIN, AnimalType.CHICKEN);
    }

    @Override
    public ProductBundle produce() {
        if (!isHungry()) {
            this.hungerLevel += 2; // Производство делает голоднее
            int amount = 5;
            System.out.println("Курица " + name + " снесла " + amount + " яиц.");
            return new ProductBundle(ProductType.EGG, amount);
        } else {
            System.out.println("Курица " + name + " слишком голодна, чтобы нести яйца.");
            return null;
        }
    }
}