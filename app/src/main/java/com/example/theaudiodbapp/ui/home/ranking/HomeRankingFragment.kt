package com.example.theaudiodbapp.ui.home.ranking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.theaudiodbapp.R
import com.example.theaudiodbapp.databinding.HomeRankingFragmentBinding
import com.google.android.material.tabs.TabLayout

class RankingFragment : Fragment() {

    private var _binding: HomeRankingFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeRankingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val fragmentManager = activity?.supportFragmentManager

        val homeTracksFragment = HomeTracksFragment()
        val homeAlbumsFragment = HomeAlbumsFragment()
        var tabLayoutChange = false

        val tabLayout = binding.tablayout
        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                fragmentManager?.beginTransaction()
                fragmentManager?.popBackStack()
                fragmentManager?.commit {
                    if (tabLayoutChange) {
                        replace(R.id.fragment_ranking_tabitem, homeTracksFragment)
                    } else {
                        replace(R.id.fragment_ranking_tabitem, homeAlbumsFragment)
                    }
                    tabLayoutChange = !tabLayoutChange
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}