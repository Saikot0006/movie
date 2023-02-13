package com.example.movieapp.view

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AbsListView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.adapter.NowShowingAdapter
import com.example.movieapp.adapter.PopularMovieAdapter
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.model.Genre
import com.example.movieapp.model.NowShowingMovieModel
import com.example.movieapp.model.PResult
import com.example.movieapp.model.Result
import com.example.movieapp.utils.ConstraintUtils
import com.example.movieapp.viewmodel.NowShowingMovieViewModel



class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel : NowShowingMovieViewModel
    private lateinit var showingAdapter : NowShowingAdapter
    private lateinit var popularMovieAdapter: PopularMovieAdapter

    var isloading = false
    var isPopularLoading = false
    var currentItems = 0
    var totalItems = 0
    var scrollItems = 0
    var nowShowingPage = 1
    var popularMoviePage = 1
    var popularCurrentItems = 0
    var popularTotalItems = 0
    var popularScrollItems = 0
    var list = ArrayList<Result>()
    var popularList = ArrayList<PResult>()
    var genreList = ArrayList<Genre>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        viewModel = ViewModelProvider(requireActivity()).get(NowShowingMovieViewModel::class.java)

        val mProgressDialog = ProgressDialog(requireContext())
        mProgressDialog.setCancelable(false)
        mProgressDialog.show()

        //binding.toolbar. = "FilmKu"

        showingAdapter = NowShowingAdapter{movie->
            findNavController().navigate(R.id.detailsFragment)
        }

       /* viewModel.getAllGenre().observe(viewLifecycleOwner){
            it.forEach{
                ConstraintUtils.movieDetails.gonerList.add(it)
            }

        }*/

        popularMovieAdapter = PopularMovieAdapter{movie,binding,value  ->
            if(value==2){
                Log.e("callback2", "onCreateView: insert" )
                for(i in 0..movie.genre_ids.size - 1){
                    var genre_ids = movie.genre_ids.get(i)
                    viewModel.getGenreDataByID(genre_ids).observe(viewLifecycleOwner){
                        for(i in 0..it.size - 1){
                             it.forEach{
                            if(genre_ids==it.id){
                                val dynamicTextView = TextView(requireContext())
                                dynamicTextView.text = it.name
                                dynamicTextView.setBackgroundResource(R.drawable.genres_border)
                                val params = LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.WRAP_CONTENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT)
                                params.setMargins(0, 10, 15, 5)

                                dynamicTextView.layoutParams = params
                                binding.popularGanerRVID.addView(dynamicTextView)
                            }

                        }
                        }

                    }
                }
            }else if(value==1){
                Log.e("callback", "onCreateView: insert" )
                findNavController().navigate(R.id.detailsFragment)
            }




        }

        fatchData(nowShowingPage)
        fatchPopularMovie(popularMoviePage)

        var nowShowinglayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        binding.nowShowingRV.apply {
            adapter = showingAdapter
            layoutManager = nowShowinglayoutManager
        }

        binding.nowShowingRV.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isloading = true
                }

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItems = nowShowinglayoutManager.childCount
                totalItems = nowShowinglayoutManager.itemCount
                scrollItems = nowShowinglayoutManager.findFirstVisibleItemPosition()

                if(isloading && (currentItems + scrollItems >= totalItems)){
                    isloading = false
                    nowShowingPage++
                    fatchData(nowShowingPage)
                    Toast.makeText(requireContext(), "End", Toast.LENGTH_SHORT).show()

                }


            }
        })

        binding.popularRV.apply {
            layoutManager =  LinearLayoutManager(requireContext())
            adapter = popularMovieAdapter
        }
        /*popularMovieAdapter.notifyDataSetChanged()*/

        binding.popularRV.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isPopularLoading = true
                }

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if(isPopularLoading && (popularCurrentItems + popularScrollItems >= popularTotalItems)){
                    isPopularLoading = false
                    popularMoviePage++
                    fatchPopularMovie(popularMoviePage)
                    Toast.makeText(requireContext(), "End", Toast.LENGTH_SHORT).show()

                }
            }
        })

        viewModel.getGenre()

        viewModel.NowShowingMovieResult.observe(viewLifecycleOwner) {

            for(i in 0..it.results.size - 1){
                 list.add(it.results.get(i))
            }
            showingAdapter.submitList(list)
            showingAdapter.notifyDataSetChanged()
        }

        viewModel.popularMovieResult.observe(viewLifecycleOwner){

            for(i in 0..it.results.size - 1){
                popularList.add(it.results.get(i))
            }
            popularMovieAdapter.submitList(popularList)
            popularMovieAdapter.notifyDataSetChanged()

            mProgressDialog.dismiss()

        }

        return binding.root
    }

    private fun fatchPopularMovie(popularMoviePage: Int) {
        Log.e("nowShowingPage", "popular: "+popularMoviePage )

        viewModel.getPopularMovie(popularMoviePage)
    }

    private fun fatchData(nowShowingPage : Int ) {
        viewModel.getNowShowingMovie(nowShowingPage)
    }




}