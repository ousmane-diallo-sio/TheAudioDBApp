package com.example.theaudiodbapp.ui.details.track

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.theaudiodbapp.R
import com.example.theaudiodbapp.databinding.TrackFragmentBinding

class TrackFragment : Fragment() {

    private var _binding: TrackFragmentBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<TrackFragmentArgs>()
    private val viewModel: TrackViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TrackFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        val btnBack = binding.btnBackTrackFragment
        val ivHeader = binding.ivHeaderTrackFragment
        val ivThumb = binding.ivThumbTrackFragment
        val tvTrackName = binding.tvTrackNameTrackFragment
        val tvLyrics = binding.tvLyricsTrackFragment

        btnBack.setOnClickListener { navController.popBackStack() }

        Glide.with(requireContext())
            .load(args.track.strTrackThumb)
            .placeholder(R.drawable.placeholder_album)
            .into(ivHeader)

        Glide.with(requireContext())
            .load(args.track.strTrackThumb)
            .placeholder(R.drawable.placeholder_album)
            .into(ivThumb)

        tvTrackName.text = args.track.strTrack


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}