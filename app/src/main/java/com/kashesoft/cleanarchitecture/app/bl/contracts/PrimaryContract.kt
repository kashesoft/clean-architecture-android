package com.kashesoft.cleanarchitecture.app.bl.contracts

import com.kashesoft.cleanarchitecture.app.bl.contracts.base.BaseContract

/**
 * Copyright (c) 2017 Kashesoft. All rights reserved.
 * Created on 2017-10-15.
 */

interface PrimaryContract {

    interface View : BaseContract.View {
        fun showProgress()
        fun hideProgress()
        fun refreshProgress(percent: Int)
    }

    interface Presenter : BaseContract.Presenter<PrimaryContract.View> {
        fun readUsers(userIds: List<Int>)
    }

}
