package utils.formattingParameters

class FormattingParameters {

    public static String removeSpecialCharacters(String parameter) {
        if (parameter.isEmpty()) return parameter
            parameter = parameter.replaceAll("[^0-9]", "")
            return parameter
    }

    public static Boolean expectedLength(String parameter, Integer parameterLength) {
        return parameter.length() == parameterLength
    }

    public static Boolean repeatSameCharacter(String parameter, int length) {
        String regexRepetedSequenceOnParameter = "(.)\\1{${length - 1}}"
        return !parameter.replaceAll(regexRepetedSequenceOnParameter,"").isEmpty()
    }   
}