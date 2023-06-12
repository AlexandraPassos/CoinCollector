package com.coinCollector

import grails.validation.ValidationException

class PayerController {
   
    def payerService

    def index() {
        return [payerList : Payer.query([customerId: 1]).list()]
    }  

    def show(Long id) {
        return [payer : Payer.query([id: id]).get()]
    }
  
     def show(Long id) {
        def payer = Payer.get(id)
        [payer: payer]
    }

    def create() {
        return [:]
    }

    def edit() {
        Long id = params.long("id")
        Payer payer = Payer.query([id: id]).get()
        Map params = [id: id, payer: payer]
        return params
    }

    def update() {
        Long id = params.long("id")
        payerService.update(id, params)
        redirect (action: 'show', id: id)
    }

    def save() {
        try {
            payerService.save(params)
            flash.message = "Pagador registrado com sucesso"
            redirect(action: 'index')
        } catch (ValidationException validationException) {
            flash.message = "Um erro ocorreu durante o registro de pagador: ${validationException.message}"
            redirect(action: 'create')
        } catch (Exception exception) {
            flash.error = "Um erro inesperado ocorreu. Por favor, contate o suporte"
            redirect(action: 'create')
        }
    }

    def delete() {
        payerService.delete(params)
    }
}