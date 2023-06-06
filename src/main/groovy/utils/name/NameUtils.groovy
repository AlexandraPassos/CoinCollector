package utils.name

class NameUtils {

    public static Boolean nameIsValid(String name) {
        
        String parameterName = name

        if(parameterName.isEmpty()) return false

        if(!parameterName.matches(/^[a-zA-Z \-]+$/)) return false
                
        return true
    }
}

