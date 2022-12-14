package com.rkemp12.trailtracker.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rkemp12.trailtracker.databinding.TrailItemBinding
import com.rkemp12.trailtracker.model.Tracker


class TrailListAdapter(
    private val clickListener: (Tracker) -> Unit
    ) : ListAdapter<Tracker, TrailListAdapter.TrackerViewHolder>(DiffCallback) {

    class TrackerViewHolder(
        private var binding: TrailItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tracker: Tracker) {
            binding.trail = tracker
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Tracker>() {
        override fun areItemsTheSame(oldItem: Tracker, newItem: Tracker): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Tracker, newItem: Tracker): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TrackerViewHolder(
            TrailItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TrackerViewHolder, position: Int) {
        val tracker = getItem(position)
        holder.itemView.setOnClickListener{
            clickListener(tracker)
        }
        holder.bind(tracker)
    }
}