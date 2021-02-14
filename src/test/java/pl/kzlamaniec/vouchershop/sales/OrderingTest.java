package pl.kzlamaniec.vouchershop.sales;

import org.junit.Before;
import org.junit.Test;
import pl.kzlamaniec.vouchershop.sales.basket.BasketStorage;
import pl.kzlamaniec.vouchershop.sales.offering.Offer;
import pl.kzlamaniec.vouchershop.sales.offering.OfferMaker;
import pl.kzlamaniec.vouchershop.sales.offering.ProductCatalogPricingProvider;

import java.math.BigDecimal;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;

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

    @Test
    public void itCreateReservationBasedOnCurrentOffer() {
        SalesFacade salesComponent = thereIsSalesComponent();
        String productId = thereIsProductAvailable();
        String customerId = thereIsCustomerWhoIsDoingHisShoping();

        salesComponent.addToBasket(productId);
        salesComponent.addToBasket(productId);

        Offer seenOffer = salesComponent.getCurrentOffer();

        PaymentDetails paymentDetails = salesComponent.acceptOffer(thereIsClientData());

        assertThat(paymentDetails.getPaymentUrl()).isNotNull();
        assertThat(paymentDetails.getPaymentId()).isNotNull();
        assertThat(paymentDetails.getReservationId()).isNotNull();

    }

    private OfferMaker thereIsOfferMaker() {
        return new OfferMaker(new ProductCatalogPricingProvider(productCatalog));
    }
}
