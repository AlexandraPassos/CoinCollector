package com.coincollector.authentication

import com.coincollector.auth.User
import com.coincollector.customer.Customer
import grails.gorm.transactions.Transactional

@Transactional
class UserService {

    public void save(Customer customer, String email, String password) {
        User user = new User()
        user.customer = customer
        user.username = email
        user.password = password

        user.save(failOnError: true)
    }
}
