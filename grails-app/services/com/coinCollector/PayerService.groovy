package com.coinCollector

import grails.gorm.transactions.Transactional
import com.coinCollector.Payer
import utils.personType.PersonType
import utils.cpfCnpj.CpfCnpjUtils 

@Transactional
class PayerService {
    public void save(Map params) {

        String validCpfCnpj

        if(PersonType.convert(params.personType) == PersonType.PF) {
            if(CpfCnpjUtils.cpfIsValid(params.cpfCnpj)) {
                validCpfCnpj = params.cpfCnpj
            } else {
                return
            }
        } else {
            if(CpfCnpjUtils.cnpjIsValid(params.cpfCnpj)) {
                validCpfCnpj = params.cpfCnpj
            } else {
                return
            }
        }
        
        Payer payer = new Payer()   
        payer.name = params.name
        payer.email = params.email
        payer.personType = params.personType
        payer.cpfCnpj = validCpfCnpj
        payer.cep = params.cep
        payer.state = params.state
        payer.city = params.city
        payer.district = params.district
        payer.address = params.address
        payer.addressNumber = params.addressNumber
        payer.complement = params.complement
        payer.phoneNumber = params.phoneNumber
        payer.customer = Customer.findById(1)
        payer.save(failOnError: true)
    }
}