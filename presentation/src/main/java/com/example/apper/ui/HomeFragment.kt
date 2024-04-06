package com.example.apper.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apper.R
import com.example.apper.adapter.NoteAdapter
import com.example.apper.databinding.FragmentHomeBinding
import com.example.apper.ui.base.BaseFragment
import com.example.apper.ui.event.EventNote
import com.example.apper.ui.viewmodel.NoteViewModel
import com.example.domain.model.Note
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private var listFilter: List<Note>? = null

    @Inject
    lateinit var mNoteViewModel: NoteViewModel

    private lateinit var mAdapter: NoteAdapter

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initEvents()
        initGetData()
        handleObservers()
    }

    private fun handleObservers() {
        lifecycleScope.launch {
            mNoteViewModel.statusMessage.observe(requireActivity()) {
                it.getContent()?.let { content ->
                    Toast.makeText(context, "$content", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initViews() {
        mAdapter = NoteAdapter(onItemClick, onItemDelete)
        binding.rvNoteHome.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    private fun initGetData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mNoteViewModel.listNoteStateIn.collect {
                    listFilter = it
                    binding.swipeRefreshLayout.isRefreshing = false
                    if (it.isEmpty()) {
                        binding.rvNoteHome.visibility = View.GONE
                        binding.tvNoteEmpty.visibility = View.VISIBLE
                    } else {
                        binding.rvNoteHome.visibility = View.VISIBLE
                        binding.tvNoteEmpty.visibility = View.GONE
                        mAdapter.submitList(it)
                    }
                }
            }
        }
    }

    private val onItemClick: (Note) -> Unit = {
        val action = HomeFragmentDirections.actionHomeFragmentToUpdateFragment(it.timestamp)
        findNavController().navigate(action)
    }

    private val onItemDelete: (Note) -> Unit = {
        mNoteViewModel.onEventNote(EventNote.EventDeleteNote(it))
    }

    private fun initEvents() {

        binding.btnLogout.setOnClickListener {
            mNoteViewModel.signOut()
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
        }

        binding.btnNavAddNote.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddFragment()
            findNavController().navigate(action)
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            initGetData()
        }

        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int, count: Int
            ) {
                if (listFilter != null) {
                    val listFiltered =
                        mNoteViewModel.searchListNoteWith(s.toString(), listFilter = listFilter!!)
                    binding.rvNoteHome.removeAllViews()
                    mAdapter.apply {
                        submitList(listFiltered)
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}