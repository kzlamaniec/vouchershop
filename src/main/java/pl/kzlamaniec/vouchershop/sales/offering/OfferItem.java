package pl.kzlamaniec.vouchershop.sales.offering;

import java.math.BigDecimal;

public class OfferItem {
    private final String productId;
    private final Integer quantity;
    private final BigDecimal unitPrice;
    private final String productDescription;

    public OfferItem(String productId, Integer quantity, BigDecimal unitPrice, String productDescription) {

        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.productDescription = productDescription;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getProductId() {
        return productId;
    }
}
