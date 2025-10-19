package model;

import model.enums.ProductType;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class ProductBundle {
    private Map<ProductType, Integer> products = new EnumMap<>(ProductType.class);

    public void addProduct(ProductType productType, int amount) {
        products.put(productType, products.getOrDefault(productType, 0) + amount);
    }

    public Map<ProductType, Integer> getProducts() {
        return new HashMap<>(products);
    }

    @Override
    public String toString() {
        return products.toString();
    }
}
