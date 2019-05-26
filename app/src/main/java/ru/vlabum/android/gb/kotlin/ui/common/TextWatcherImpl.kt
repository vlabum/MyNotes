package ru.vlabum.android.gb.kotlin.ui.common

import android.text.Editable
import android.text.TextWatcher

class TextWatcherImpl(var action: () -> Unit) : TextWatcher {
    override fun afterTextChanged(s: Editable?) = action();
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
}