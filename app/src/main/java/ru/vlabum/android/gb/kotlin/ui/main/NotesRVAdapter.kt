package ru.vlabum.android.gb.kotlin.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_note.view.*
import ru.vlabum.android.gb.kotlin.R
import ru.vlabum.android.gb.kotlin.common.getColorInt
import ru.vlabum.android.gb.kotlin.data.entity.Note

class NotesRVAdapter(val onItemClick: ((Note) -> Unit)? = null) : RecyclerView.Adapter<NotesRVAdapter.ViewHolder>() {

    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        )

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(notes[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note) = with(note) {
            itemView.tv_title.text = title
            itemView.tv_text.text = text

            itemView.setBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    this.color.getColorInt(itemView.context)
                )
            )

            itemView.setOnClickListener {
                onItemClick?.invoke(note)
            }
        }
    }

}