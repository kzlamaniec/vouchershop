package pl.kzlamaniec.vouchershop.sales.offering;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class ProductPricing {
    private BigDecimal unitPrice;
    private String productDescription;

}
