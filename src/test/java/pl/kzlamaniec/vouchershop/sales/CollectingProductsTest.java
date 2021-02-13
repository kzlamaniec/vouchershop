package pl.kzlamaniec.vouchershop.sales;

import org.junit.Before;
import org.junit.Test;
import pl.kzlamaniec.vouchershop.catalog.ProductCatalog;
import pl.kzlamaniec.vouchershop.catalog.ProductCatalogConfiguration;
import pl.kzlamaniec.vouchershop.sales.basket.Basket;
import pl.kzlamaniec.vouchershop.sales.basket.BasketStorage;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;


public class CollectingProductsTest {

    private BasketStorage basketStorage;
    private CurrentCustomerContext userContext;
    private ProductCatalog productCatalog;
    private String customerId;

    @Before
    public void setUp() {
        this.basketStorage = new BasketStorage();
        this.customerId =  UUID.randomUUID().toString();
        this.userContext = () -> customerId;
        this.productCatalog = thereIsProductCatalog();
    }


    @Test
    public void itAllowAddProductToBasket() {
        SalesFacade salesComponent = thereIsSalesComponent();
        String productId = thereIsProductAvailable();
        String customerId = thereIsCustomerWhoIsDoingHisShoping();

        salesComponent.addToBasket(productId);

        thereIsNProductsInCustomerBasket(1, customerId);
    }

    private String thereIsCustomerWhoIsDoingHisShoping() {
        var id = UUID.randomUUID().toString();
        this.customerId = id;
        return id;
    }

    private String thereIsProductAvailable() {
        String productId = productCatalog.registerProduct();
        productCatalog.applyPrice(productId, BigDecimal.valueOf(100.100));
        productCatalog.updateDetails(productId, "Han Solo - action figure", "Han Solo with a gun");

        return productId;
    }

    private SalesFacade thereIsSalesComponent() {
        return new SalesFacade(userContext, basketStorage, productCatalog);
    }

    private void thereIsNProductsInCustomerBasket(int productCount, String customerId) {
        Basket basket = basketStorage.getBasketForCustomer(customerId)
                .orElse(Basket.empty());

        assertThat(basket.getProductCount()).isEqualTo(productCount);
    }

    private static ProductCatalog thereIsProductCatalog() {
        return new ProductCatalogConfiguration().myProductCatalog();
    }
}
