package com.coincollector.customer

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import utils.controller.BaseController
import utils.message.MessageType

class CustomerController extends BaseController {

    def customerService

    def index() {
        return [:]
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
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

        Customer currentCustomer = getCurrentCustomer()
        if (currentCustomer.id != customer.id) {
            flash.message = "Você não tem permissão para ver este cliente."
            redirect(action: 'index')
            return
        }

        return [customer: getCurrentCustomer()]
    }

    @Secured(['permitAll'])
    def create() {
        return [:]
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
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

        Customer currentCustomer = getCurrentCustomer()
        if (currentCustomer.id != customer.id) {
            flash.message = "Você não tem permissão para editar este cliente."
            redirect(action: 'index')
            return
        }

        Map params = [customer: customer]
        return params
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def update(Long id) {
        try {
            Customer currentCustomer = getCurrentCustomer()
            if (currentCustomer.id != id) {
                throw new Exception("Você não tem permissão para atualizar este cliente.")
            }

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

    @Secured(['permitAll'])
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