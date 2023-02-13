package com.example.movieapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.adapter.GenresAdapter
import com.example.movieapp.databinding.FragmentDetailsBinding
import com.example.movieapp.model.BookmarkModel
import com.example.movieapp.model.Genre
import com.example.movieapp.utils.ConstraintUtils
import com.example.movieapp.viewmodel.MovieDetailsViewModel



class DetailsFragment : Fragment() {
    private lateinit var viewModel: MovieDetailsViewModel
    private lateinit var binding : FragmentDetailsBinding
    private var genresList = ArrayList<Genre>()
    private lateinit var genresAdapter: GenresAdapter
    private lateinit var bookmarks : BookmarkModel
    private  var favorite : Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(MovieDetailsViewModel::class.java)


        genresAdapter = GenresAdapter()

        binding.movieDetailsRV.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            adapter = genresAdapter
        }



        genresAdapter.submitList(genresList)
        genresAdapter.notifyDataSetChanged()

        viewModel.getMovieDetails().observe(viewLifecycleOwner){
            Log.e("details", "onCreateView: "+it.poster_path )

            var genre : String? = null
            val builder = StringBuilder()

            var hour = it.runtime / 60
            var minute = it.runtime % 60
            var time = hour.toString()+" h "+minute.toString()+" min"

            genresList.clear()
            for(i in 0..it.genres.size - 1){
                genresList.add(Genre(it.genres.get(i).id,it.genres.get(i).name))
                genre = builder.append(genresList.get(i).name)
                    .append(",")
                    .toString()
            }

            //checkFavorite(it.id.toLong())
            checkBookMark()

            bookmarks = BookmarkModel(
                bookmarkId = it.id.toLong(),name = it.title, runTime = time, genreList = genre!!,
                ratting = it.vote_average.toString(), imageUrl = it.poster_path)


            binding.movieTimeTVID.text = hour.toString()+" h "+minute.toString()+" min"
            Glide.with(requireActivity())
                .load("https://image.tmdb.org/t/p/w500/"+it.backdrop_path)
                .into(binding.movieDetailsIV)
            binding.details = it

        }

        binding.detailsFavoriteID.setOnClickListener {
            if(favorite){
                viewModel.deleteBookMarks(ConstraintUtils.movieDetails.nowShowingMovieID.toLong())
                checkBookMark()
            }else{
                viewModel.insertBookMarks(bookmarks)
                checkBookMark()
            }
        }




        return binding.root
    }

    private fun checkBookMark() {
        viewModel.getMovieById(ConstraintUtils.movieDetails.nowShowingMovieID.toLong())
            .observe(viewLifecycleOwner){
                if(it!=null){
                    favorite = true
                    Glide.with(requireActivity())
                        .load(R.drawable.baseline_bookmark)
                        .into(binding.detailsFavoriteID)
                }else{
                    favorite = false
                    Glide.with(requireActivity())
                        .load(R.drawable.baseline_bookmark_border_24)
                        .into(binding.detailsFavoriteID)
                }
                Log.e("fads", "onCreateView: "+it.toString() )
            }
    }


}