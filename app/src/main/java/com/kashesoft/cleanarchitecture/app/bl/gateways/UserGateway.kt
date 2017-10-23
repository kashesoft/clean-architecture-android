package com.kashesoft.cleanarchitecture.app.bl.gateways

import com.kashesoft.cleanarchitecture.app.bl.entities.User
import com.kashesoft.cleanarchitecture.app.bl.gateways.base.Params
import io.reactivex.Observable

interface UserGateway {

    // database operations
    fun fetchUsers(input: Params.Input.Ids): Observable<Params.Output.Entities<User>>
    fun saveUser(input: Params.Input.Entity<User>): Observable<Params.Output.Entity<User>>

    // server CRUD operations
    fun createUser(input: Params.Input.Entity<User>): Observable<Params.Output.Entity<User>>
    fun readUsers(input: Params.Input.Ids): Observable<Params.Output.Entities<User>>
    fun updateUser(input: Params.Input.Entity<User>): Observable<Params.Output.Entity<User>>
    fun deleteUser(input: Params.Input.Entity<User>): Observable<Params.Output.Entity<User>>

}
