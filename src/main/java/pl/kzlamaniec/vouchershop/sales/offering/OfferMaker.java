package pl.kzlamaniec.vouchershop.sales.offering;

import pl.kzlamaniec.vouchershop.sales.basket.BasketItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class OfferMaker {
    private final PricingProvider pricingProvider;

    public OfferMaker(PricingProvider pricingProvider) {
        this.pricingProvider = pricingProvider;
    }

    public Offer calculate(List<BasketItem> items) {
        List<OfferItem> offerItems = items
                .stream()
                .map(this::createOfferItem)
                .collect(Collectors.toUnmodifiableList());

        return new Offer(calculateTotal(offerItems), offerItems);
    }

    private OfferItem createOfferItem (BasketItem basketItem) {
        ProductPricing productPricing = pricingProvider.getForProduct(basketItem.getProductId());

        return new OfferItem(
                basketItem.getProductId(),
                basketItem.getQuantity(),
                productPricing.getUnitPrice(),
                productPricing.getProductDescription());
    }

    private BigDecimal calculateTotal(List<OfferItem> items) {
        return items.stream()
                .map(offerItem -> offerItem.getUnitPrice().multiply(BigDecimal.valueOf(offerItem.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
