package service;

import model.animal.Animal;
import model.animal.Chicken;
import model.animal.Cow;
import model.animal.Sheep;
import model.enums.AnimalType;
import model.enums.FeedType;
import model.enums.ProductType;

import java.util.Map;

public class MarketService {
    private final Map<AnimalType, Double> animalBuyPrices;
    private final Map<ProductType, Double> productSellPrices;
    private final Map<FeedType, Double> feedBuyPrices;

    public MarketService() {
        animalBuyPrices = Map.of(
                AnimalType.COW, 1000.0,
                AnimalType.CHICKEN, 100.0,
                AnimalType.SHEEP, 500.0
        );
        productSellPrices = Map.of(
                ProductType.MILK, 20.0,
                ProductType.EGG, 5.0,
                ProductType.WOOL, 150.0
        );
        feedBuyPrices = Map.of(
                FeedType.HAY, 10.0,
                FeedType.GRAIN, 25.0
        );
    }

    public void showAnimalPrices() {
        System.out.println("--- Цены на животных ---");
        for (var entry : animalBuyPrices.entrySet()) {
            System.out.println((entry.getKey().ordinal() + 1) + ". " + entry.getKey().getDisplayName() + " - " + entry.getValue() + " сом");
        }
    }

    public void showFeedPrices() {
        System.out.println("--- Цены на корм ---");
        for (var entry : feedBuyPrices.entrySet()) {
            System.out.println((entry.getKey().ordinal() + 1) + ". " + entry.getKey().getDisplayName() + " - " + entry.getValue() + " сом/шт.");
        }
    }

    public void showProductPrices() {
        System.out.println("--- Цены продажи продукции ---");
        for (var entry : productSellPrices.entrySet()) {
            System.out.println((entry.getKey().ordinal() + 1) + ". " + entry.getKey().getDisplayName() + " - " + entry.getValue() + " сом/шт.");
        }
    }

    public void buyAnimal(Farm farm, AnimalType type, String name, int age) {
        double price = animalBuyPrices.get(type);
        if (farm.spendBalance(price)) {
            Animal newAnimal = null;
            if (type == AnimalType.COW) newAnimal = new Cow(name, age);
            else if (type == AnimalType.CHICKEN) newAnimal = new Chicken(name, age);
            else if (type == AnimalType.SHEEP) newAnimal = new Sheep(name, age);
            if (newAnimal != null) farm.addAnimal(newAnimal);
        }
    }

    public void buyFeed(Farm farm, FeedType type, int amount) {
        if (amount <= 0) {
            System.out.println("Количество должно быть положительным.");
            return;
        }
        double totalPrice = feedBuyPrices.get(type) * amount;
        if (farm.spendBalance(totalPrice)) farm.addFeed(type, amount);
    }

    public double sellProducts(Farm farm, ProductType type) {
        int amount = farm.getWarehouse().get(type);
        if (amount <= 0) {
            System.out.println("На складе нет '" + type.getDisplayName() + "' для продажи.");
            return 0;
        }
        double totalProfit = amount * productSellPrices.get(type);
        farm.addBalance(totalProfit);
        farm.clearWarehouse(type);
        System.out.println("Продано " + amount + " ед. '" + type.getDisplayName() + "' за " + totalProfit + " сом.");
        return totalProfit;
    }
}
