package com.example.theaudiodbapp.ui.details.artist

import android.annotation.SuppressLint
import com.example.theaudiodbapp.databinding.ArtistFragmentBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.theaudiodbapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

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

        Glide.with(requireContext())
            .load(args.artist.strArtistWideThumb ?: args.artist.strArtistFanart)
            .placeholder(R.drawable.placeholder_artiste)
            .into(ivHeader)
        tvArtistName.text = args.artist.strArtist
        tvLocationAndType.text = "${args.artist.strCountry ?: ""} - ${args.artist.strGenre ?: ""}"
        tvDesc.text = args.artist.strBiographyEN

        btnBack.setOnClickListener {
            navigationController.navigate(ArtistFragmentDirections.actionArtistFragmentToSearchFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNavigationView?.visibility = View.VISIBLE
        _binding = null
    }

}