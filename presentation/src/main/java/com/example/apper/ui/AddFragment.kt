package com.example.apper.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.apper.R
import com.example.apper.ui.base.BaseFragment
import com.example.apper.ui.event.EventNote
import com.example.apper.ui.viewmodel.NoteViewModel
import com.example.domain.model.Note
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddFragment : BaseFragment(R.layout.fragment_add) {

    @Inject
    lateinit var mNoteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEvents()
        handleObservers()
    }

    private fun handleObservers() {
        lifecycleScope.launch {
            mNoteViewModel.statusMessage.observe(requireActivity()) {
                Toast.makeText(context, "${it.getContent()}", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        }
    }

    private fun initEvents() {
        tvSave.setOnClickListener {
            if (edtContent.text.toString().isEmpty() || edtTitle.text.toString().isEmpty()) {
                Toast.makeText(
                    context,
                    context?.resources?.getString(R.string.txt_pls_enter_fields),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val title = edtTitle.text.toString()
                val content = edtContent.text.toString()
                val time = System.currentTimeMillis()
                mNoteViewModel.onEventNote(EventNote.EventInsertNote(Note(title, content, time)))
            }
        }

        btn_back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}