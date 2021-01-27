package pl.kzlamaniec.vouchershop.catalog;

import java.util.*;

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
    public Optional<Product> load(String productId) {
        return Optional.ofNullable(products.get(productId));
    }

    @Override
    public List<Product> allProducts() {
        return new ArrayList<>(products.values());
    }
}
