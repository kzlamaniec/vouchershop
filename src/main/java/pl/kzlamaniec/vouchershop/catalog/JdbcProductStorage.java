package pl.kzlamaniec.vouchershop.catalog;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class JdbcProductStorage implements ProductStorage {
    private final JdbcTemplate jdbcTemplate;

    public JdbcProductStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Product newProduct) {
        jdbcTemplate.update("INSERT INTO `productsCatalog-products` " +
                        "(`id`, `description`, `picture`, `price`) values " +
                        "(?,?,?,?)",
                newProduct.getId(),
                newProduct.getDescription(),
                newProduct.getPicture(),
                newProduct.getPrice()
        );
    }

    @Override
    public boolean isExists(String productId) {
        int productCount = jdbcTemplate.queryForObject(
                "Select count(*) from `productsCatalog-products` where id = ?",
                new Object[]{productId},
                Integer.class
        );

        return productCount > 0;
    }

    @Override
    public Optional<Product> load(String productId) {
        return null;
    }

    @Override
    public List<Product> allProducts() {
        var query = "Select * from `productsCatalog-products` ";
        List<Product> allProducts = jdbcTemplate.query(
                query,
                (resultSet, i) -> {
                    Product p = new Product(UUID.fromString(resultSet.getString("id")));
                    p.setDescription(resultSet.getString("description"));
                    p.setPicture(resultSet.getString("picture"));

                    return p;
                }
        );

        return allProducts;
    }

    @Override
    public void clean() {
        jdbcTemplate.execute("Delete from `productsCatalog-products`");
    }
}
