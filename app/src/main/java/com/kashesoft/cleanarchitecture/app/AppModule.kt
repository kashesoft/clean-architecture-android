package com.kashesoft.cleanarchitecture.app

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Copyright (c) 2017 Kashesoft. All rights reserved.
 * Created on 2017-10-16.
 */

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

}
