package com.kashesoft.cleanarchitecture.app.bl.presenters.base

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.os.Bundle
import android.support.annotation.CallSuper
import android.util.Log
import com.kashesoft.cleanarchitecture.app.bl.contracts.base.BaseContract
import com.kashesoft.cleanarchitecture.app.bl.interactors.base.BaseInteractor

/**
 * Copyright (c) 2017 Kashesoft. All rights reserved.
 * Created on 2017-10-15.
 */

abstract class BasePresenter<V : BaseContract.View> : LifecycleObserver, BaseContract.Presenter<V> {

    private val tag: String
        get() = this::class.simpleName!!

    private val trail = ":::::::::::::::"

    final override val stateBundle: Bundle = Bundle()
    override final var view: V? = null
        private set

    override val isViewAttached: Boolean
        get() = view != null

    private val interactors = mutableListOf<BaseInteractor<*, *, *>>()

    protected abstract fun provideInteractors(): Array<BaseInteractor<*, *, *>>

    override fun attachLifecycle(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
    }

    override fun detachLifecycle(lifecycle: Lifecycle) {
        lifecycle.removeObserver(this)
    }

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    @CallSuper
    override fun onCreatePresenter() {
        logTrailed("onCreatePresenter")
        interactors.addAll(provideInteractors())
    }

    @CallSuper
    override fun onDestroyPresenter() {
        logTrailed("onDestroyPresenter")
        if (!stateBundle.isEmpty) {
            stateBundle.clear()
        }
        for (interactor in interactors) {
            interactor.dispose()
        }
        interactors.clear()
    }

    @CallSuper
    protected open fun onCreateView() {
        logTrailed("onCreateView")
    }

    @CallSuper
    protected open fun onStartView() {
        logTrailed("onStartView")
    }

    @CallSuper
    protected open fun onResumeView() {
        logTrailed("onResumeView")
    }

    @CallSuper
    protected open fun onPauseView() {
        logTrailed("onPauseView")
    }

    @CallSuper
    protected open fun onStopView() {
        logTrailed("onStopView")
    }

    @CallSuper
    protected open fun onDestroyView() {
        logTrailed("onDestroyView")
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_CREATE)
    private fun onCreateLifecycleEvent() {
        onCreateView()
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    private fun onStartLifecycleEvent() {
        onStartView()
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_RESUME)
    private fun onResumeLifecycleEvent() {
        onResumeView()
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_PAUSE)
    private fun onPauseLifecycleEvent() {
        onPauseView()
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_STOP)
    private fun onStopLifecycleEvent() {
        onStopView()
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_DESTROY)
    private fun onDestroyLifecycleEvent() {
        onDestroyView()
    }

    protected fun log(message: String) {
        Log.d(tag, message)
    }

    private fun logTrailed(message: String) {
        Log.d(tag, "$trail$message$trail")
    }

}
