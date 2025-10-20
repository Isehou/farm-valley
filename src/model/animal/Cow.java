package model.animal;

import model.enums.AnimalType;
import model.ProductBundle;
import model.enums.FeedType;
import model.enums.ProductType;

public class Cow extends Animal {

    public Cow(String name, int age) {
        super(name, age, FeedType.HAY, AnimalType.COW);
    }

    @Override
    public ProductBundle produce() {
        if (!isHungry()) {
            this.hungerLevel += 3;
            int amount = 2;
            System.out.println("Корова " + name + " дала " + amount + "л. молока.");
            return new ProductBundle(ProductType.MILK, amount);
        } else {
            System.out.println("Корова " + name + " слишком голодна, чтобы давать молоко.");
            return null;
        }
    }
}