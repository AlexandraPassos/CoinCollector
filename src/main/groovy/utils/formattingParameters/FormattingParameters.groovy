package utils.formattingParameters

class FormattingParameters {

    public static String removeSpecialCharacters(String parameter) {
        parameter = parameter.replaceAll("[^0-9]", "")
        return parameter
    }

    public static Boolean expectedLength(String parameter, Integer parameterLength) {
        if (!(parameter.length() == parameterLength)) {
            return false
        } else {
            return true
        }
    }

    public static Boolean repeatSameCharacter(String parameter, int length) {
        String regexRepetedSequenceOnParameter = "(.)\\1{${length - 1}}"
        if (parameter.replaceAll(regexRepetedSequenceOnParameter,"").isEmpty()) {
            return false
        } else {
            return true 
        }
    }   
}





