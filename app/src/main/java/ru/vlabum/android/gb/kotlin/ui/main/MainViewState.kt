package ru.vlabum.android.gb.kotlin.ui.main

import ru.vlabum.android.gb.kotlin.data.entity.Note
import ru.vlabum.android.gb.kotlin.ui.base.BaseViewState

class MainViewState(val notes: List<Note>? = null, error: Throwable? = null) : BaseViewState<List<Note>?>(notes, error)
