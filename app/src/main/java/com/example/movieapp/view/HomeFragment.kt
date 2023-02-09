package com.example.movieapp.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapter.NowShowingAdapter
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.viewmodel.NowShowingMovieViewModel



class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel : NowShowingMovieViewModel
    private lateinit var showingAdapter : NowShowingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        //binding.toolbar. = "FilmKu"

        showingAdapter = NowShowingAdapter()
        binding.nowShowingRV.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            adapter = showingAdapter
        }

        viewModel = ViewModelProvider(requireActivity()).get(NowShowingMovieViewModel::class.java)
        viewModel.getNowShowingMovie(1).observe(viewLifecycleOwner) {
            Log.e("NowShowingMovieViewModel", "onCreateView: hello9"+it.results.get(0).release_date.toString() )
            showingAdapter.submitList(it.results)
        }


        return binding.root
    }


}