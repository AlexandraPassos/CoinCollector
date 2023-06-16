package com.coinCollector

import grails.validation.ValidationException

class PayerController {
   
    def payerService

    def index() {
        Boolean mustShowDeletedPayers = params.boolean("mustShowDeletedPayers") ?: false
        List<Payer> payerList = Payer.query([customerId: 1, deleted: mustShowDeletedPayers]).list()
        return [payerList: payerList, mustShowDeletedPayers: mustShowDeletedPayers]
    }

    def show(Long id) {
        if (!id) {
            flash.message = "Erro ao buscar pagador. ID não informado."
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
        if (!payer) {
            flash.message = "Pagador não encontrado com o ID ${id}"
            redirect(action: 'index')
            return
        }
        Map params = [payer: payer]
        return params
    }

    def update() {
        try {
            Long id = params.long("id")
            payerService.update(id, params)
            flash.message = "Pagador atualizado com sucesso"
            redirect(action: 'show', id: id)
        } catch (Exception exception) {
            flash.message = exception.message
            redirect(action: 'index')
        }
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
        } catch (Exception exception) {
            flash.message = exception.message
            redirect(action: 'index')
        }
    }

    def restore() {
        try {
            Long id = params.long("id")
            payerService.restore(id)
            flash.message = "Pagador inativo restaurado com sucesso"
            redirect(action: 'index')
        } catch (Exception exception) {
            flash.message = exception.message
            redirect(action: 'index')
        }
    }
}