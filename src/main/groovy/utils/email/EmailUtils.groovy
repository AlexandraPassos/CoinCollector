package utils.email

class EmailUtils {

    public static Boolean emailIsValid(String email) {

        String parameterEmail = email

        return !(parameterEmail.isEmpty()) && parameterEmail.matches(/^[a-z0-9\-\.]+@+[a-z0-9]+(\.com)?(\.br)/)
    }
}
