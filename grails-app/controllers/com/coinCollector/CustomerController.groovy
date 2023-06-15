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
        customerService.save(params)
        flash.message = "Cliente registrado com sucesso"
        redirect(action: 'index')
    }
}
