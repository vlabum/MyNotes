package ru.vlabum.android.gb.kotlin.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import ru.vlabum.android.gb.kotlin.R
import ru.vlabum.android.gb.kotlin.data.entity.Note
import ru.vlabum.android.gb.kotlin.ui.base.BaseActivity
import ru.vlabum.android.gb.kotlin.ui.note.NoteActivity

class MainActivity : BaseActivity<List<Note>?, MainViewState>() {

    companion object {
        fun start(context: Context) = Intent(context, MainActivity::class.java).run {
            context.startActivity(this)
        }
    }

    override fun renderData(data: List<Note>?) {
        data?.let {
            adapter.notes = it
        }
    }

    override val layoutRes: Int = R.layout.activity_main
    override val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
    lateinit var adapter: NotesRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        adapter = NotesRVAdapter {
            NoteActivity.start(this, it.id)
        }

        rv_notes.layoutManager = GridLayoutManager(this, 2)
        rv_notes.adapter = adapter

//        viewModel.viewState().observe(this, Observer<MainViewState> { viewState ->
//            viewState?.let { adapter.notes = viewState.notes }
//        })

        fab.setOnClickListener {
            NoteActivity.start(this)
        }

    }
}
