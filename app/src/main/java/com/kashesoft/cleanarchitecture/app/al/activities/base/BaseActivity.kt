package com.kashesoft.cleanarchitecture.app.al.activities.base

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.CallSuper
import com.kashesoft.cleanarchitecture.app.al.BaseViewModel
import com.kashesoft.cleanarchitecture.app.al.LAYOUT_NOT_DEFINED
import com.kashesoft.cleanarchitecture.app.al.Viewable
import com.kashesoft.cleanarchitecture.app.bl.contracts.base.BaseContract
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Provider

/**
 * Copyright (c) 2017 Kashesoft. All rights reserved.
 * Created on 2017-10-15.
 */

abstract class BaseActivity<V : BaseContract.View, P : BaseContract.Presenter<V>> :
        DaggerAppCompatActivity(), BaseContract.View {

    protected lateinit var presenter: P
    protected open lateinit var presenterProvider: Provider<P>

    @CallSuper
    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        val viewable = this::class.annotations.find { it is Viewable } as? Viewable
        val layoutResId = viewable!!.layout
        if (layoutResId != LAYOUT_NOT_DEFINED) {
            setContentView(layoutResId)
        }
        val viewModel: BaseViewModel<V, P> = ViewModelProviders.of(this)
                .get(BaseViewModel::class.java) as BaseViewModel<V, P>
        var isPresenterCreated = false
        if (viewModel.getPresenter() == null) {
            viewModel.setPresenter(presenterProvider.get())
            isPresenterCreated = true
        }
        presenter = viewModel.getPresenter()!!
        presenter.attachLifecycle(lifecycle)
        presenter.attachView(this as V)
        if (isPresenterCreated) {
            presenter.onCreatePresenter()
        }
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        presenter.detachLifecycle(lifecycle)
        presenter.detachView()
    }

}
