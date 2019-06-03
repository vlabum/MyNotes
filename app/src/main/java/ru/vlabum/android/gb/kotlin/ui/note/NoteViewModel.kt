package ru.vlabum.android.gb.kotlin.ui.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import ru.vlabum.android.gb.kotlin.data.NotesRepository
import ru.vlabum.android.gb.kotlin.data.entity.Note
import ru.vlabum.android.gb.kotlin.model.NoteResult
import ru.vlabum.android.gb.kotlin.ui.base.BaseViewModel

class NoteViewModel : BaseViewModel<Note?, NoteViewState>() {

    init {
        viewStateLiveData.value = NoteViewState()
    }

    private val noteObserver = object : Observer<NoteResult> {
        override fun onChanged(t: NoteResult?) {
            if (t == null) return

            when (t) {
                is NoteResult.Success<*> -> {
                    viewStateLiveData.value = NoteViewState(note = t.data as? Note)
                }
                is NoteResult.Error -> {
                    viewStateLiveData.value = NoteViewState(error = t.error)
                }
            }
        }
    }

    private var repositoryNote: LiveData<NoteResult>? = null

    private var pendingNote: Note? = null

    fun save(note: Note) {
        pendingNote = note
    }

    fun loadNote(noteId: String) {
        repositoryNote = NotesRepository.getNoteById(noteId)
        repositoryNote!!.observeForever(noteObserver)
    }

    override fun onCleared() {
        pendingNote?.let { note ->
            NotesRepository.saveNote(note)
        }
        repositoryNote?.let { it.removeObserver(noteObserver) }
    }

}