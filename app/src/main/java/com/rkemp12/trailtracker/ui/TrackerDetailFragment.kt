package com.rkemp12.trailtracker.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rkemp12.trailtracker.BaseApplication
import com.rkemp12.trailtracker.R
import com.rkemp12.trailtracker.databinding.TrailItemBinding
import com.rkemp12.trailtracker.databinding.FragmentTrailDetailBinding
import com.rkemp12.trailtracker.model.Tracker
import com.rkemp12.trailtracker.ui.viewmodel.TrailViewModel
import com.rkemp12.trailtracker.ui.viewmodel.TrackerViewModelFactory

class TrackerDetailFragment : Fragment() {

    private val navigationArgs: TrackerDetailFragmentArgs by navArgs()

    private val viewModel: TrailViewModel by activityViewModels {
        TrackerViewModelFactory(
            (activity?.application as BaseApplication).database.trackerDao()
        )
    }
    private lateinit var tracker: Tracker

    private var _binding: FragmentTrailDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTrailDetailBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.id
        viewModel.singleTracker(id).observe(viewLifecycleOwner) {
            tracker = it
            bindTracker()

        }
    }

    private fun bindTracker() {
        binding.apply {
            name.text = tracker.name
            location.text = tracker.address
            notes.text = tracker.notes

            editTrackerFab.setOnClickListener {
                val action = TrackerDetailFragmentDirections
                    .actionTrackerDetailFragmentToAddTrackerFragment(tracker.id)
                findNavController().navigate(action)
            }

            location.setOnClickListener {
                launchMap()
            }
        }
    }

    private fun launchMap() {
        val address = tracker.address.let {
            it.replace(", ", ",")
            it.replace(". ", " ")
            it.replace(" ", "+")
        }
        val gmmIntentUri = Uri.parse("geo:0,0?q=$address")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }
}