package ru.vlabum.android.gb.kotlin.data

import ru.vlabum.android.gb.kotlin.data.entity.Note
import ru.vlabum.android.gb.kotlin.data.provider.FireStoreDataPrivider
import ru.vlabum.android.gb.kotlin.data.provider.RemoteDataProvider

object NotesRepository {


    private val remoteProvider: RemoteDataProvider = FireStoreDataPrivider()

    fun getNotes() = remoteProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = remoteProvider.saveNote(note)
    fun getNoteById(id: String) = remoteProvider.getNoteById(id)

    /*
    private val notesLiveData = MutableLiveData<List<Note>>()

    var _notes = mutableListOf(
        Note(
            UUID.randomUUID().toString(),
            "Первая заметка",
            "Текст первой заметки. Не очень длинный, но интересный",
            Note.Color.WHITE
        ),
        Note(
            UUID.randomUUID().toString(),
            "Вторая заметка",
            "Текст второй заметки. Не очень длинный, но интересный",
            Note.Color.YELOW
        ),
        Note(
            UUID.randomUUID().toString(),
            "Третья заметка",
            "Текст третьей заметки. Не очень длинный, но интересный",
            Note.Color.GREEN
        ),
        Note(
            UUID.randomUUID().toString(),
            "Четвертая заметка",
            "Текст четвертой заметки. Не очень длинный, но интересный",
            Note.Color.BLUE
        ),
        Note(
            UUID.randomUUID().toString(),
            "Пятая заметка",
            "Текст пятой заметки. Не очень длинный, но интересный",
            Note.Color.RED
        ),
        Note(
            UUID.randomUUID().toString(),
            "Шестая заметка",
            "Текст шестой заметки. Не очень длинный, но интересный",
            Note.Color.VIOLET
        )
    )

    init {
        notesLiveData.value = _notes
    }

    fun getNotesLiveData(): LiveData<List<Note>> {
        return notesLiveData
    }

    fun saveNote(note: Note) {
        addOrReplace(note)
        notesLiveData.value = _notes
    }

    private fun addOrReplace(note: Note) {
        for (i in 0 until _notes.size) {
            if (_notes[i] == note) {
                _notes[i] = note
            }
        }
    }

    val notes: List<Note>
        get() {
            return _notes
        }
        */
}

