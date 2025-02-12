package com.example.retrofitapp.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitapp.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentOverviewBinding.inflate(inflater)
        val viewModel =  ViewModelProvider(this).get(OverviewViewModel::class.java)
        binding.overviewViewModel = viewModel
        binding.lifecycleOwner = this
        binding.photosGrid.adapter = PhotoGridAdapter()

        return binding.root
    }
}