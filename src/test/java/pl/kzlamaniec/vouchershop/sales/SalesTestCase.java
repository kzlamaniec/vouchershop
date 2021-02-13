package pl.kzlamaniec.vouchershop.sales;

import pl.kzlamaniec.vouchershop.catalog.ProductCatalog;
import pl.kzlamaniec.vouchershop.catalog.ProductCatalogConfiguration;
import pl.kzlamaniec.vouchershop.sales.basket.BasketStorage;
import pl.kzlamaniec.vouchershop.sales.offering.OfferMaker;

import java.math.BigDecimal;
import java.util.UUID;

public class SalesTestCase {
    protected BasketStorage basketStorage;
    protected CurrentCustomerContext userContext;
    protected ProductCatalog productCatalog;
    protected String customerId;
    protected OfferMaker offerMaker;

    protected static ProductCatalog thereIsProductCatalog() {
        return new ProductCatalogConfiguration().myProductCatalog();
    }

    protected String thereIsCustomerWhoIsDoingHisShoping() {
        var id = UUID.randomUUID().toString();
        this.customerId = id;
        return id;
    }

    protected String thereIsProductAvailable() {
        String productId = productCatalog.registerProduct();
        productCatalog.applyPrice(productId, BigDecimal.valueOf(100.100));
        productCatalog.updateDetails(productId, "Han Solo - action figure", "Han Solo with a gun");

        return productId;
    }

    protected SalesFacade thereIsSalesComponent() {
        return new SalesFacade(userContext, basketStorage, productCatalog, offerMaker);
    }
}
