package com.kashesoft.cleanarchitecture.app.al

import android.arch.lifecycle.ViewModel
import com.kashesoft.cleanarchitecture.app.bl.contracts.base.BaseContract

/**
 * Copyright (c) 2017 Kashesoft. All rights reserved.
 * Created on 2017-10-15.
 */

class BaseViewModel<V : BaseContract.View, P : BaseContract.Presenter<V>> : ViewModel() {

    private var presenter: P? = null

    internal fun setPresenter(presenter: P) {
        if (this.presenter == null) {
            this.presenter = presenter
        }
    }

    internal fun getPresenter(): P? {
        return this.presenter
    }

    override fun onCleared() {
        super.onCleared()
        presenter!!.onDestroyPresenter()
        presenter = null
    }
}
