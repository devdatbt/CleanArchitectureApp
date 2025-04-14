package com.example.apper.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.apper.databinding.FragmentSplashBinding
import com.example.apper.ui.base.BaseFragment


class SplashFragment : BaseFragment(com.example.apper.R.layout.fragment_splash) {

    private var dots = arrayOfNulls<TextView>(4)
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun initViews() {
    }

//    fun setUpindicator(position: Int) {
//        binding.indicatorLayout.removeAllViews()
//
//        for (i in 0 until dots.size) {
//            dots.[i] = TextView(requireContext())
//            dots.[i].setText(Html.fromHtml("&#8226"))
//            dots.get(i).setTextSize(35)
//            dots.get(i).setTextColor(
//                resources.getColor(
//                    R.color.inactive,
//                    getApplicationContext().getTheme()
//                )
//            )
//            binding.indicatorLayout.addView(dots[i])
//        }
//
//        dots.get(position)
//            .setTextColor(resources.getColor(R.color.active, getApplicationContext().getTheme()))
//    }

}