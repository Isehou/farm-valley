package model.animal;

import model.enums.AnimalType;
import model.ProductBundle;
import model.enums.FeedType;
import model.enums.ProductType;

public class Cow extends Animal {

    public Cow(String name, int age, int hungerLevel, AnimalType animalType) {
        super(name, age, hungerLevel, animalType);
    }

    @Override
    public boolean feed(FeedType feedType) {
        if(feedType == FeedType.HAY) {
            changeHunger(-30);
        } else {
            changeHunger(-10);
        }
        return true;
    }

    @Override
    public ProductBundle produce() {
        ProductBundle bundle = new ProductBundle();
        if(getHungerLevel() <= 60) {
            bundle.addProduct(ProductType.MILK, 5 + getAge() / 2);
        }
        return bundle;
    }
}
