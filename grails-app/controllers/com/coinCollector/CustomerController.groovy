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

    def edit() {
        Long id = params.long("id")
        if (!id) {
            flash.message = "Erro ao editar cliente. ID não informado."
            redirect(action: 'index')
            return
        }

        Customer customer = Customer.query([id: id]).get()

        if (!customer) {
            flash.message = "Cliente não encontrado com o ID ${id}"
            redirect(action: 'index')
            return
        }

        Map params = [customer: customer]
        return params
    }

    def update(Long id) {
        try {
            customerService.update(id, params)
            flash.message = "Cliente atualizado com sucesso"
            flash.type = MessageType.SUCCESS
            redirect(action: 'show', id: id)
        } catch (ValidationException validationException) {
            flash.message = message(error: validationException.errors.allErrors[0])
            flash.type = MessageType.ERROR
            redirect(action: 'edit', id: id)
        } catch (Exception exception) {
            log.error("CustomerController.update >> Erro ao atualizar conta.", exception)
            flash.message = "Erro ao atualizar conta"
            flash.type = MessageType.ERROR
            redirect(action: 'edit', id: id)
        }
    }

    def save() {
        customerService.save(params)
        flash.message = "Cliente registrado com sucesso"
        redirect(action: 'index')
    }
}
