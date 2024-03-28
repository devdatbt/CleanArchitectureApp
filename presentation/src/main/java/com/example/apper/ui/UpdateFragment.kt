package com.example.apper.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.apper.R
import com.example.apper.ui.base.BaseFragment
import com.example.apper.ui.event.EventNote
import com.example.apper.ui.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_add.btn_back
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.coroutines.launch
import javax.inject.Inject


class UpdateFragment : BaseFragment(R.layout.fragment_update) {

    @Inject
    lateinit var mNoteViewModel: NoteViewModel

    private val mArgs: UpdateFragmentArgs by navArgs()

    private var mTimeId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        mTimeId = mArgs.timeId
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
                mNoteViewModel.onEventNote(
                    EventNote.EventUpdateNote(
                        edtUpdateTitle.text.toString(),
                        edtUpdateContent.text.toString(),
                        mTimeId
                    )
                )
            }
        }
    }
}