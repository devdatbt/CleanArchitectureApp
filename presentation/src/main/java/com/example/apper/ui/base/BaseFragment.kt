package com.example.apper.ui.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.apper.MainActivity

open class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {
    protected val appComponent by lazy { (requireActivity() as MainActivity).appComponent }
}