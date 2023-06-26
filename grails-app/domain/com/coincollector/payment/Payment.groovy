package com.coincollector.payment

import com.coincollector.customer.Customer
import com.coincollector.payer.Payer
import utils.baseEntity.BaseEntity
import utils.billingType.BillingType
import utils.paymentStatus.PaymentStatus

class Payment extends BaseEntity {

    Customer customer

    Payer payer

    BillingType billingType

    BigDecimal value

    PaymentStatus status = PaymentStatus.PENDING

    Date dueDate

    Date receivedDate

    static constraints = {
        value shared: "nonNegative"
        receivedDate nullable: true
    }

    static namedQueries = {
        query { Map search ->
            if (search.containsKey("id")) eq("id", Long.valueOf(search.id))

            if (search.containsKey("customerId")) eq("customer.id", Long.valueOf(search.customerId))

            if (search.containsKey("payerId")) eq("payer.id", Long.valueOf(search.payerId))
        }
    }
}
