package com.coinCollector

import grails.gorm.transactions.Transactional
import com.coinCollector.Payer
import utils.personType.PersonType
import utils.cpfCnpj.CpfCnpjUtils 
import utils.phoneNumber.PhoneNumberUtils 
import utils.name.NameUtils 
import utils.email.EmailUtils
import grails.validation.ValidationException

@Transactional
class PayerService {
    public void save(Map params) {

        if(PersonType.convert(params.personType) == PersonType.PF && !CpfCnpjUtils.cpfIsValid(params.cpfCnpj)) return
        if(PersonType.convert(params.personType) == PersonType.PJ && !CpfCnpjUtils.cnpjIsValid(params.cpfCnpj)) return 
        if (!PhoneNumberUtils.phoneNumberIsValid(params.phoneNumber)) return
        if (!NameUtils.nameIsValid(params.name)) return
        if (!EmailUtils.emailIsValid(params.email)) return

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
        payer.customer = Customer.findById(1)
        payer.save(failOnError: true)
    }

    def getAllPayers() {
        Payer.list()
    }

    def getPayerById(Long id) {
    def payer = Payer.get(id)
    return payer
    }
}