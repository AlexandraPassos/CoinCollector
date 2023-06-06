package utils.email

class EmailUtils {

    public static Boolean emailIsValid(String email) {

        String parameterEmail = email

        if(parameterEmail.isEmpty()) return false
        if(!parameterEmail.matches(/^[a-z0-9\-\.]+@+[a-z0-9]+(\.com)?(\.br)/)) return false
        return true
    }
}
