package com.coinCollector

import com.coinCollector.Payer

class PayerController {
   
    def payerService

    def index() {
        return [:]
    }

    def create() {
        return [:]
     }

    def save() {
        payerService.save(params)
        flash.message = "Pagador registrado com sucesso"
        redirect(action: 'index')
        
    }
}
    