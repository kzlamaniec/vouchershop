package pl.kzlamaniec.vouchershop.sales;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kzlamaniec.payu.PayU;
import pl.kzlamaniec.payu.PayUApiCredentials;
import pl.kzlamaniec.payu.http.NetHttpClientPayuHttp;
import pl.kzlamaniec.vouchershop.catalog.Product;
import pl.kzlamaniec.vouchershop.catalog.ProductCatalog;
import pl.kzlamaniec.vouchershop.sales.basket.BasketStorage;
import pl.kzlamaniec.vouchershop.sales.offering.Offer;
import pl.kzlamaniec.vouchershop.sales.offering.OfferMaker;
import pl.kzlamaniec.vouchershop.sales.offering.ProductCatalogPricingProvider;


@Configuration
public class SalesConfiguration {

    @Bean
    SalesFacade salesFacade(CurrentCustomerContext customerContext, ProductCatalog productCatalog, OfferMaker offerMaker, PaymentGateway paymentGateway) {
        return new SalesFacade(customerContext, new BasketStorage(), productCatalog, offerMaker, paymentGateway);
    }

    @Bean
    PaymentGateway paymentGateway() {
        return new PayUPaymentGateway(new PayU(PayUApiCredentials.sandbox(), new NetHttpClientPayuHttp()));
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
