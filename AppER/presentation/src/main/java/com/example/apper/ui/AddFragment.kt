package com.example.apper.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.apper.R
import com.example.apper.databinding.FragmentAddBinding
import com.example.apper.ui.base.BaseFragment
import com.example.apper.ui.common.AppProgressBar
import com.example.apper.ui.event.EventNote
import com.example.domain.model.Note
import kotlinx.coroutines.launch

class AddFragment : BaseFragment(R.layout.fragment_add) {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private var appProgressBar: AppProgressBar? = null
    private val mArgs: AddFragmentArgs by navArgs()
    private var mNote: Note? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNote = mArgs.note
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initEvents()
        handleObservers()
    }

    private fun initViews() {
        appProgressBar = AppProgressBar(
            binding.progressBar,
            AppProgressBar.DEFAULT_COUNT_TIME,
            AppProgressBar.DEFAULT_TIME_INTERVAL
        )
        binding.apply {
            mNote?.let {
                this.tvSave.text = context?.resources?.getString(R.string.txt_update)
            }
        }
    }

    private fun handleObservers() {
        mNoteViewModel.statusMessage.observe(requireActivity()) {
            appProgressBar?.endLoading()
            findNavController().popBackStack()
        }
    }

    private fun initEvents() {
        binding.apply {
            tvSave.setOnClickListener {
                val title = edtTitle.text?.toString().orEmpty()
                val content = edtContent.text?.toString().orEmpty()
                if (content.isEmpty() || title.isEmpty()) {
                    Toast.makeText(
                        context,
                        context?.resources?.getString(R.string.txt_pls_enter_fields),
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    appProgressBar?.startLoading()
                    mNote?.let { note ->
                        // Update note
                        mNoteViewModel.onEventNote(
                            EventNote.EventUpdateNote(title, content, note.timestamp)
                        )
                    } ?: run {
                        // Insert new note
                        val time = System.currentTimeMillis()
                        lifecycleScope.launch {
                            val currentUser = mNoteViewModel.getCurrentUser()
                            mNoteViewModel.onEventNote(
                                EventNote.EventInsertNote(
                                    Note(
                                        title,
                                        content,
                                        time,
                                        currentUser
                                    )
                                )
                            )
                        }
                    }
                }
            }
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        appProgressBar?.endLoading()
        appProgressBar = null
        _binding = null
    }
}