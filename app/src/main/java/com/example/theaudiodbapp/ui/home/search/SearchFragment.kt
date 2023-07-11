package com.example.theaudiodbapp.ui.home.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.theaudiodbapp.R
import com.example.theaudiodbapp.components.CustomInput
import com.example.theaudiodbapp.databinding.SearchFragmentBinding
import com.example.theaudiodbapp.ui.home.search.components.SearchAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ciSearch = view.findViewById<CustomInput>(R.id.ciSearchSearchFragment)
        val rvArtists = view.findViewById<RecyclerView>(R.id.rvArtistsSearchFragment)
        val lytNoResult = view.findViewById<View>(R.id.lytNoResultSearchFragment)
        val lytSearch = view.findViewById<View>(R.id.lytSearchSearchFragment)

        val searchAdapter = SearchAdapter(mutableListOf())
        rvArtists.layoutManager = LinearLayoutManager(requireContext())
        rvArtists.adapter = searchAdapter

        ciSearch.onTextChange = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetArtists()
                if (!s.isNullOrEmpty()) {
                    viewModel.getArtists(s.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        lifecycleScope.launch {
            viewModel.artistsFlow.collect { artists ->
                artists.artists?.let { searchAdapter.updateData(it) }
                if (artists.artists.isNullOrEmpty()) {
                    if (ciSearch.et.text.isNotEmpty()) {
                        lytNoResult.visibility = View.VISIBLE
                        lytSearch.visibility = View.GONE
                    } else {
                        lytNoResult.visibility = View.GONE
                        lytSearch.visibility = View.VISIBLE
                    }
                    return@collect
                }
                lytNoResult.visibility = View.GONE
                lytSearch.visibility = View.GONE
            }
        }

        // How to update list :
        // searchAdapter.updateData(listOf())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}