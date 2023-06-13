package com.example.apper.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apper.R
import com.example.apper.adapter.NoteAdapter
import com.example.apper.ui.base.BaseFragment
import com.example.apper.ui.viewmodel.NoteViewModel
import com.example.apper.utils.Status
import com.example.apper.utils.convertCurrency
import com.example.domain.model.Note
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class HomeFragment : BaseFragment(R.layout.fragment_home) {

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

    private fun initViews() {
        mAdapter = NoteAdapter(onItemClick, onItemDelete)
        rvNoteHome.setHasFixedSize(true)
        rvNoteHome.layoutManager = LinearLayoutManager(context)
        rvNoteHome.adapter = mAdapter
    }

    private fun initGetData() {
        mNoteViewModel.getCurrencyFromServer()
        mNoteViewModel.getSearchNoteLists()
    }

    private val onItemClick: (Note) -> Unit = {
        val action = HomeFragmentDirections.actionHomeFragmentToUpdateFragment(it.timestamp)
        findNavController().navigate(action)
    }

    private val onItemDelete: (Note) -> Unit = {
        mNoteViewModel.deleteNote(it)
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
                if (s.isEmpty()) mNoteViewModel.getSearchNoteLists()
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                mNoteViewModel.getSearchNoteLists(s.toString())
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun handleObservers() {

        mNoteViewModel.statusGetCurrency.observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.LOADING -> {
                        swipeRefreshLayout.isRefreshing = true
                    }
                    Status.SUCCESS -> {
                        swipeRefreshLayout.isRefreshing = false
                        tvCurrencyVietnam.text =
                            it.data?.usdVnd?.convertCurrency() + context?.resources?.getString(R.string.txt_VND)
                    }
                    Status.ERROR -> {
                        swipeRefreshLayout.isRefreshing = false
                        showToast(context?.resources?.getString(R.string.txt_error_data_server) + resource.message)
                    }
                }
            }
        }

        mNoteViewModel.statusGetNote.observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.LOADING -> {
                        swipeRefreshLayout.isRefreshing = true
                    }
                    Status.SUCCESS -> {
                        swipeRefreshLayout.isRefreshing = false
                        if (resource.data!!.isEmpty()) {
                            rvNoteHome.visibility = View.GONE
                            tvNoteEmpty.visibility = View.VISIBLE
                        } else {
                            rvNoteHome.visibility = View.VISIBLE
                            tvNoteEmpty.visibility = View.GONE
                            mAdapter.submitList(resource.data)
                        }
                    }
                    Status.ERROR -> {
                        swipeRefreshLayout.isRefreshing = false
                        showToast(context?.resources?.getString(R.string.txt_error_data_local) + resource.message)
                    }
                }
            }
        }

        mNoteViewModel.statusNote.observe(viewLifecycleOwner) {
            it?.let {
                when (it.status) {
                    Status.LOADING -> {

                    }
                    Status.SUCCESS -> {
                        showToast(it.data.toString())
                    }
                    Status.ERROR -> {

                    }
                }
            }
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

}