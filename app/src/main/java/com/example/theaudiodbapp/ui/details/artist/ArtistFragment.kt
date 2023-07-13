package com.example.theaudiodbapp.ui.details.artist

import android.annotation.SuppressLint
import com.example.theaudiodbapp.databinding.ArtistFragmentBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.theaudiodbapp.R
import com.example.theaudiodbapp.components.ResourceLink
import com.example.theaudiodbapp.components.recyclerview.HeaderType
import com.example.theaudiodbapp.components.recyclerview.RecyclerViewHeader
import com.example.theaudiodbapp.components.recyclerview.SearchAdapter
import com.example.theaudiodbapp.utils.Helpers
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ArtistFragment : Fragment() {

    private var _binding: ArtistFragmentBinding? = null
    private val binding get() = _binding!!
    private val args: ArtistFragmentArgs by navArgs()
    private val viewModel: ArtistViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ArtistFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navigationController = findNavController()

        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNavigationView?.visibility = View.GONE

        val btnBack = view.findViewById<ImageView>(R.id.btnBackArtistFragment)
        val ivHeader = view.findViewById<ImageView>(R.id.ivHeaderArtistFragment)
        val tvArtistName = view.findViewById<TextView>(R.id.tvArtistNameArtistFragment)
        val tvLocationAndType = view.findViewById<TextView>(R.id.tvLocationAndTypeArtistFragment)
        val tvDesc = view.findViewById<TextView>(R.id.tvDescArtistFragment)
        val rvDetails = view.findViewById<RecyclerView>(R.id.rvDetailsArtistFragment)

        val headerImg = args.artist.strArtistFanart2 ?: args.artist.strArtistFanart
        ?: args.artist.strArtistWideThumb ?: args.artist.strArtistThumb

        Glide.with(requireContext())
            .load(headerImg)
            .placeholder(R.drawable.placeholder_artiste)
            .into(ivHeader)
        tvArtistName.text = args.artist.strArtist
        tvLocationAndType.text = "${args.artist.strCountry ?: ""} - ${args.artist.strGenre ?: ""}"
        tvDesc.text = args.artist.strBiographyEN

        btnBack.setOnClickListener {
            navigationController.navigate(
                ArtistFragmentDirections.actionArtistFragmentToSearchFragment(
                    args.artist.strArtist
                )
            )
        }

        val searchAdapter = SearchAdapter(
            mutableListOf(),
            null,
            null,
            null
        )
        rvDetails.layoutManager = LinearLayoutManager(requireContext())
        rvDetails.adapter = searchAdapter
        addItemDecoration(rvDetails)

        val albumsHeader = if (args.albums.isNotEmpty()) {
            RecyclerViewHeader(requireContext(), HeaderType.ALBUMS)
        } else null

        searchAdapter.updateData(
            listOf(albumsHeader) +
                    args.albums.toList()
        )

        lifecycleScope.launch {
            viewModel.popularTitlesFlow.collect {
                val popularTitlesHeader = if (it.isNotEmpty()) {
                    RecyclerViewHeader(requireContext(), HeaderType.POPULAR_TITLES)
                } else null

                searchAdapter.updateData(
                    listOf(albumsHeader) +
                            args.albums.toList() +
                            listOf(popularTitlesHeader) +
                            it
                )
            }
        }
    }

    private fun addItemDecoration(rv: RecyclerView) {
        rv.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: android.graphics.Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                when(view) {
                    is ResourceLink -> {
                        outRect.top = Helpers.convertDpToPx(10).toInt()
                    }
                }
            }
        })
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        try {
            val animation = AnimationUtils.loadAnimation(requireContext(), nextAnim)
            if (enter) {
                animation?.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {}

                    override fun onAnimationEnd(animation: Animation?) {
                        viewModel.getPopularTitles(args.artist.strArtist)
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
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNavigationView?.visibility = View.VISIBLE
        _binding = null
    }

}