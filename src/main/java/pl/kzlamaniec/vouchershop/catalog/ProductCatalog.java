package pl.kzlamaniec.vouchershop.catalog;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ProductCatalog {
    private HashMap<String, Product> products;

    public ProductCatalog(){
        this.products = new HashMap<String,Product>();
    }

    public String registerProduct() {
        Product newProduct = new Product(UUID.randomUUID());
        products.put(newProduct.getId(), newProduct);
        return newProduct.getId();
    }

    public boolean isExists(String productId) {
        return products.containsKey(productId);
    }

    public Product load(String productId) {
        return products.get(productId);
    }

    public void updateDetails(String productId, String productDesc, String productPicture) {
        Product loaded = products.get(productId);
        loaded.setDescription(productDesc);
        loaded.setPicture(productPicture);
    }

    public void applyPrice(String productId, BigDecimal productPrice) {
        Product loaded = products.get(productId);
        loaded.setPrice(productPrice);
    }

    public List<Product> allPublished() {
        return products.values()
                .stream()
                .filter(p -> p.getDescription() != null)
                .filter(p -> p.getPicture() != null)
                .collect(Collectors.toList());
    }
}
