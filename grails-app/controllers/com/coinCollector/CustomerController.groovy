package com.coinCollector

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
        customerService.save(params)
        flash.message = "Cliente registrado com sucesso"
        redirect(action: 'index')
    }
}
