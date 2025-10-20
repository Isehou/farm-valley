package service;

import model.ProductBundle;
import model.animal.Animal;
import model.enums.FeedType;
import model.enums.ProductType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Farm {
    private double balance;
    private final List<Animal> animals;
    private final Map<ProductType, Integer> warehouse;
    private final Map<FeedType, Integer> feedStock;

    public Farm(double initialBalance) {
        balance = initialBalance;
        animals = new ArrayList<>();
        warehouse = new HashMap<>();
        feedStock = new HashMap<>();
        for (ProductType type : ProductType.values()) warehouse.put(type, 0);
        for (FeedType type : FeedType.values()) feedStock.put(type, 0);
    }

    public double getBalance() {
        return balance;
    }

    public void addBalance(double amount) {
        if (amount > 0) balance += amount;
    }

    public boolean spendBalance(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        System.out.println("Недостаточно средств. Ваш баланс: " + balance);
        return false;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
        System.out.println(animal.getAnimalType().getDisplayName() + " " + animal.getName() + " добавлен(а) на ферму.");
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void addFeed(FeedType type, int amount) {
        feedStock.put(type, feedStock.get(type) + amount);
        System.out.println("Куплено " + amount + " ед. корма '" + type.getDisplayName() + "'.");
    }

    public Map<ProductType, Integer> getWarehouse() {
        return warehouse;
    }

    public void clearWarehouse(ProductType type) {
        warehouse.put(type, 0);
    }

    public void showStatus() {
        System.out.println("--- Состояние фермы ---");
        System.out.println("Баланс: " + String.format("%.2f", balance) + " сом");
        System.out.println("\n--- Животные (" + animals.size() + " шт.) ---");
        if (animals.isEmpty()) System.out.println("На ферме пока нет животных.");
        else animals.forEach(System.out::println);
        System.out.println("\n--- Склад продукции ---");
        warehouse.forEach((type, amount) -> System.out.println(type.getDisplayName() + ": " + amount + " шт."));
        System.out.println("\n--- Склад корма ---");
        feedStock.forEach((type, amount) -> System.out.println(type.getDisplayName() + ": " + amount + " шт."));
        System.out.println("---------------------------------");
    }

    public void feedAnimals() {
        System.out.println("--- Кормление животных ---");
        if (animals.isEmpty()) {
            System.out.println("Некого кормить, нет животных.");
            return;
        }
        for (Animal animal : animals) {
            FeedType feedType = animal.getCompatibleFeedType();
            int stock = feedStock.get(feedType);
            if (stock > 0) {
                if (animal.feed(feedType)) feedStock.put(feedType, stock - 1);
            } else {
                System.out.println("Недостаточно корма '" + feedType.getDisplayName() + "' для " + animal.getName());
            }
        }
    }

    public void collectProducts() {
        System.out.println("--- Сбор продукции ---");
        boolean collected = false;
        for (Animal animal : animals) {
            ProductBundle bundle = animal.produce();
            if (bundle != null) {
                warehouse.put(bundle.getProduct(), warehouse.get(bundle.getProduct()) + bundle.getAmount());
                collected = true;
            }
        }
        if (!collected) System.out.println("Не удалось собрать продукцию. Животные голодны или их нет.");
    }
}
