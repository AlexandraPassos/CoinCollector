package utils.personType

import groovy.transform.CompileStatic

@CompileStatic
enum PersonType { 
    PF('Pessoa Física'),
    
    PJ('Pessoa Jurídica'),

    final String id
    PersonType(String id) { this.id = id }
}