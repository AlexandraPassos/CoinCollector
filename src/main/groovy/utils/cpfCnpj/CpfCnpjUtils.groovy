package utils.cpfCnpj

import java.lang.String

class CpfCnpjUtils {
    public static final int cnpjLength = 14
    public static final int cpfLength = 11

    public static Boolean cpfIsValid(String cpf) {
        
        int firstWeightedSum = 0, secondeWeightedSum = 0, firstDigit = 0, secondDigit = 0, rest = 0, cpfDigit

        for (int counter = 1; counter < cpf.length() - 1; counter++) {
            cpfDigit = Integer.valueOf(cpf.substring(counter - 1, counter)).intValue()
            firstWeightedSum = firstWeightedSum + (11 - counter) * cpfDigit
            secondeWeightedSum = secondeWeightedSum + (12 - counter) * cpfDigit
        }

        rest = (firstWeightedSum % 11)

        if (rest < 2) {
            firstDigit = 0
        } else {
            firstDigit = 11 - rest
        }

        secondeWeightedSum += 2 * firstDigit

        rest = (secondeWeightedSum % 11)

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
            return 
        }
    }

    public static Boolean cnpjIsValid(String cnpj) {

        char thirteenthDigit, fourteenthDigit
        int number, weight = 2 , sum = 0

        for (int counter = 11; counter >= 0; counter--) {
            number = (int) (cnpj.charAt(counter) - 48)
            sum = sum + (number * weight)
            weight = weight + 1
            if (weight == 10) {
              weight = 2
            }
        }

        int rest = sum % 11

        if ((rest == 0) || (rest == 1)) {
            thirteenthDigit = '0'
        } else {
            thirteenthDigit = (char) ((11 - rest) + 48)
        }

        sum = 0
        weight = 2

        for (int counter = 12; counter >= 0; counter--) {
            number = (int) (cnpj.charAt(counter) - 48)
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
            fourteenthDigit = (char) ((11 - rest) + 48)
        }
          
        if ((thirteenthDigit == cnpj.charAt(12)) && (fourteenthDigit == cnpj.charAt(13))) {
            return true
        } else {
            return
        } 
    }

}
