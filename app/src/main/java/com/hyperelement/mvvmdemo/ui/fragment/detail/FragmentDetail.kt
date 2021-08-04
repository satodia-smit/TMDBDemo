package com.hyperelement.mvvmdemo.ui.fragment.detail


import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.arch.BaseFragment
import com.hyperelement.mvvmdemo.databinding.FragmentDetailBinding
import com.hyperelement.mvvmdemo.ui.fragment.FragmentMoviesVM


class FragmentDetail :
    BaseFragment<FragmentMoviesVM>(R.layout.fragment_detail, FragmentMoviesVM::class) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSpecificBinding<FragmentDetailBinding>()?.item = arguments?.let {
            FragmentDetailArgs.fromBundle(
                it
            ).movie
        }
    }
}