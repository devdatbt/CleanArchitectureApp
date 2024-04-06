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
import com.example.apper.databinding.FragmentUpdateBinding
import com.example.apper.ui.base.BaseFragment
import com.example.apper.ui.event.EventNote
import com.example.apper.ui.viewmodel.NoteViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class UpdateFragment : BaseFragment(R.layout.fragment_update) {

    @Inject
    lateinit var mNoteViewModel: NoteViewModel

    private val mArgs: UpdateFragmentArgs by navArgs()

    private var mTimeId: Long = 0

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

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
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.tvUpdate.setOnClickListener {
            if (binding.edtUpdateContent.text.toString().isEmpty()
                || binding.edtUpdateTitle.text.toString().isEmpty()
            ) {
                Toast.makeText(
                    context,
                    context?.resources?.getString(R.string.txt_pls_enter_fields),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                mNoteViewModel.onEventNote(
                    EventNote.EventUpdateNote(
                        binding.edtUpdateTitle.text.toString(),
                        binding.edtUpdateContent.text.toString(),
                        mTimeId
                    )
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}