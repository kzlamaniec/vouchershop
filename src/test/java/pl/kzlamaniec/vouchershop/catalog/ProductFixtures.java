package pl.kzlamaniec.vouchershop.catalog;

import java.util.UUID;

public class ProductFixtures {
    public static Product randomProduct() {
        return new Product(UUID.randomUUID());
    }
}
