package com.coinCollector

import grails.gorm.transactions.Transactional
import utils.cpfCnpj.CpfCnpjUtils
import utils.email.EmailUtils
import utils.formattingParameters.FormattingParameters
import utils.name.NameUtils
import utils.personType.PersonType
import utils.phoneNumber.PhoneNumberUtils

@Transactional
class PayerService {

    public void save(Map params) {
        if(PersonType.convert(params.personType) == PersonType.PF && !CpfCnpjUtils.cpfIsValid(params.cpfCnpj)) return
        if(PersonType.convert(params.personType) == PersonType.PJ && !CpfCnpjUtils.cnpjIsValid(params.cpfCnpj)) return 
        if(!PhoneNumberUtils.phoneNumberIsValid(params.phoneNumber)) return
        if(!NameUtils.nameIsValid(params.name)) return
        if(!EmailUtils.emailIsValid(params.email)) return

        Payer payer = new Payer()
        payer.name = params.name
        payer.email = params.email
        payer.personType = params.personType
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
  
    public void update(Long id, Map params) {
        Payer payer = Payer.query([id: id]).get()
        if (!payer) {
            throw new Exception("Pagador não encontrado")
        }
        payer.name = params.name
        payer.email = params.email
        payer.personType = params.personType
        payer.cpfCnpj = FormattingParameters.removeSpecialCharacters(params.cpfCnpj)
        payer.cep = FormattingParameters.removeSpecialCharacters(params.cep)
        payer.state = params.state
        payer.city = params.city
        payer.district = params.district
        payer.address = params.address
        payer.addressNumber = FormattingParameters.removeSpecialCharacters(params.addressNumber)
        payer.complement = params.complement
        payer.phoneNumber = FormattingParameters.removeSpecialCharacters(params.phoneNumber)
        payer.save(failOnError: true)
    }
  
    public void delete(Long id) {
        if (!id) throw new Exception("ID do pagador não informado")
        Payer payer = Payer.query([id: id]).get()
        if (!payer) throw new Exception("Pagador com o ID ${id} não encontrado.")
        payer.deleted = true
        payer.save(failOnError: true) 
    }

    public void restore(Long id) {
        if (!id) throw new Exception("ID do pagador não informado")
        Payer payer = Payer.query([id: id]).get()
        if (!payer) throw new Exception("Pagador com o ID ${id} não encontrado.")
        payer.deleted = false
        payer.save(failOnError: true)
    }
}
