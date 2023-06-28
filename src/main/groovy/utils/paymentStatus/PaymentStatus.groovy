package utils.paymentStatus

enum PaymentStatus {

    PENDING,
    RECEIVED,
    OVERDUE

    public static PaymentStatus convert(String paymentStatus) {
        try {
            return paymentStatus.toUpperCase() as PaymentStatus
        } catch (Exception exception) {
            return null
        }
    }
}