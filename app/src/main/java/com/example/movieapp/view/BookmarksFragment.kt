package com.example.movieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movieapp.databinding.FragmentBookmarksBinding
import com.example.movieapp.databinding.FragmentHomeBinding


class BookmarksFragment : Fragment() {

    private lateinit var binding: FragmentBookmarksBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBookmarksBinding.inflate(inflater,container,false)
        binding.toolbar.notificationAppBarIV.visibility = View.INVISIBLE
        binding.toolbar.appBarTV.text = "Bookmarks"
        return binding.root

    }

}