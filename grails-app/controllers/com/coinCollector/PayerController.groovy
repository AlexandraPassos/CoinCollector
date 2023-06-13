package com.coinCollector

import grails.validation.ValidationException

class PayerController {
   
    def payerService

    def index() {
        return [payerList : Payer.query([customerId: 1]).list()]
    }  

    def show(Long id) {
        if (!id) {
            flash.message = "Erro ao buscar pagador. ID inválido ou não informado."
            redirect(action: "index")
            return
        }

        Payer payer = Payer.query([id: id]).get()
        
        if (!payer) {
            flash.message = "Pagador não encontrado com o ID informado."
            redirect(action: "index")
            return
        }

        return [payer: payer]
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
        try {
            Long id = params.long("id")
            payerService.delete(id)
            flash.message = "Pagador deletado com sucesso"
            redirect(action: 'index')
        } catch (ValidationException validationException) {
            flash.message = validationException.message
            redirect(action: 'index') 
        } catch (Exception exception) {
            flash.message = exception.message
            redirect(action: 'index')
        }
    }
}