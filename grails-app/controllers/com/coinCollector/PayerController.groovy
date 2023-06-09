package com.coinCollector

class PayerController {
   
    def payerService

    def index() {
        def payerList = payerService.getAllPayers()
        [payerList: payerList]
    }

    def show(Long id) {
        def payer = payerService.getPayerById(id)
        [payer: payer]
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