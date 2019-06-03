package ru.vlabum.android.gb.kotlin.ui.splash

import ru.vlabum.android.gb.kotlin.data.NotesRepository
import ru.vlabum.android.gb.kotlin.error.NoAuthException
import ru.vlabum.android.gb.kotlin.ui.base.BaseViewModel

class SplashViewModel : BaseViewModel<Boolean?, SplashViewState>() {

    fun requestUser() {
        NotesRepository.getCurrentUser().observeForever {
            viewStateLiveData.value = if (it != null) {
                SplashViewState(authenticated = true)
            } else {
                SplashViewState(error = NoAuthException())
            }
        }
    }

}