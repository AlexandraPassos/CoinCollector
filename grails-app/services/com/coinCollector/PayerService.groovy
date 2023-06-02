package com.coinCollector

import grails.gorm.transactions.Transactional
import com.coinCollector.Payer

@Transactional
class PayerService {

    public void save(Map params) {
        Payer payer = new Payer()
        payer.name = params.name
        payer.email = params.email
        payer.personType = params.personType
        payer.cpfCnpj = params.cpfCnpj
        payer.cep = params.cep
        payer.state = params.state
        payer.city = params.city
        payer.district = params.district
        payer.address = params.address
        payer.addressNumber = params.addressNumber
        payer.complement = params.complement
        payer.phoneNumber = params.phoneNumber
        payer.save(failOnError: true)
    }
}