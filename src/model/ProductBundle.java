package model;

import model.enums.ProductType;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class ProductBundle {
    private final ProductType product;
    private final int amount;

    public ProductBundle(ProductType product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public ProductType getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }
}