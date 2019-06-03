package ru.vlabum.android.gb.kotlin.ui.splash

import ru.vlabum.android.gb.kotlin.ui.base.BaseViewState

class SplashViewState(authenticated: Boolean? = null, error: Throwable? = null) :
    BaseViewState<Boolean?>(authenticated, error)