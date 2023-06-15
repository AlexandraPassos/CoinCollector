package com.coinCollector

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import utils.cpfCnpj.CpfCnpjUtils
import utils.email.EmailUtils
import utils.name.NameUtils
import utils.personType.PersonType
import utils.phoneNumber.PhoneNumberUtils

@Transactional
class CustomerService {

    public Customer save(Map params) {
        Customer validatedCustomer = validateCustomer(params)

        if (validatedCustomer.hasErrors()) {
            throw new ValidationException("Erro ao salvar um cliente", validatedCustomer.errors)
        }

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

    private Customer validateCustomer(Map params) {
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

        if (!params.complement) {
            validatedCustomer.errors.reject("", null, "O campo complemento é obrigatório")
        }

        if (!params.phoneNumber) {
            validatedCustomer.errors.reject("", null, "O campo celular é obrigatório")
        } else if(!PhoneNumberUtils.phoneNumberIsValid(params.phoneNumber)) {
            validatedCustomer.errors.reject("", null, "Número de celular inválido")
        }

        return validatedCustomer
    }
}
