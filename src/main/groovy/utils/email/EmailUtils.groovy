package utils.email

class EmailUtils {

    public static Boolean emailIsValid(String email) {
        if (email.isEmpty()) return false
        return email.matches(/^[a-z0-9\-\.]+@+[a-z0-9]+(\.com)+(\.br)*/)
    }
}
