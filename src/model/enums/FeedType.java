package model.enums;

public enum FeedType {
    HAY("Сено"),
    GRAIN("Зерно");

    private final String displayName;

    FeedType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}