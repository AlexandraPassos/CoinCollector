package utils.cpfCnpj

import java.lang.String
import utils.formattingParameters.FormattingParameters

class CpfCnpjUtils {
    public static final Integer cpfLength = 11
    public static final Integer cnpjLength = 14

    public static Boolean cpfIsValid(String cpf) {
                
        cpf = FormattingParameters.removeSpecialCharacters(cpf)
        if(!FormattingParameters.expectedLength(cpf, cpfLength)) return false
        if(!FormattingParameters.repeatSameCharacter(cpf, cpfLength)) return false
        
        Integer firstWeightedSum = 0, secondWeightedSum = 0, firstDigit = 0, secondDigit = 0, rest = 0, cpfDigit

        for (Integer counter = 1; counter < cpf.length() - 1; counter++) {
            cpfDigit = Integer.valueOf(cpf.substring(counter - 1, counter)).intValue()
            firstWeightedSum = firstWeightedSum + (11 - counter) * cpfDigit
            secondWeightedSum = secondWeightedSum + (12 - counter) * cpfDigit
        }

        rest = (firstWeightedSum % 11)

        if (rest < 2) {
            firstDigit = 0
        } else {
            firstDigit = 11 - rest
        }

        secondWeightedSum += 2 * firstDigit

        rest = (secondWeightedSum % 11)

        if (rest < 2) {
          secondDigit = 0
        } else {
          secondDigit = 11 - rest
        }

        String nDigVerify = cpf.substring(cpf.length() - 2, cpf.length())

        String nDigResult = String.valueOf(firstDigit) + String.valueOf(secondDigit)

        if (nDigVerify.equals(nDigResult)) {
            return true
        } else {
            return false
        }
    }
    
    public static Boolean cnpjIsValid(String cnpj) {
        
        cnpj = FormattingParameters.removeSpecialCharacters(cnpj)
        if(!FormattingParameters.expectedLength(cnpj, cnpjLength)) return false
        if(!FormattingParameters.repeatSameCharacter(cnpj, cnpjLength)) return false

        Character thirteenthDigit, fourteenthDigit
        Integer number, weight = 2 , sum = 0

        for (Integer counter = 11; counter >= 0; counter--) {
            number = (Integer) (cnpj.charAt(counter) - 48)
            sum = sum + (number * weight)
            weight = weight + 1
            if (weight == 10) {
              weight = 2
            }
        }

        Integer rest = sum % 11

        if ((rest == 0) || (rest == 1)) {
            thirteenthDigit = '0'
        } else {
            thirteenthDigit = (Character) ((11 - rest) + 48)
        }

        sum = 0
        weight = 2

        for (Integer counter = 12; counter >= 0; counter--) {
            number = (Integer) (cnpj.charAt(counter) - 48)
            sum = sum + (number * weight)
            weight = weight + 1
            if (weight == 10) {
                weight = 2
            }
        }

        rest = sum % 11;

        if ((rest == 0) || (rest == 1)) {
            fourteenthDigit = '0'
        } else {
            fourteenthDigit = (Character) ((11 - rest) + 48)
        }
          
        if ((thirteenthDigit == cnpj.charAt(12)) && (fourteenthDigit == cnpj.charAt(13))) {
            return true
        } else {
            return false
        } 
    }

}
