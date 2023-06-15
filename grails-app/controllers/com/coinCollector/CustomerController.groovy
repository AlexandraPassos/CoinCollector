package com.coinCollector

class CustomerController {

    def customerService

    def index() {
        return [customerList : Customer.list()]
    }

    def show(Long id) {
        if (!id) {
            flash.message = "Erro ao buscar pagador. ID não informado."
            redirect(action: "index")
            return
        }

        Customer customer = Customer.query([id: id]).get()
        
        if (!customer) {
            flash.message = "Pagador não encontrado com o ID informado."
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
