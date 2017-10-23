package com.kashesoft.cleanarchitecture.app.bl.gateways.base

/**
 * Copyright (c) 2017 Kashesoft. All rights reserved.
 * Created on 2017-10-15.
 */

public class Params {

    public class Input {
        public data class Id(val id: String)
        public data class Ids(val ids: List<Int>)
        public data class Entity<E>(val entity: E)
    }

    public class Output {
        public data class Entity<E>(val status: String, val message: String, val entity: E?)
        public data class Entities<E>(val status: String, val message: String, val entities: List<E>, val progress: Int)
    }

}
