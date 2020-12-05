package pl.kzlamaniec.vouchershop.catalog;

import org.junit.Test;

public class ProductCatalogTest {
    @Test
    public void itAllowsToRegisterNewProduct(){
        //Arrange
        ProductCatalog catalog = therIsProductCatalog();
        //Act
        String productId = catalog.registerProduct();
        //Assert
        Assert.assertTrue(catalog.isExists(productId));
    }
}
