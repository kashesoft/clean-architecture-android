package com.kashesoft.cleanarchitecture.app.al.fragments.base

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.CallSuper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kashesoft.cleanarchitecture.app.bl.contracts.base.BaseContract
import com.kashesoft.cleanarchitecture.app.al.BaseViewModel
import com.kashesoft.cleanarchitecture.app.al.LAYOUT_NOT_DEFINED
import com.kashesoft.cleanarchitecture.app.al.Viewable
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Provider



/**
 * Copyright (c) 2017 Kashesoft. All rights reserved.
 * Created on 2017-10-15.
 */

abstract class BaseFragment<V : BaseContract.View, P : BaseContract.Presenter<V>> :
        DaggerFragment(), BaseContract.View {

    protected lateinit var presenter: P
    protected open lateinit var presenterProvider: Provider<P>

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val viewable = this::class.annotations.find { it is Viewable } as? Viewable
        val layoutResId = viewable!!.layout
        if (layoutResId != LAYOUT_NOT_DEFINED) {
            return inflater!!.inflate(layoutResId, container, false)
        }
        return null
    }

    @CallSuper
    @Suppress("UNCHECKED_CAST")
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onViewCreated(view, savedInstanceState)
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
    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachLifecycle(lifecycle)
        presenter.detachView()
    }

}
