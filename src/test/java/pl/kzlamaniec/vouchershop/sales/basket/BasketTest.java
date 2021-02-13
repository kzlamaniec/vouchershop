package pl.kzlamaniec.vouchershop.sales.basket;

import org.junit.Test;
import pl.kzlamaniec.vouchershop.catalog.Product;
import pl.kzlamaniec.vouchershop.sales.basket.Basket;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;


public class BasketTest {

    @Test
    public void newlyCreatedBasketIsEmpty() {
        Basket basket = new Basket();

        assertThat(basket.isEmpty())
                .isTrue();
    }

    @Test
    public void basketWithProductIsNotEmpty() {
        Basket basket = new Basket();
        Product product = thereIsProduct("darth vader - action figure");

        basket.add(product);

        assertThat(basket.isEmpty())
                .isFalse();
    }

    @Test
    public void itShowsProductsCount() {
        Basket basket = new Basket();
        var product1 = thereIsProduct("Darth Vader - action figure");
        var product2 = thereIsProduct("Luke Skywalker - action figure");

        basket.add(product1);
        basket.add(product2);

        assertThat(basket.getProductCount())
                .isEqualTo(2);
    }

    @Test
    public void itShowsSingleLineForSameProductAddedTwice() {
        Basket basket = new Basket();
        var product1 = thereIsProduct("Darth Vader - action figure");

        basket.add(product1);
        basket.add(product1);

        assertThat(basket.getProductCount())
                .isEqualTo(1);
    }

    @Test
    public void itContainsBasketLineQuantity() {
        Basket basket = new Basket();
        var product1 = thereIsProduct("Darth Vader - action figure");
        var product2 = thereIsProduct("Luke Skywalker - action figure");

        basket.add(product1);
        basket.add(product1);
        basket.add(product1);
        basket.add(product2);

        basketContainsProductWithQuantity(basket, product1, 3);
        basketContainsProductWithQuantity(basket, product2, 1);

    }

    private void basketContainsProductWithQuantity(Basket basket, Product product1, int expectedQuantity) {
        assertThat(basket.getBasketItems())
                .filteredOn(basketItem -> basketItem.getProductId().equals(product1.getId()))
                .extracting(basketItem -> basketItem.getQuantity())
                .first()
                .isEqualTo(expectedQuantity);
    }

    private Product thereIsProduct(String name) {
        Product product = new Product(UUID.randomUUID());
        product.setDescription(name);

        return product;
    }
}
