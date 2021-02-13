package pl.kzlamaniec.vouchershop.sales;


import java.util.UUID;

public class RandomCustomerContext implements CurrentCustomerContext {
    @Override
    public String getCustomerId() {
        return UUID.randomUUID().toString();
    }
}
