package com.kashesoft.cleanarchitecture.app.bl.interactors

import com.kashesoft.cleanarchitecture.app.bl.entities.User
import com.kashesoft.cleanarchitecture.app.bl.gateways.UserGateway
import com.kashesoft.cleanarchitecture.app.bl.gateways.base.Params
import com.kashesoft.cleanarchitecture.app.bl.interactors.base.BaseInteractor
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

/**
 * Copyright (c) 2017 Kashesoft. All rights reserved.
 * Created on 2017-10-15.
 */

class ReadUserInteractor @Inject
internal constructor(internal val userGateway: UserGateway) :
        BaseInteractor<Params.Input.Ids, Params.Output.Entities<User>, ReadUserInteractor.OnResult>() {

    interface OnResult {
        fun readUserOnOutput(output: Params.Output.Entities<User>)
        fun readUserOnSuccess()
        fun readUserOnFailure(error: Throwable)
    }

    override fun buildObservable(input: Params.Input.Ids): Observable<Params.Output.Entities<User>> {
        return this.userGateway.readUsers(input)
    }

    override fun buildObserver(onResult: OnResult): DisposableObserver<Params.Output.Entities<User>> {
        return object : DisposableObserver<Params.Output.Entities<User>>() {
            override fun onNext(output: Params.Output.Entities<User>) {
                onResult.readUserOnOutput(output)
            }
            override fun onComplete() {
                onResult.readUserOnSuccess()
            }
            override fun onError(error: Throwable) {
                onResult.readUserOnFailure(error)
            }
        }
    }

}
