package pl.kzlamaniec.vouchershop.sales;

import pl.kzlamaniec.vouchershop.sales.offering.Offer;

import java.util.UUID;


public class Reservation {
    String id;

    public Reservation() {
        this.id = UUID.randomUUID().toString();
    }

    public static Reservation of(Offer offer, ClientData clientData) {
        return new Reservation();
    }

    public String getId() {
        return id;
    }
}
