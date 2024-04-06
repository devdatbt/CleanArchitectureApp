package com.example.apper.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.apper.R
import com.example.apper.databinding.FragmentAddBinding
import com.example.apper.databinding.FragmentUpdateBinding
import com.example.apper.ui.base.BaseFragment
import com.example.apper.ui.common.AppProgressBar
import com.example.apper.ui.event.EventNote
import com.example.apper.ui.viewmodel.NoteViewModel
import com.example.domain.model.Note
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class AddFragment : BaseFragment(R.layout.fragment_add) {

    @Inject
    lateinit var mNoteViewModel: NoteViewModel

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var appProgressBar: AppProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initEvents()
        handleObservers()
    }

    private fun initViews() {
        appProgressBar = AppProgressBar(binding.progressBar, 10 * 1000L, 1000L)
    }

    private fun handleObservers() {
        mNoteViewModel.statusMessage.observe(requireActivity()) {
            appProgressBar.endLoading()
            findNavController().popBackStack()
        }
    }

    private fun initEvents() {
        binding.tvSave.setOnClickListener {
            if (binding.edtContent.text.toString().isEmpty() || binding.edtTitle.text.toString()
                    .isEmpty()
            ) {
                Toast.makeText(
                    context,
                    context?.resources?.getString(R.string.txt_pls_enter_fields),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                appProgressBar.startLoading()
                val title = binding.edtTitle.text.toString()
                val content = binding.edtContent.text.toString()
                val time = System.currentTimeMillis()
                mNoteViewModel.onEventNote(EventNote.EventInsertNote(Note(title, content, time)))
            }
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        appProgressBar.endLoading()
        _binding = null
    }
}