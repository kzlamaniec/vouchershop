package pl.kzlamaniec.vouchershop.sales;

import pl.kzlamaniec.payu.PayU;
import pl.kzlamaniec.payu.model.Buyer;
import pl.kzlamaniec.payu.model.OrderCreateRequest;

public class PayUPaymentGateway implements PaymentGateway{

    private final PayU payU;

    public PayUPaymentGateway(PayU payU) {

        this.payU = payU;
    }

    @Override
    public PaymentDetails registerFor(Reservation reservation, ClientData clientData) {

        payU.handle(OrderCreateRequest.builder()
                .totalAmount(reservation.getTotalAmount())
                .buyer(Buyer.builder()
                        .firstname(clientData.firstname)
                        .lastname(clientData.lastname)
                        .build())
                .products()
                .build());
    }
}
