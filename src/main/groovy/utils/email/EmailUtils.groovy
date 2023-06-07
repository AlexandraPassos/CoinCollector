package utils.email

class EmailUtils {

    public static Boolean emailIsValid(String email) {

        return !(email.isEmpty()) && email.matches(/^[a-z0-9\-\.]+@+[a-z0-9]+(\.com)?(\.br)/)
    }
}
