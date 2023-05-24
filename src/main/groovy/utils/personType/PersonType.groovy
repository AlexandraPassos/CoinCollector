package utils.personType

import groovy.transform.CompileStatic

@CompileStatic
enum PersonType { 
    PJ('Pessoa Jurídica'),
    
    PF('Pessoa Física'),

    final String id
    PersonType(String id) { this.id = id }
}