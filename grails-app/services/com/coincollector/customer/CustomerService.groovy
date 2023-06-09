package com.coincollector.customer


import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import utils.cpfCnpj.CpfCnpjUtils
import utils.email.EmailUtils
import utils.formattingParameters.FormattingParameters
import utils.name.NameUtils
import utils.personType.PersonType
import utils.phoneNumber.PhoneNumberUtils

@Transactional
class CustomerService {

    def userService

    public Customer save(Map params) {
        Map parsedParams = parsedParams(params)
        Customer validatedCustomer = validateCustomer(parsedParams)

        if (validatedCustomer.hasErrors()) throw new ValidationException(null, validatedCustomer.errors)

        Customer customer = new Customer()

        List<String> savableParams = ["name", "email", "personType", "cpfCnpj", "cep", "state", "city", "district", "address", "address", "addressNumber", "complement", "phoneNumber"]
        customer.properties[savableParams] = parsedParams
        customer.save(failOnError: true)

        userService.save(customer, customer.email, params.password, params.confirmPassword)
    }

    public Map parsedParams(Map params) {
        Map parsedParams = params

        parsedParams.personType = PersonType.convert(params.personType)
        parsedParams.cpfCnpj = FormattingParameters.removeSpecialCharacters(params.cpfCnpj)
        parsedParams.cep = FormattingParameters.removeSpecialCharacters(params.cep)
        parsedParams.addressNumber = FormattingParameters.removeSpecialCharacters(params.addressNumber)
        parsedParams.phoneNumber = FormattingParameters.removeSpecialCharacters(params.phoneNumber)

        return parsedParams
    }

    public Customer update(Long id, Map params) {
        Map parsedParams = parsedParams(params)
        Customer validatedCustomer = validateCustomer(parsedParams)

        if (validatedCustomer.hasErrors()) throw new ValidationException(null, validatedCustomer.errors)

        Customer customer = Customer.get(id)

        List<String> updatableParams = ["name", "email", "personType", "cpfCnpj", "cep", "state", "city", "district", "address", "address", "addressNumber", "complement", "phoneNumber"]
        customer.properties[updatableParams] = parsedParams
        customer.save(failOnError: true)
    }

    private Customer validateCustomer(Map parsedParams) {
        Customer validatedCustomer = new Customer()

        if (!parsedParams.name) {
            validatedCustomer.errors.reject("", null, "O campo nome é obrigatório")
        } else if (!NameUtils.nameIsValid(parsedParams.name)) {
            validatedCustomer.errors.reject("", null, "Nome inválido")
        }

        if (!parsedParams.email) {
            validatedCustomer.errors.reject("", null, "O campo e-mail é obrigatório")
        } else if (!EmailUtils.emailIsValid(parsedParams.email)) {
            validatedCustomer.errors.reject("", null, "Formato de e-mail inválido")
        }

        if (!parsedParams.personType) {
            validatedCustomer.errors.reject("", null, "Tipo de pessoa inválido")
        }

        if (!parsedParams.cpfCnpj) {
            validatedCustomer.errors.reject("", null, "O campo CPF/CNPJ é obrigatório")
        } else if (parsedParams.personType == PersonType.PF && !CpfCnpjUtils.cpfIsValid(parsedParams.cpfCnpj)) {
            validatedCustomer.errors.reject("", null, "CPF inválido")
        } else if (parsedParams.personType == PersonType.PJ && !CpfCnpjUtils.cnpjIsValid(parsedParams.cpfCnpj)) {
            validatedCustomer.errors.reject("", null, "CNPJ inválido")
        }

        if (!parsedParams.cep) {
            validatedCustomer.errors.reject("", null, "O campo CEP é obrigatório")
        }

        if (!parsedParams.state) {
            validatedCustomer.errors.reject("", null, "O campo estado é obrigatório")
        }

        if (!parsedParams.city) {
            validatedCustomer.errors.reject("", null, "O campo cidade é obrigatório")
        }

        if (!parsedParams.district) {
            validatedCustomer.errors.reject("", null, "O campo bairro é obrigatório")
        }

        if (!parsedParams.address) {
            validatedCustomer.errors.reject("", null, "O campo endereço é obrigatório")
        }

        if (!parsedParams.addressNumber) {
            validatedCustomer.errors.reject("", null, "O campo número do endereço é obrigatório")
        }

        if (!parsedParams.phoneNumber) {
            validatedCustomer.errors.reject("", null, "O campo celular é obrigatório")
        } else if (!PhoneNumberUtils.phoneNumberIsValid(parsedParams.phoneNumber)) {
            validatedCustomer.errors.reject("", null, "Número de celular inválido")
        }

        return validatedCustomer
    }
}
