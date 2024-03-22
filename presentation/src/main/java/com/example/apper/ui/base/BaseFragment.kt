package com.example.apper.ui.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.apper.ui.MainActivity
import com.example.apper.ui.viewmodel.NoteViewModel

open class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {
     val mNoteViewModel: NoteViewModel by viewModels()
}