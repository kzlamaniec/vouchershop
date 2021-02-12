package pl.kzlamaniec.vouchershop.sales;

import pl.kzlamaniec.vouchershop.catalog.Product;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {
    private final Map<String, Product> products;

    public Basket() {
        this.products = new HashMap<>();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public void add(Product product) {
        products.put(product.getId(), product);
    }

    public Integer getProductCount() {
        return products.size();
    }

    public List<BasketItem> getBasketItems() {
        return Collections.emptyList();
    }

    public void remove(String id) {
    }
}
