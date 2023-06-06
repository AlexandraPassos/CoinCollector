package utils.phoneNumber

import utils.formattingParameters.FormattingParameters

class PhoneNumberUtils {
    public static final Integer phoneNumberLength = 11
    public static final Integer[]areaCode = [11, 12, 13, 14, 15, 16, 17, 18, 19, 21, 22, 24, 27, 28, 31, 32, 33, 34, 35, 37, 38, 41, 42, 43, 44, 45, 46, 47, 48, 49, 51, 53, 54, 55, 61, 62, 64, 63, 65, 66, 67, 68, 69, 71, 73, 74, 75, 77, 79, 81, 82, 83, 84, 85, 86, 87, 88, 89 ,91, 92, 93, 94, 95, 96, 97, 98, 99]

    public static Boolean phoneNumberIsValid(String phoneNumber) {

        String cleanedPhoneNumber = FormattingParameters.removeSpecialCharacters(phoneNumber)

        if(!FormattingParameters.expectedLength(cleanedPhoneNumber, phoneNumberLength)) return false

        if(!areaCode.contains(cleanedPhoneNumber[0..1] as Integer)) return false

        if(cleanedPhoneNumber[2] != "9") return false
    
        String referenceDigit= cleanedPhoneNumber[3]
        for(Integer index = 4; index < cleanedPhoneNumber.size(); index++) {
            if(cleanedPhoneNumber[index] != referenceDigit) {
                return true
            }
        }
        return false
    }
}
