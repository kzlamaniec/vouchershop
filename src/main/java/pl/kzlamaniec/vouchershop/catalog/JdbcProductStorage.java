package pl.kzlamaniec.vouchershop.catalog;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public class JdbcProductStorage implements ProductStorage {
    private final JdbcTemplate jdbcTemplate;

    public JdbcProductStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Product newProduct) {

    }

    @Override
    public boolean isExists(String productId) {
        return true;
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
