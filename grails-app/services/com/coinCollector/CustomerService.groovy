package com.coinCollector

import grails.gorm.transactions.Transactional
import utils.formattingParameters.FormattingParameters
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

    public Customer update(Long id, Map params) {
        Customer existingCustomer = Customer.query([id: id]).get()
        Customer validatedCustomer = validateCustomer(params, existingCustomer)

        if (validatedCustomer.hasErrors()) {
            return validatedCustomer
        }

        existingCustomer.name = params.name
        existingCustomer.email = params.email
        existingCustomer.personType = params.personType
        existingCustomer.cpfCnpj = FormattingParameters.removeSpecialCharacters(params.cpfCnpj)
        existingCustomer.cep = FormattingParameters.removeSpecialCharacters(params.cep)
        existingCustomer.state = params.state
        existingCustomer.city = params.city
        existingCustomer.district = params.district
        existingCustomer.address = params.address
        existingCustomer.addressNumber = FormattingParameters.removeSpecialCharacters(params.addressNumber)
        existingCustomer.complement = params.complement
        existingCustomer.phoneNumber = FormattingParameters.removeSpecialCharacters(params.phoneNumber)
        existingCustomer.save(failOnError: true)
    }

    private Customer validateCustomer(Map params, Customer existingCustomer) {
        Customer validatedCustomer = new Customer()

        if (!params.name) {
            validatedCustomer.errors.reject("", null, "O campo nome é obrigatório")
        } else if(!NameUtils.nameIsValid(params.name)) {
            validatedCustomer.errors.reject("", null, "Nome inválido")
        }

        if (!params.email) {
            validatedCustomer.errors.reject("", null, "O campo e-mail é obrigatório")
        } else if(!EmailUtils.emailIsValid(params.email)) {
            validatedCustomer.errors.reject("", null, "Formato de e-mail inválido")
        }

        if (!params.personType) {
            validatedCustomer.errors.reject("", null, "O campo tipo de pessoa é obrigatório")
        }

        if (!params.cpfCnpj) {
            validatedCustomer.errors.reject("", null, "O campo CPF/CNPJ é obrigatório")
        } else if(PersonType.convert(params.personType) == PersonType.PF && !CpfCnpjUtils.cpfIsValid(params.cpfCnpj)) {
            validatedCustomer.errors.reject("", null, "CPF inválido")
        } else if(PersonType.convert(params.personType) == PersonType.PJ && !CpfCnpjUtils.cnpjIsValid(params.cpfCnpj)) {
            validatedCustomer.errors.reject("", null, "CNPJ inválido")
        }

        if (!params.cep) {
            validatedCustomer.errors.reject("", null, "O campo CEP é obrigatório")
        }

        if (!params.state) {
            validatedCustomer.errors.reject("", null, "O campo estado é obrigatório")
        }

        if (!params.city) {
            validatedCustomer.errors.reject("", null, "O campo cidade é obrigatório")
        }

        if (!params.district) {
            validatedCustomer.errors.reject("", null, "O campo bairro é obrigatório")
        }

        if (!params.address) {
            validatedCustomer.errors.reject("", null, "O campo endereço é obrigatório")
        }

        if (!params.addressNumber) {
            validatedCustomer.errors.reject("", null, "O campo número do endereço é obrigatório")
        }

        if (!params.phoneNumber) {
            validatedCustomer.errors.reject("", null, "O campo celular é obrigatório")
        } else if(!PhoneNumberUtils.phoneNumberIsValid(params.phoneNumber)) {
            validatedCustomer.errors.reject("", null, "Número de celular inválido")
        }
        return validatedCustomer
    }
}
