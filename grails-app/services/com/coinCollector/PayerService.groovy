package com.coinCollector

import grails.gorm.transactions.Transactional
import com.coinCollector.Payer
import utils.personType.PersonType
import utils.formattingParameters.FormattingParameters
import utils.cpfCnpj.CpfCnpjUtils 
import utils.phoneNumber.PhoneNumberUtils 
import utils.name.NameUtils 
import utils.email.EmailUtils

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
        payer.personType = FormattingParameters.removeSpecialCharacters(params.personType)
        payer.cpfCnpj = FormattingParameters.removeSpecialCharacters(params.cpfCnpj)
        payer.cep = FormattingParameters.removeSpecialCharacters(params.cep)
        payer.state = params.state
        payer.city = params.city
        payer.district = params.district
        payer.address = params.address
        payer.addressNumber = FormattingParameters.removeSpecialCharacters(params.addressNumber)
        payer.complement = params.complement
        payer.phoneNumber = FormattingParameters.removeSpecialCharacters(params.phoneNumber)
        payer.customer = Customer.findById(1)
        payer.save(failOnError: true)
    }
}