package pl.kzlamaniec.vouchershop.catalog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public Product load(String productId) {
        var optional = products
                .stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst();

        return optional.get();
    }

    @Override
    public List<Product> allProducts() {
        return Collections.unmodifiableList(products);
    }
}
