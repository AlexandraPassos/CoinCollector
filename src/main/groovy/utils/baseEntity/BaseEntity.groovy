package utils.baseEntity

abstract class BaseEntity {
    Date dateCreated

    Date lastUpdated
    
    Boolean deleted

    static mapping = {
        tablePerHierarchy false
        deleted defaultValue: false
    }
}