package com.coinCollector

import grails.validation.ValidationException

class PayerController {
   
    def payerService

    def index() {
        def payerList = Payer.list()
        [payerList: payerList]
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
        if (payer == null) {
            flash.message = "Pagador não encontrado com o ID ${id}"
            redirect(action: 'index')
            return
        }
        Map params = [id: id, payer: payer]
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
}