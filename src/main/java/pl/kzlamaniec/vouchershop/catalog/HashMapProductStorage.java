package pl.kzlamaniec.vouchershop.catalog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HashMapProductStorage implements ProductStorage {
    private final HashMap<String, Product> products;

    public HashMapProductStorage() {
        this.products = new HashMap<>();
    }
    @Override
    public void save(Product newProduct) {
        products.put(newProduct.getId(), newProduct);
    }

    @Override
    public boolean isExists(String productId) {
        return products.containsKey(productId);
    }

    @Override
    public Product load(String productId) {
        return products.get(productId);
    }

    @Override
    public List<Product> allProducts() {
        return new ArrayList<>(products.values());
    }
}
