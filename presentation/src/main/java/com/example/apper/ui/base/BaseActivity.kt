package com.example.apper.ui.base

import androidx.appcompat.app.AppCompatActivity
import com.example.apper.NoteApplication

open class BaseActivity : AppCompatActivity() {
    val appComponent by lazy {
        (application as NoteApplication).appComponent
    }
}