package com.coincollector.payment

import com.coincollector.customer.Customer
import com.coincollector.payer.Payer
import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import utils.billingType.BillingType
import utils.dateFormat.CustomDateUtils
import utils.paymentStatus.PaymentStatus

@Transactional
class PaymentService {

    public Payment save(Map params) {
        Map parsedParams = parseParams(params)

        Payment validatedPayment = validatePayment(parsedParams)
        if (validatedPayment.hasErrors()) throw new ValidationException(null, validatedPayment.errors)

        Payment payment = new Payment()

        List<String> savableParams = ["customer", "payer", "billingType", "value", "dueDate"]
        payment.properties[savableParams] = parsedParams
        payment.save(failOnError: true)
    }

    public Payment updateToReceived(Long id) {
        Payment payment = Payment.query([id: id]).get()
        if (payment.status == PaymentStatus.RECEIVED) {
            payment.errors.reject("", null, "A cobrança já foi recebida")
            throw new ValidationException(null, payment.errors)
        }
        payment.status = PaymentStatus.RECEIVED
        payment.save(failOnError: true)
    }

    public Map parseParams(Map params) {
        Map parsedParams = params

        parsedParams.customer = Customer.findById(1)
        parsedParams.billingType = BillingType.convert(params.billingType)
        parsedParams.value = params.value ? params.value as BigDecimal : 0
        parsedParams.dueDate = CustomDateUtils.fromString(params.dueDate)

        return parsedParams
    }

    private Payment validatePayment(Map parsedParams) {
        Payment validatedPayment = new Payment()

        if (!parsedParams.payerId) {
            validatedPayment.errors.reject("", null, "Id do pagador não encontrado")
            return validatedPayment
        }

        parsedParams.payer = Payer.query([id: parsedParams.payerId]).get()

        if (!parsedParams.payer) {
            validatedPayment.errors.reject("", null, "Pagador não encontrado")
        }

        if (!parsedParams.billingType) {
            validatedPayment.errors.reject("", null, "Forma de pagamento inválida")
        }

        Date today = new Date()
        if (!parsedParams.dueDate) {
            validatedPayment.errors.reject("", null, "Data de vencimento inválida")
        } else if (parsedParams.dueDate < today) {
            validatedPayment.errors.reject("", null, "A data de vencimento não pode ser inferior ao dia de hoje.")
        }

        if (!parsedParams.value) {
            validatedPayment.errors.reject("", null, "Valor da cobrança inválida")
        } else if (parsedParams.value <= BigDecimal.ZERO) {
            validatedPayment.errors.reject("", null, "Valor da cobrança não pode ser menor ou igual a zero")
        }

        return validatedPayment
    }

    public void jobPendingToOverdue(){
        List<Long> pendingPaymentsIdList = Payment.query(["column": "id", "beforeDueDate": new Date(), "onlyPendingPayments": true]).list()

        if (pendingPaymentsIdList.isEmpty()) return

        for (Long paymentId : pendingPaymentsIdList) {
            Payment.withNewTransaction { status ->
                try {
                    Payment payment = Payment.get(paymentId)
                    payment.status = PaymentStatus.OVERDUE

                    payment.save(failOnError: true)
                } catch (Exception exception) {
                    log.error("updatePendingPaymentStatus >> Erro ao atualizar status da cobrança de id: [${paymentId}] [Mensagem de erro]: ${exception.message}")
                    status.setRollbackOnly()
                }
            }
        }
    }
}
