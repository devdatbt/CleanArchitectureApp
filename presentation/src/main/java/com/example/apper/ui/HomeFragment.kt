package com.example.apper.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apper.R
import com.example.apper.adapter.NoteAdapter
import com.example.apper.ui.base.BaseFragment
import com.example.apper.ui.event.EventNote
import com.example.apper.ui.viewmodel.NoteViewModel
import com.example.domain.model.Note
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private var listFilter: List<Note>? = null

    @Inject
    lateinit var mNoteViewModel: NoteViewModel

    private lateinit var mAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
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
                Toast.makeText(context, "${it.getContent()}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initViews() {
        mAdapter = NoteAdapter(onItemClick, onItemDelete)
        rvNoteHome.setHasFixedSize(true)
        rvNoteHome.layoutManager = LinearLayoutManager(context)
        rvNoteHome.adapter = mAdapter
    }

    private fun initGetData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mNoteViewModel.listNoteStateIn.collect {
                    listFilter = it
                    if (it.isEmpty()) {
                        rvNoteHome.visibility = View.GONE
                        tvNoteEmpty.visibility = View.VISIBLE
                    } else {
                        rvNoteHome.visibility = View.VISIBLE
                        tvNoteEmpty.visibility = View.GONE
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

        btnNavAddNote.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddFragment()
            findNavController().navigate(action)
        }

        swipeRefreshLayout.setOnRefreshListener {
            initGetData()
        }

        edtSearch.addTextChangedListener(object : TextWatcher {
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
                    rvNoteHome.removeAllViews()
                    mAdapter.apply {
                        submitList(listFiltered)
                    }
                }
            }
        })
    }
}