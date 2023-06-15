package com.coinCollector

import grails.gorm.transactions.Transactional
import utils.cpfCnpj.CpfCnpjUtils
import utils.email.EmailUtils
import utils.name.NameUtils
import utils.personType.PersonType
import utils.phoneNumber.PhoneNumberUtils

@Transactional
class CustomerService {

    public void save(Map params) {

        if(PersonType.convert(params.personType) == PersonType.PF && !CpfCnpjUtils.cpfIsValid(params.cpfCnpj)) return
        if(PersonType.convert(params.personType) == PersonType.PJ && !CpfCnpjUtils.cnpjIsValid(params.cpfCnpj)) return
        if(!PhoneNumberUtils.phoneNumberIsValid(params.phoneNumber)) return
        if(!NameUtils.nameIsValid(params.name)) return
        if(!EmailUtils.emailIsValid(params.email)) return

        Customer customer = new Customer()
        customer.name = params.name
        customer.email = params.email
        customer.personType = params.personType
        customer.cpfCnpj = params.cpfCnpj
        customer.cep = params.cep
        customer.state = params.state
        customer.city = params.city
        customer.district = params.district
        customer.address = params.address
        customer.addressNumber = params.addressNumber
        customer.complement = params.complement
        customer.phoneNumber = params.phoneNumber
        customer.save(failOnError: true)
    }

    public void update(Long id, Map params) {
        Customer customer = Customer.query([id: id]).get()
        if (!customer) {
            throw new Exception("Cliente não encontrado")
        }
        customer.name = params.name
        customer.email = params.email
        customer.personType = params.personType
        customer.cpfCnpj = params.cpfCnpj
        customer.cep = params.cep
        customer.state = params.state
        customer.city = params.city
        customer.district = params.district
        customer.address = params.address
        customer.addressNumber = params.addressNumber
        customer.complement = params.complement
        customer.phoneNumber = params.phoneNumber
        customer.save(failOnError: true)
    }
}
