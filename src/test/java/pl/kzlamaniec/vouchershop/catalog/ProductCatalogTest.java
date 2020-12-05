package pl.kzlamaniec.vouchershop.catalog;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class ProductCatalogTest {
    public static final String NOT_EXISTS_ID = "notExistsId";
    public static final String PRODUCT_PICTURE = "http://nice_picture";
    public static final String PRODUCT_DESC = "My nice product";

    @Test
    public void itAllowsToRegisterNewProduct(){
        //Arrange //Given
        ProductCatalog catalog = therIsProductCatalog();
        //Act     //When
        String productId = catalog.registerProduct();
        //Assert //Then
        Assert.assertTrue(catalog.isExists(productId));

        Assert.assertFalse(catalog.isExists(NOT_EXISTS_ID));
    }

    @Test
    public void itAllowsLoadCreatedProduct(){
        //Arrange
        ProductCatalog catalog = therIsProductCatalog();
        //Act
        String productId = catalog.registerProduct();
        Product loaded = catalog.load(productId);
        //Assert
        Assert.assertEquals(loaded.getId(), productId);
    }

    @Test
    public void itAllowsFIllADetails(){
        //Arrange
        ProductCatalog catalog = therIsProductCatalog();
        //Act
        String productId = catalog.registerProduct();
        catalog.updateDetails(productId, PRODUCT_DESC, PRODUCT_PICTURE);
        Product loaded = catalog.load(productId);
        //Assert
        Assert.assertEquals(loaded.getPicture(), PRODUCT_PICTURE);
        Assert.assertEquals(loaded.getDescription(), PRODUCT_DESC);

    }

    @Test
    public void itAllowsApplyPrice(){
        //Arrange
        ProductCatalog catalog = therIsProductCatalog();
        //Act
        String productId = catalog.registerProduct();
        catalog.applyPrice(productId, BigDecimal.TEN);
        Product loaded = catalog.load(productId);
        //Assert
        Assert.assertEquals(loaded.getPrice(), BigDecimal.TEN);

    }

    @Test
    public void itAllowsToListAllProducts() {
        //Arrange
        ProductCatalog catalog = therIsProductCatalog();
        //Act
        String productId = catalog.registerProduct();
        catalog.updateDetails(productId, PRODUCT_DESC, PRODUCT_PICTURE);
        catalog.applyPrice(productId, BigDecimal.TEN);
        Product loaded = catalog.load(productId);

        String draftProductId = catalog.registerProduct();
        List<Product> all = catalog.allPublished();

        //Assert
        Assert.assertEquals(1, all.size());



    }

    private static ProductCatalog therIsProductCatalog() {
        return new ProductCatalog();
    }
}