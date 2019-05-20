package ru.vlabum.android.gb.kotlin.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import ru.vlabum.android.gb.kotlin.R

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var adapter: NotesRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        adapter = NotesRVAdapter()

        rv_notes.layoutManager = GridLayoutManager(this, 2)
        rv_notes.adapter = adapter

        viewModel.viewState().observe(this, Observer { viewState ->
            viewState?.let { adapter.notes = viewState.notes }
        })

    }
}