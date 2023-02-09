package com.example.movieapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get

import com.example.movieapp.R
import com.example.movieapp.viewmodel.MovieDetailsViewModel


class DetailsFragment : Fragment() {
    private lateinit var viewModel: MovieDetailsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(requireActivity()).get(MovieDetailsViewModel::class.java)

        viewModel.getMovieDetails().observe(viewLifecycleOwner){}
        return inflater.inflate(R.layout.fragment_details, container, false)
    }


}