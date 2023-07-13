package com.example.theaudiodbapp.ui.home.search

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.theaudiodbapp.R
import com.example.theaudiodbapp.components.recyclerview.HeaderType
import com.example.theaudiodbapp.components.recyclerview.RecyclerViewHeader
import com.example.theaudiodbapp.databinding.SearchFragmentBinding
import com.example.theaudiodbapp.components.recyclerview.SearchAdapter
import com.example.theaudiodbapp.ui.details.artist.ArtistFragmentArgs
import com.example.theaudiodbapp.utils.Helpers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Timer

class SearchFragment : Fragment() {

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModels()
    private val args: SearchFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ciSearch = binding.ciSearchSearchFragment
        val rvArtists = binding.rvArtistsSearchFragment
        val lytNoResultArtists = view.findViewById<View>(R.id.lytNoResultSearchFragment)
        val lytSearchArtistsPlaceholder = view.findViewById<View>(R.id.lytSearchSearchFragment)

        val navController = findNavController()

        val searchAdapter = SearchAdapter(
            mutableListOf(),
            { item ->
                navController.navigate(
                    SearchFragmentDirections.actionSearchFragmentToArtistFragment(
                        item,
                        viewModel.albumsFlow.value.album?.filter { it.strArtist == item.strArtist }
                            ?.toTypedArray()
                            ?: arrayOf()
                    )
                )
            },
            { navController.navigate(SearchFragmentDirections.actionSearchFragmentToAlbumFragment(it)) },
            null
        )
        rvArtists.layoutManager = LinearLayoutManager(requireContext())
        rvArtists.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: android.graphics.Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.top = Helpers.convertDpToPx(5).toInt()
                outRect.bottom = Helpers.convertDpToPx(5).toInt()
            }
        })
        rvArtists.adapter = searchAdapter

        ciSearch.onTextChange = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetArtists()
                viewModel.resetAlbums()
                if (!s.isNullOrEmpty()) {
                    GlobalScope.launch {
                        delay(100)
                        viewModel.getArtists(s.toString())
                        viewModel.getAlbums(s.toString())
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        lifecycleScope.launch {
            viewModel.loadingFlow.collect {
                if (it) {
                    binding.pbSearchFragment.visibility = View.VISIBLE
                    lytNoResultArtists.visibility = View.GONE
                    lytSearchArtistsPlaceholder.visibility = View.GONE
                } else {
                    binding.pbSearchFragment.visibility = View.GONE
                }
            }
        }

        lifecycleScope.launch {
            viewModel.artistsFlow.collect { artists ->
                artists.artists?.let { artists ->

                    val artistHeader = if (artists.isNotEmpty()) {
                        HeaderType.ARTISTS
                    } else null

                    val albumHeader = if ((viewModel.albumsFlow.value.album?.size ?: 0) > 0) {
                        HeaderType.ALBUMS
                    } else null

                    searchAdapter.updateData(
                        listOf(artistHeader) +
                                artists +
                                listOf(albumHeader) +
                                viewModel.albumsFlow.value.album.orEmpty()
                    )
                }
                if (artists.artists.isNullOrEmpty()) {
                    if (ciSearch.et.text.isNotEmpty()) {
                        lytNoResultArtists.visibility = View.VISIBLE
                        lytSearchArtistsPlaceholder.visibility = View.GONE
                    } else {
                        lytNoResultArtists.visibility = View.GONE
                        lytSearchArtistsPlaceholder.visibility = View.VISIBLE
                    }
                    return@collect
                }
                lytNoResultArtists.visibility = View.GONE
                lytSearchArtistsPlaceholder.visibility = View.GONE
            }
        }
        lifecycleScope.launch {
            viewModel.albumsFlow.collect { albums ->
                albums.album?.let { album ->
                    val artistHeader = if ((viewModel.artistsFlow.value.artists?.size ?: 0) > 0) {
                        HeaderType.ARTISTS
                    } else null

                    val albumHeader = if (album.isNotEmpty()) {
                        HeaderType.ALBUMS
                    } else null
                    searchAdapter.updateData(
                        listOf(artistHeader) +
                                viewModel.artistsFlow.value.artists.orEmpty() +
                                listOf(albumHeader) +
                                album
                    )
                }
            }
        }

        // TODO remove the following line
        binding.ciSearchSearchFragment.onTextChange?.onTextChanged("eminem", 0, 0, 0)
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        try {
            val animation = AnimationUtils.loadAnimation(requireContext(), nextAnim)
            if (enter) {
                animation?.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {}

                    override fun onAnimationEnd(animation: Animation?) {
                        args.search?.let {
                            binding.ciSearchSearchFragment.et.setText(it)
                            binding.ciSearchSearchFragment.onTextChange?.onTextChanged(it, 0, 0, 0)
                        }
                    }

                    override fun onAnimationRepeat(animation: Animation?) {}
                })
            }
            return animation
        } catch (e: Exception) {
            return super.onCreateAnimation(transit, enter, nextAnim)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}