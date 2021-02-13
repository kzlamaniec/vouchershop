package pl.kzlamaniec.vouchershop.sales.offering;

import org.junit.Test;
import pl.kzlamaniec.vouchershop.sales.basket.BasketItem;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OfferMakerTest {

    @Test
    public void itCreateOfferBasedOnEmptyBasketItemsList() {
        List<BasketItem> items = Collections.emptyList();
        OfferMaker offerMaker = thereIsOfferMaker();

        Offer offer = offerMaker.calculate(items);

        assertThat(offer.getTotal()).isEqualTo(BigDecimal.ZERO);
        assertThat(offer.getOfferItems()).hasSize(0);
    }


    @Test
    public void itCreateOfferBasedSingleItem() {
        List<BasketItem> items = thereIsOneItemList();
        OfferMaker offerMaker = thereIsOfferMaker();

        Offer offer = offerMaker.calculate(items);

        assertThat(offer.getTotal()).isEqualTo(BigDecimal.valueOf(20.20));
    }

    @Test
    public void itCreateOfferBasedItemsList() {
        List<BasketItem> items = thereIsMultipleItemsList();
        OfferMaker offerMaker = thereIsOfferMaker();

        Offer offer = offerMaker.calculate(items);

        assertThat(offer.getTotal()).isEqualTo(BigDecimal.valueOf(60.60));
    }


    private OfferMaker thereIsOfferMaker() {
        return new OfferMaker((productId) -> new ProductPricing(BigDecimal.valueOf(20.20), "Star wars action figure"));
    }

    private List<BasketItem> thereIsMultipleItemsList() {
        return Arrays.asList(
                new BasketItem("prod1", 1),
                new BasketItem("prod2", 2)
        );
    }

    private List<BasketItem> thereIsOneItemList() {
        return Collections.singletonList(
                new BasketItem("prod1", 1)
        );
    }
}
