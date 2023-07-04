package utils.controller

import com.coincollector.customer.Customer
import grails.plugin.springsecurity.SpringSecurityService

class BaseController {

    SpringSecurityService springSecurityService

    protected Customer getCurrentCustomer() {
        return springSecurityService.getCurrentUser().customer
    }
}
