import model.enums.AnimalType;
import model.enums.FeedType;
import model.enums.ProductType;
import service.Farm;
import service.MarketService;
import service.ReportsService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FarmApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Farm farm = new Farm(10000.0);
    private static final MarketService market = new MarketService();
    private static final ReportsService reports = new ReportsService(farm);

    public static void main(String[] args) {
        System.out.println("--- Добро пожаловать на Java Ферму ---");

        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            int choice = readInt(0, 7);

            switch (choice) {
                case 1 -> farm.showStatus();
                case 2 -> handleBuyAnimal();
                case 3 -> handleBuyFeed();
                case 4 -> farm.feedAnimals();
                case 5 -> farm.collectProducts();
                case 6 -> handleSellProducts();
                case 7 -> reports.printAllReports();
                case 0 -> isRunning = false;
            }
            if (isRunning) {
                System.out.println("\n(Enter для продолжения)");
                scanner.nextLine();
            }
        }
        System.out.println("--- Выход ---");
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--- Меню ---");
        System.out.println("1 - Показать состояние фермы");
        System.out.println("2 - Добавить животное");
        System.out.println("3 - Купить корм");
        System.out.println("4 - Кормить животных");
        System.out.println("5 - Собрать продукцию");
        System.out.println("6 - Продать продукцию");
        System.out.println("7 - Показать отчёты");
        System.out.println("0 - Выход");
        System.out.print("Выберите действие: ");
    }

    private static void handleBuyAnimal() {
        System.out.println("--- Покупка животного ---");
        market.showAnimalPrices();
        System.out.println("0. Отмена");
        System.out.print("Ваш выбор: ");

        int choice = readInt(0, AnimalType.values().length);
        if (choice == 0) return;

        AnimalType type = AnimalType.values()[choice - 1];
        System.out.print("Введите имя: ");
        String name = readString();
        System.out.print("Возраст: ");
        int age = readInt(1, 20);

        market.buyAnimal(farm, type, name, age);
    }

    private static void handleBuyFeed() {
        System.out.println("--- Покупка корма ---");
        market.showFeedPrices();
        System.out.println("0. Отмена");
        System.out.print("Ваш выбор: ");

        int choice = readInt(0, FeedType.values().length);
        if (choice == 0) return;

        FeedType type = FeedType.values()[choice - 1];
        System.out.print("Количество: ");
        int amount = readInt(1, 1000);

        market.buyFeed(farm, type, amount);
    }

    private static void handleSellProducts() {
        System.out.println("--- Продажа продукции ---");
        farm.getWarehouse().forEach((type, amount) ->
                System.out.println(type.getDisplayName() + ": " + amount + " шт.")
        );
        market.showProductPrices();
        System.out.println("0. Отмена");
        System.out.print("Ваш выбор: ");

        int choice = readInt(0, ProductType.values().length);
        if (choice == 0) return;

        ProductType type = ProductType.values()[choice - 1];
        double profit = market.sellProducts(farm, type);
        reports.addRevenue(profit);
    }

    private static int readInt(int min, int max) {
        while (true) {
            try {
                int input = scanner.nextInt();
                scanner.nextLine();
                if (input < min || input > max) {
                    System.out.print("Введите от " + min + " до " + max + ": ");
                } else {
                    return input;
                }
            } catch (InputMismatchException e) {
                
                System.out.print("Ошибка. Введите число: ");
                scanner.nextLine();
            }
        }
    }

    private static String readString() {
        while (true) {
            String input = scanner.nextLine();
            if (input == null || input.trim().isEmpty()) {
                System.out.print("Введите текст: ");
            } else {
                return input.trim();
            }
        }
    }
}
