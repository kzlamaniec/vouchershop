package pl.kzlamaniec.vouchershop.sales.offering;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class Offer {

    private final BigDecimal total;
    private final List<OfferItem> offerItems;

    public Offer(BigDecimal total, List<OfferItem> offerItems) {
        this.total = total;
        this.offerItems = offerItems;
    }
}
