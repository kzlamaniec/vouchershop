package pl.kzlamaniec.vouchershop.sales;

import pl.kzlamaniec.vouchershop.catalog.Product;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Basket {
    private final Map<String, Product> products;
    private final Map<String, Integer> productsQuantities;


    public Basket() {
        this.products = new HashMap<>();
        this.productsQuantities = new HashMap<>();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public void add(Product product) {
        products.put(product.getId(), product);
        if (productsQuantities.containsKey(product.getId())) {
            productsQuantities.put(
                    product.getId(),
                    productsQuantities.get(product.getId()) + 1);
        } else {
            productsQuantities.put(product.getId(), 1);
        }
    }

    public Integer getProductCount() {
        return products.size();
    }

    public List<BasketItem> getBasketItems() {
        return productsQuantities.entrySet()
                .stream()
                .map(es -> new BasketItem(es.getKey(), es.getValue()))
                .collect(Collectors.toList());
    }

    public void remove(String id) {
    }
}
