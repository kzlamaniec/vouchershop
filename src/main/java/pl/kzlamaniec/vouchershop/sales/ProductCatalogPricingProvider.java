package pl.kzlamaniec.vouchershop.sales;

import pl.kzlamaniec.vouchershop.catalog.Product;
import pl.kzlamaniec.vouchershop.catalog.ProductCatalog;
import pl.kzlamaniec.vouchershop.sales.offering.PricingProvider;
import pl.kzlamaniec.vouchershop.sales.offering.ProductPricing;

public class ProductCatalogPricingProvider implements PricingProvider {
    private final ProductCatalog productCatalog;

    public ProductCatalogPricingProvider(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    @Override
    public ProductPricing getForProduct(String productId) {
        Product product = productCatalog.load(productId);
        return new ProductPricing(product.getPrice(), product.getDescription());
    }
}
