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

    static mapping = {
        personType enumType: 'string'
    }
}