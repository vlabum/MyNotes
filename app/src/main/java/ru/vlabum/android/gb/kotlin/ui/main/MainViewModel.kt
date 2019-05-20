package ru.vlabum.android.gb.kotlin.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.vlabum.android.gb.kotlin.data.NotesRepository

class MainViewModel : ViewModel() {

    private val viewStateLiveData: MutableLiveData<MainViewState> = MutableLiveData()

    init {
        viewStateLiveData.value = MainViewState(NotesRepository.notes)
    }

    fun viewState(): LiveData<MainViewState> = viewStateLiveData

}