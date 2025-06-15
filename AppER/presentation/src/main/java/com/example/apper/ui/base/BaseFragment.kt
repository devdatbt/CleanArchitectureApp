package com.example.apper.ui.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.apper.ui.viewmodel.LoginViewModel
import com.example.apper.ui.viewmodel.NoteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

open class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {
    val mNoteViewModel: NoteViewModel by viewModel()
    val mLoginViewModel: LoginViewModel by viewModel()
}