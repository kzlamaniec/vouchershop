package pl.kzlamaniec.vouchershop.catalog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

@Configuration
public class ProductCatalogConfiguration {

    public ProductCatalog myProductCatalog() {
        return new ProductCatalog(new HashMapProductStorage());
    }


    @Bean
    ProductStorage listProductStorage() {
        return new ListProductStorage();
    }

    @Bean
    ProductCatalog myFixtureAwareCatalog(ProductStorage productStorage) {
        ProductCatalog productCatalog = new ProductCatalog(productStorage);

        var p1 = productCatalog.registerProduct();
        productCatalog.applyPrice(p1, BigDecimal.valueOf(22.22));
        productCatalog.updateDetails(p1, "Darth Vader - action figure", "Darth Vader with red lightsaber");

        var p2 = productCatalog.registerProduct();
        productCatalog.applyPrice(p2, BigDecimal.valueOf(122.22));
        productCatalog.updateDetails(p2, "Luke Skywalker - action figure", "Luke Skywalker with green lightsaber");

        var p3 = productCatalog.registerProduct();
        productCatalog.applyPrice(p3, BigDecimal.valueOf(222.22));
        productCatalog.updateDetails(p3, "Princess Leia - action figure", "Princess Leia with a gun");

        return productCatalog;
    }
}
