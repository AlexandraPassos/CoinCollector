package com.coincollector.payment

import com.coincollector.payer.Payer
import grails.validation.ValidationException
import utils.message.MessageType

class PaymentController {

    def paymentService

    def index() {
        return [:]
    }

    def save() {
        try {
            paymentService.save(params)

            flash.message = "Cobrança registrada com sucesso"
            flash.type = MessageType.SUCCESS

            redirect(action: 'index')
        } catch (ValidationException validationException) {
            flash.message = message(error: validationException.errors.allErrors[0])
            flash.type = MessageType.ERROR

            redirect(action: 'create')
        } catch (Exception exception) {
            log.error("CustomerController.save >> Erro ao salvar cobrança", exception)

            flash.message = "Erro ao salvar cobrança"
            flash.type = MessageType.ERROR

            redirect(action: 'create')
        }
    }

    def create() {
        List<Payer> payerList = Payer.query([customerId: 1, deleted: false]).list()
        return [payerList: payerList]
    }

}
