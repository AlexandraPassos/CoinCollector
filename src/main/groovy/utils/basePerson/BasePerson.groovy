package utils.basePerson

import utils.baseEntity.BaseEntity

abstract class BasePerson extends BaseEntity {
    String name

    String email

    String cpfCnpj

    String cep

    String state

    String city 

    String district

    String address

    String addressNumber
    
    String complement
}