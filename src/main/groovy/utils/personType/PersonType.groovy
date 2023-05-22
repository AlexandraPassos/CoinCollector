package utils.personType

enum PersonType { 
    PJ('Pessoa Física'),
    
    PF('Pessoa Jurídica'),

    final String id
    PersonType(String id) { this.id = id }
}