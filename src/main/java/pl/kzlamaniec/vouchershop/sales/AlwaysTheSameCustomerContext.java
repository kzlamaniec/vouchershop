package pl.kzlamaniec.vouchershop.sales;


import java.util.UUID;

public class AlwaysTheSameCustomerContext implements CurrentCustomerContext {
    @Override
    public String getCustomerId() {
        return "Customer_1";
    }
}
