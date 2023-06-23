package utils.personType

import groovy.transform.CompileStatic

@CompileStatic
enum PersonType { 
    PJ('Pessoa Jurídica'),
    
    PF('Pessoa Física'),

    public static PersonType convert(String personType) {
        try {
            return personType.toUpperCase() as PersonType
        } catch (Exception exception) {
            return null
        }
    }

    final String id
    PersonType(String id) { this.id = id }
}