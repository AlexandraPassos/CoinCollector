package com.coinCollector

import utils.basePerson.BasePerson

class Payer extends BasePerson {
    Customer customer

    static namedQueries = {
        query { Map search ->
            if (search.containsKey("id")) eq("id", Long.valueOf(search.id as String))

            if (search.containsKey("deletedOnly") && search.deletedOnly) {
                eq("deleted", search.deletedOnly)
            } else if (search.containsKey("includeDeleted") && search.includeDeleted) {
                eq("deleted", !search.includeDeleted)
            }

            if (search.containsKey("customerId")) eq("customer.id", Long.valueOf(search.customerId as String))
        }
    }
}
