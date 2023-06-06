package com.coinCollector

import grails.gorm.transactions.Transactional
import com.coinCollector.Payer
import utils.personType.PersonType
import utils.cpfCnpj.CpfCnpjUtils 
import utils.phoneNumber.PhoneNumberUtils 
import utils.name.NameUtils 


@Transactional
class PayerService {
    public void save(Map params) {

        String validCpfCnpj
        String validPhoneNumber
        String validName

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
        
        Payer payer = new Payer()   
        payer.name = validName
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
        payer.phoneNumber = validPhoneNumber
        payer.customer = Customer.findById(1)
        payer.save(failOnError: true)
    }
}