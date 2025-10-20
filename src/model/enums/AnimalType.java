package model.enums;

public enum AnimalType {
    COW("Корова"),
    CHICKEN("Курица"),
    SHEEP("Овца");

    private final String displayName;

    AnimalType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}