package pl.kzlamaniec.vouchershop.sales;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kzlamaniec.vouchershop.catalog.ProductCatalog;
import pl.kzlamaniec.vouchershop.sales.basket.BasketStorage;


@Configuration
public class SalesConfiguration {

    @Bean
    SalesFacade salesFacade(CurrentCustomerContext customerContext, ProductCatalog productCatalog) {
        return new SalesFacade(customerContext, new BasketStorage(), productCatalog);
    }

    @Bean
    CurrentCustomerContext currentCustomerContext() {
        return new RandomCustomerContext();
    }
}
