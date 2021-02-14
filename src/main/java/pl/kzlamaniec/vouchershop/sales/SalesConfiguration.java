package pl.kzlamaniec.vouchershop.sales;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kzlamaniec.vouchershop.catalog.Product;
import pl.kzlamaniec.vouchershop.catalog.ProductCatalog;
import pl.kzlamaniec.vouchershop.sales.basket.BasketStorage;
import pl.kzlamaniec.vouchershop.sales.offering.Offer;
import pl.kzlamaniec.vouchershop.sales.offering.OfferMaker;
import pl.kzlamaniec.vouchershop.sales.offering.ProductCatalogPricingProvider;


@Configuration
public class SalesConfiguration {

    @Bean
    SalesFacade salesFacade(CurrentCustomerContext customerContext, ProductCatalog productCatalog, OfferMaker offerMaker) {
        return new SalesFacade(customerContext, new BasketStorage(), productCatalog, offerMaker);
    }

    @Bean
    CurrentCustomerContext currentContext() {
        return new AlwaysTheSameCustomerContext();
    }

    @Bean
    OfferMaker offerMaker(ProductCatalog productCatalog) {
        return new OfferMaker(new ProductCatalogPricingProvider(productCatalog));
    }
}
