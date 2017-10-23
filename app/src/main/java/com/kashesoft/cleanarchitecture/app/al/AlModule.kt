package com.kashesoft.cleanarchitecture.app.al

import com.kashesoft.cleanarchitecture.app.AppModule
import com.kashesoft.cleanarchitecture.app.al.activities.PrimaryActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Copyright (c) 2017 Kashesoft. All rights reserved.
 * Created on 2017-10-16.
 */

@Module
abstract class AlModule {

    @ContributesAndroidInjector(modules = arrayOf(AppModule::class))
    internal abstract fun bindPrimaryActivity(): PrimaryActivity

}
