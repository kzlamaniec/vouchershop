package pl.kzlamaniec.vouchershop.sales;

import org.junit.Before;
import org.junit.Test;
import pl.kzlamaniec.vouchershop.catalog.ProductCatalog;
import pl.kzlamaniec.vouchershop.sales.basket.BasketStorage;
import pl.kzlamaniec.vouchershop.sales.offering.Offer;
import pl.kzlamaniec.vouchershop.sales.offering.OfferMaker;
import pl.kzlamaniec.vouchershop.sales.offering.PricingProvider;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderingTest extends SalesTestCase{
    @Before
    public void setUp() {
        this.basketStorage = new BasketStorage();
        this.customerId =  UUID.randomUUID().toString();
        this.userContext = () -> customerId;
        this.productCatalog = thereIsProductCatalog();
        this.offerMaker = thereIsOfferMaker();
    }

    @Test
    public void itCreateOfferBasedOnSelectedProducts() {
        SalesFacade salesComponent = thereIsSalesComponent();
        String productId = thereIsProductAvailable();
        String customerId = thereIsCustomerWhoIsDoingHisShoping();

        salesComponent.addToBasket(productId);
        salesComponent.addToBasket(productId);

        Offer offer = salesComponent.getCurrentOffer();

        assertThat(offer.getTotal()).isEqualTo(BigDecimal.valueOf(200.200));

    }

    private OfferMaker thereIsOfferMaker() {
        return new OfferMaker(new ProductCatalogPricingProvider(productCatalog));
    }
}
