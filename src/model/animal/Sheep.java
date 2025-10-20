package model.animal;

import model.enums.AnimalType;
import model.ProductBundle;
import model.enums.FeedType;
import model.enums.ProductType;

public class Sheep extends Animal {

    public Sheep(String name, int age) {
        super(name, age, FeedType.HAY, AnimalType.SHEEP);
    }

    @Override
    public ProductBundle produce() {
        if (!isHungry()) {
            this.hungerLevel += 4;
            int amount = 1;
            System.out.println("Овца " + name + " дала " + amount + " ед. шерсти.");
            return new ProductBundle(ProductType.WOOL, amount);
        } else {
            System.out.println("Овца " + name + " слишком голодна, чтобы давать шерсть.");
            return null;
        }
    }
}