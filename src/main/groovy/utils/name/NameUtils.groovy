package utils.name

class NameUtils {

    public static Boolean nameIsValid(String name) {
        return !(name.isEmpty()) && name.matches(/^[a-zA-Z \-]+$/)
    }
}

