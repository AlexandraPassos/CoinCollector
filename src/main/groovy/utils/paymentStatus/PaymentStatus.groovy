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

    public static Boolean isPending() {
        return this == PaymentStatus.PENDING
    }

    public static Boolean isReceived() {
        return this == PaymentStatus.RECEIVED
    }

    public static Boolean isOverdue() {
        return this == PaymentStatus.OVERDUE
    }
}