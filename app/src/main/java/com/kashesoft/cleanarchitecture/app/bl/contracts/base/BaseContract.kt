package com.kashesoft.cleanarchitecture.app.bl.contracts.base

import android.arch.lifecycle.Lifecycle
import android.os.Bundle

/**
 * Copyright (c) 2017 Kashesoft. All rights reserved.
 * Created on 2017-10-15.
 */

interface BaseContract {

    interface View

    interface Presenter<V : View> {

        val stateBundle: Bundle

        val view: V?

        val isViewAttached: Boolean

        fun attachLifecycle(lifecycle: Lifecycle)

        fun detachLifecycle(lifecycle: Lifecycle)

        fun attachView(view: V)

        fun detachView()

        fun onCreatePresenter()

        fun onDestroyPresenter()
    }

}
