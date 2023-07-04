package com.coincollector.payer

import com.coincollector.customer.Customer
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import utils.controller.BaseController

class PayerController extends BaseController {

    PayerService payerService

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def index() {

        if (params.deletedOnly && !Boolean.valueOf(params.deletedOnly)) {
            params.includeDeleted = true
        }

        List<Payer> payerList = Payer.query([customerId: getCurrentCustomer().id, deletedOnly: params.deletedOnly, includeDeleted: params.includeDeleted]).list()
        return [payerList: payerList]
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def show(Long id) {
        if (!id) {
            flash.message = "Erro ao buscar pagador. ID não informado."
            redirect(action: "index")
            return
        }

        Payer payer = Payer.query([id: id, includeDeleted: true]).get()
        if (!payer) {
            flash.message = "Pagador com o ID ${id} não encontrado."
            redirect(action: "index")
            return
        }

        Customer currentCustomer = getCurrentCustomer()
        if (payer.customer != currentCustomer) {
            flash.message = "Você não tem permissão para acessar os detalhes deste pagador."
            redirect(action: "index")
            return
        }

        return [payer: payer]
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def create() {
        return [:]
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def edit() {
        Long id = params.long("id")
        Payer payer = Payer.findByCustomerAndId(currentCustomer, id)
        if (!payer) {
            flash.message = "Pagador não encontrado com o ID ${id}"
            redirect(action: 'index')
            return
        }

        Customer currentCustomer = getCurrentCustomer()
        if (payer.customer != currentCustomer) {
            flash.message = "Você não tem permissão para editar os detalhes deste pagador."
            redirect(action: "index")
            return
        }

        Map params = [payer: payer]
        return params
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def update() {
        try {
            Long id = params.long("id")
            Payer payer = Payer.findByCustomerAndId(currentCustomer, id)
            if (!payer) {
                flash.message = "Pagador não encontrado com o ID ${id}"
                redirect(action: 'index')
                return
            }

            if (payer.customer != currentCustomer) {
                throw new Exception("Você não tem permissão para atualizar os detalhes deste pagador.")
            }

            payerService.update(id, params)
            flash.message = "Pagador atualizado com sucesso"
            redirect(action: 'show', id: id)
        } catch (Exception exception) {
            flash.message = exception.message
            redirect(action: 'index')
        }
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def save() {
        try {
            payerService.save(params, getCurrentCustomer())
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

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def delete() {
        try {
            Long id = params.long("id")

            Payer payer = Payer.findByCustomerAndId(currentCustomer, id)
            if (!payer) {
                flash.message = "Pagador não encontrado com o ID ${id}"
                redirect(action: 'index')
                return
            }

            if (payer.customer != currentCustomer) {
                throw new Exception("Você não tem permissão para excluir este pagador.")
            }

            payerService.delete(id, getCurrentCustomer())
            flash.message = "Pagador deletado com sucesso"
            redirect(action: 'index')
        } catch (Exception exception) {
            flash.message = exception.message
            redirect(action: 'index')
        }
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def restore() {
        try {
            Long id = params.long("id")

            Payer payer = Payer.findByCustomerAndId(currentCustomer, id)
            if (!payer) {
                flash.message = "Pagador não encontrado com o ID ${id}"
                redirect(action: 'index')
                return
            }

            if (payer.customer != currentCustomer) {
                throw new Exception("Você não tem permissão para restaurar este pagador.")
            }
            payerService.restore(id, currentCustomer)
            flash.message = "Pagador inativo restaurado com sucesso"
            redirect(action: 'index')
        } catch (Exception exception) {
            flash.message = exception.message
            redirect(action: 'index')
        }
    }
}