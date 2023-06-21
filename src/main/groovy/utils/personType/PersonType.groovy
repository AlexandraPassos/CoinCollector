package utils.personType

import groovy.transform.CompileStatic

@CompileStatic
enum PersonType { 
    PJ('Pessoa Jurídica'),
    
    PF('Pessoa Física'),

    public static PersonType convert(String personType) {
        try {
            if (personType instanceof String) personType = personType.toUpperCase()
            return personType as PersonType
        } catch (Exception exception) {
            return null
        }
    }

    final String id
    PersonType(String id) { this.id = id }
}