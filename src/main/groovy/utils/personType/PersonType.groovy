package utils.personType

import groovy.transform.CompileStatic

@CompileStatic
enum PersonType { 
    PJ('Pessoa Jurídica'),
    
    PF('Pessoa Física'),

    public static PersonType convert(String personType) {
        return personType as PersonType
    }

    final String id
    PersonType(String id) { this.id = id }
}