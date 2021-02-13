package pl.kzlamaniec.vouchershop.sales;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kzlamaniec.vouchershop.catalog.ProductCatalog;
import pl.kzlamaniec.vouchershop.sales.basket.BasketStorage;
import pl.kzlamaniec.vouchershop.sales.offering.OfferMaker;


@Configuration
public class SalesConfiguration {

    @Bean
    SalesFacade salesFacade(CurrentCustomerContext customerContext, ProductCatalog productCatalog, OfferMaker offerMaker) {
        return new SalesFacade(customerContext, new BasketStorage(), productCatalog, offerMaker);
    }

    @Bean
    CurrentCustomerContext currentContext() {
        return new RandomCustomerContext();
    }
}
