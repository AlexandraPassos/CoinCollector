package com.coinCollector

import utils.basePerson.BasePerson

class Customer extends BasePerson {

    static namedQueries = {
        query { Map search ->
            if (search.containsKey("id")) eq("id", Long.valueOf(search.id))
        }
    }
}
