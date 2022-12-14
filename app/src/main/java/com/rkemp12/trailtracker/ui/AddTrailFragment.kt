package com.rkemp12.trailtracker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rkemp12.trailtracker.BaseApplication
import com.rkemp12.trailtracker.R
import com.rkemp12.trailtracker.databinding.FragmentAddTrailBinding
import com.rkemp12.trailtracker.model.Tracker
import com.rkemp12.trailtracker.ui.viewmodel.TrailViewModel
import com.rkemp12.trailtracker.ui.viewmodel.TrackerViewModelFactory

class AddTrailFragment : Fragment() {

    private val navigationArgs: AddTrailFragmentArgs by navArgs()

    private var _binding: FragmentAddTrailBinding? = null

    private lateinit var tracker: Tracker

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: TrailViewModel by activityViewModels {
        TrackerViewModelFactory(
            (activity?.application as BaseApplication).database.trackerDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddTrailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.id
        if (id > 0) {

            viewModel.singleTracker(id).observe(viewLifecycleOwner) {
                tracker = it
                bindTracker(it)
            }
            binding.deleteBtn.visibility = View.VISIBLE
            binding.deleteBtn.setOnClickListener {
                deleteTracker(tracker)
            }
        } else {
            binding.saveBtn.setOnClickListener {
                addTracker()
            }
        }
    }

    private fun deleteTracker(tracker: Tracker) {
        viewModel.deleteTracker(tracker)
        findNavController().navigate(
            R.id.action_addTrackerFragment_to_trackerListFragment
        )
    }

    private fun addTracker() {
        if (isValidEntry()) {
            viewModel.addTracker(
                binding.nameInput.text.toString(),
                binding.locationAddressInput.text.toString(),
                binding.notesInput.text.toString()
            )
            findNavController().navigate(
                R.id.action_addTrackerFragment_to_trackerListFragment
            )
        }
    }

    private fun updateTracker() {
        if (isValidEntry()) {
            viewModel.updateTracker(
                id = navigationArgs.id,
                name = binding.nameInput.text.toString(),
                address = binding.locationAddressInput.text.toString(),
                notes = binding.notesInput.text.toString()
            )
            findNavController().navigate(
                R.id.action_addTrackerFragment_to_trackerListFragment
            )
        }
    }

    private fun bindTracker(tracker: Tracker) {
        binding.apply{
            nameInput.setText(tracker.name, TextView.BufferType.SPANNABLE)
            locationAddressInput.setText(tracker.address, TextView.BufferType.SPANNABLE)
            notesInput.setText(tracker.notes, TextView.BufferType.SPANNABLE)
            saveBtn.setOnClickListener {
                updateTracker()
            }
        }

    }

    private fun isValidEntry() = viewModel.isValidEntry(
        binding.nameInput.text.toString(),
        binding.locationAddressInput.text.toString()
    )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}