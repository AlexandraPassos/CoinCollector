package com.coincollector

import com.coincollector.payment.PaymentService

class PendingToOverduePaymentStatusJob {
    PaymentService paymentService

    static triggers = {
//        cron name: "processPendingToOverduePayment", cronExpression: "0 0 1 ? * MON-FRI"
        simple name: "processPendingToOverduePayment", repeatInterval: 10000
    }

    def execute() {
        paymentService.jobPendingToOverdue()
    }
}