package model.animal;

import model.enums.AnimalType;
import model.ProductBundle;
import model.enums.FeedType;
import model.enums.ProductType;

public class Sheep extends Animal {

    public Sheep(String name, int age, int hungerLevel, AnimalType animalType) {
        super(name, age, hungerLevel, animalType);
    }

    @Override
    public boolean feed(FeedType feedType) {
        if(feedType == FeedType.GRAIN) {
            changeHunger(-30);
        } else {
            changeHunger(-10);
        }
        return true;
    }

    @Override
    public ProductBundle produce() {
        ProductBundle bundle = new ProductBundle();
        if(getHungerLevel() <= 80) {
            bundle.addProduct(ProductType.WOOL, 3 + getAge() / 2);
        }
        return bundle;
    }
}
