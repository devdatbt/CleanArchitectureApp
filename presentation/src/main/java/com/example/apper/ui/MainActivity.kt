package com.example.apper.ui

import android.os.Bundle
import com.example.apper.NoteApplication
import com.example.apper.R
import com.example.apper.ui.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}