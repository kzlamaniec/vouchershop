package pl.kzlamaniec.vouchershop.catalog;

import java.util.List;
import java.util.Optional;

public class JdbcProductStorage implements ProductStorage {
    @Override
    public void save(Product newProduct) {

    }

    @Override
    public boolean isExists(String productId) {
        return false;
    }

    @Override
    public Optional<Product> load(String productId) {
        return null;
    }

    @Override
    public List<Product> allProducts() {
        return null;
    }
}
