package com.coinCollector

class CustomerController {

    def customerService

    def index() {
        return [:]
    }

    def create() {
        return [:]
    }

    def save() {
        Long id = params.long("id")

        try {
            Customer validatedCustomer = customerService.save(params)

            if (validatedCustomer.hasErrors()) {
                List<String> errorMessages = validatedCustomer.errors.allErrors.collect { it.getDefaultMessage() }
                String errorMessage = errorMessages.join(", ")
                flash.message = "Erro ao salvar o cliente: " + errorMessage
                redirect([action: 'create', id: id])
                return
            }

            flash.message = "Cliente registrado com sucesso"
            redirect([action: 'index', id: id])
            return

        } catch (Exception exception) {
            flash.message = "Um erro inesperado ocorreu. Por favor, contate o suporte."
        }
    }
}