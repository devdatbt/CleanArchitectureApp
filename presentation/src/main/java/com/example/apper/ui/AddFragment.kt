package com.example.apper.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.apper.R
import com.example.apper.ui.base.BaseFragment
import com.example.apper.utils.Status
import com.example.domain.model.Note
import kotlinx.android.synthetic.main.fragment_add.*

class AddFragment : BaseFragment(R.layout.fragment_add) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEvents()
        handleObservers()
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
               // mNoteViewModel.insertNote(Note(title, content, time))
            }
        }

        btn_back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun handleObservers() {
        mNoteViewModel.statusNote.observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.LOADING -> {

                    }
                    Status.SUCCESS -> {
                        Toast.makeText(context, resource.data.toString(), Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    }
                    Status.ERROR -> {

                    }
                }
            }
        }
    }

}