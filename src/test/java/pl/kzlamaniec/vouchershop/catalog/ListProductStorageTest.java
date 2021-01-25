package pl.kzlamaniec.vouchershop.catalog;

import org.junit.Assert;
import org.junit.Test;
import pl.kzlamaniec.vouchershop.catalog.exceptions.NoSuchProductException;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;

public class ListProductStorageTest {
    @Test
    public void itAllowAddProduct() {
        //A
        ProductStorage productStorage = new ListProductStorage();
        Product product = thereIsProduct();
        //A
        productStorage.save(product);
        //A
        Assert.assertTrue(productStorage.isExists(product.getId()));
    }

    @Test
    public void itAllowLoadAllProducts(){
        //Arrange
        ProductStorage productStorage = new ListProductStorage();
        var product1 =thereIsProduct();
        var product2 =thereIsProduct();
        //Act
        productStorage.save(product1);
        productStorage.save(product2);
        //Assert
        List<Product> all = productStorage.allProducts();
        assertThat(all).hasSize(2).extracting(Product::getId).contains(product1.getId()).contains(product2.getId());
    }

    @Test
    public void itAllowCheckIfProductExists() {
        ProductStorage productStorage = new ListProductStorage();
        var product1 = thereIsProduct();

        productStorage.save(product1);

        assertThat(productStorage.isExists(product1.getId())).isTrue();
        assertThat(productStorage.isExists(UUID.randomUUID().toString())).isFalse();

    }
    @Test
    public void itAllowLoadSingleProduct() {
        ProductStorage productStorage = new ListProductStorage();
        var product1 = thereIsProduct();

        productStorage.save(product1);

        var loaded = productStorage.load(product1.getId())
                .get();

        assertThat(loaded.getId()).isEqualTo(product1.getId());
    }

    @Test(expected = NoSuchProductException.class)
    public void itShouldProtectFromDefenseProgrammming() {
        ProductStorage productStorage = new ListProductStorage();

        var loaded = productStorage.load(UUID.randomUUID().toString())
                .orElseThrow(() -> new NoSuchProductException("no such product"));

    }

    @Test
    public void testIt() {
        assertThat("Ala ma kota").containsIgnoringCase("ala");
        assertThat(Arrays.asList("klaudia","katarzyna","joanna")).hasSize(3).contains("klaudia").doesNotContain("emilia");
    }

    private Product thereIsProduct() {
        return new Product(UUID.randomUUID());
    }
}
