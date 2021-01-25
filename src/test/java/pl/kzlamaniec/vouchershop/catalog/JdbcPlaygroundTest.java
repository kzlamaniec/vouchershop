package pl.kzlamaniec.vouchershop.catalog;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JdbcPlaygroundTest {

    private static final String PRODUCT_ID = "b64e3853-851c-427a-ad82-1a144c5d84ce";
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        //Arrange
        jdbcTemplate.execute("DROP TABLE `productsCatalog-products` IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE `productsCatalog-products` (" +
                "`id` varchar(100) NOT NULL," +
                "`description` varchar(255)," +
                "`picture` varchar(255)," +
                "`price` DECIMAL(12,2)," +
                "PRIMARY KEY(id)" +
                ");");
    }

    @Test
    public void itCountProducts() {

        //Act
        int productCount = jdbcTemplate.queryForObject(
                "Select count(*) from `productsCatalog-products`",
                Integer.class
        );
        //Assert
        assertThat(productCount).isEqualTo(0);
    }

    @Test
    public void itAllowToAddProduct() {
        jdbcTemplate.execute("INSERT INTO `productsCatalog-products` (`id`, `description`, `picture`, `price`) values " +
                "('b64e3853-851c-427a-ad82-1a144c5d84ce', 'p1 desc', 'p1 pic', 5.5)," +
                "('b64e3853-851c-427a-ad82-1a144c5d84c1', 'p2 desc', 'p2 pic', 15.15)" +
                "; ");
        //Act
        int productCount = jdbcTemplate.queryForObject(
                "Select count(*) from `productsCatalog-products`",
                Integer.class
        );
        //Assert
        assertThat(productCount).isEqualTo(2);
    }

    @Test
    public void itAllowsLoadProduct() {
        jdbcTemplate.execute("INSERT INTO `productsCatalog-products` (`id`, `description`, `picture`, `price`) values " +
                "('b64e3853-851c-427a-ad82-1a144c5d84ce', 'p1 desc', 'p1 pic', 5.5)," +
                "('b64e3853-851c-427a-ad82-1a144c5d84c1', 'p2 desc', 'p2 pic', 15.15)" +
                "; ");

        var query = "Select * from `productsCatalog-products` where id = ?";
        Product product = jdbcTemplate.queryForObject(query, new Object[]{PRODUCT_ID}, new ProductRowMapper());

        assertThat(product.getId())
                .isEqualTo(PRODUCT_ID);
    }

    @Test
    public void  itAllowToLoadsAllProducts() {
        jdbcTemplate.execute("INSERT INTO `productsCatalog-products` (`id`, `description`, `picture`, `price`) values " +
                "('b64e3853-851c-427a-ad82-1a144c5d84ce', 'p1 desc', 'p1 pic', 5.5)," +
                "('b64e3853-851c-427a-ad82-1a144c5d84c1', 'p2 desc', 'p2 pic', 15.15)" +
                "; ");

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

        assertThat(allProducts)
                .hasSize(2)
                .extracting(Product::getId)
                .contains(PRODUCT_ID);
    }

    @Test
    public void itAllowToStoreProduct() {
        Product product = new Product(UUID.randomUUID());
        product.setDescription("Green pen");

        jdbcTemplate.update("INSERT INTO `productsCatalog-products` " +
                        "(`id`, `description`, `picture`, `price`) values " +
                        "(?,?,?,?)",
                product.getId(),
                product.getDescription(),
                product.getPicture(),
                product.getPrice()
        );

        List<Product> productList = jdbcTemplate.query(
                "select * from `productsCatalog-products`",
                new ProductRowMapper()
        );

        assertThat(productList)
                .hasSize(1)
                .extracting(Product::getId)
                .contains(product.getId());
    }

    private class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet resultSet, int i) throws SQLException {
            Product product = new Product(UUID.fromString(resultSet.getString("id")));
            product.setDescription(resultSet.getString("description"));
            product.setPicture(resultSet.getString("picture"));

            return product;
        }
    }
}
