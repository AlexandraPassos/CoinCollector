package com.coinCollector

import utils.basePerson.BasePerson

class Payer extends BasePerson {
    Customer customer

    static namedQueries = {
        query { Map search ->
            if (search.containsKey("id")) eq("id", Long.valueOf(search.id))

            if (search.containsKey("deleted")) eq("deleted", search.deleted)

            if (search.containsKey("customerId")) eq("customer.id", Long.valueOf(search.customerId))
        }
    }
}
