package ru.vlabum.android.gb.kotlin.ui.note

import androidx.lifecycle.ViewModel
import ru.vlabum.android.gb.kotlin.data.NotesRepository
import ru.vlabum.android.gb.kotlin.data.entity.Note

class NoteViewModel : ViewModel() {

    private var pendingNote: Note? = null

    fun save(note: Note) {
        pendingNote = note
    }

    override fun onCleared() {
        pendingNote?.let { note ->
            NotesRepository.saveNote(note)
        }
    }
}