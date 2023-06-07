package utils.name

class NameUtils {

    public static Boolean nameIsValid(String name) {
        
        String parameterName = name
    
        return !(parameterName.isEmpty()) && parameterName.matches(/^[a-zA-Z \-]+$/)
    }
}

