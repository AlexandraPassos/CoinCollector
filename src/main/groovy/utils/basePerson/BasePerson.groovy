package utils.basePerson

import utils.baseEntity.BaseEntity

import utils.personType.PersonType

abstract class BasePerson extends BaseEntity {
    String name

    String email

    PersonType personType

    String cpfCnpj

    String cep

    String state

    String city 

    String district

    String address

    String addressNumber
    
    String complement

    String phoneNumber

    static mapping = {
        personType enumType: 'string'
    }

    static constraints = {
        personType maxSize: 2
        cpfCnpj maxSize: 14
        cep maxSize: 8
        state maxSize: 2
        complement blank: true, nullable: true
        phoneNumber maxSize: 11
    } 
}