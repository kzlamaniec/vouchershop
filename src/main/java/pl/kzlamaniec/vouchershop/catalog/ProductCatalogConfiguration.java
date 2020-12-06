package pl.kzlamaniec.vouchershop.catalog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

@Configuration
public class ProductCatalogConfiguration {

    ProductCatalog myProductCatalog() {
        return new ProductCatalog(new HashMapProductStorage());
    }

    @Bean
    ProductStorage mongoDbStorage() {
        return null;
    }

    @Bean
    ProductStorage myHashMapProductStorage() {
        return new HashMapProductStorage();
    }

    @Bean
    ProductCatalog myFixtureAwareCatalog(ProductStorage productStorage) {
        ProductCatalog productCatalog = new ProductCatalog(productStorage);

        var p1 = productCatalog.registerProduct();
        productCatalog.applyPrice(p1, BigDecimal.valueOf(22.22));
        productCatalog.updateDetails(p1, "My nice product", "Nice picture");

        var p2 = productCatalog.registerProduct();
        productCatalog.applyPrice(p2, BigDecimal.valueOf(122.22));
        productCatalog.updateDetails(p2, "My nice product 2", "Nice picture 2");

        var p3 = productCatalog.registerProduct();
        productCatalog.applyPrice(p3, BigDecimal.valueOf(222.22));
        productCatalog.updateDetails(p3, "My nice product 3", "Nice picture 3");

        return productCatalog;
    }
}
