package ru.vlabum.android.gb.kotlin.ui.note

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_note.*
import ru.vlabum.android.gb.kotlin.R
import ru.vlabum.android.gb.kotlin.data.entity.Note
import ru.vlabum.android.gb.kotlin.ui.common.TextWatcherImpl
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : AppCompatActivity() {

    companion object {

        private val EXTRA_NOTE = NoteActivity::class.java.name + "extra.NOTE"
        private const val DATE_TIME_FORMAT = "dd.MM.yy HH:mm"

        fun start(context: Context, note: Note? = null) {
            val intent = Intent(context, NoteActivity::class.java).apply {
                note?.let { putExtra(EXTRA_NOTE, note) }
            }
            context.startActivity(intent)
        }

    }

    private var note: Note? = null
    lateinit var viewModel: NoteViewModel
    private val textWatcher = TextWatcherImpl { saveNote() }

//    val textWatcher = object : TextWatcher {
//        override fun afterTextChanged(s: Editable?) = saveNote();
//        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
//        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        note = intent.getParcelableExtra(EXTRA_NOTE)

        supportActionBar?.title = note?.let {
            SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(it.lastChanged)
        } ?: let {
            getString(R.string.newNote)
        }

        initView()
    }

    private fun initView() {
        note?.let { note ->
            et_title.setText(note.title)
            et_text.setText(note.text)
            val color = when (note.color) {
                Note.Color.WHITE -> R.color.white
                Note.Color.YELOW -> R.color.yellow
                Note.Color.GREEN -> R.color.green
                Note.Color.BLUE -> R.color.blue
                Note.Color.RED -> R.color.red
                Note.Color.VIOLET -> R.color.violet
                Note.Color.PINK -> R.color.pink
            }

            toolbar.setBackgroundColor(ResourcesCompat.getColor(resources, color, null))
        }

        et_title.addTextChangedListener(textWatcher)
        et_text.addTextChangedListener(textWatcher)
    }

    private fun saveNote() {
        if (et_title.text == null || et_title.text!!.length < 3) return
        note = note?.copy(
            title = et_title.text.toString(),
            text = et_text.text.toString(),
            lastChanged = Date()
        ) ?: createNewNote()

        if (note != null) viewModel.save(note!!)
    }

    fun createNewNote() = Note(UUID.randomUUID().toString(), et_title.text.toString(), et_text.toString())

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

}