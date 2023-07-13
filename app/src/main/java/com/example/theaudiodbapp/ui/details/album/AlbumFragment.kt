package com.example.theaudiodbapp.ui.details.album

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.theaudiodbapp.R
import com.example.theaudiodbapp.components.recyclerview.HeaderType
import com.example.theaudiodbapp.components.recyclerview.RecyclerViewHeader
import com.example.theaudiodbapp.components.recyclerview.SearchAdapter
import com.example.theaudiodbapp.databinding.AlbumFragmentBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class AlbumFragment : Fragment() {

    private var _binding: AlbumFragmentBinding? = null
    private val binding get() = _binding!!
    private val args: AlbumFragmentArgs by navArgs()
    private val viewModel: AlbumViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AlbumFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNavigationView?.visibility = View.GONE

        val btnBack = binding.btnBackAlbumFragment
        val ivHeader = binding.ivHeaderAlbumFragment
        val tvArtistName = binding.tvArtistNameAlbumFragment
        val tvAlbumName = binding.tvAlbumNameAlbumFragment
        val tvTrackCount = binding.tvTrackCountAlbumFragment
        val ivThumbnail = binding.ivThumbAlbumFragment

        val tvRating = binding.tvRatingAlbumFragment
        val tvVoteCount = binding.tvVoteCountAlbumFragment
        val tvDesc = binding.tvDescAlbumFragment

        val rvTracks = binding.rvTracksAlbumFragment

        Glide.with(requireContext())
            .load(args.album.strAlbumThumb)
            .placeholder(R.drawable.placeholder_album)
            .into(ivHeader)
        Glide.with(requireContext())
            .load(args.album.strAlbumThumb)
            .placeholder(R.drawable.placeholder_album)
            .into(ivThumbnail)

        tvArtistName.text = args.album.strArtist
        tvAlbumName.text = args.album.strAlbum
        tvRating.text = args.album.intScore
        tvVoteCount.text = "${args.album.intScoreVotes ?: 0} votes"
        tvDesc.text = args.album.strDescriptionEN

        btnBack.setOnClickListener { navController.popBackStack() }

        viewModel.getAlbumTracks(args.album.idAlbum)
        rvTracks.layoutManager = LinearLayoutManager(requireContext())
        val searchAdapter = SearchAdapter(
            mutableListOf(),
            null,
            null,
            null
        )
        rvTracks.adapter = searchAdapter

        lifecycleScope.launch {
            viewModel.tracksFlow.collect {
                val tracksHeader = if (it.isNotEmpty()) {
                    HeaderType.TRACKS
                } else null
                searchAdapter.updateData(
                    listOf(
                        tracksHeader,
                        *it.toTypedArray()
                    )
                )
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}