package utils.basePerson

import utils.baseEntity.BaseEntity
import grails.gorm.dirty.checking.DirtyCheck
import utils.personType.PersonType

@DirtyCheck
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
}