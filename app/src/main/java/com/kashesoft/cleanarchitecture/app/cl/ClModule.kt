package com.kashesoft.cleanarchitecture.app.cl

import com.kashesoft.cleanarchitecture.app.bl.gateways.UserGateway
import com.kashesoft.cleanarchitecture.app.cl.repositories.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Copyright (c) 2017 Kashesoft. All rights reserved.
 * Created on 2017-10-22.
 */

@Module
class ClModule {

    @Provides
    @Singleton
    internal fun provideUserGateway(): UserGateway {
        return UserRepository()
    }

}
