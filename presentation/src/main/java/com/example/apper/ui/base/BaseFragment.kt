package com.example.apper.ui.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.apper.MainActivity
import com.example.apper.ui.viewmodel.LoginViewModel
import com.example.apper.ui.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

open class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {
    val mNoteViewModel: NoteViewModel by viewModels()
    val mLoginViewModel: LoginViewModel by viewModels()
}