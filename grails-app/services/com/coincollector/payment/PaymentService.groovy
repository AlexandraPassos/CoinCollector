package com.coincollector.payment

import com.coincollector.customer.Customer
import com.coincollector.payer.Payer
import grails.validation.ValidationException
import utils.billingType.BillingType
import utils.dateFormat.CustomDateUtils

import javax.transaction.Transactional

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

    public Map parseParams(Map params) {
        Map parsedParams = params

        parsedParams.customer = Customer.findById(1)

        if (!params.payer) {
            throw new Exception("O campo pagador é obrigatório")
        } else {
            parsedParams.payer = Payer.get(params.long("payer"))
        }

        if (!params.billingType) {
            throw new Exception("O tipo de pagamento é obrigatório")
        } else {
            parsedParams.billingType = BillingType.convert(params.billingType)
        }

        if (!parsedParams.value) {
            throw new Exception("O campo valor é obrigatório")
        } else {
            parsedParams.value = params.value as BigDecimal
        }

        if (!parsedParams.dueDate) {
            throw new Exception("O campo data é obrigatório")
        } else {
            parsedParams.dueDate = CustomDateUtils.fromString(params.dueDate)
        }

        return parsedParams
    }

    private Payment validatePayment(Map parsedParams) {
        Payment validatedPayment = new Payment()

        if (parsedParams.value <= BigDecimal.ZERO) {
            validatedPayment.errors.reject("", null, "Valor da cobrança inválido")
        }

        Date today = new Date()
        if (parsedParams.dueDate < today) {
            validatedPayment.errors.reject("", null, "A data de vencimento não pode ser inferior ao dia de hoje.")
        }

        return validatedPayment
    }
}
