package pl.kzlamaniec.vouchershop.sales;

import pl.kzlamaniec.vouchershop.catalog.Product;
import pl.kzlamaniec.vouchershop.catalog.ProductCatalog;
import pl.kzlamaniec.vouchershop.sales.basket.Basket;
import pl.kzlamaniec.vouchershop.sales.basket.BasketStorage;
import pl.kzlamaniec.vouchershop.sales.offering.Offer;
import pl.kzlamaniec.vouchershop.sales.offering.OfferMaker;

public class SalesFacade {
    private final CurrentCustomerContext currentCustomerContext;
    private final BasketStorage basketStorage;
    private final ProductCatalog productCatalog;
    private final OfferMaker offerMaker;
    private final PaymentGateway paymentGateway;

    public SalesFacade(CurrentCustomerContext currentCustomerContext, BasketStorage basketStorage, ProductCatalog productCatalog, OfferMaker offerMaker, PaymentGateway paymentGateway) {
        this.currentCustomerContext = currentCustomerContext;
        this.basketStorage = basketStorage;
        this.productCatalog = productCatalog;
        this.offerMaker = offerMaker;
        this.paymentGateway = paymentGateway;
    }

    public void addToBasket(String productId) {
        Basket basket =  basketStorage.getBasketForCustomer(getCurrentCustomerId())
                .orElse(Basket.empty());

        Product product = productCatalog.load(productId);

        basket.add(product);

        basketStorage.addForCustomer(getCurrentCustomerId(), basket);
    }

    private String getCurrentCustomerId() {
        return currentCustomerContext.getCustomerId();
    }

    public Offer getCurrentOffer() {
        Basket basket =  basketStorage.getBasketForCustomer(getCurrentCustomerId())
                .orElse(Basket.empty());

        return offerMaker.calculate(basket.getBasketItems());
    }

    public PaymentDetails acceptOffer(ClientData clientData) {
        Basket basket =  basketStorage.getBasketForCustomer(getCurrentCustomerId())
                .orElse(Basket.empty());

        Offer offer = offerMaker.calculate(basket.getBasketItems());

        Reservation reservation = Reservation.of(offer, clientData);

        PaymentDetails paymentDetails = paymentGateway.registerFor(reservation, clientData);

        return paymentDetails;
    }
}
