package com.kashesoft.cleanarchitecture.app.bl.interactors.base

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Copyright (c) 2017 Kashesoft. All rights reserved.
 * Created on 2017-10-15.
 */

abstract class BaseInteractor<Input, Output, OnResult> internal constructor(
        private val ioScheduler: Scheduler = Schedulers.io(),
        private val uiScheduler: Scheduler = AndroidSchedulers.mainThread()
) {

    private val subscription: CompositeDisposable = CompositeDisposable()

    protected abstract fun buildObservable(input: Input): Observable<Output>

    protected abstract fun buildObserver(onResult: OnResult): DisposableObserver<Output>

    fun execute(input: Input, onResult: OnResult) {
        val observable = buildObservable(input)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
        val observer = buildObserver(onResult)
        addObserver(observable.subscribeWith(observer))
    }

    fun dispose() {
        if (!subscription.isDisposed) {
            subscription.dispose()
        }
    }

    private fun addObserver(observer: Disposable) {
        subscription.add(observer)
    }

}
