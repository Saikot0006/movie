package com.example.movieapp.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.adapter.NowShowingAdapter
import com.example.movieapp.adapter.PopularMovieAdapter
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.utils.ConstraintUtils
import com.example.movieapp.viewmodel.NowShowingMovieViewModel



class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel : NowShowingMovieViewModel
    private lateinit var showingAdapter : NowShowingAdapter
    private lateinit var popularMovieAdapter: PopularMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        viewModel = ViewModelProvider(requireActivity()).get(NowShowingMovieViewModel::class.java)


        //binding.toolbar. = "FilmKu"

        showingAdapter = NowShowingAdapter{movie->
            findNavController().navigate(R.id.detailsFragment)
        }

        popularMovieAdapter = PopularMovieAdapter()

        binding.nowShowingRV.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            adapter = showingAdapter
        }

        binding.popularRV.apply {
            layoutManager =  LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            adapter = popularMovieAdapter
        }


        viewModel.getGenre()

        viewModel.getNowShowingMovie(1).observe(viewLifecycleOwner) {
            showingAdapter.submitList(it.results)
        }

        viewModel.getPopularMovie(1).observe(viewLifecycleOwner){
            popularMovieAdapter.submitList(it.results)
            Log.e("popularMovieResult", "getPopularMovie009: "+it.results.get(0).original_language )

        }

        return binding.root
    }


}