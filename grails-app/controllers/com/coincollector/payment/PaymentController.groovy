package com.coincollector.payment

import com.coincollector.payer.Payer
import grails.validation.ValidationException
import utils.message.MessageType

class PaymentController {

    def paymentService

    def index() {
        Map search = params.findAll { it.value }

        List<Payer> payerList = Payer.query([customerId: 1]).list()
        List<Payment> paymentList = Payment.query([customerId: 1] + search).list()

        return [paymentList: paymentList, payerList: payerList]
    }

    def show(Long id) {
        if (!id) {
            flash.message = "Erro ao buscar cobrança. ID não informado."
            redirect(action: "index")
            return
        }

        Payment payment = Payment.query([id: id]).get()
        Payer payer= Payer.query([id: id]).get()

        if (!payment) {
            flash.message = "Cobrança com o ID ${id} não encontrado."
            redirect(action: "index")
            return
        }

        return [payment: payment, payer: payer]
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
