package ru.vlabum.android.gb.kotlin.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Note(
    val id: String = "",
    val title: String = "",
    val text: String = "",
    val color: Color = Note.Color.WHITE,
    val lastChanged: Date = Date()
) : Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Note
        if (other.id != id) return false
        return true
    }

    enum class Color {
        WHITE,
        YELOW,
        GREEN,
        BLUE,
        RED,
        VIOLET,
        PINK
    }

}