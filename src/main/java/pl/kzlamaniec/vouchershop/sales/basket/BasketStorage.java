package pl.kzlamaniec.vouchershop.sales.basket;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class BasketStorage {
    private final Map<String, Basket> baskets;

    public BasketStorage() {
        this.baskets = new ConcurrentHashMap<String, Basket>();
    }

    public Optional<Basket> getBasketForCustomer(String currentCustomerId) {
        return Optional.ofNullable(baskets.get(currentCustomerId));
    }

    public void addForCustomer(String currentCustomerId, Basket basket) {
        baskets.put(currentCustomerId, basket);
    }
}
