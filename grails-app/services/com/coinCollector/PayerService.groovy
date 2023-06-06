package com.coinCollector

import grails.gorm.transactions.Transactional
import com.coinCollector.Payer
import utils.personType.PersonType
import utils.cpfCnpj.CpfCnpjUtils 
import utils.phoneNumber.PhoneNumberUtils 
import utils.name.NameUtils 
import utils.email.EmailUtils 

@Transactional
class PayerService {
    public void save(Map params) {

        String validCpfCnpj
        String validPhoneNumber
        String validName
        String validEmail

        if(PersonType.convert(params.personType) == PersonType.PF && !CpfCnpjUtils.cpfIsValid(params.cpfCnpj)) return

        if(PersonType.convert(params.personType) == PersonType.PJ && !CpfCnpjUtils.cnpjIsValid(params.cpfCnpj)) return 

        validCpfCnpj = params.cpfCnpj

        if(PhoneNumberUtils.phoneNumberIsValid(params.phoneNumber)) {
            validPhoneNumber = params.phoneNumber
        } else {
            return 
        }

        if(NameUtils.nameIsValid(params.name)) {
            validName = params.name
        } else {
            return
        }

        if(EmailUtils.emailIsValid(params.email)) {
            validEmail = params.email
        } else {
            return
        }

        Payer payer = new Payer()   
        payer.name = validName
        payer.email = validEmail
        payer.personType = params.personType
        payer.cpfCnpj = validCpfCnpj
        payer.cep = params.cep
        payer.state = params.state
        payer.city = params.city
        payer.district = params.district
        payer.address = params.address
        payer.addressNumber = params.addressNumber
        payer.complement = params.complement
        payer.phoneNumber = validPhoneNumber
        payer.customer = Customer.findById(1)
        payer.save(failOnError: true)
    }
}