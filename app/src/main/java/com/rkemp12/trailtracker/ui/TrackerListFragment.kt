package com.rkemp12.trailtracker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rkemp12.trailtracker.BaseApplication
import com.rkemp12.trailtracker.R
import com.rkemp12.trailtracker.databinding.FragmentTrailListBinding
import com.rkemp12.trailtracker.model.Tracker
import com.rkemp12.trailtracker.ui.adapter.TrailListAdapter
import com.rkemp12.trailtracker.ui.viewmodel.TrailViewModel
import com.rkemp12.trailtracker.ui.viewmodel.TrackerViewModelFactory
import kotlinx.coroutines.flow.observeOn

class TrackerListFragment : Fragment() {

    private val viewModel: TrailViewModel by activityViewModels {
        TrackerViewModelFactory(
            (activity?.application as BaseApplication).database.trackerDao()
        )
    }

    private var _binding: FragmentTrailListBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTrailListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TrailListAdapter { tracker ->
            val action = TrackerListFragmentDirections
                .actionTrackerListFragmentToTrackerDetailFragment(tracker.id)
            findNavController().navigate(action)
        }

        viewModel.fullTracker.observe(this.viewLifecycleOwner) {
            adapter.submitList(it)

        }
        binding.apply {
            recyclerView.adapter = adapter
            addTrailFab.setOnClickListener {
                findNavController().navigate(
                    R.id.action_TrackerListFragment_to_addTrackerFragment
                )
            }
        }
    }
}