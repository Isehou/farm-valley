package model.animal;

import model.enums.AnimalType;
import model.ProductBundle;
import model.enums.FeedType;
import model.enums.ProductType;

public class Chicken extends Animal{

    public Chicken(String name, int age, int hungerLevel, AnimalType animalType) {
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
        if(getHungerLevel() <= 40) {
            bundle.addProduct(ProductType.EGG, 10 + getAge() / 2);
        }
        return bundle;
    }
}
