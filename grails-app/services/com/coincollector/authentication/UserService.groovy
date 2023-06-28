package com.coincollector.authentication

import com.coincollector.auth.User
import com.coincollector.customer.Customer
import grails.gorm.transactions.Transactional
import grails.validation.ValidationException

@Transactional
class UserService {

    public void save(Customer customer, String email, String password, String confirmPassword) {
        User validatedPassword = validatePassword(password, confirmPassword)

        if (validatedPassword.hasErrors()) throw new ValidationException(null, validatedPassword.errors)

        User user = new User()
        user.customer = customer
        user.username = email
        user.password = password

        user.save(failOnError: true)
    }

    private User validatePassword(password, confirmPassword) {
        User validatedPassword = new User()

        if (!password) {
            validatedPassword.errors.reject("", null, "O campo senha é obrigatório")
        }

        if (password != confirmPassword) {
            validatedPassword.errors.reject("", null, "As senhas não correspondem")
        }

        return validatedPassword
    }
}
