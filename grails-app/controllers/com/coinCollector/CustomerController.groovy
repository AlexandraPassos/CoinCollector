package com.coinCollector

class CustomerController {

    def customerService

    def index() {
        return [customerList : Customer.list()]
    }

    def show(Long id) {
        if (!id) {
            flash.message = "Erro ao editar cliente. ID não informado."
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

    def update() {
        try {
            Long id = params.long("id")
            customerService.update(id, params)
            flash.message = "Cliente atualizado com sucesso"
            redirect(action: 'show', id: id)
        } catch (Exception exception) {
            flash.message = exception.message
            redirect(action: 'index')
        }
    }

    def save() {
        customerService.save(params)
        flash.message = "Cliente registrado com sucesso"
        redirect(action: 'index')
    }
}
