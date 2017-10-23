package com.kashesoft.cleanarchitecture.app.cl.repositories

import com.kashesoft.cleanarchitecture.app.bl.entities.User
import com.kashesoft.cleanarchitecture.app.bl.gateways.UserGateway
import com.kashesoft.cleanarchitecture.app.bl.gateways.base.Params
import io.reactivex.Observable
import javax.inject.Inject


/**
 * Copyright (c) 2017 Kashesoft. All rights reserved.
 * Created on 2017-10-16.
 */

class UserRepository @Inject
constructor() : UserGateway {

    private val userNames = listOf("Andrew Kashaed", "Anastasia Evsigneeva", "John Doe",
            "Lee Loo", "Lana Do", "Mick Sia", "Jack Black", "Bob Green", "Nick Yellow", "Billy Red")

    override fun fetchUsers(input: Params.Input.Ids): Observable<Params.Output.Entities<User>> {
        return Observable.create<Params.Output.Entities<User>> { emitter ->
            for ((index, id) in input.ids.withIndex()) {
                Thread.sleep(1000)
                val user = User(id, userNames[index])
                val progress = 100 * (index + 1) / (input.ids.size)
                emitter.onNext(Params.Output.Entities("success", "", listOf(user), progress))
            }
            Thread.sleep(1000)
            emitter.onComplete()
        }
    }

    override fun saveUser(input: Params.Input.Entity<User>): Observable<Params.Output.Entity<User>> {
        return Observable.create<Params.Output.Entity<User>> { emitter ->
            Thread.sleep(1000)
            val user = input.entity
            user.id *= -1
            emitter.onNext(Params.Output.Entity("success", "", user))
            emitter.onComplete()
        }
    }

    override fun createUser(input: Params.Input.Entity<User>): Observable<Params.Output.Entity<User>> {
        return Observable.create<Params.Output.Entity<User>> { emitter ->
            Thread.sleep(1000)
            val user = input.entity
            user.id *= -1
            emitter.onNext(Params.Output.Entity("success", "", user))
            emitter.onComplete()
        }
    }

    override fun readUsers(input: Params.Input.Ids): Observable<Params.Output.Entities<User>> {
        return Observable.create<Params.Output.Entities<User>> { emitter ->
            for ((index, id) in input.ids.withIndex()) {
                Thread.sleep(1000)
                val user = User(id, userNames[index])
                val progress = 100 * (index + 1) / (input.ids.size)
                emitter.onNext(Params.Output.Entities("success", "", listOf(user), progress))
            }
            Thread.sleep(1000)
            emitter.onComplete()
        }
    }

    override fun updateUser(input: Params.Input.Entity<User>): Observable<Params.Output.Entity<User>> {
        return Observable.create<Params.Output.Entity<User>> { emitter ->
            Thread.sleep(1000)
            val user = input.entity
            user.id *= -1
            emitter.onNext(Params.Output.Entity("success", "", user))
            emitter.onComplete()
        }
    }

    override fun deleteUser(input: Params.Input.Entity<User>): Observable<Params.Output.Entity<User>> {
        return Observable.create<Params.Output.Entity<User>> { emitter ->
            Thread.sleep(1000)
            val user = input.entity
            user.id *= -1
            emitter.onNext(Params.Output.Entity("success", "", user))
            emitter.onComplete()
        }
    }

}
