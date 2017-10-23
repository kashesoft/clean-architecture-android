package com.kashesoft.cleanarchitecture.app

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Copyright (c) 2017 Kashesoft. All rights reserved.
 * Created on 2017-10-15.
 */

class App : DaggerApplication() {

    lateinit var appComponent: AppComponent

    override fun applicationInjector(): AndroidInjector<App> {
        appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build()
        return appComponent
    }

}
