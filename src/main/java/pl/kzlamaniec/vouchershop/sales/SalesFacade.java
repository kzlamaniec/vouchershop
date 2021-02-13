package pl.kzlamaniec.vouchershop.sales;

import pl.kzlamaniec.vouchershop.catalog.Product;
import pl.kzlamaniec.vouchershop.catalog.ProductCatalog;
import pl.kzlamaniec.vouchershop.sales.basket.Basket;
import pl.kzlamaniec.vouchershop.sales.basket.BasketStorage;

public class SalesFacade {
    private final CurrentCustomerContext currentCustomerContext;
    private final BasketStorage basketStorage;
    private final ProductCatalog productCatalog;

    public SalesFacade(CurrentCustomerContext currentCustomerContext, BasketStorage basketStorage, ProductCatalog productCatalog) {
        this.currentCustomerContext = currentCustomerContext;
        this.basketStorage = basketStorage;
        this.productCatalog = productCatalog;
    }

    public void addToBasket(String productId) {
        Basket basket =  basketStorage.getBasketForCustomer(getCurrentCustomerId())
                .orElse(Basket.empty());

        Product product = productCatalog.load(productId);

        basket.add(product);

        basketStorage.addForCustomer(getCurrentCustomerId(), basket);
    }

    private String getCurrentCustomerId() {
        return currentCustomerContext.getCustomerId();
    }
}
