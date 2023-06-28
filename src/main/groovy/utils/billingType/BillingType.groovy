package utils.billingType

enum BillingType {

    PIX,
    BANK_SLIP,
    DEPOSIT,
    TRANSFER,
    DEBIT_CARD,
    CREDIT_CARD

    public static BillingType convert(billingType) {
        try {
            return billingType.toUpperCase() as BillingType
        } catch (Exception exception) {
            return null
        }
    }
}

