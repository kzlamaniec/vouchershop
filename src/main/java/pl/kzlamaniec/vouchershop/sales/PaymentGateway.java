package pl.kzlamaniec.vouchershop.sales;

interface PaymentGateway {
    public PaymentDetails registerFor(Reservation reservation, ClientData clientData);
}
