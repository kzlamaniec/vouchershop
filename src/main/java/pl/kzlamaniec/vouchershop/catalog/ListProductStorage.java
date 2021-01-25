package pl.kzlamaniec.vouchershop.catalog;

import java.util.*;

public class ListProductStorage implements ProductStorage {
        private List<Product> products;

        public ListProductStorage() {
            this.products = new ArrayList<>();
    }

    @Override
    public void save(Product newProduct) {
        products.add(newProduct);
    }

    @Override
    public boolean isExists(String productId) {
        return products
                .stream()
                .anyMatch(product -> product.getId().equals(productId));
    }

    @Override
    public Optional<Product> load(String productId) {
        return products
                .stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst();
    }

    @Override
    public List<Product> allProducts() {
        return Collections.unmodifiableList(products);
    }
}
