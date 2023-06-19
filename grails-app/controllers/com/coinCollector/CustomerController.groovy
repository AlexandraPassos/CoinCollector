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
        Long id = params.long("id")
        Customer validatedCustomer = customerService.update(id, params)

        if (validatedCustomer.hasErrors()) {
            List<String> errorMessages = validatedCustomer.errors.allErrors.collect { it.getDefaultMessage() }
            String errorMessage = errorMessages.join(", ")
            flash.message = "Erro ao atualizar o cliente: " + errorMessage
            redirect(action: 'edit', id: id)
            return
        }
        flash.message = "Cliente atualizado com sucesso"
        redirect(action: 'show', id: id)
        return
    }

    def save() {
        customerService.save(params)
        flash.message = "Cliente registrado com sucesso"
        redirect(action: 'index')
    }
}
