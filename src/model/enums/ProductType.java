package model.enums;

public enum ProductType {
    MILK("Молоко"),
    EGG("Яйцо"),
    WOOL("Шерсть");

    private final String displayName;

    ProductType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}