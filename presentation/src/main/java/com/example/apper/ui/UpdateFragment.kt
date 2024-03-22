package com.example.apper.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.apper.R
import com.example.apper.ui.base.BaseFragment
import com.example.apper.ui.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add.btn_back
import kotlinx.android.synthetic.main.fragment_update.*
import javax.inject.Inject

@AndroidEntryPoint
class UpdateFragment : BaseFragment(R.layout.fragment_update) {

    private val mArgs: UpdateFragmentArgs by navArgs()

    private var mTimeId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mTimeId = mArgs.timeId
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initEvents()
        handleObservers()
    }

    private fun handleObservers() {
        mNoteViewModel.statusNote.observe(viewLifecycleOwner) {
            it?.let { resource ->
                Toast.makeText(context, resource.data.toString(), Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        }
    }

    private fun initEvents() {
        btn_back.setOnClickListener {
            findNavController().popBackStack()
        }
        tvUpdate.setOnClickListener {
            if (edtUpdateContent.text.toString().isEmpty()
                || edtUpdateTitle.text.toString().isEmpty()
            ) {
                Toast.makeText(
                    context,
                    context?.resources?.getString(R.string.txt_pls_enter_fields),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                mNoteViewModel.updateNote(
                    edtUpdateTitle.text.toString(),
                    edtUpdateContent.text.toString(),
                    mTimeId
                )
            }
        }
    }
}