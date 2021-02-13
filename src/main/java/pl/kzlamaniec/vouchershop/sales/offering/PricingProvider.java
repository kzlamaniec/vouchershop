package pl.kzlamaniec.vouchershop.sales.offering;

public interface PricingProvider {
    ProductPricing getForProduct(String productId);
}
