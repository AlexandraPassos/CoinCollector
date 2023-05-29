package com.coinCollector

import com.coinCollector.Payer

class PayerController {
   
    def payerService

    def save() {
        payerService.save(params)
    }
}
    