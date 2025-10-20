package service;

import model.animal.Animal;
import model.enums.AnimalType;
import service.Farm;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportsService {

    private final Farm farm;
    private double totalRevenue;

    public ReportsService(Farm farm) {
        this.farm = farm;
        this.totalRevenue = 0;
    }

    public void addRevenue(double revenue) {
        this.totalRevenue += revenue;
    }

    public void printAllReports() {
        System.out.println("--- ОТЧЁТЫ ---");
        printAnimalCounts();
        printAnimalsSortedByAge();
        printWarehouseSummary();
        printTotalRevenue();
        System.out.println("--------------");
    }

    private void printAnimalCounts() {
        System.out.println("\n1. Поголовье по видам:");
        Map<AnimalType, Long> counts = farm.getAnimals().stream()
                .collect(Collectors.groupingBy(
                        Animal::getAnimalType,
                        Collectors.counting()
                ));

        if (counts.isEmpty()) {
            System.out.println("   Животных нет.");
        } else {
            counts.forEach((type, count) ->
                    System.out.println("   - " + type.getDisplayName() + ": " + count + " шт.")
            );
        }
    }

    private void printAnimalsSortedByAge() {
        System.out.println("\n2. Животные по возрасту:");
        String sortedNames = farm.getAnimals().stream()
                .sorted(Comparator.comparingInt(Animal::getAge))
                .map(animal -> "   - " + animal.getName() + " (" + animal.getAnimalType().getDisplayName() + ", " + animal.getAge() + " лет)")
                .collect(Collectors.joining("\n"));

        if (sortedNames.isEmpty()) {
            System.out.println("   Животных нет.");
        } else {
            System.out.println(sortedNames);
        }
    }

    private void printWarehouseSummary() {
        System.out.println("\n3. Сводка по складу:");
        int totalProducts = farm.getWarehouse().values().stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("   - Всего продукции: " + totalProducts);

        farm.getWarehouse().entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .filter(entry -> entry.getValue() > 0)
                .ifPresentOrElse(
                        entry -> System.out.println("   - Больше всего: " + entry.getKey().getDisplayName() + " (" + entry.getValue() + " шт.)"),
                        () -> System.out.println("   - Склад пуст.")
                );
    }

    private void printTotalRevenue() {
        System.out.println("\n4. Общая выручка:");
        System.out.println("   - " + String.format("%.2f", totalRevenue) + " сом");
    }
}
