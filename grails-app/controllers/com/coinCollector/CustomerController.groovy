package com.coinCollector

import grails.validation.ValidationException
import utils.message.MessageType

class CustomerController {

    def customerService

    def index() {
        return [customerList : Customer.list()]
    }

    def show(Long id) {
        if (!id) {
            flash.message = "Erro ao buscar cliente. ID não informado."
            redirect(action: "index")
            return
        }

        Customer customer = Customer.query([id: id]).get()
        
        if (!customer) {
            flash.message = "Cliente com o ID ${id} não encontrado."
            redirect(action: "index")
            return
        }

        return [customer: customer]
    }

    def create() {
        return [:]
    }

    def save() {
        try {
            customerService.save(params)

            flash.message = "Cliente registrado com sucesso"
            flash.type = MessageType.SUCCESS

            redirect(action: 'index')
        } catch (ValidationException validationException) {
            flash.message = message(error: validationException.errors.allErrors[0])
            flash.type = MessageType.ERROR

            redirect(action: 'create')
        } catch (Exception exception) {
            log.error("CustomerController.save >> Erro ao salvar cliente", exception)

            flash.message = "Erro ao salvar cliente"
            flash.type = MessageType.ERROR

            redirect(action: 'create')
        }
    }
}