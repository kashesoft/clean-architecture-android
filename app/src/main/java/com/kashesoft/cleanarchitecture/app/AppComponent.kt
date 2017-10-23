package com.kashesoft.cleanarchitecture.app

import android.app.Application
import com.kashesoft.cleanarchitecture.app.al.AlModule
import com.kashesoft.cleanarchitecture.app.bl.BlModule
import com.kashesoft.cleanarchitecture.app.cl.ClModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Copyright (c) 2017 Kashesoft. All rights reserved.
 * Created on 2017-10-16.
 */

@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        AppModule::class,
        AlModule::class,
        BlModule::class,
        ClModule::class
))
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

}
