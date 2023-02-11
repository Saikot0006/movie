package com.example.movieapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.databinding.FragmentBookmarksBinding
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.viewmodel.BookMarkViewModel


class BookmarksFragment : Fragment() {

    private lateinit var binding: FragmentBookmarksBinding
    private lateinit var viewModel : BookMarkViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBookmarksBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(BookMarkViewModel::class.java)
        binding.toolbar.notificationAppBarIV.visibility = View.INVISIBLE
        binding.toolbar.appBarTV.text = "Bookmarks"

        viewModel.getBookMarkMovie().observe(viewLifecycleOwner){
            for(i in 0..it.size-1){
                val strs = it.get(i).genreList.split(",").toTypedArray()

            }
        }

        return binding.root

    }

}