package ru.vlabum.android.gb.kotlin.ui.main

import androidx.lifecycle.Observer
import ru.vlabum.android.gb.kotlin.data.NotesRepository
import ru.vlabum.android.gb.kotlin.data.entity.Note
import ru.vlabum.android.gb.kotlin.model.NoteResult
import ru.vlabum.android.gb.kotlin.ui.base.BaseViewModel

class MainViewModel : BaseViewModel<List<Note>?, MainViewState>() {

    private val notesObserver = object : Observer<NoteResult> {
        override fun onChanged(t: NoteResult?) {
            if (t == null) return

            when (t) {
                is NoteResult.Success<*> -> {
                    viewStateLiveData.value = MainViewState(notes = t.data as? List<Note>)
                }
                is NoteResult.Error -> {
                    viewStateLiveData.value = MainViewState(error = t.error)
                }
            }
        }
    }

    private val repositoryNotes = NotesRepository.getNotes()

    init {
        viewStateLiveData.value = MainViewState()
        repositoryNotes.observeForever(notesObserver)
    }

    override fun onCleared() {
        repositoryNotes.removeObserver(notesObserver)
    }

}