package com.kashesoft.cleanarchitecture.app.al

/**
 * Copyright (c) 2017 Kashesoft. All rights reserved.
 * Created on 2017-10-22.
 */

const val LAYOUT_NOT_DEFINED = -1

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Viewable(val layout: Int = LAYOUT_NOT_DEFINED)
