package com.coinCollector

import grails.validation.ValidationException

class CustomerController {

    def customerService

    def index() {
        return [:]
    }

    def create() {
        return [:]
    }

    def save() {
        try {
            Customer customer = customerService.save(params)
            flash.message = "Cliente registrado com sucesso"
            redirect(action: 'index')
        } catch (ValidationException validationException) {
            flash.message = validationException.errors.allErrors.first().defaultMessage
            redirect(action: 'create')
        }
    }
}