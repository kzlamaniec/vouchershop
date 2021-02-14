package pl.kzlamaniec.vouchershop.sales;

public class PaymentDetails {


    private final String payUrl;
    private final String payId;
    private final String reservationId;

    public PaymentDetails(String payUrl, String payId, String reservationId) {

        this.payUrl = payUrl;
        this.payId = payId;
        this.reservationId = reservationId;
    }

    public String getPaymentUrl() {
        return payUrl;
    }

    public String getPaymentId() {
        return payId;
    }

    public String getReservationId() {
        return reservationId;
    }
}
