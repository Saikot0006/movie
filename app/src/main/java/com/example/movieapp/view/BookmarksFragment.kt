package com.example.movieapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.adapter.BookMarkAdapter
import com.example.movieapp.custom_dialog.DeleteDialog
import com.example.movieapp.databinding.FragmentBookmarksBinding
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.utils.ConstraintUtils
import com.example.movieapp.viewmodel.BookMarkViewModel


class BookmarksFragment : Fragment() {

    private lateinit var binding: FragmentBookmarksBinding
    private lateinit var viewModel : BookMarkViewModel
    private lateinit var bookMarkAdapter: BookMarkAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBookmarksBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(BookMarkViewModel::class.java)
        binding.toolbar.notificationAppBarIV.visibility = View.INVISIBLE
        binding.toolbar.appBarTV.text = "Bookmarks"

        bookMarkAdapter = BookMarkAdapter {bookMark, value ->
            if(value==1){
                DeleteDialog(
                    bookMark.name,
                    "Do you want to delete this item?",
                    "Yes",
                    "No"
                ){
                    viewModel.deleteBookMarks(bookMark.bookmarkId)
                }.show(childFragmentManager,null)

            }else{
                ConstraintUtils.movieDetails.nowShowingMovieID = bookMark.bookmarkId.toInt()
                findNavController().navigate(R.id.detailsFragment)
            }
        }

        binding.bookmarksRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = bookMarkAdapter
        }
        bookMarkAdapter.notifyDataSetChanged()

        viewModel.getBookMarkMovie().observe(viewLifecycleOwner){
            bookMarkAdapter.submitList(it)
        }

        return binding.root

    }

}