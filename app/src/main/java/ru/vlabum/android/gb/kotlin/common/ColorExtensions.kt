package ru.vlabum.android.gb.kotlin.common

import android.content.Context
import androidx.core.content.ContextCompat
import ru.vlabum.android.gb.kotlin.R
import ru.vlabum.android.gb.kotlin.data.entity.Note

fun Note.Color.getColorInt(context: Context): Int =
    ContextCompat.getColor(
        context,
        when (this) {
            Note.Color.WHITE -> R.color.white
            Note.Color.YELLOW -> R.color.yellow
            Note.Color.GREEN -> R.color.green
            Note.Color.BLUE -> R.color.blue
            Note.Color.RED -> R.color.red
            Note.Color.VIOLET -> R.color.violet
            Note.Color.PINK -> R.color.pink
        }
    )