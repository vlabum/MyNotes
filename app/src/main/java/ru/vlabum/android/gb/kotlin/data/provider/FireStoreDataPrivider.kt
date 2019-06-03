package ru.vlabum.android.gb.kotlin.data.provider

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import ru.vlabum.android.gb.kotlin.data.entity.Note
import ru.vlabum.android.gb.kotlin.data.entity.User
import ru.vlabum.android.gb.kotlin.error.NoAuthException
import ru.vlabum.android.gb.kotlin.model.NoteResult

class FireStoreDataPrivider : RemoteDataProvider {

    companion object {
        private const val USERS_COLLECTION = "users"
        private const val NOTES_COLLECTION = "notes"
    }

    private val store by lazy { FirebaseFirestore.getInstance() }
    private val notesReference by lazy { store.collection(NOTES_COLLECTION) }
    private val currentUser
        get() = FirebaseAuth.getInstance().currentUser

    override fun getCurrentUser(): LiveData<User?> = MutableLiveData<User?>().apply {
        value = currentUser?.let {
            User(it.displayName ?: "", it.email ?: "")
        }
    }

    private fun getUserCollection() = currentUser?.let {
        store.collection(USERS_COLLECTION).document(it.uid).collection(NOTES_COLLECTION)
    } ?: throw NoAuthException()

    override fun subscribeToAllNotes() = MutableLiveData<NoteResult>().apply {
        try {
            getUserCollection()
                .addSnapshotListener { snapshot, e ->
                    value = e?.let {
                        throw it
                    } ?: snapshot?.let {
                        val notes = it.documents.map { docs -> docs.toObject(Note::class.java) }
                        NoteResult.Success(notes)
                    }
                }
        } catch (e: Throwable) {
            value = NoteResult.Error(e)
        }
    }

    override fun getNoteById(id: String) = MutableLiveData<NoteResult>().apply {
        try {
            getUserCollection()
                .document(id)
                .get()
                .addOnSuccessListener { value = NoteResult.Success(it.toObject(Note::class.java)) }
                .addOnFailureListener { value = NoteResult.Error(it) }
        } catch (e: Throwable) {
            value = NoteResult.Error(e)
        }
    }

    override fun saveNote(note: Note) = MutableLiveData<NoteResult>().apply {
        try {
            getUserCollection()
                .document(note.id)
                .set(note)
                .addOnSuccessListener { value = NoteResult.Success(note) }
                .addOnFailureListener { value = NoteResult.Error(it) }
        } catch (e: Throwable) {
            value = NoteResult.Error(e)
        }
    }

}