package com.kashesoft.cleanarchitecture.app.bl

import com.kashesoft.cleanarchitecture.app.bl.contracts.PrimaryContract
import com.kashesoft.cleanarchitecture.app.bl.gateways.UserGateway
import com.kashesoft.cleanarchitecture.app.bl.interactors.ReadUserInteractor
import com.kashesoft.cleanarchitecture.app.bl.presenters.PrimaryPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Copyright (c) 2017 Kashesoft. All rights reserved.
 * Created on 2017-10-22.
 */

@Module
class BlModule {

    @Provides
    internal fun providePrimaryContract_Presenter(interactor: ReadUserInteractor): PrimaryContract.Presenter {
        return PrimaryPresenter(interactor)
    }

    @Provides
    @Singleton
    internal fun provideReadUserInteractor(userGateway: UserGateway): ReadUserInteractor {
        return ReadUserInteractor(userGateway)
    }

}
