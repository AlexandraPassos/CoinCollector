package utils.billingType

enum BillingType {

    PIX,
    BANK_SLIP,
    DEPOSIT,
    TRANSFER,
    DEBIT_CARD,
    CREDIT_CARD

    public static BillingType convert(String billingType) {
        try {
            return billingType.toUpperCase() as BillingType
        } catch (Exception exception) {
            return null
        }
    }

    public static Boolean isPix() {
        return this == BillingType.PIX
    }

    public static Boolean isBankSlip() {
        return this == BillingType.BANK_SLIP
    }

    public static Boolean isDeposit() {
        return this == BillingType.DEPOSIT
    }

    public static Boolean isTransfer() {
        return this == BillingType.TRANSFER
    }

    public static Boolean isDebitCard() {
        return this == BillingType.DEBIT_CARD
    }

    public static Boolean isCreditCard() {
        return this == BillingType.CREDIT_CARD
    }

}

