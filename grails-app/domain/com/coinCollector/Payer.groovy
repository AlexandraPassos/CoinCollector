package com.coinCollector

import utils.basePerson.BasePerson

class Payer extends BasePerson {
    Customer customer

    static namedQueries = {
        query { Map search ->
            if (search.containsKey("id")) eq("id", Long.valueOf(search.id as String))

            if (Boolean.valueOf(search.deletedOnly)) {
                eq("deleted", true)
            } else if (!Boolean.valueOf(search.includeDeleted)) {
                eq("deleted", false)
            }

            if (search.containsKey("customerId")) eq("customer.id", Long.valueOf(search.customerId as String))
        }
    }
}
